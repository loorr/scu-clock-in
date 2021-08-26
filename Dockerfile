# 添加 Java 8 镜像来源
FROM openjdk:8-jdk-alpine
COPY target/*.jar /application.jar

CMD ["--server.port=8080"]

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]