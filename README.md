# Spring-abc

## 环境要求
* Java7+ 
* Maven3+ 
* MariaDB10+(Mysql5.6+) 
* Tomcat7+

## 运行
1. 克隆或者下载源码，执行```database.sql```创建数据库
2. 修改```src/main/resources/application.properties```里面的 jdbc 设置
3. 申请一个['七牛云存储']()的空间,然后配置```application.properties```的 qiuniu 配置
4. 终端执行命令```mvn jetty:run```构建，使用 Maven jetty 插件做 Servlet 容器去运行
5. 浏览器打开```htttp://localhost:8080/```访问

>七牛云存储有一定量免费流量，可以配置一个免费的空间存储文件，按照信息填写配置。否则文件上传功能不可用

## 使用
1. 默认账户 springabc springabc
2. `hostname/back/`后台,配置分类节点，导航等

## 预览

![list](https://raw.githubusercontent.com/zh-h/spring-abc/master/show-off/list.png)

![article](https://raw.githubusercontent.com/zh-h/spring-abc/master/show-off/article.png)

![home](https://raw.githubusercontent.com/zh-h/spring-abc/master/show-off/home.png)

![log](https://raw.githubusercontent.com/zh-h/spring-abc/master/show-off/log.png)

![](https://raw.githubusercontent.com/zh-h/spring-abc/master/show-off/node.png)


