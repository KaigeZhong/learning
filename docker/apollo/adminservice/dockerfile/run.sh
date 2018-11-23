#!/bin/bash

#### create db table
cat /opt/sql/ApolloConfigDB.sql | mysql -u$DB_USER -p$DB_PW --host=$DB_HOST --port=$DB_PORT
echo 'UPDATE ApolloConfigDB.ServerConfig SET value = "'$EUREKA_ADDRS'" WHERE `key` = "eureka.service.url";' | mysql -uroot -p111111 --host=$DB_HOST --port=$DB_PORT


############配置###########

sed -i -e '/^spring.datasource.url/c\spring.datasource.url=jdbc:mysql://'$DB_HOST':'$DB_PORT'/ApolloConfigDB?characterEncoding=utf8' $APOLLO_HOME/config/application-github.properties
sed -i -e '/^spring.datasource.username/c\spring.datasource.username='$DB_USER'' $APOLLO_HOME/config/application-github.properties
sed -i -e '/^spring.datasource.password/c\spring.datasource.password='$DB_PW'' $APOLLO_HOME/config/application-github.properties

sed -i '$d' $APOLLO_HOME/scripts/startup.sh
echo "tail -f /dev/null" >> $APOLLO_HOME/scripts/startup.sh
/bin/bash
#$APOLLO_HOME/scripts/startup.sh
