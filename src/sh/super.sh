#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ]; then
    printf "usage: $0 {restart|stop|status|restart-one|stop-one|deploy-other|rollback-one|rollback-other} {local|dev|test|uat|aliyun_prod} {0|1|2|3|4}\n"
    exit 1
fi

source /etc/profile

base_dir='/data/application/'
bin_dir=${base_dir}order_mf_service/bin/

case $1 in
restart|stop|status)
    case $2 in
    dev|test|uat)
        ssh -t -p 22 deploy@10.0.21.124 ${bin_dir}'service.sh '$1 $2
    ;;
    prod|aliyun_prod)
        ssh -t -p 22 deploy@10.1.30.35 ${bin_dir}'service.sh '$1 $2
        ssh -t -p 22 deploy@10.1.30.36 ${bin_dir}'service.sh '$1 $2
    ;;
    esac
    ;;
restart-one)
    case $2 in
    dev|test|uat)
        ssh -t -p 22 deploy@10.0.21.124 ${bin_dir}'service.sh restart ' $2
    ;;
    prod|aliyun_prod)
        ssh -t -p 22 deploy@10.1.30.35 ${bin_dir}'service.sh restart ' $2
    ;;
    esac
    ;;
stop-one)
    case $2 in
    dev|test|uat)
        ssh -t -p 22 deploy@10.0.21.124 ${bin_dir}'service.sh stop '$2
        ;;
    prod|aliyun_prod)
        ssh -t -p 22 deploy@10.1.30.35 ${bin_dir}'service.sh stop '$2
    ;;
    esac
    ;;
deploy-other)
    case $2 in
    dev|test|uat)
    ;;
    prod|aliyun_prod)
        ssh -t -p 22 deploy@10.1.30.36 ${base_dir}'deploy_order_mf_service.sh '$2 0
    ;;
    esac
    ;;
rollback-one)
    if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]; then
        printf "usage: $0 {rollback-one|rollback-other} {local|dev|test|uat|aliyun_prod} {0|1|2|3|4}\n"
        exit 1
    fi
    case $2 in
    dev|test|uat)
        ssh -t -p 22 deploy@10.0.21.124 ${bin_dir}'rollback.sh' $3 $2
    ;;
    prod|aliyun_prod)
        ssh -t -p 22 deploy@10.1.30.35 ${bin_dir}'rollback.sh' $3 $2
    ;;
    esac
    ;;
rollback-other)
    if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ]; then
        printf "usage: $0 {rollback-one|rollback-other} {local|dev|test|uat|aliyun_prod} {0|1|2|3|4}\n"
        exit 1
    fi
    case $2 in
    dev|test|uat)
        ssh -t -p 22 deploy@10.0.21.124 ${bin_dir}'rollback.sh' $3 $2
    ;;
    prod|aliyun_prod)
        ssh -t -p 22 deploy@10.1.30.36 ${bin_dir}'rollback.sh' $3 $2
    ;;
    esac
    ;;
esac