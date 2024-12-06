# 指定基础镜像
FROM openjdk:21
LABEL authors="hmh"

# 设置工作目录
WORKDIR /app

# 复制 Spring Boot JAR 文件
COPY  /target/excel2db.jar /app/excel2db.jar

# 复制前端构建后的静态文件
COPY  /ui/dist /app/static

# 暴露应用端口
EXPOSE 8080

# 启动 Spring Boot 应用，使用内嵌的静态文件（前端代码）
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

COPY ./target/spring-boot-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["top", "-b"]