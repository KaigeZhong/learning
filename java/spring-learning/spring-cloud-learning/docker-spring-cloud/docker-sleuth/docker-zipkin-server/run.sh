#!/bin/bash

java -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n -jar ${ZIPKIN_HOME}/zipkin-server-exec.jar