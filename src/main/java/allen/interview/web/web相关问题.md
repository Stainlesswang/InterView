# Java Web Question 
1. **啥是servlet?**

    Java Servlet 是运行在 Web 服务器或应用服务器上的程序， 它是作为来自
    Web 浏览器或其他 HTTP 客户端的请求和 HTTP
    服务器上的数据库或应用程序之间的中间层 init()在初始化的时候被调用,然后
    servlet实例就会存活在web容器中根据web.xml等配置的url映射路径进行分配
    相应的请求到不同的servlet中去处理(此时调用service方法,service会根据
    请求的类型决定调用什么类型的具体方法执行,所以我们只需要重写
    doGet()或者doPost()方法即可),然后返回response对象交给web容器,并由
    容器返回给客户端浏览器

2. **Session和Cookies**

   由于Http请求的是无状态的,所以出现了保存在服务器端的Session和保存在客户端
   浏览器的Cookies以保存一次浏览过程中的数据.