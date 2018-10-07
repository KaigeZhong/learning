#!/bin/sh

ZOO_CFG="/opt/zookeeper/conf/zoo.cfg"

# server ID
echo "server id (myid): $MYID"
sed -i -e '1c\${SERVER_ID}' 
echo "$MYID" > $DATA_DIR/myid

# Add additional ZooKeeper servers into the zoo.cfg file
echo "${SERVER_1}" >> ${ZOO_CFG}
echo "${SERVER_2}" >> ${ZOO_CFG}
echo "${SERVER_3}" >> ${ZOO_CFG}

# Start Zookeeper
/opt/zookeeper/bin/zkServer.sh start-foreground
