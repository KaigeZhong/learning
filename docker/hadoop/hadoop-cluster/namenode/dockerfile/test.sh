#!/bin/bash
if [ $1 -a $1 = true ] #aa
then
    echo "value:""$1"
else
    echo "false"
fi
sleep 1s
echo "1s"
sleep 1s
echo "1s"

for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
    break
done

