#!/bin/sh

LS_CONFIG="$LS_HOME/config/logstash-sample.conf"
#logstash 主要配置输入和输出，输入默认配置beat，端口号为5044.输出默认为elasticsearch,这里配置elasticsearch的地址
sed -i -e '/^    hosts/c\    hosts => ["http://elasticsearch:9200"]' $LS_CONFIG
#监控api外网访问
echo "http: \n  host: 0.0.0.0" >> $LS_HOME/config/logstash.yml
#/bin/bash
$LS_HOME/bin/logstash -f $LS_CONFIG
