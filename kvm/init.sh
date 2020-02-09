ip link add name kvm_br0 type bridge
echo "1"
ip link set dev kvm_br0 up
echo "2"
ip addr del 192.168.1.5/24 dev enp4s0
echo "3"
ip addr add 192.168.1.5/24 dev kvm_br0
echo "4"
ip link set dev enp4s0 master kvm_br0
echo "5"
ip route add default via 192.168.1.1
echo "6"
