# SCF(Service Conmunication Freamwork)

58集团的SCF框架,是一个远程调用框架,服务A像调用本地方法一样去调用远程服务B,不需要关注网络细节以及负载均衡等细节,有点像dubbo一样

### 初探SCF框架
58同城的项目习惯，一个项目由两部分组成，SCF提供服务，各种Service， WF提供web框架处理外部的各种请求和逻辑，wf项目使用socket和SCF通信

首先通用查询的项目中，使用maven的方式将项目更新到仓库中使改动生效，然后调用初始化的方式，开启一个Socket端口监听客户端需要条用的请求，这个
启动的就相当于**服务端** ，等待其他客户端发送请求到服务端进行响应

SF是一个简单的MVC框架，对结果进行封装，让程序员关注Controller中的逻辑，将项目和Service完全隔离开，方便维护扩展。SF提供了Spring MVC
有的全部功能，比如 文件上传下载，拦截器，过滤器，采用注解+实现类的方式来自定义响应的过滤器和拦截器

关于项目的更深入的了解，需要更深的理解项目功能结构，例如分布式事务，数据量上来以后为什么能够扛起来这么大的QPS？  慢慢深入了解吧。


