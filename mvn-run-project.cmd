@ECHO OFF

pushd . && (

mvn clean -DskipTests=true package

  ) && (

start mvn spring-boot:run

 ) && (

popd )





