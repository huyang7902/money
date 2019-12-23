# money
记账系统

## 说明
本项目整合了以下技术
- springboot(2.1.0.RELEASE)
- jsp模板
- ehcache
- mybatis
- druid连接池
- 前端使用了bootstrap框架

> 注意：springboot不推荐使用jsp引擎



## 运行及部署说明
本项目最终打包为war包，如果打包成jar包部署时会有问题（idea工具下面没有问题）
比较重要的依赖

```xml
<!-- 用于编译jsp，springboot内置tomcat没有此依赖 -->
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <!--<scope>provided</scope>-->
</dependency>
<!--jsp页面使用jstl标签-->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>
```

默认使用的内嵌tomcat进行启动：java -jar money.war

如果使用外部容器进行部署需要修改pom.xml的配置，移除springboot内置的tomcat，并把`tomcat-embed-jasper`的作用范围改成`provided`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
```

## 关于tomcat-embed-jasper依赖的问题
> 有provided情况

- 右键运行启动类，访问页面报404错误
- 使用spring-boot:run运行正常
- 打包成jar，通过 java -jar money.jar 运行报错
- 打包成war，通过 java -jar money.war 运行正常
> 注释provided情况

- 右键运行启动类，访问页面正常
- spring-boot:run运行 访问页面正常
- 打包成jar，通过 java -jar money.jar 运行报错
- 打包成war，通过 java -jar money.war 运行正常
