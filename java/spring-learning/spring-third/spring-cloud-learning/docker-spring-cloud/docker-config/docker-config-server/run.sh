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

java -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n -jar /opt/${JAR_FILE}