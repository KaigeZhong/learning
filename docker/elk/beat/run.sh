#!/bin/sh

BEAT_CONFIG="$BEAT_HOME/filebeat.yml"
#filebeat主要配置输入和输入，输入默认配置了路径- /var/log/*.log, 但需要enable. 输出默认是elasticsearch，这里我们改成logstash
sed -i -e '/^  enabled: false/c\  enabled: true' $BEAT_CONFIG
#设置nginx日志文件
sed -i -e '/^    - \/var\/log\//c\    - /var/log/nginx/access.log' $BEAT_CONFIG
#输出默认为elasticsearch，这里我们关闭elasticsearch设置
sed -i -e '/^output.elasticsearch:/c\#output.elasticsearch: ' $BEAT_CONFIG
sed -i -e '/^  hosts: /c\#  hosts: ["localhost:9200"]' $BEAT_CONFIG
#输出默认为elasticsearch，这里我们设置成logstash
sed -i -e '/^#output.logstash/c\output.logstash: ' $BEAT_CONFIG
sed -i -e '/^  #hosts:/c\  hosts: ["logstash:5044"]' $BEAT_CONFIG
#运行nginx
nginx
#/bin/bash
$BEAT_HOME/filebeat -c $BEAT_CONFIG -e
