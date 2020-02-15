BR=kvm_br0
ETH=enp4s0
IP=192.168.1.5/24
GATEWAY=192.168.1.1
echo "1"
ip link set dev ${BR} down
echo "2"
ip link set dev ${ETH} nomaster
echo "3"
ip link delete dev ${BR}
echo "4"
ip addr add ${IP} dev ${ETH}
echo "5"
ip route add default via ${GATEWAY}
echo "6"

