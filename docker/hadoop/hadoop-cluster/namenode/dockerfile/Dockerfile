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

ARG HADOOP_VERSION=2.9.1
ENV HADOOP_HOME=/opt/hadoop
RUN wget -q -O - http://mirrors.shu.edu.cn/apache/hadoop/common/stable/hadoop-${HADOOP_VERSION}.tar.gz | tar -xzf - -C /opt \
 && mv /opt/hadoop-${HADOOP_VERSION} $HADOOP_HOME

#在hadoop-env.sh会读取JAVA_HOME环境变量
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

COPY config/ $HADOOP_HOME/etc/hadoop/
#在run.sh中配置core-site.xml和hdfs-site.xml中会使用到
ENV ZOOKEEPER_CONNS=zk1:2181,zk2:2181,zk3:2181
ENV JOURNAL_CONNS=hadoop_hdfs_journalnode1:8485;hadoop_hdfs_journalnode2:8485;hadoop_hdfs_journalnode3:8485
ENV HADOOP_DATA_DIR=$HADOOP_HOME/data
ENV CLUSTER_NAME=cluter1
ENV CLUSTERS="cluster1:namenode1,namenode2;cluster2:namenode3,namenode4"

ADD ./run.sh /opt/run.sh
RUN chmod 777 /opt/run.sh
CMD ["bin/sh", "-c", "/opt/run.sh"]
