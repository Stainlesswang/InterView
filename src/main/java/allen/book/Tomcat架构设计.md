## Tomcat架构解析-读书笔记

首先我们要理解tomcat的组件抽象的`Lifecycle`接口,它代表了tomcat中几乎所有组件的生命周期,

- Init(): 初始化组件
- start(): 启动组件
- stop(): 停止组件
- destroy(): 销毁组件

##### 使用文字描述下tomcat的整体架构:

手先*Server*代表的是整个tomcat应用服务器,其中可以包含多个*Service*(他们互相独立,只是共享一个JVM以及系统类库),每个*Service*负责管理
自己的*Connector*组件,以及*Container*组件,Connector负责服务的连接和通信,并能无感的在多重
协议之间切换,例如常见的HTTP协议,以及作为单独的Servlet容器和其他web服务器配合使用的时候使用
的**AJP协议**

每个Service的Connector处理的请求只能由属于该Service的Container去处理,但是为了概念上的和Tomcat组件命名一直
我们改口Container为Engine,Engine代表的是Servlet引擎,并不是Servlet容器,代表整个容器的是Server,
引擎只是负责请求的处理,并需要要管其他的链接,协议等等不相关的事情,还需要注意的是,Engine不仅仅是一个单独的业务系统,
它还需要管理Web应用,当接收到Connector的请求之后,Engine容器能够选择合适的Web应用来处理

Engine 下边可以抽象出来多个 Host,每个Host下边可以包含多个Context,并且每个Context代表一个Web应用的话,那么具体到代码中的一个
Servlet就是更小单位的容器,命名为Wrapper, 上边的每个组件的共性就是处理来自同属一个Service中的Connector的请求,并且返回响应的
结果,具体Engine下边是Host还是Context需要看具体实现而定,都是有Engine这个管理容器的接口对外提供服务

##### 请求处理-职责链模式的应用

Tomcat中每个Container组件通过执行一个职责链来完成具体的请求处理,Pipeline(管道)负责构造职责链,每个节点就是一个Value(阀),
并且每个层级的Container(Engine,Host,Context,Wrapper)都有对应的基础Value实现,同时维护了一个Pipeline实例,

##### Connector的设计

一个Connector一般要完成如下的几个主要工作:

- 监听服务器端口,读取来自客户端的请求
- 将请求按照指定协议解析
- 根据请求地址匹配正确的容器进行处理
- 将相应数据返回给客户端

所以一个**Connector** 根据协议的不同提供不同的ProtocolHandler,例如Http11NioProtocol,表示基于NIO的HTTP协议处理器,
一个ProtocolHandler包含一个Endpoint(用来启动Socket监听),一个Processor(按照指定协议读取数据,并将请求交由容器处理)

Processor交由哪个容器处理的时候会根据一个容器的映射来查找,Mapper用来维护容器的映射信息,MapperListener实现了ContainerListener
和LifecycleListener,用于在容器组件发生变化的时候注册或者取消对应的容器映射信息

整个框架的总结

| 组件名称 | 说明 |
| :---- | :---- |
| Server |  表示整个Servlet容器,Tomcat运行环境中只有一个Server实例 |
| Service | Service表示一个或者多个Connector的集合,他们共享同一个Container来处理其请求,同一个Tomcat实例中可以由任意个相互独立的Service |
| Connector | Tomcat的连接器,监听并转化Socket请求,同时将读取的Socket请求交由Container处理,支持不同的协议以及不同的I/O方式|
| Container | 处理客户端的请求并返回结果的一类组件统称,四个级别的容器分别为Engine,Host,Context,Wrapper |
| Engine| 表示整个Servlet引擎,为最高层的容器对象,尽管Engine不处理具体的请求,确实获取目标容器的入口 |
| Host | Host作为一类容器,表示Servlet引擎(Engine)中的虚拟机,与一个服务的网络名有关,如域名等,客户端可以使用这个网络名连接服务器,这个名称要在DNS服务器上注册|
|Context| Context作为一类容器,用于表示ServletContext,在Servlet规范中,一个ServletContext即表示一个独立的Web应用
| Wrapper| Wrapper作为一类容器,用于表示Web应用中定义的Servlet
| Executor | 表示Tomcat组件间可以共享的线程池|