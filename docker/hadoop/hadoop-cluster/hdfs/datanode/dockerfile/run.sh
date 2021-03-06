#!/bin/bash

##################################配置#########################

sed -i -e '/hadoop\.tmp\.dir/!b;n;c\        <value>'"$HADOOP_DATA_DIR/tmp"'</value>' $HADOOP_HOME/etc/hadoop/core-site.xml

#hdfs-site.xml
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

#获取nameservice的list字符串
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
    <!--hdfs的nameservices为'$nameservices_str'-->\
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
    
done

######################################启动########################
##/bin/bash

######如果想只配置,然后再手动去启动hadoop，可以注释掉下面所有的指令，然后放开上面的/bin/bash
$HADOOP_HOME/bin/hdfs datanode
