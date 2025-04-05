@ECHO OFF

pushd . && (

call mvn-build-project.cmd  ) && (

docker rm app-spring-maven-demo
docker image rm img-spring-maven-demo
call docker-build.cmd

 ) && (

start docker-run.cmd

 ) && (

popd )


