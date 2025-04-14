 rem docker build -t img-spring-maven-demo .
 docker rm app-dev-spring-maven-demo
 docker run --name app-dev-spring-maven-demo   -p 8081:8080   --env-file=dev.env   img-spring-maven-demo

