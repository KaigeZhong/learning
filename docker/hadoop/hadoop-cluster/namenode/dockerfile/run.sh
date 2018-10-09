#!/bin/bash

##################################配置#########################

#core-site.xml
sed -i -e '/fs\.defaultFS/!b;n;c\        <value>hdfs://'"$CLUSTER_NAME"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml
sed -i -e '/hadoop\.tmp\.dir/!b;n;c\        <value>'"$HADOOP_DATA_DIR/tmp"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml
sed -i -e '/ha\.zookeeper\.quorum/!b;n;c\        <value>'"$ZOOKEEPER_CONNS"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml

#hdfs-site.xml
sed -i -e '/dfs\.journalnode\.edits\.dir/!b;n;c\        <value>'$HADOOP_DATA_DIR/journal'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.namenode\.name\.dir/!b;n;c\        <value>file://'$HADOOP_DATA_DIR/namenode'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.datanode\.data\.dir/!b;n;c\        <value>file://'$HADOOP_DATA_DIR/datanode'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml

sed -i -e '/dfs\.namenode\.shared\.edits\.dir/!b;n;c\        <value>qjournal://'$JOURNAL_CONNS'/'$CLUSTER_NAME'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml

splitStrToArray(){
#shell函数定义的变量也是global的，其作用域从 函数被调用执行变量的地方 开始，到shell或结束或者显示删除为止。函数定义的变量可以是local的，其作用域局限于函数内部。但是函数的参数是local的。
    local str=$1
    local separator=$2
    local OLD_IFS=$IFS
    IFS=$separator
    local arr=($str)
    IFS=$OLD_IFS
    echo ${arr[*]}
}

clusters_arr=(`splitStrToArray "$CLUSTERS" ";"`)
for cluster_str in ${clusters_arr[*]} 
do 
    cluster_arr=(`splitStrToArray "$cluster_str" ":"`)
    cluster=${cluster_arr[0]} 
    ##字符拼接成cluster1,cluster2,...
    if [ ! $nameservices ]
    then
        nameservices=$cluster
    else
        nameservices="$nameservices"",""$cluster"
    fi
done

sed -i -e '/<\/configuration>/ i\
    <!--hdfs的nameservice为'$nameservices'-->\
    <property>\
        <name>dfs.nameservices</name>\
        <value>'$nameservices'</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/hdfs-site.xml

for cluster_info_str in ${clusters_arr[*]} 
do 
    cluster_info_arr=(`splitStrToArray "$cluster_info_str" ":"`)
    s=${cluster_info_arr[0]}
    namenode_info_str=${cluster_info_arr[1]}
    sed -i -e '/<\/configuration>/ i\
\
\
    <!--配置'$s'-->\
    <!--'$s'下有两个namenode,分别是nn1,nn2-->\
    <property>\
        <name>dfs.ha.namenodes.'$s'</name>\
        <value>'$namenode_info_str'</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/hdfs-site.xml


    namenode_arr=(`splitStrToArray "$namenode_info_str" ","`)
    for nn in ${namenode_arr[*]}
    do
        sed -i -e '/<\/configuration>/ i\
        <!--'$s' '${nn}'的RPC通信地址-->\
        <property>\
            <name>dfs.namenode.rpc-address.'$s'.'$nn'</name>\
            <value>'${nn}':9000</value>\
        </property>\
        <!--'$s' '${nn}'的http通信地址-->\
        <property>\
            <name>dfs.namenode.http-address.'$s'.'$nn'</name>\
            <value>'$nn':50070</value>\
        </property>'\
        $HADOOP_HOME/etc/hadoop/hdfs-site.xml
    done
    
    sed -i -e '/<\/configuration>/ i\
    <property>\
        <name>dfs.ha.automatic-failover.enabled.'$s'</name>\
        <value>true</value>\
    </property>\
    <property>\
        <name>dfs.client.failover.proxy.provider.'$s'</name>\
        <value>org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/hdfs-site.xml
done

######################################启动########################
##/bin/bash
######如果想只配置，然后再手动去启动hadoop，可以注释掉下面所有的指令，然后放开上面的/bin/bash

#第一步，格式化zkfc。在一个cluster中,只会在一台namenode上执行
if [ $IS_FIRST -a $IS_FIRST = true ] #shell中没有布尔，这里的true实际是字符串
then
    for loop in 1 2 3 4 5
    do
        echo "$CLUSTER_NAME"" is_first->"$IS_FIRST""":""zkfc formatZK  ""loop:""$loop"
        $HADOOP_HOME/bin/hdfs zkfc -formatZK
        if [ $? == 0 ]
        then
            break
        fi
    done
fi
#第二步，格式化namenode。在一个cluster中，在不同namenode上执行的命令不同
if [ $IS_FIRST -a $IS_FIRST = true ]
then
    for loop in 1 2 3 4 5
    do
        echo "$CLUSTER_NAME"" is_first->"$IS_FIRST""":""namenode format  ""loop:""$loop"
        $HADOOP_HOME/bin/hdfs namenode -format
        if [ $? == 0 ]
        then
            break
        fi
    done
else
    for loop in 1 2 3 4 5
    do
        echo "$CLUSTER_NAME"" is_first->"$IS_FIRST""":""namenode bootstrapStandby  ""loop:""$loop"
        $HADOOP_HOME/bin/hdfs namenode -bootstrapStandby
        if [ $? == 0 ]
        then
            break
        fi
    done
fi
##第三步, 启动namenode。后台启动采用hadoop-daemon.sh start namenode, 前台启动采用hdfs namenode.在这里我们采用后台启动
#$HADOOP_HOME/sbin/hadoop-daemon.sh start namenode
##第四步, 启动zkfc。在这里采用前台启动，应为docker需要一个前台进程。zkfc依赖namenode，会去调用namenode的rpc 9000端口。但其实第三步和第四步是可以颠倒顺序的，因为当zkfc找不到namenode时会每隔一秒去尝试。
#$HADOOP_HOME/bin/hdfs zkfc

#颠倒上面的第三步和第四步
#第三步
    for loop in 1 2 3 4 5
    do
        echo "$CLUSTER_NAME"":""start zkfc  ""loop:""$loop"
        $HADOOP_HOME/sbin/hadoop-daemon.sh start zkfc
        if [ $? == 0 ]
        then
            break
        fi
    done
#第四步
    for loop in 1 2 3 4 5
    do
        echo "$CLUSTER_NAME"":""start namenode  ""loop:""$loop"
        $HADOOP_HOME/bin/hdfs namenode
        if [ $? == 0 ]
        then
            break
        fi
    done
