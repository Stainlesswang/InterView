### 公司营销平台索引构建服务总结

Q1:功能点有两个 1.count计数 2.uid或者phone信息导出


Q2:部署相关


Q3:Hbase涉及到的两个表数据举例

#### 自上而下分析该项目的功能点

1. IPersonGroupServiceImpl （主要的RPC服务提供类）

   主要目标是根据接收到的标签参数，根据标签参数给出最终匹配数量以及返回.cvs文件的URL
   
   downloadIdMapping（）方法作为导出用户数据的表格为主要功能，此处发送短信以及push名单的主要依据。
   这里需要搞清楚Hbase返回的结果中的BitMap如何计算成Gid进而得到用户id或者电话等属性
   
   TagSearchClient 返回的一个List结果  一个Result类代表Hbase一个协处理的处理结果
   
   ```
   for (Result result : results) {
   
   1.获取一个result的startId,每个数字代表一百万
   2.获取结果中的bitmap,bitmap中取出来的结果+startId就是真实结果
   3.然后一个一个循环取出来bitMap中的id(以200个一个小组构建ThreadTask,Thread是封装 任务id,gid的list)
   4.所有的任务task封装到一个Master对象当中
   5.最终的结果是一个resultMap对象
   6.解析结果的Map写入文件(关于写入文件细节需要具体需求例子来理解)
   
   }
   ```
   
  
   
2. TagSearchClient 

   主动触发Hbase某个表上的EndPoint协处理器， 返回List<Result>结果，一个结果是一个分区针对本次标签的如下四个参数
   
   - startId 分区启始id
   - count 数量
   - countJson 
   - bitMap 该用户分区下的bitMap
   
   ```
      class Result {
          private int startId;
          private String countJson;
          private int count;
          private ImmutableRoringBitMap bitmap;
      }
      ```
   
   
#### 项目部署相关

项目打包部署方式? scf采用自动部署,Hbase协处理器放到相应的HDFS文件目录,通过对Hbase中相应的表执行指定协处理器的命令然后执行协处理器

#### Hbase中表结构及示例数据

`hdp_jinrong_qiangui:index`(标签索引找到对应标签的用户)

 - cf.name = "d"
 - q.name = "q"

`hdp_jinrong_qiangui:gid-mapping`(根据Gid找到对应用户信息phone iemi uid等信息)

  - gid.hbase.cf.name = "d"
  - gid.hbase.q.phone = "p"
  - gid.hbase.q.imei = "i"
  - gid.hbase.q.uid = "u"
  - gid.hbase.q.xparams = "x"

  
  
  
#### 出现错误count返回结果出错 解决方案:

首先要加上日志,看看报错的时候日志信息是否有价值,然后报错的时候查看JVM的状态,是否存在内存泄漏导致的OOMCPU占用过高等异常情况,看是否是垃圾回收机制错误  



出错的时候首先查看java进程的运行状态啥的步骤:

1. jps 查看java进程对应的pid
2. jmap -histo pid > histo.log
3. jstack -F pid 


















