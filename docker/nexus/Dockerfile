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
ARG NEXUS_VERSION=3.14.0-04
ENV NEXUS_HOME=/opt/nexus
RUN apt-get install -y wget
RUN wget -q -O - https://sonatype-download.global.ssl.fastly.net/repository/repositoryManager/3/nexus-${NEXUS_VERSION}-unix.tar.gz | tar -xzf - -C /opt
RUN mv /opt/nexus-${NEXUS_VERSION} $NEXUS_HOME
#ENV DATA_DIR=$ZK_HOME/data
#RUN mkdir -p $DATA_DIR \
# && sed -i -e "/^dataDir/c\dataDir=$DATA_DIR" $ZK_HOME/conf/zoo.cfg
#
#ADD ./run.sh /opt/run.sh
#RUN chmod 777 /opt/run.sh

CMD ["bin/bash", "-c", "${NEXUS_HOME}/bin/nexus run"]
