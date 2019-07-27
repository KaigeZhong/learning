#!/bin/sh

KI_CONFIG="$KI_HOME/config/kibana.yml"
echo "elasticsearch: \n  url: http://elasticsearch:9200" >> $KI_CONFIG
echo "server: \n  host: 0.0.0.0" >> $KI_CONFIG
#/bin/bash
$KI_HOME/bin/kibana
