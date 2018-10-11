#!/bin/bash

##################################配置#########################

#yarn-site.xml
sed -i -e '/yarn\.resourcemanager\.ha\.id/!b;n;c\        <value>'$RM'</value>' $HADOOP_HOME/etc/hadoop/yarn-site.xml

sed -i -e '/yarn\.resourcemanager\.cluster-id/!b;n;c\        <value>'$CLUSTER_ID'</value>' $HADOOP_HOME/etc/hadoop/yarn-site.xml
sed -i -e '/yarn\.resourcemanager\.zk-address/!b;n;c\        <value>'$ZOOKEEPER_CONNS'</value>' $HADOOP_HOME/etc/hadoop/yarn-site.xml
sed -i -e '/yarn\.nodemanager\.local-dirs/!b;n;c\        <value>'$HADOOP_DATA_DIR'/yarn/data</value>' $HADOOP_HOME/etc/hadoop/yarn-site.xml
sed -i -e '/yarn\.nodemanager\.log-dirs/!b;n;c\        <value>'$HADOOP_DATA_DIR'/yarn/log</value>' $HADOOP_HOME/etc/hadoop/yarn-site.xml

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


sed -i -e '/<\/configuration>/ i\
    <!--yarn的resourcemanager的ha集群为'$RMS'-->\
    <property>\
        <name>yarn.resourcemanager.ha.rm-ids</name>\
        <value>'$RMS'</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/yarn-site.xml

rms_arr=(`splitStrToArray "$RMS" ","`)

for res_manager in ${rms_arr[*]} 
do 

    sed -i -e '/<\/configuration>/ i\
    <!--配置'$res_manager' -->\
    <property>\
        <name>yarn.resourcemanager.hostname.'$res_manager'</name>\
        <value>'$res_manager'</value>\
    </property>\
    <property>\
        <name>yarn.resourcemanager.address.'$res_manager'</name>\
        <value>'$res_manager':8132</value>\
    </property>\
    <property>\
        <name>yarn.resourcemanager.scheduler.address.'$res_manager'</name>\
        <value>'$res_manager':8130</value>\
    </property>\
    <property>\
        <name>yarn.resourcemanager.webapp.address.'$res_manager'</name>\
        <value>'$res_manager':8088</value>\
    </property>\
    <property>\
        <name>yarn.resourcemanager.resource-tracker.address.'$res_manager'</name>\
        <value>'$res_manager':8131</value>\
    </property>\
    <property>\
        <name>yarn.resourcemanager.admin.address.'$res_manager'</name>\
        <value>'$res_manager':8033</value>\
    </property>\
    <property>\
        <name>yarn.resourcemanager.ha.admin.address.'$res_manager'</name>\
        <value>'$res_manager':23142</value>\
    </property>'\
    $HADOOP_HOME/etc/hadoop/yarn-site.xml
    
done

######################################启动########################
##/bin/bash


#######如果想只配置,然后再手动去启动hadoop，可以注释掉下面所有的指令，然后放开上面的/bin/bash
$HADOOP_HOME/bin/yarn resourcemanager
