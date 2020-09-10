#!/bin/bash

set -e

echo "> 전송확인"
echo `ls -al`

DEPLOY_USER=ubuntu
echo "DEPLOY USER = $DEPLOY_USER"

ZIP_DIR='/home/'$DEPLOY_USER'/app/zip'
sudo mkdir -p $ZIP_DIR
echo "ZIP_DIR = $ZIP_DIR"

NONE_STOP_DIR='/home/'$DEPLOY_USER'/app/nonstop'
sudo mkdir -p $NONE_STOP_DIR
echo "NONE_STOP_DIR = $NONE_STOP_DIR"


JAR_COUNT=$(ls -al $NONE_STOP_DIR | grep jar | wc -l)
if [ "$JAR_COUNT" -eq 1 ]
then
  sudo mv ~/app/nonstop/*.jar ~/backup-app/
  echo "> 이전 버전 jar파일 백업"
else
  echo "> 백업할 파일이 존재하지 않습니다"
fi

sudo mv '/home/'$DEPLOY_USER'/hashtagmap-web.tar' $NONE_STOP_DIR/
echo "tar moved to $NONE_STOP_DIR"

sudo tar -xvf $NONE_STOP_DIR/hashtagmap-web.tar -C $NONE_STOP_DIR/
echo "> done untar"

sudo mv $NONE_STOP_DIR/hashtagmap-web.tar $ZIP_DIR/
echo "tar moved to $ZIP_DIR"

echo "> run nonstop-deploy.sh"
sudo chmod +x $NONE_STOP_DIR/nonstop-deploy.sh
sudo sh $NONE_STOP_DIR/nonstop-deploy.sh
