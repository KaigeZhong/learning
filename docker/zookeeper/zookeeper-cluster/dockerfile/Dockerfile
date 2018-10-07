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
ARG ZK_VERSION=3.4.12
RUN apt-get install -y wget\
 && wget -q -O - http://mirrors.hust.edu.cn/apache/zookeeper/stable/zookeeper-${ZK_VERSION}.tar.gz | tar -xzf - -C /opt \
 && mv /opt/zookeeper-${ZK_VERSION} /opt/zookeeper \
 && cp /opt/zookeeper/conf/zoo_sample.cfg /opt/zookeeper/conf/zoo.cfg
ENV DATA_DIR=/tmp/zooke
RUN mkdir -p $DATA_DIR \
 && sed -i -e "/^dataDir/c\dataDir=$DATA_DIR" /opt/zookeeper/conf/zoo.cfg

ADD ./run.sh /opt/run.sh
RUN chmod 777 /opt/run.sh
CMD ["/opt/run.sh"]
