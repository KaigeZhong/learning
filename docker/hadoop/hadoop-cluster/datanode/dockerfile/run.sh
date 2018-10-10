#!/bin/bash

##################################配置#########################

#core-site.xml
sed -i -e '/fs\.defaultFS/!b;n;c\        <value>hdfs://'"$NAME_SERVICE"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml
sed -i -e '/hadoop\.tmp\.dir/!b;n;c\        <value>'"$HADOOP_DATA_DIR/tmp"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml
sed -i -e '/ha\.zookeeper\.quorum/!b;n;c\        <value>'"$ZOOKEEPER_CONNS"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml

#hdfs-site.xml
sed -i -e '/dfs\.namenode\.shared\.edits\.dir/!b;n;c\        <value>qjournal://'$JOURNAL_CONNS'/'$NAME_SERVICE'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml

sed -i -e '/dfs\.journalnode\.edits\.dir/!b;n;c\        <value>'$HADOOP_DATA_DIR/journal'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.namenode\.name\.dir/!b;n;c\        <value>file://'$HADOOP_DATA_DIR/namenode'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.datanode\.data\.dir/!b;n;c\        <value>file://'$HADOOP_DATA_DIR/datanode'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml

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

name_services_arr=(`splitStrToArray "$NAME_SERVICES" ";"`)
for name_service_str in ${name_services_arr[*]} 
do 
    name_service_arr=(`splitStrToArray "$name_service_str" ":"`)
    name_service=${name_service_arr[0]} 
    ##字符拼接成name_service1,name_service2,...
    if [ ! $nameservices_str ]
    then
        nameservices_str=$name_service
    else
        nameservices_str="$nameservices_str"",""$name_service"
    fi
done

## hdfs federation nameservices集群之间是需要相互知道的
sed -i -e '/<\/configuration>/ i\
    <!--hdfs的nameservices为'$nameservices'-->\
    <property>\
        <name>dfs.nameservices</name>\
        <value>'$nameservices_str'</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/hdfs-site.xml

for name_service_info_str in ${name_services_arr[*]} 
do 
    name_service_info_arr=(`splitStrToArray "$name_service_info_str" ":"`)
    nameservice=${name_service_info_arr[0]}
    namenode_info_str=${name_service_info_arr[1]}
    sed -i -e '/<\/configuration>/ i\
\
\
    <!--配置'$nameservice'-->\
    <!--'$nameservice'下有两个namenode,分别是nn1,nn2-->\
    <property>\
        <name>dfs.ha.namenodes.'$nameservice'</name>\
        <value>'$namenode_info_str'</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/hdfs-site.xml


    namenode_arr=(`splitStrToArray "$namenode_info_str" ","`)
    for nn in ${namenode_arr[*]}
    do
        sed -i -e '/<\/configuration>/ i\
        <!--'$nameservice' '${nn}'的RPC通信地址-->\
        <property>\
            <name>dfs.namenode.rpc-address.'$nameservice'.'$nn'</name>\
            <value>'${nn}':9000</value>\
        </property>\
        <!--'$nameservice' '${nn}'的http通信地址-->\
        <property>\
            <name>dfs.namenode.http-address.'$nameservice'.'$nn'</name>\
            <value>'$nn':50070</value>\
        </property>'\
        $HADOOP_HOME/etc/hadoop/hdfs-site.xml
    done
    
    sed -i -e '/<\/configuration>/ i\
    <property>\
        <name>dfs.ha.automatic-failover.enabled.'$nameservice'</name>\
        <value>true</value>\
    </property>\
    <property>\
        <name>dfs.client.failover.proxy.provider.'$nameservice'</name>\
        <value>org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/hdfs-site.xml
done

######################################启动########################
/bin/bash


######如果想只配置,然后再手动去启动hadoop，可以注释掉下面所有的指令，然后放开上面的/bin/bash


####一下代码只适用于当采用docker-compose自动启动

##第一步，格式化zkfc。在一个cluster中,只会在一台namenode上执行
#if [ $NAMENODE_FIRST_TAG -a $NAMENODE_FIRST_TAG = true ] #shell中没有布尔，这里的true实际是字符串
#then
#    # 由于当采用
#    for loop in 1 2 3 4 5
#    do
#        echo "$CLUSTER_NAME"" is_first->"$NAMENODE_FIRST_TAG""":""zkfc formatZK  ""loop:""$loop"
#        $HADOOP_HOME/bin/hdfs zkfc -formatZK
#        if [ $? == 0 ]
#        then
#            break
#        fi
#        sleep 1s
#    done
#fi
##第二步，格式化namenode。在一个cluster中，在不同namenode上执行的命令不同
#if [ $NAMENODE_FIRST_TAG -a $NAMENODE_FIRST_TAG = true ]
#then
#    for loop in 1 2 3 4 5
#    do
#        echo "$CLUSTER_NAME"" is_first->"$NAMENODE_FIRST_TAG""":""namenode format  ""loop:""$loop"
#        #HDFS Federation下所有的nameservices的cluster id必须是相同的
#        $HADOOP_HOME/bin/hdfs namenode -format -clusterId $CLUSTER_ID
#        if [ $? == 0 ]
#        then
#            break
#        fi
#        sleep 1s
#    done
#else
#    for loop in 1 2 3 4 5
#    do
#        echo "$CLUSTER_NAME"" is_first->"$NAMENODE_FIRST_TAG""":""namenode bootstrapStandby  ""loop:""$loop"
#        $HADOOP_HOME/bin/hdfs namenode -bootstrapStandby
#        if [ $? == 0 ]
#        then
#            break
#        fi
#        sleep 1s
#    done
#fi
###第三步, 启动namenode。后台启动采用hadoop-daemon.sh start namenode, 前台启动采用hdfs namenode.在这里我们采用后台启动
#    for loop in 1 2 3 4 5
#    do
#        echo "$CLUSTER_NAME"":""start namenode  ""loop:""$loop"
#        $HADOOP_HOME/sbin/hadoop-daemon.sh start namenode
#        if [ $? == 0 ]
#        then
#            break
#        fi
#        sleep 1s
#    done
###第四步, 启动zkfc。在这里采用前台启动，应为docker需要一个前台进程。zkfc依赖namenode，会去调用namenode的rpc 9000端口。但其实第三步和第四步是可以颠倒顺序的，因为当zkfc找不到namenode时会每隔一秒去尝试。
#    for loop in 1 2 3 4 5
#    do
#        echo "$CLUSTER_NAME"":""start zkfc  ""loop:""$loop"
#        $HADOOP_HOME/bin/hdfs zkfc
#        if [ $? == 0 ]
#        then
#            break
#        fi
#        sleep 1s
#    done
#
#
#
##颠倒上面的第三步和第四步
##第三步
##    for loop in 1 2 3 4 5
##    do
##        echo "$CLUSTER_NAME"":""start zkfc  ""loop:""$loop"
##        $HADOOP_HOME/sbin/hadoop-daemon.sh start zkfc
##        if [ $? == 0 ]
##        then
##            break
##        fi
##        sleep 1s
##    done
##第四步
##    for loop in 1 2 3 4 5
##    do
##        echo "$CLUSTER_NAME"":""start namenode  ""loop:""$loop"
##        $HADOOP_HOME/bin/hdfs namenode
##        if [ $? == 0 ]
##        then
##            break
##        fi
##        sleep 1s
##    done
