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

RUN apt-get install -y wget openssh-server

ARG KAFKA_VERSION=2.12-2.0.0
ENV KAFKA_HOME=/opt/kafka
RUN wget -q -O - http://mirrors.hust.edu.cn/apache/kafka/2.0.0/kafka_${KAFKA_VERSION}.tgz | tar -xzf - -C /opt \
 && mv /opt/kafka_${KAFKA_VERSION} $KAFKA_HOME

##在run.sh中配置server.properties中会使用到
ENV ZOOKEEPER_CONNS=zk1:2181,zk2:2181,zk3:2181 \
BROKER_ID=0

COPY ./run.sh /opt/run.sh
RUN chmod 777 /opt/run.sh
CMD ["bin/bash", "-c", "/opt/run.sh"]
