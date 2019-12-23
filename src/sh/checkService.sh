#!/bin/bash -x
while [ 1 ];do
    now=$(date +%Y%m%d-%H%M%S)
    printf $now"=>http://dc.cloud-manage.test.cn/config/info.json\n"
    curl http://dc.cloud-manage.test.cn/config/info.json
    printf "\n"
    sleep 1
done