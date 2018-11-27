#!/bin/bash
for loop in 1 2 3 4 5
do
    echo "check eureka server ""loop:""$loop"
    curl eureka-server:8761
    if [ $? == 0 ]
    then
        break
    fi
    sleep 5s
done

for loop in 1 2 3 4 5
do
    echo "check config server ""loop:""$loop"
    curl http://config-server:8101/application-a/default
    if [ $? == 0 ]
    then
        break
    fi
    sleep 15s
done

java -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n -jar /opt/${JAR_FILE}