#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function deploy_second_app()
{
  IDLE_PORT=$(find_idle_port)

  echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"
  IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

  if [ -z ${IDLE_PID} ]
  then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
  else
    echo "> kill -15 $IDLE_PID"
    kill -15 ${IDLE_PID}
    sleep 5
  fi

  echo "> 두번째 어플리케이션 배포"
  JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

  echo "> JAR Name: $JAR_NAME"
  echo "> $JAR_NAME 에 실행권한 추가"
  chmod +x $JAR_NAME

  echo "> $JAR_NAME 실행"

  IDLE_PROFILE=$(find_idle_profile)
  echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

  nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
}