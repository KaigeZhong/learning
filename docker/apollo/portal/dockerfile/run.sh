#!/bin/bash

#### create db table
for loop in 1 2 3 4 5
do
    echo "check eureka server ""loop:""$loop"
    echo 'select 0 from dual' | mysql -u$DB_USER -p$DB_PW --host=$DB_HOST --port=$DB_PORT
    if [ $? == 0 ]
    then
        break
    fi
    sleep 15s
done

cat /opt/sql/ApolloPortalDB.sql | mysql -u$DB_USER -p$DB_PW --host=$DB_HOST --port=$DB_PORT
echo 'UPDATE ApolloPortalDB.ServerConfig SET value = "'$APOLLO_PORTAL_ENVS'" WHERE `key` = "apollo.portal.envs";' | mysql -u$DB_USER -p$DB_PW --host=$DB_HOST --port=$DB_PORT


############配置###########

sed -i -e '/^spring.datasource.url/c\spring.datasource.url=jdbc:mysql://'$DB_HOST':'$DB_PORT'/ApolloPortalDB?characterEncoding=utf8' $APOLLO_HOME/config/application-github.properties
sed -i -e '/^spring.datasource.username/c\spring.datasource.username='$DB_USER'' $APOLLO_HOME/config/application-github.properties
sed -i -e '/^spring.datasource.password/c\spring.datasource.password='$DB_PW'' $APOLLO_HOME/config/application-github.properties


sed -i -e '/^dev.meta/c\dev.meta='$META_SERVICE_DEV'' $APOLLO_HOME/config/apollo-env.properties
sed -i -e '/^fat.meta/c\fat.meta='$META_SERVICE_FAT'' $APOLLO_HOME/config/apollo-env.properties

sed -i '$d' $APOLLO_HOME/scripts/startup.sh
echo "tail -f /dev/null" >> $APOLLO_HOME/scripts/startup.sh


#/bin/bash

for loop in 1 2 3 4 5
do
    echo "check meta service ""loop:""$loop"
    curl $META_SERVICE_DEV
    if [ $? == 0 ]
    then
        curl $META_SERVICE_FAT
        if [ $? == 0 ]
        then
            break
        fi
    fi
    sleep 15s
done
$APOLLO_HOME/scripts/startup.sh
