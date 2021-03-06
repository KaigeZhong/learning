#!/bin/bash

#### configservice use the same db with adminservice. since adminservice had do db configuration, configservice do not need do it again.

while true
do
    echo "check eureka server ""loop:""$loop"
    echo 'select 0 from dual' | mysql -u$DB_USER -p$DB_PW --host=$DB_HOST --port=$DB_PORT
    if [ $? == 0 ]
    then
        break
    fi
    sleep 5s
done

############配置###########

sed -i -e '/^spring.datasource.url/c\spring.datasource.url=jdbc:mysql://'$DB_HOST':'$DB_PORT'/ApolloConfigDB?characterEncoding=utf8' $APOLLO_HOME/config/application-github.properties
sed -i -e '/^spring.datasource.username/c\spring.datasource.username='$DB_USER'' $APOLLO_HOME/config/application-github.properties
sed -i -e '/^spring.datasource.password/c\spring.datasource.password='$DB_PW'' $APOLLO_HOME/config/application-github.properties

sed -i '$d' $APOLLO_HOME/scripts/startup.sh
echo "tail -f /dev/null" >> $APOLLO_HOME/scripts/startup.sh
#/bin/bash


while true
do
    echo "check eureka server ""loop:""$loop"
    curl $EUREKA_TEST_ADDR
    if [ $? == 0 ]
    then
        break
    fi
    sleep 5s
done

$APOLLO_HOME/scripts/startup.sh
