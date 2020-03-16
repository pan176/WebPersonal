# WebPersonal

# 项目介绍

> 在家没事做？不如跟我一起做网站吧，每天收入0元，我和身边朋友都在做，反正闲着也是闲着
>
> 网站地址：http://pan176.com  网站文档：http://github.com/pan176/webpersonal_doc

## 组织结构

```
WebPersonal
├── business -- 业务服务
├── gateway -- 网关服务
└── frontend -- 前端界面
```

## 技术选型

### 后端技术

| 技术                                                         | 说明                        |
| ------------------------------------------------------------ | --------------------------- |
| [Spring Boot](https://spring.io/projects/spring-boot)        | MVC框架                     |
| [Spring Cloud Alibaba](https://spring.io/projects/spring-cloud-alibaba) | 微服务开发的一站式解决方案  |
| [Spring Security oAuth2](https://spring.io/projects/spring-security-oauth) | 认证和授权框架              |
| [TkMyBatis]()                                                | 基于 MyBatis 框架的一个工具 |
| [MyBatisCodeHelperPro](https://www.baidu.com/link?url=8CK-ANR7tXoyIxsVK6gBR1SNF-kPeJY2gvBISlyztPPAxLERrrynNJJ_8wj73T6ZnouYVH9ekFLzZ97G7ANNNa&wd=&eqid=ef1fd0e2000cd5b4000000065de278fb) | 生成 MyBatis 相关代码       |
| [OkHttp3](https://square.github.io/okhttp/)                  | 网络请求的框架              |
| [Jackson](https://www.baidu.com/link?url=kTs9ZdJ-uFzbdHoqQNaEmr0rJrayLClHHSDJwoqrLnQoDtgME7NQMBfMb55EXdx6UAb35JVhCXFQezJPgToRMq&wd=&eqid=950f9c22002a4fe7000000065de2796b) | 提供了处理 JSON 数据的工具  |
| [Lombok](	https://github.com/rzwitserloot/lombok)         | 简化对象封装工具            |
| [Docker](https://www.docker.com/)                            | 应用容器引擎                |
| [Redis](https://redis.io/)                                   | 分布式缓存                  |
| [HikariCP](https://github.com/brettwooldridge/HikariCP)      | 数据库连接池                |
| [Nacos](https://github.com/alibaba/nacos)                    | 注册中心                    |

### 前端技术

| 技术                                                         | 说明             |
| ------------------------------------------------------------ | ---------------- |
| [Vue]( https://vuejs.org/ )                                  | 前端框架         |
| [Vue Router](https://router.vuejs.org/)                      | 路由框架         |
| [Vuex](https://vuex.vuejs.org/)                              | 全局状态管理框架 |
| [Axios](https://www.baidu.com/link?url=NKzjfqpbSMXR4AjHRO2EtLxVgP5dgLrWeC0zJl1Frug1TlHStw1CJVp9n4eAAdF0&wd=&eqid=ff4d26e200159260000000065de27b5d) | 前端 HTTP 框架   |
| [Element UI](https://element.eleme.io/)                      | 前端 UI 框架     |
| [Vue Element Admin](https://panjiachen.github.io/vue-element-admin-site/zh/) | 前端模板         |

## 环境搭建

### 开发工具

| 工具                                                       | 说明                 |
| ---------------------------------------------------------- | -------------------- |
| [IntelliJ IDEA](https://www.jetbrains.com/idea/download)   | 开发 IDE             |
| [RedisDesktop](https://redisdesktop.com/download)          | Redis 客户端连接工具 |
| [VMware](https://www.vmware.com/)                          | 虚拟机               |
| [Xshell](http://www.netsarang.com/download/software.html)  | Linux 远程连接工具   |
| [SQLyog](https://sqlyog.en.softonic.com/download)          | 数据库连接工具       |
| [Postman](https://www.getpostman.com/downloads/)           | HTTP 请求工具        |
| [亿图图示](https://www.edrawsoft.cn/download-edrawmax.php) | 流程图绘制工具       |

### 开发环境

| 工具                                                         | 版本号 |
| ------------------------------------------------------------ | ------ |
| [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) | 1.8    |
| [MySQL](https://www.mysql.com/)                              | 8.0.17 |
| [Redis](https://redis.io/download)                           | 5.0.6  |

### 服务规划

> 本地环境

| 服务  | IP/端口        |
| ----- | -------------- |
| Nacos | 127.0.0.1:8848 |
| MySQL | 127.0.0.1:3306 |
| Redis | 127.0.0.1:6379 |

> 开发环境

| 服务  | IP/端口      |
| ----- | ------------ |
| Nacos | 公网 IP:8848 |
| MySQL | 公网 IP:3306 |
| Redis | 公网 IP:6379 |