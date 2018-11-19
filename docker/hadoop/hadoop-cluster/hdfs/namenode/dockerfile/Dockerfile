FROM ubuntu:16.04
# 有时apt由于GFW的原因，我们获取到的文件和官方源上的文件大小不一致，导致md5值也不一样，所以会出现Hash Sum mismatch。我们可以通过设置代理的方式解决这个问题,这里我们使用阿里的apt代理。文件/etc/apt/sources.list是一个普通可编辑的文本文件，保存了ubuntu软件更新的源服务器的地址。和sources.list功能一样的是/etc/apt/sources.list.d/*.list(*代表一个文件名，只能由字母、数字、下划线、英文句号组成)。sources.list.d目录下的*.list文件为在单独文件中写入源的地址提供了一种方式，通常用来安装第三方的软件
RUN sed -i -e '1,$c\deb http://mirrors.aliyun.com/ubuntu/ xenial main restricted\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial universe\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates universe\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-security universe\n\
deb http://mirrors.aliyun.com/ubuntu/ xenial-security multiverse\n\' /etc/apt/sources.list

RUN apt-get update && apt-get install -y openjdk-8-jdk

RUN apt-get install -y wget openssh-server

ARG HADOOP_VERSION=2.9.1
ENV HADOOP_HOME=/opt/hadoop
RUN wget -q -O - http://mirrors.shu.edu.cn/apache/hadoop/common/stable/hadoop-${HADOOP_VERSION}.tar.gz | tar -xzf - -C /opt \
 && mv /opt/hadoop-${HADOOP_VERSION} $HADOOP_HOME

#在hadoop-env.sh会读取JAVA_HOME环境变量
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
#在run.sh中配置core-site.xml和hdfs-site.xml中会使用到
ENV ZOOKEEPER_CONNS=zk1:2181,zk2:2181,zk3:2181 \
JOURNAL_CONNS=journalnode1:8485;journalnode2:8485;journalnode3:8485 \
HADOOP_DATA_DIR=$HADOOP_HOME/data \
NAME_SERVICE=nameservice1 \
NAME_SERVICES="nameservice1:namenode1,namenode2;nameservice2:namenode3,namenode4" \
#HDFS Federation下所有的nameservices的cluster id必须是相同的
CLUSTER_ID=uuid_jhagb4854hhjba5b1

COPY config/ $HADOOP_HOME/etc/hadoop/
COPY ./run.sh /opt/run.sh
RUN chmod 777 /opt/run.sh
CMD ["bin/bash", "-c", "/opt/run.sh"]
