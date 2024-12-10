# 指定基础镜像
FROM openjdk:21
LABEL authors="hmh"

# 设置工作目录
WORKDIR /app

# 复制 Spring Boot JAR 文件
COPY  /target/excel2db.jar /app/excel2db.jar
COPY /src/main/resources/application.yml /app/application.yml

# 暴露应用端口
EXPOSE 8080

# 启动 Spring Boot 应用，使用内嵌的静态文件（前端代码）
ENTRYPOINT ["java", "-jar", "/app/excel2db.jar"]

