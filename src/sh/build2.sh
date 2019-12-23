#!/bin/bash

if [ -z "$1" ]; then
   printf "usage: $0 {dev|test|uat|aliyun_stg|aliyun_prod}\n"
   exit 1
fi

function check() {
   if [ $1 != 0 ];then
      echo "exec fail"
      exit 1
   fi
}

# it's very important !!!
source /etc/profile

proj_name="restful-api"

echo $"[$1] environment building ..."

gradle clean bootRepackage -x test 2>&1
check $?
cd build/libs
pwd
cp ../../src/sh/start.sh ./start.sh
cp ../../src/sh/service.sh ./service.sh
rm -rf *sources.jar  *.original
mv *-SNAPSHOT.jar ${proj_name}.jar
mkdir -p ${proj_name}
mv *.jar *.sh ${proj_name}
tar -cvzf ${proj_name}.tar.gz ./*
mv -f ${proj_name}.tar.gz ../../
echo "MW_SUCCESS"