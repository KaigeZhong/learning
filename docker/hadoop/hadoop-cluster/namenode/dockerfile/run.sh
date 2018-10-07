#!/bin/bash

#core-site.xml
CLUSTER_NAME=cluter1
ZOOKEEPER_CONNS=zk1,zk2,zk3
HADOOP_TEMP_DIR=$HADOOP_HOME/tmp
sed -i -e '/fs\.defaultFS/!b;n;c\        <value>'"$CLUSTER_NAME"'</value>' config/core-site.xml
sed -i -e '/hadoop\.tmp\.dir/!b;n;c\        <value>'"$HADOOP_TEMP_DIR"'</value>' config/core-site.xml
sed -i -e '/ha\.zookeeper\.quorum/!b;n;c\        <value>'"$ZOOKEEPER_CONNS"'</value>' config/core-site.xml

CLUSTERS="cluster1,cluster2"
OLD_IFS="$IFS" 
IFS="," 
arr=($CLUSTERS)
IFS="$OLD_IFS" 
for s in ${arr[@]} 
do 
    echo "$s" 
done
