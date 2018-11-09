FROM ubuntu:16.04
# 有时apt由于GFW的原因，我们获取到的文件和官方源上的文件大小不一致，导致md5值也不一样，所以会出现Hash Sum mismatch。我们可以通过设置代理的方式解决这个问题,这里我们使用阿里的apt代理。
RUN sed -i -e '1,$c\deb http://mirrors.aliyun.com/ubuntu/ xenial main restricted universe multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted universe multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted universe multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-proposed main restricted universe multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse\n\
deb-src http://mirrors.aliyun.com/ubuntu/ xenial main restricted universe multiverse\n\
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted universe multiverse\n\
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted universe multiverse\n\
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-proposed main restricted universe multiverse\n\
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse' /etc/apt/sources.list

RUN apt-get update && apt-get install -y openjdk-8-jdk
ARG TOMCAT_VERSION=8.5.35
ENV TOMCAT_HOME=/opt/tomcat
RUN apt-get install -y wget
RUN wget -q -O - http://mirrors.hust.edu.cn/apache/tomcat/tomcat-8/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz | tar -xzf - -C /opt
RUN mv /opt/apache-tomcat-${TOMCAT_VERSION} $TOMCAT_HOME

CMD ["bin/bash", "-c", "${TOMCAT_HOME}/bin/catalina.sh run"]
