#!/bin/bash -x

if [ -z "$1" ]; then
    printf "usage: $0 {local|dev|test|uat|aliyun_prod}\n"
    exit 1
fi

base_dir='/data/application'

case $1 in
 dev|test|uat)
    #scp -r ../../src/sh/deploy_order_mf_service.sh deploy@10.0.21.124:${base_dir}
    #scp -r *.tar deploy@10.0.21.124:${base_dir}
    ;;
 prod|aliyun_prod)
    #scp -r ../../src/sh/deploy_order_mf_service.sh deploy@10.1.30.35:${base_dir}
    #scp -r *.tar deploy@10.1.30.35:${base_dir}

    #scp -r ../../src/sh/deploy_order_mf_service.sh deploy@10.1.30.36:${base_dir}
    #scp -r *.tar deploy@10.1.30.36:${base_dir}
    ;;
esac