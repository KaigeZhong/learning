FROM centos:6.9
RUN yum install -y readline-devel; \
yum install -y openssl openssl-devel;\
yum install -y zlib zlib-devel;\
yum install -y pcre-devel;\
yum install -y gcc gcc-c++;\
yum install -y unzip zip;\
yum install -y libuuid-devel;\
yum install -y epel-release;
RUN yum -y install openssh-server;\
yum -y install openssh-clients;\
service sshd start;
COPY tool/ /root/dsp_issue/
RUN cd /root/dsp_issue;\
chmod 777 install.sh;\
./install.sh;
COPY src /data/dsp/src
COPY c_src /data/dsp/c_src
COPY sh /data/dsp/sh
RUN cd /data/dsp/c_src;\
chmod 777 make.sh;\
./make.sh;\
cp libxcomm.so /data/dsp/src/libxcomm.so;\
cp /data/dsp/src/nginx.conf /usr/local/nginx/conf/
CMD ["/usr/local/nginx/sbin/nginx", "-g", "daemon off;"]
