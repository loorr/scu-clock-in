# 添加 Java 8 镜像来源
FROM openjdk:8-jdk-alpine
COPY target/*.jar /application.jar

CMD ["--server.port=8000"]

EXPOSE 8000
ENV TZ Asia/Shanghai

RUN apk add tzdata && cp /usr/share/zoneinfo/${TZ} /etc/localtime \
    && echo ${TZ} > /etc/timezone \
    && apk del tzdata
ENTRYPOINT ["java","-jar","/application.jar"]