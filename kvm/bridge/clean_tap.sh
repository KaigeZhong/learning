ip link set dev ${1} nomaster
ip link set dev ${1} down
ip tuntap del dev ${1} mod tap
