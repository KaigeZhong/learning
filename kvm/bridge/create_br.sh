BR=kvm_br0
ETH=enp4s0
IP=192.168.1.5/24
GATEWAY=192.168.1.1
ip link add name ${BR} type bridge
echo "1"
ip link set dev ${BR} up
echo "2"
ip link set dev ${ETH} master ${BR}
echo "3"
ip addr del ${IP} dev ${ETH}
echo "4"
ip addr add ${IP} dev ${BR}
echo "5"
ip route add default via ${GATEWAY}
echo "6"
