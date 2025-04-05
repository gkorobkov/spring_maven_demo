FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
WORKDIR /
ENV env-dockerfile-property-1=env-dockerfile-val1
ENV env-dockerfile-property-2=env-dockerfile-val2
ENV env-file-property-2=${env-file-property-2}
ENV env-file-property-3=env-dockerfile-val3

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080