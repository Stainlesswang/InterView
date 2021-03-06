# Spring Cloud learn 

Spring Cloud 为开发者提供了快速构建一些常见模式的分布式系统的工具,例如: 配置管理,服务发现/注册,断路器,智能路由,微代理,控制总线,一次性令牌,全局锁,领导者选举,分布式会话,集群状态

Distributed/versioned configuration

Service registration and discovery

Routing

Service-to-service calls

Load balancing

Circuit Breakers

Global locks

Leadership election and cluster state

Distributed messaging


1. **Spring Boot自带的性能监测依赖`actuator`**

   actuator只需要在pom文件中引入对应的依赖并且在配置文件中配置如下简单的信息
   
   pom文件中需要加入的
   
   ```
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```
   
   ```
   #Spring 内置的监控
	info.app.name=rush
	info.app.version= 1.0.0
	info.app.test=test
	
	management.endpoints.web.exposure.include=*
	management.endpoint.health.show-details=always
	#management.endpoints.web.base-path=/monitor
	
	management.endpoint.shutdown.enabled=true
   ```

   这时候只需要访问/actuator/info 就可以获取项目的信息 /actuator/health 获取项目的运行状态
   还可以查看创建的bean,项目中的web对应的mapping路径,条件配置是根据哪些条件产生的还有未生效的自动配置详情
   
2. **服务治理:Spring Cloud Eureka**

Spring Cloud Eureka是服务的注册和发现中心,一般搭建都会搭建高可用的服务注册中心,例如两台机器启动两个注册中心,分别向对方注册自己,那么注册中心就会保持高可用状态,两台机器其中一台不可用也可以保证服务的正常注册,发现.

  - **注册中心**

	三个重要角色
	
	注册中心:保留服务提供者和维护其有效地址列表,向服务消费者提供provider的列表
	 
	服务提供者:向服务注册中心注册自己的服务,并且发送心跳(rest接口方式)保持自己存活并且不被剔除
	
	服务消费者:向注册中心请求服务列表,然后根据负载均衡规则调用服务提供者提供的服务

  - **断路器:**

    在多个微服务相互调用的过程中,A服务因为在调用B的时候因为B的处理出了问题或者网络延迟等原因造成了A服务挂起, 如果在微服务当中这样挂起的程序过多会导致整个微服务瘫痪.  所以,断路器在这里会检测这样的情况出现,一旦发现立马向A返回一个错误结果,防止一直挂起等待
    
    断路器还可以设置发生异常的时候进行服务降级,也可以在服务超过默认3000ms的时候进行服务的降级处理.
    
  - **Feign:**

    Feign整和了Ribbon和Hynix断路器,实现了服务降级,服务降级需要实现一个接口,并且在降级的时候指定该接口来替换原有的服务. 并且Feign能够像调用本地方法一样去调用服务提供者