set -e

BASE=`pwd`
WEB_JAR=./hashtagmap-web/build/libs/*.jar
WEB_DEPLOY_SCRIPT=./script/nonstop-deploy.sh
WEB_BASE=./script/hashtagmap-web
WEB_TARGET=./script/hashtagmap-web/items
ITEM_NAME=nonstop_test

echo "> workspace clear! exclude $ITEM_NAME"
cd /var/lib/jenkins/workspace
sudo rm -rf `ls /var/lib/jenkins/workspace | grep -v $ITEM_NAME`

echo "> move to web workspace"
cd $ITEM_NAME

echo "> WEB DIR 생성"
mkdir -p $WEB_BASE
mkdir -p $WEB_TARGET

echo "> WEB 배포 스크립트 이동"
cp $WEB_DEPLOY_SCRIPT $WEB_TARGET/

echo "> WEB jar 이동"
cp $WEB_JAR $WEB_TARGET/

echo "> WEB 배포 스크립트, jar 압축"
cd $WEB_TARGET
tar -cvf hashtagmap-web.tar *

echo "> 압축 이후 .jar, deploy.sh 삭제"
rm *.jar
rm nonstop-deploy.sh

echo "> tar 파일 이동"
mv hashtagmap-web.tar $BASE/$WEB_BASE/

echo "> PROJECT_ROOT/script/hashtagmap-web/hashtagmap-web.tar 생성"
