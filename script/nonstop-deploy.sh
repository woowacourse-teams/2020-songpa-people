#!/bin/bash

set -e

BUILD_PATH=$(ls ~/app/nonstop/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

echo "> 현재 구동중인 Set 확인"
CURRENT_PROFILE=$(curl -s http://localhost/profile)
echo "> $CURRENT_PROFILE"

sleep 1
# 쉬고 있는 set 찾기: set1이 사용중이면 set2가 쉬고 있고, 반대면 set1이 쉬고 있음
if [ $CURRENT_PROFILE == set1 ]
then
  IDLE_PROFILE="set2"
  IDLE_PORT=8082
elif [ $CURRENT_PROFILE == set2 ]
then
  IDLE_PROFILE="set1"
  IDLE_PORT=8081
else
  echo "> 일치하는 Profile이 없습니다. Profile: $CURRENT_PROFILE"
  echo "> set1을 할당합니다. IDLE_PROFILE: set1"
  IDLE_PROFILE="set1"
  IDLE_PORT=8081
fi
echo "> IDLE_PORT 확인 : $IDLE_PORT"

# 전환해야 할 포트로 구동중인 애플리케이션이 있는지 확인
echo "> $IDLE_PROFILE 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(ps -ef | grep java | grep $IDLE_PROFILE | awk '{print $2}')
echo "> pid : $IDLE_PID"

if [ -z $IDLE_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  sudo kill -15 $IDLE_PID
  echo "> 20초 뒤에 배포 시작"
  sleep 20
fi

cd ~/app/nonstop
echo "> $IDLE_PROFILE 배포"
nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_NAME &

#sleep 10;

# switch.sh
#echo "> 스위칭"
#echo "> 현재 구동중인 Port 확인"
#echo "> 현재 배포중인 profile : $CURRENT_PROFILE"
#
#echo "> 전환할 Port: $IDLE_PORT"
#echo "> Port 전환"
#echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" |sudo tee /etc/nginx/conf.d/service-url.inc
#
#sleep 3
#
#echo "> Nginx restart"
#sudo service nginx restart

echo "> $IDLE_PROFILE 10초 후 Health check 시작"
echo "> curl -s http://localhost:$IDLE_PORT/actuator/health "
sleep 10

for retry_count in {1..10}
do
  response=$(curl -s http://localhost:$IDLE_PORT/actuator/health)
  echo "> $response"
  up_count=$(echo $response | grep 'UP' | wc -l)

  if [ $up_count -ge 1 ]
  then # $up_count >= 1 ("미소" 문자열이 있는지 검증)
      echo "> Health check 성공"
      break
  else
      echo "> Health check의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
      echo "> Health check: ${response}"
  fi

  if [ $retry_count -eq 10 ]
  then
    echo "> Health check 실패. "
    echo "> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done

 switch.sh
echo "> 스위칭"
echo "> 현재 구동중인 Port 확인"
echo "> 현재 배포중인 profile : $CURRENT_PROFILE"

echo "> 전환할 Port: $IDLE_PORT"
echo "> Port 전환"
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" |sudo tee /etc/nginx/conf.d/service-url.inc

sleep 1

echo "> Nginx restart"
sudo service nginx restart

sleep 2

PROXY_PORT=$(curl -s http://localhost/profile)
echo "> Nginx Current Proxy Port: $PROXY_PORT"

echo "> jar파일을 back_dir 로 이동"
sudo mv ~/app/nonstop/*.jar ~/backup-app/

exit 0
