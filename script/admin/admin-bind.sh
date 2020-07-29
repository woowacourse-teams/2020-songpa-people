#!/bin/bash

set -e

WEB_JAR=./hashtagmap-admin/build/libs/*.jar
WEB_DEPLOY_SCRIPT=./script/deploy.sh
WEB_BASE=./script/hashtagmap-admin
WEB_TARGET=./script/hashtagmap-admin/items

echo "> WEB DIR 생성"
mkdir -p $WEB_BASE
mkdir -p $WEB_TARGET

echo "> WEB 배포 스크립트 이동"
cp $WEB_DEPLOY_SCRIPT $WEB_TARGET/

echo "> WEB jar 이동"
cp $WEB_JAR $WEB_TARGET/

echo "> WEB 배포 스크립트, jar 압축"
cd $WEB_TARGET
tar -cvf hashtagmap-admin.tar *

echo "> 압축 이후 .jar, deploy.sh 삭제"
rm *.jar
rm deploy.sh

echo "> tar 파일 이동"
mv hashtagmap-admin.tar $WEB_BASE/

echo "> PROJECT_ROOT/script/hashtagmap-admin/hashtagmap-admin.tar 생성"
