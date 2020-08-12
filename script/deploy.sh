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

PROJECT_NAME=hashtagmap

echo "> Build 파일 이동"

mv $DEPLOY_DIR/zip/*.jar $DEPLOY_DIR/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl $PROJECT_NAME | grep java | awk '{print $1}')

echo "현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    sudo kill -15 $CURRENT_PID

    STILL_ALIVE=$(pgrep -fl $PROJECT_NAME | grep java | awk '{print $1}')
    if [ -n "$STILL_ALIVE" ]; then
      echo "> 애플리케이션이 종료되지 않아 종료를 재시도합니다."
      echo "> kill -9 $CURRENT_PID"
      sudo kill -9 $CURRENT_PID
    fi
    STILL_ALIVE=$(pgrep -fl $PROJECT_NAME | grep java | awk '{print $1}')
    if [ -n "$STILL_ALIVE" ]; then
      echo "> 애플리케이션이 종료되지 않아 배포를 중단합니다."
      exit 1
    fi

    JAR_COUNT=$(ls -al | grep jar | wc -l)
    if [ "$JAR_COUNT" -eq 1 -o "$JAR_COUNT" -eq 0 ]; then
      echo "> 백업할 JAR가 존재하지 않습니다."
    else
      echo "> back up to $BACKUP_DIR"
      OLD_JAR_NAME=$(ls -t $DEPLOY_DIR/*.jar | tail -n 1)
      mv $OLD_JAR_NAME $BACKUP_DIR/
      sleep 5
    fi
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $DEPLOY_DIR/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar -DSpring.profiles.active=prod $JAR_NAME 1> /dev/null 2>&1 &

exit 0
