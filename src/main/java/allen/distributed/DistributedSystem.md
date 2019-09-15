## Java 分布式相关问题


1. **CAP原理**

  CAP定理是指在一个分布式计算系统下,一致性,可用性,分区容错性,最多满足其中的两种,不能保证全部.[CAP博客图文详解](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/)
  
  - 一致性(Consistency):任何读操作返回的数据必须都是最新的,前提是在写操作执行完毕后去读
  - 可用性(Availability):系统中的每个可用的节点收到请求后必须做出响应,否则就不满足可用性; 系统是高可用的,请求必有回响
  - 分区容错性(Partition Tolerance):两个服务A和B在分布式系统当中,其中A在China,B在American 所以A向B发送消息很可能出现无法接收的情况,系统必须考虑这一点.  所以**分区容错性是都要考虑的,那么 C A两个特性我们现在只能保证一个了**

2. **负责均衡算法**

  - 轮询(*Round Robin*)法:

     轮询很容易理解,就是对当前集群中的机器进行无差别轮换,不考虑机器的差别和实际的负载情况. 实现代码就是对一个机器的list进行position选择,并且必须对共享变量加上synchronized,保证多线程下不会出现position出现异常导致IP地址的List发生数组越界,这样会导致多线程下吞吐量下降
     
  - 随机(*Random*)法:

     随机从概率上来讲保证了一定的公平性,特别是在访问量较大的时候,在分配策略方面无限接近于轮询法.所以建议在访问量足够大的应用场景下避免使用轮询法耗费大量性能,关键代码如下:
    
     ```
     Set<String> keySet=serverMap.keySet();
     ArrayList<String> keyList=new ArrayList<String>();
     keyList.addAll(keySet);//使用ip列表的拷贝
     
     Rondom random=new Rondom();
     int randomPos=random.nextInt(keyList.size());
     
     String server=keyList.get(randomPos);
     return server;
     ```
     
  - 源地址哈希(*Hash*)法:

     使用访问地址ip进行hash函数得到一个值,把该值和服务器列表size进行取模运算,得到服务器列表的位置.然后取到相应的服务器ip.
     
     ```
     Set<String> keySet=serverMap.keySet();
     ArrayList<String> keyList=new ArrayList<String>();
     keyList.addAll(keySet);//使用ip列表的拷贝
     
     int hashCode=remoteIp.hashCode();
     int serverListSize=keyList.size();
     int postion=hashCode % serverListSize;
     
     String server=keyList.get(postion);
     return server;
     ```
     
  - 加权轮询法:

     每个IP有相应的权重,例如10.10.23.29 权重1 20.10.168.20 权重3 我们在构造IP列表的时候,权重是几就增加几个重复的IP.  然后对该list采用轮询策略
     
  - 加权随机发:

     在加权轮询法构造完的IP列表以后,随机的从列表中选取IP,当请求足够多的情况下,IP的分配情况无线接近于权重对应的概率,性能要优于加权轮询法
     
  - 最小连接数(*Least Connections*)

     即每次选择连接数最小的Server，当最小的Server有多个时则使用加权轮询选择其中一个，原理比较简单，就算法就不过多的叙述了
     
 
3. **分布式事务:**

   主要使用一个案例,来实现分布式事务的实践, 首先描述场景: 支付宝的一个转账业务主要分如下的过程: A->B转账 100
块,整个过程涉及到 三个系统,四个数据库, 所以整个过程我们无法保证都能狗100%成功,所以分布式事务在这里起到防止数据不一致的风险,而且是必须要重视的点
   
   1. 创建订单, 交易系统, 一个DB
   2. 记录支付明细, 支付系统, 一个DB
   3. 账务系统 A扣钱
   4. 账务系统 B加钱

   预备知识,首先讲一下必须要知道的一些原理,可能这些原理是支撑后边更加严谨的分布式事务的的基础,首先讲解一下什么是`2PC(Two Phase Commitment Protocol)`两段提交协议,然后讲解什么是`TCC(Try-Confirm-Cancel)`,它作为资源管理器的实现方式的具体讲解
   
   1. **2PC:**两段提交协议,做一下场景类比,有个叫**事务管理器**的角色,它就像是一场活动的话事人,另外该话事人有多个小弟,(小弟学名叫做**资源管理器**)假设现在有两个小弟,首先在**第一阶段**话事人会向两个小弟确认家伙事是否都已经准备好!灯光啊,所需物品,收到回复后就进入**第二阶段**:只有当两个小弟全部都告诉话事人准备好的时候,才会开始这场活动,执行相应的操作,但凡有一个没准备好,就好要就会要求回滚,保持原来的状态防止混乱
   2. **TCC:**其中的try,confirm,cancel三个方法都是由业务编码实现,所以TCC可以被称为服务获得资源管理器,try作为第一阶段实现数据的检查预留,Confirm阶段执行提交操作,执行真正的业务逻辑, Cancel阶段清楚所有的预留数据,恢复为初始状态.最终所有的小弟要么全部都是提交的要么全部都是会馆操作.

      TCC操作的关键点就是如何分为两步走,例如A要减去三十元应该分为两步,第一首先检查是否够三十元,然后把三十元锁上.等待事务管理器确认其他资源管理器状态. 第二阶段才是真正的执行数据库操作,吧三十元减掉.

   
     