#!/bin/bash

#core-site.xml
sed -i -e '/fs\.defaultFS/!b;n;c\        <value>'"$CLUSTER_NAME"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml
sed -i -e '/hadoop\.tmp\.dir/!b;n;c\        <value>'"$HADOOP_DATA_DIR/tmp"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml
sed -i -e '/ha\.zookeeper\.quorum/!b;n;c\        <value>'"$ZOOKEEPER_CONNS"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml

#hdfs-site.xml

sed -i -e '/dfs\.namenode\.shared\.edits\.dir/!b;n;c\        <value>qjournal://'$JOURNAL_CONNS'/'$CLUSTER_NAME'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.journalnode\.edits\.dir/!b;n;c\        <value>'$HADOOP_DATA_DIR/journal'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.namenode\.name\.dir/!b;n;c\        <value>'$HADOOP_DATA_DIR/namenode'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sed -i -e '/dfs\.datanode\.data\.dir/!b;n;c\        <value>'$HADOOP_DATA_DIR/datanode'</value>' $HADOOP_HOME/etc/hadoop/hdfs-site.xml


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
done
/bin/bash
