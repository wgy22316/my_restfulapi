#!/bin/bash -x

source /etc/profile

if [ -z "$1" ]; then
    printf "usage: $0 {local|dev|test|uat|aliyun_prod} {auto}\n"
    exit 1
fi

function check() {
  if [ $1 != 0 ];then
    echo "exec fail"
    exit 1
  fi
}

tar_file='restful-api.tar'
base_dir='/data/application/'
service_dir="restful-api"

case $1 in
local|dev|test|uat|aliyun_prod)
    echo $"[$1] environment building ..."

    gradle pack clean -Denv=$1 -x test 2>$1
    check $?
    cd build/install/order_mf_service
    mv lib/* ../
    cd ../
    rm -rf order_mf_service
    mkdir -p lib
    mkdir -p bin
    pwd
    mv resources lib
    mv *.jar lib
    cp ../../src/sh/start*.sh ./bin
    cp ../../src/sh/service*.sh ./bin
    cp ../../src/sh/rollback*.sh ./bin
    mkdir -p ${service_dir}
    mv ./lib ${service_dir}
    mv ./bin ${service_dir}
    tar -czf ${tar_file} ./*
    ;;
esac
echo "MW_SUCCESS"


#temp
#cp *.tar ${base_dir}
#cp ../../src/sh/deploy_order_mf_service.sh ${base_dir}

if [ ! -z "$2" ] && [ "$2" = "auto" ]; then
    echo "auto deploy start ..."
else
    case $1 in
    local)
        rm -rf ${base_dir}/*.tar
        cp *.tar ${base_dir}
        cp ../../src/sh/deploy_order_mf_service.sh ${base_dir}
        cd ${base_dir}
        ./deploy_order_mf_service.sh $1
        ;;
    dev|test|uat)
        echo $"copy ${tar_file} to [$1] environment ..."
        ../../src/sh/copy.sh $1
        #ssh -t -p 22 deploy@10.0.21.124 ${base_dir}'deploy_order_mf_service.sh' $1 0
        ;;
    prod|aliyun_prod)
        echo $"copy ${tar_file} to [$1] environment ..."
        ../../src/sh/copy.sh $1
        ;;
    esac
fi