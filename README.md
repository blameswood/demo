# 图文发布，编辑活动的restFul风格的网站后台。
spring boot, redis ,dubbo, guava cache, zookeeper， spring boot admin
## 1.项目结构
  
  如图所示，
``` xml
demo-app-->demo-app-parent
    |
    |--demo-core-spring
    |     |
    |     |--demo-core-common
    |     |
    |     |--spring-boot-starter-web
    |     |
    |     |--spring-boot-starter-thymeleaf
    |
    |--demo-core-redis-->demo-core-parent
    |     |
    |     |--demo-core-common
    |     |
    |     |--spring-boot-starter-redis
    |
    |--spring-boot-starter-jdbc
```
## 2.更新demo-core-parent
  每次更新前需要更新本地包：mvn clean install
- 其中demo-core-common提供和限制了设计模式，线程,IO,加载,注入等操作
- demo-core-spring 提供对spring的依赖和接口扩展
- demo-core-redis 提供了对redis的依赖,使用jedis和redis连接池

```xml
            demo-core-parent
             ↑
             |--demo-core-common
             |--demo-core-spring
             |--demo-core-redis
```
