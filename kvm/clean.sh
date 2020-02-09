if [ ! -z "`ip link show | grep kvm_br0`" ]; then 
    echo "1"
    ip link set dev kvm_br0 down
    echo "2"
    ip link set dev enp4s0 nomaster
    echo "3"
    ip link delete dev kvm_br0
    echo "4"
    ip addr add 192.168.1.5/24 dev enp4s0
    echo "5"
    ip route add default via 192.168.1.1
    echo "6"
fi

