#!/bin/bash

# it's very important !!!
source /etc/profile

if [ -z "$1" ] || [ -z "$2" ];then
    printf "usage: $0 {start|stop|restart|status} {local|dev|test|uat|aliyun_prod}\n"
    exit 1
fi

if [ -z "$3" ] ;then
    cd /data/application/order_mf_service/bin
else
    cd $3/order_mf_service/bin
fi

cmd_pwd=`pwd`
pwd_dir=${cmd_pwd}
pid_file=${pwd_dir}/../running.pid
app_desc="MFService"
main_class='cn.mwee.order.service.MFServiceProvider'


#启动
sub_start() {
    printf  "%s starting..." ${app_desc}
    nohup ./start.sh $1 >/dev/null 2>&1 &sleep 2
    sleep 5
    if test $(jps -l | grep ${main_class} | wc -l) -ne 0
    then
        echo "MW_SUCCESS"
    else
        echo "fail !"
    fi
}

#停止
sub_stop() {
    printf  "%s stopping..." ${app_desc}
    count=0
    while [ 1 ];do
        if test $(jps -l | grep ${main_class} | wc -l) -ne 0
        then
            ps -ef|grep java|grep ${main_class} | awk '{print $2}' |xargs kill
            let count+=1
            if [ $count -gt 10 ] ; then
               ps -ef|grep java|grep ${main_class} | awk '{print $2}' |xargs kill -9
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
    if test $(jps -l | grep ${main_class} | wc -l) -ne 0
    then
      printf "%s is running.\n" ${app_desc}
    else
      printf "%s is NOT running.\n" ${app_desc}
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