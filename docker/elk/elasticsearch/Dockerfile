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
ARG EL_VERSION=6.5.3
ENV EL_HOME=/opt/elasticsearch
RUN apt-get install -y wget
RUN wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-${EL_VERSION}.tar.gz 
RUN tar -xzf elasticsearch-${EL_VERSION}.tar.gz -C /opt \
 && mv /opt/elasticsearch-${EL_VERSION} $EL_HOME 
RUN chmod -R 777 $EL_HOME
ADD ./run.sh /opt/run.sh
RUN chmod 777 /opt/run.sh
RUN useradd elsearch
#由于ElasticSearch可以接收用户输入的脚本并且执行，为了系统安全考虑, 不能以root权限来执行elasticsearch
USER elsearch

CMD ["bin/bash", "-c", "/opt/run.sh"]
