#!/bin/bash
set -e

DEPLOY_USER=ubuntu
echo "DEPLOY USER = $DEPLOY_USER"

DEPLOY_DIR='/home/'$DEPLOY_USER'/app'
mkdir -p $DEPLOY_DIR
echo "DEPLOY_DIR = $DEPLOY_DIR"

BACKUP_DIR='/home/'$DEPLOY_USER'/backup-app'
mkdir -p $BACKUP_DIR
echo "BACKUP_DIR = $BACKUP_DIR"

PROJECT_NAME=hashtagmap-web

echo "> Build 파일 복사"

mv $DEPLOY_DIR/zip/*.jar $DEPLOY_DIR/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl $PROJECT_NAME | grep jar | awk '{print $1}')

echo "현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -2 $CURRENT_PID"
    kill -2 $CURRENT_PID

    echo "> back up to $BACKUP_DIR"
    OLD_JAR_NAME=$(ls -t $DEPLOY_DIR/*.jar | tail -n 1)
    mv $OLD_JAR_NAME $BACKUP_DIR/

    sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $DEPLOY_DIR/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar $JAR_NAME &
