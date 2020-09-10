#!/bin/bash

set -e

echo "> 전송확인"
echo `ls -al`

DEPLOY_USER=ubuntu
echo "DEPLOY USER = $DEPLOY_USER"

ZIP_DIR='/home/'$DEPLOY_USER'/app/zip'
sudo mkdir -p $ZIP_DIR
echo "ZIP_DIR = $ZIP_DIR"

JAR_DIR='/home/'$DEPLOY_USER'/app'
sudo mkdir -p $JAR_DIR
echo "JAR_DIR = $JAR_DIR"


JAR_COUNT=$(ls -al $JAR_DIR | grep jar | wc -l)
if [ "$JAR_COUNT" -eq 1 ]
then
  sudo mv ~/app/*.jar ~/backup-app/
  echo "> 이전 버전 jar파일 백업"
else
  echo "> 백업할 파일이 존재하지 않습니다"
fi

sudo mv '/home/'$DEPLOY_USER'/hashtagmap-web.tar' $JAR_DIR/
echo "tar moved to $JAR_DIR"

sudo tar -xvf $JAR_DIR/hashtagmap-web.tar -C $JAR_DIR/
echo "> done untar"

sudo mv $JAR_DIR/hashtagmap-web.tar $ZIP_DIR/
echo "tar moved to $ZIP_DIR"

echo "> run nonstop-deploy.sh"
sudo chmod +x $JAR_DIR/nonstop-deploy.sh
sudo sh $JAR_DIR/nonstop-deploy.sh
