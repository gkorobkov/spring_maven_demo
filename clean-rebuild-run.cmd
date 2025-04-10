@ECHO OFF

pushd . && (

call mvn-build-project.cmd  ) && (

rem docker rm app-spring-maven-demo
rem docker image rm img-spring-maven-demo

call docker-compose-down.cmd

 ) && (

start docker-compose-up.cmd

 ) && (

popd )


