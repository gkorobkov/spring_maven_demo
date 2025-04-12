@ECHO OFF

pushd . && (

call mvn-build-project.cmd

  ) && (

rem java-run-project.cmd
mvn-run-project.cmd

 ) && (

popd )





