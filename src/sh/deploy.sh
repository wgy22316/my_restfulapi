#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ] ; then
    printf "usage: $0 {local|dev|test|uat|aliyun_prod} {rollbackFile} {bakId}\n"
    printf "\t- rollbackFile: the backup file that you want to rollback to \n"
    printf "\t- bakId: the id that you want to backup after deploy done  \n"
    exit 1
fi

# it's very important !!!
source /etc/profile

base_dir="/data/application"

if [ $2 = "0" ] ; then # no need rollback
    if [ ! -f "${base_dir}/order_mf_service.tar" ] ; then
        printf "${base_dir}/order_mf_service.tar not exist!\n"
        exit 1
    fi
else # rollback
    if [ ! -f "${base_dir}/$2" ] ; then
        printf "${base_dir}/$2 not exist!\n"
        exit 1
    fi
fi

if [ -d "${base_dir}/order_mf_service/bin" ] ; then
    cd ${base_dir}/order_mf_service/bin
    ./service.sh stop $1
    sleep 1
fi

cd ${base_dir}
rm -rf order_mf_service/*
if [ $2 = "0" ] ; then
   tar -zxvf order_mf_service.tar # no need rollback
else
   tar -zxvf $2 # rollback
fi
cd ${base_dir}/order_mf_service/bin
./service.sh restart $1

#backup current tar
cd ${base_dir}

if [ -f "${base_dir}/order_mf_service.tar" ] ; then
    if [ -z "$3" ] ; then
        DATE=$(date +%y%m%d%H%M%S)
        mv order_mf_service.tar "order_mf_service_"${DATE}".tar"
    else
        mv order_mf_service.tar "order_mf_service_"$3".tar"
    fi
fi

deleteCount=0
for dir in $(ls -ltx *.tar)
do
   if [ -f $dir ]; then
    test=`echo $dir | grep '^order_mf_service_[0-9]\{1,12\}.tar$'`
    if [ ! -z "$test" ] ; then
        let deleteCount+=1
        [ $deleteCount -gt 5 ] && rm -rf $dir
    fi
   fi
done

${base_dir}/order_mf_service/bin/service.sh status $1

exit 0