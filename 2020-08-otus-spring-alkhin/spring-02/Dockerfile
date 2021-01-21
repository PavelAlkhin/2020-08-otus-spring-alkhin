FROM openjdk:11-jre-slim
COPY /target/spring-02-1.0-SNAPSHOT.jar /opt/app/app.jar
#WORKDIR /opt/app
RUN ln -s /dev/stdout /opt/app/logs/app.log
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
