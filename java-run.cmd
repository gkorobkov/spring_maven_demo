rem java -Denviroment=.env -jar ./target/spring-maven-demo-0.0.1-SNAPSHOT.jar
java -DPOSTGRES_HOST=192.168.243.3 -DPOSTGRES_PORT=6543 -DPOSTGRES_DB=gk_docker_db -DPOSTGRES_USER=postgres -DPOSTGRES_PASSWORD=postgres -jar ./target/spring-maven-demo-0.0.1-SNAPSHOT.jar

rem pause