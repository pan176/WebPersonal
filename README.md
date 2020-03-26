<p align="center">
  <img src="https://s1.ax1x.com/2020/03/26/G9A1vd.png" alt="G9A1vd.png" border="0" />
  <br><strong>WebPersonal</strong><br>
  <strong>程序员怎么能没有自己的网站呢</strong>
</p>


<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/%E5%BE%AE%E4%BF%A1-17679120076-brightgreen" alt="微信"></a>
  <a href="#"><img src="https://img.shields.io/badge/version-1.0.0.RELEASE-blue" alt="版本"></a>
</p>

由 **Spring Boot + Spring Security + MyBatis** 等技术实现的个人网站，在家没事做？不如跟我一起做网站吧，每天收入0元，我和身边朋友都在做，反正闲着也是闲着:dog:

**演示地址**：http://www.pan176.com

**项目文档**：http://www.pan176.top/webpersonal_doc

**前端模板**：[Vue Admin Template](https://github.com/PanJiaChen/vue-admin-template)

## 本地部署

> 项目搭配阿里云 OSS使用，需要阿里云的 ACCESS_KEY_ID/ACCESS_KEY_SECRET

1. **克隆项目到本地**

   ```shell
   git clone https://github.com/pan176/WebPersonal.git
   ```

2. **修改阿里云的相关配置**

3. **数据库注入 webpersonal.sql**

4. **本地部署 Nacos**

   ```shell
   git clone https://github.com/alibaba/nacos.git
   cd nacos/
   mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U  
   ls -al distribution/target/
   
   // 选择你下载的版本号替换$version
   cd distribution/target/nacos-server-$version/nacos/bin
   cmd startup.cmd
   ```

5. **启动 business、gateway**

   ```shell
   java -jar business-1.0.0-SNAPSHOT.jar
   java -jar gateway-1.0.0-SNAPSHOT.jar
   ```

6. **启动 frontend**

   ```shell
   npm run dev
   ```

7. **访问 http://127.0.0.1:9527**

## 技术支持

**后端**：Spring Boot、Spring Cloud、Spring Security、Nacos、TkMybatis、HikariCP、OkHttp3、Jackson、Lombok、Docker

**前端**：Vue、Axios、Element UI、Vue Element Admin

**环境**：JDK 1.8、MySQL 8.0.17、Redis 5.0.6

## 项目结构

```
WebPersonal
    ├── business -- 业务服务
    ├── gateway  -- 网关服务
    └── frontend -- 前端界面
```

## 效果图

![8XZnQP.jpg](https://s1.ax1x.com/2020/03/25/8XZnQP.jpg)

![8XZ2y6.jpg](https://s1.ax1x.com/2020/03/25/8XZ2y6.jpg)