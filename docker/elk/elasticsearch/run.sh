#!/bin/sh

EL_CONFIG="$EL_HOME/config/elasticsearch.yml"
#elasticsearch 远程访问需要设置下面两个参数
echo "network: \n  host: 0.0.0.0" >> $EL_CONFIG
echo "transport: \n  host: localhost" >> $EL_CONFIG
#/bin/bash
$EL_HOME/bin/elasticsearch
