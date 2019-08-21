## Java 分布式相关问题

1. 负责均衡算法

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