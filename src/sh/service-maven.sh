#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ];then
    printf "usage: $0 {start|stop|restart|status} {dev|test|uat|aliyun_prod}\n"
    exit 1
fi

# it's very important !!!
source /etc/profile

proj_name="restful-api"

if [ -z "$3" ] ;then
    cd /data/application/${proj_name}/
else
    cd $3/${proj_name}/
fi

#启动
sub_start() {
    printf  "%s starting..." ${proj_name}
    nohup sh start.sh $1 ${proj_name} >/dev/null 2>&1 &sleep 2
    sleep 10
    if test $(jps -l | grep ${proj_name}.jar | wc -l) -ne 0
    then
        echo "MW_SUCCESS"
    else
        echo "fail !"
    fi
}

#停止
sub_stop() {
    printf  "%s stopping..." ${proj_name}
    count=0
    while [ 1 ];do
        if test $(jps -l | grep ${proj_name}.jar | wc -l) -ne 0
        then
            ps -ef | grep java | grep ${proj_name}.jar  | awk '{print $2}' | xargs kill
            let count+=1
            if [ $count -gt 10 ] ; then
               ps -ef | grep java | grep ${proj_name}.jar  | awk '{print $2}' | xargs kill -9
            fi
        else
            echo "MW_SUCCESS"
            break
        fi
        sleep 1
    done
}

#查看状态
sub_status(){
    if test $(jps -l | grep ${proj_name}.jar | wc -l) -ne 0
    then
      printf "%s is running.\n" ${proj_name}
    else
      printf "%s is NOT running.\n" ${proj_name}
    fi
}

case $1 in
 start)
    sub_start $2
    ;;
 stop)
    sub_stop
    ;;
 restart)
    sub_stop
    sub_start $2
    sub_status
    ;;
  status)
    sub_status
    ;;
esac