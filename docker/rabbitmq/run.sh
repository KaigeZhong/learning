#!/bin/sh

RABBITMQ_CFG="/etc/rabbitmq/rabbitmq.conf"
#让管理员用户guest可以远程访问
echo "loopback_users = none" > $RABBITMQ_CFG
#rabbitmq-server start #启动服务 rabbitmq-server status #查看服务状态  rabbitmq-server stop # 停止服务 
rabbitmq-server start &
#启动web管理界面,web port: 15672
rabbitmq-plugins enable rabbitmq_management
#保证容器不关闭
tail -f /dev/null
########## 文档
#rabbitmq-server start # 启动服务
#rabbitmq-server status  # 查看服务状态
#rabbitmq-server stop   # 停止服务
#rabbitmqctl list_users # 查看当前所有用户
#rabbitmqctl list_user_permissions guest # 查看默认guest用户的权限
#rabbitmqctl delete_user guest # 由于RabbitMQ默认的账号用户名和密码都是guest。为了安全起见, 先删掉默认用户
#rabbitmqctl add_user username password # 添加新用户
#rabbitmqctl set_user_tags username administrator # 设置用户tag
#rabbitmqctl set_permissions -p / username ".*" ".*" ".*" # 赋予用户默认vhost的全部操作权限
#rabbitmqctl list_user_permissions username # 查看用户的权限

