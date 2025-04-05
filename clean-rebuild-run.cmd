@ECHO OFF

pushd . && (

mvn clean package  ) && (

docker rm app-spring-maven-demo
docker image rm img-spring-maven-demo
docker-build.cmd

 ) && (

start docker-run.cmd

 ) && (

popd )


