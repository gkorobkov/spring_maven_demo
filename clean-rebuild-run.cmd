@ECHO OFF

pushd . && (

call mvn-build-project.cmd    ) && (

call docker-compose-down.cmd 

 ) && (

docker rm app-spring-maven-demo
docker image rm img-spring-maven-demo

start docker-compose-up.cmd   ) && (

popd )


