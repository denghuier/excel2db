# excel2db
## 简介
excel2db是一个将excel文件转换为数据库的工具，目前支持mysql、postgresql、oracle、sqlserver。

1、前端采用vue3、element-plus构建

2、后端采用java21、springboot3、mybatis3构建

3、数据库mysql

> 注意：目前只支持单表导入，不支持多表导入；仅mysql传输进行测试

## 架构图
![img.png](img/design.png)

## 功能

1、数据源管理
![img.png](img/img.png)
2、任务管理
![img_1.png](img/img_1.png)
![img_2.png](img/img_2.png)
![img_3.png](img/img_3.png)
![img_4.png](img/img_4.png)
![img_5.png](img/img_5.png)

## 部署

### docker部署

#### 前端打包

```vue
npm run build
```

#### 后端打包

```
mvn clean package -DskipTests
```

#### docker构建镜像

```
docker build  -t excel2db:1.0 . 
```

#### docker运行容器

不替换application.yml文件

```
docker run -p 8080:8080 --name excel2db -d excel2db:1.0 . 
```

**替换application.yml文件**

```
docker run -p 8080:8080 -v {you_path}\application.yml:/app/application.yml --name excel2db -d excel2db:1.0 .
```



## 后续计划

1、增加数据源

2、增加转换插件
