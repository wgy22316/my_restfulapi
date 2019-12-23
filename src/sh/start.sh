#!/bin/bash

if [ -z "$1" ];then
    printf "usage: $0 {local|dev|test|uat|aliyun_prod}\n"
    exit 1
fi

# it's very important !!!
source /etc/profile

cd ../lib

main_class='cn.mwee.order.service.MFServiceProvider'

case $1 in
local|dev|test|uat)
    java -server -Xms512m -Xmx512m -Djava.ext.dirs=. ${main_class}
    ;;
prod|aliyun_prod)
    java -server -Xms2560m -Xmx2560m -Djava.ext.dirs=. ${main_class}
    ;;
esac