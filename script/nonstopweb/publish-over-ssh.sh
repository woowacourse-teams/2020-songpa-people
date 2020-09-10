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

sudo mv '/home/'$DEPLOY_USER'/hashtagmap-web.tar' $NONE_STOP_DIR/
echo "tar moved to $NONE_STOP_DIR"

sudo tar -xvf $NONE_STOP_DIR/hashtagmap-web.tar -C $NONE_STOP_DIR/
echo "> done untar"

sudo mv $NONE_STOP_DIR/hashtagmap-web.tar $ZIP_DIR/
echo "tar moved to $ZIP_DIR"

echo "> run deploy.sh"
sudo chmod +x $ZIP_DIR/deploy.sh
sudo sh $ZIP_DIR/deploy.sh
