### 公司营销平台索引构建服务总结

自上而下分析该项目的功能点

1. IPersonGroupServiceImpl （主要的RPC服务提供类）

   主要目标是根据接收到的标签参数，根据标签参数给出最终匹配数量以及返回.cvs文件的URL
   
2. TagSearchClient 

   主动触发Hbase某个表上的EndPoint协处理器， 返回List<Result>结果，一个结果是一个分区针对本次标签的如下四个参数
   
   - startId 分区启始id
   - count 数量
   - countJson 
   - bitMap 该用户分区下的bitMap