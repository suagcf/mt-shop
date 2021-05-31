### 技术架构

项目是采用目前比较流行的 SpringBoot/SpringCloudAlibaba构建新零售微服务电商项目，从项目中台架构技术选型、模块设计、基础设施的构建、分布式解决方 案、互联网安全架构设计、Devops与K8S容器化部署，apm应用程序性能监控、实现一套串联的新零售领域驱动模型社区电商项目，能完全掌握该知识，可以在一线城市 拿到月薪 30-50k 薪资。
### 相关作者
97后架构师-余胜军   
QQ644064779 /微信yushengjun644   
相关QQ粉丝群：496920771   
个人网站:www.mayikt.com

### 技术图

![输入图片说明](https://images.gitee.com/uploads/images/2021/0310/153838_765977e2_926394.jpeg "电商项目架构图.jpg")

### 适合人群

1.工作 1-5 年的后端开发工程师；  
2.传统企业转互联网开发同学；  
3.金三银四跳槽，缺少互联网项目优势；  
4.计划从二线跳槽去一线开发同学；  
技术选项

### 核心架构技术方案

1.项目采用中台化设计，分为技术中台、业务中台、组织中台；  
2.基于SpringBoot+SpringCloudAlibaba构建微服务电商项目  
3.使用 Nacos 作为注册中心与配置中心，实现服务治理；  
4.使用新一代 Gateway 网关框架管理服务请求入口；  
5.使用 Ribbon 实现本地负载均衡器和 OpenFegin 客户端调用工具；  
6.使用 Sentinel 服务保护框架(系统自适应限流、降级、热词限流等)；  
7.微服务 API 接口安全控制与单点登陆系统 CAS+JWT+Oauth2.0；  
8.使用mybatisplus数据库持久层管理
分布式基础设施  
1.分布式任务调度平台 XXL-Job；  
3.分布式事务解决方案 Seata；  
4.分布式锁 redislock/Redisson 与高可用设计原理；  
5.分布式配置中心 Nacos ；  
6.高并发分布式全局 ID 生成雪花算法；  
7.基于 canal 结合 MQ 解决 MySQL 与 Redis/ES 一致性问题  
8.基于网关统一解决微服务接口跨域问题  
9.基于 openresty+lua+Redis 实现亿级商品详情页面  
10.基于SkyWalking实现分布式系统监控APM；  
项目运营与部署环境
1.分布式设施环境，统一采用 docker 安装；  
2.使用 jenkins+结合 kubernetes（k8s）容器部署技术；  
3.微服务 API 管理 ApiSwagger；  
4.使用 GitLab 代码管理；  
5.数据库使用MySQL5.7以上；  
6.使用七牛云服务器对静态资源实现加速；  
8.构建企业级 Maven 私服仓库管理jar包；   
### 相关安排


Day1-项目架构技术选型与技术讨论：  
1. 架构技术选型与需求讨论、大中台 小前台设计；  
2. 新零售电商设计、领域驱动模型(DDD)设计等；  

Day2-微服务项目构建初始化与企业级分布式基础设施构建：  
1. 项目构建初始化、注释规范、开发规范：po/do/vo/dto/bo 选择与应用；  
2. 项目全局异常、交互协议设计、统一错误码；  
3. 整合 Swagger 文档/gitlab 代码仓库管理/企业级 Maven 私服构建；  
4. Nacos 服务治理、与分布式配置中心构建 多 namespace 管理  
5. 整合 xxl-job 分布式任务调度平台 分片策略  

Day3-构建企业级微服务公众号开发  
1. 微服务公众号开发思想、整合 wxjava 公众号快速开发框架  
2. 外网映射工具的使用、自动回复关键字设计、二维码生成  

Day4-构建微服务会员服务中台设计  
1. 令牌登陆、唯一登陆、QQ/微信联合登陆、挤下线设计  
2. 拦截器、整合 jwt 授权设计+Oauth2.0 开放平台设计；  
3. 整合线程池、异步发送短信/邮件、改造 MQ 异步设计  
4. SSO 单点登陆设计原理整合 CAS  

Day5-构建微服务消息服务中台设计  
1. 整合微信消息模板、第三方邮件服务、阿里云短信接口  
2. app 消息推送/消息服务中台同步/异步接口设计  
3. 整合 kafka 实现高性能异步群发消息服务平台  

Day6-构建微服务优惠券与秒杀抢购中台设计  
1. 大型电商满减券、无门槛、新人优惠券设计；  
2. 大型电商优惠券库存锁定、定时优惠券任务发放设计  
3. 高并发情况下，如何保证秒杀扣库存超卖问题  
结合 Redis+lua 脚本实现分布式锁落地  
4. 高并发情况下，如何提高分布式锁的效率常见问题总结  

Day7-构建微服务商品服务中台设计  
1. 商品中台 sku 库存锁定设计  
2. 结合优惠券、购物车复杂业务逻辑设计  
3. 整合 Elasticsearch 7 实现高性能搜索  
4. 基于 canal 解决 Elasticsearch 与 MySQL之间数据一致性问题  
5. CDN 设计原理、结合 openresty+lua 实现商品详情页面  

静态设计  
Day8-构建微服务-聚合支付中台设计：  
1. 整合支付宝、微信支付沙箱环境、支付架构原理  
2. 基于策略+模板自定义 starter 构建聚合支付框架  
3. 支付回调幂等、超时、安全、延迟设计  
4. 基于 lua 脚本+Redis 实现防重设计、定时对账设计  
5. 基于 seata 解决支付回调分布式事务问题  

mayikt-springboot-starte  

Day9-构建分布式基础设计日志采集系统：  
1. Aop 切面采集、打印日志规范、日志队列设计  
1. log-pilot 开源日志框架介绍  
2. 结合 k8s 整合 log-pilot 框架日志解决方案  

Day10-构建微服务智能报警中台设计：  
1. 接口，服务器监控、接口埋点原理  
2. 宝塔安装与监控私有云服务配置  
3. 网站 pv/uv 、qps/tps 之间的区别4. 整合百度统计、cnzz 大数据分析  
5. 全链路压测实战设计原理  
6. 生产环境流量回访、Grafana 运维报警  

Day11-构建微服务落地容器化部署  
1. 高并发请求下，私有服务器弹性与缩容设计  
2. 整合 k8s+jenkins+私有仓库自动化部署  
3. 服务器配置选项、团队规模人数定义  
Day12-一线大厂简历面试辅导  
1. 提供架构师/一线大厂真实简历模板  
2.真实大厂面试经验分享  


### 环境要求  

 
为了能够更好的学习互联网微服务架构，该项目对环境要求非常高，建议电脑配置CPU在I7 8700k以上处理、32GB内存或者电脑采用集群化部署。  
1.JDK统一要求:JDK1.8以上  
2.Maven 统一管理Jar  
3.统一采用Docker安装软件  
4.编码统一采用为UTF-8  
5.开发工具完全采用IDEA  

学习方式

1.腾讯课堂在线学习方式（选择第七期和第八期）
https://ke.qq.com/webcourse/index.html#cid=291872&term_id=102601151&taid=9006198527652896&type=1024&vid=5285890815286075327



### 项目的架构模块架构模型


mt-sp-parent-----公共Pranet接口 pom  
-----mt-sp-basics----分布式基础设施 pom  
---------mt-sp-basics-alibaba-nacos—注册中心 8080 jar  
---------mt-sp-basics-alibaba-nacos—分布式配置中心 8080    
---------mt-sp-basics-alibaba-seata 分布式事务解决方案 8730  
---------mt-sp-basics-gateway—统一请求入口 80  
---------mt-sp-basics-SkyWalking ---分布式服务追踪与服务监控  
mysql、redis、es

---------mt-sp-basics-alibaba-canal mysql与redis/es一致性的问题  
---------mt-sp-basics-xuxueli-xxljob—分布式任务调度平台  

-----mt-sp-service-api提供公共接口 没有实现业务代码  
------------ mt-sp-service-api-member会员服务接口  
------------ mt-sp-service-member会员服务接口实现    7070 7071

------------ mt-sp-service-api-weixin 微信服务接口  


-----mt-sp-service-api提供公共接口 没有实现业务代码  
------------ mt-sp-service-api-base api相关base继承类  
------------ mt-sp-service-api-weixin 微信服务接口  
------------ mt-sp-service-api-member会员服务接口  
------------ mt-sp-service-api-sso  sso服务接口  
------------ mt-sp-service-api-item 商品服务接口  
------------ mt-sp-service-api-coupon 优惠券  
------------ mt-sp-service-api-search 搜索服务接口  
------------ mt-sp-service-api-pay聚合支付平台  
------------ mt-sp-service-api-order订单服务接口  
------------ mt-sp-service-api-spike 秒杀服务接口  
------------ mt-sp-service-api-sms 消息服务平台  
包名格式：com.mayikt.api.weixin  
 
服务接口中包含内存内容: 实体类层、接口层   

-----mt-sp-service-impl 公共接口的实现层  
------------ mt-sp-service-weixin 微信服务接口实 现 9090 9091 9092  
------------ mt-sp-service-member会员服务接口实现  7070 7071  
------------ mt-sp-service-api-sso  sso服务接口实现 6060  
------------ mt-sp-service-tem商品服务接口实现  5050  
------------ mt-sp-service-coupon 优惠券  
------------ mt-sp-service-search 搜索服务接口实现 3030  
------------ mt-sp-service-pay聚合支付平台接口实现 2020  
------------ mt-sp-service-order订单服务接口实现 1010  
------------ mt-sp-service-spike 秒杀服务接口 4040  
------------ mt-sp-service-sms 消息服务平台 9810  




