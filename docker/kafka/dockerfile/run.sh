#!/bin/bash

##################################配置#########################

#server.properties
sed -i -e '/^zookeeper.connect=/c\zookeeper.connect='$ZOOKEEPER_CONNS'' $KAFKA_HOME/config/server.properties
sed -i -e '/^broker.id=/c\broker.id='$BROKER_ID'' $KAFKA_HOME/config/server.properties
###获取当前容器的ip地址，kafka broker会将自己的ip注册到zookeeper。producer，consumer配置时可以只配置一个broker地址，其余的会从zookeeper中获取
host=`hostname`
IP=`sed -n -e '/'$host'/p' /etc/hosts | awk '{print $1}'`
if [ $IP ]
then
    sed -i '$a\advertised.listeners=PLAINTEXT://'$IP':9092' $KAFKA_HOME/config/server.properties
fi
#/bin/bash
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties
