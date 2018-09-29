FROM centos:6.9
RUN yum -y install MySQL-python;\
yum -y install screen;\
yum -y install openssh-server;\
yum -y install openssh-clients;\
service sshd start;
COPY ./ /control
RUN chmod 777 /control/ctl_start.sh
CMD /control/ctl_start.sh
