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

`hdp_jinrong_qiangui:uid-mapping`(根据Uid找到对应用户信息phone iemi gid等信息)

  - gid.hbase.cf.name = "d"
  - gid.hbase.q.phone = "p"
  - gid.hbase.q.imei = "i"
  - gid.hbase.q.gid = "g"
  - gid.hbase.q.xparams = "x"

  
  
  
#### 出现错误count返回结果出错 解决方案:

首先要加上日志,看看报错的时候日志信息是否有价值,然后报错的时候查看JVM的状态,是否存在内存泄漏导致的OOMCPU占用过高等异常情况,看是否是垃圾回收机制错误  



出错的时候首先查看java进程的运行状态啥的步骤:

1. jps 查看java进程对应的pid
2. jmap -histo pid > histo.log
3. jstack -F pid 

#### 整个营销平台的数据流程及走向:


 1.  首先每个标签任务跑出相应的字段对应的标签

	例如新增了一个标签组c27 的hive表字段配置:

	配置地址为:

	`viewfs://58-cluster/home/hdp_jinrong_qiangui/resultdata/fsmart/configuration/dict/c27_fields.conf` 

	配置内容为:

	```
	 userid
	 1270001,limit_status
	 1270002,removed
	 1270003,derating_days
	 1270004,is_black
	 1270005,withdraw_status
	 1270006,withdraw_days
	 1270007,cur_overdue_days
	 1270008,score_id
	 1270009,risk_level
	```
	标识了key`1270001`对应的字段名为'limit_status'

2. Spark 任务`itf_fsmart_indexbuilder_datamerge_001`


 - 首先读取`viewfs://58-cluster/home/hdp_jinrong_qiangui/resultdata/fsmart/configuration/dict/*_fields.conf`下的所有配置文件
  - 然后获取每个标签,例如`c27`的文件存放路径`viewfs://58-cluster/home/hdp_jinrong_qiangui/resultdata/fsmart/configuration/fsmart_data_import/c27/20200320`
  - 将所有的现有的27个标签整和成 一个uid 和对应标签的值作为一行数据存储在hdfs

  
  最终的格式为,假设两个uid 他们的存储数据为
  
  ```
  41790988673294 1170001:-99 1170002:6 1170003:-99 1170004:-99 1170005:-99 1170006:-99 1170007:-99 1170008:-99 1170009:-99 1170010:-99
  ```
  
  合并成 uid 以及符合uid27个标签的所有码值


3. Spark 任务`itf_fsmart_indexbuilder_gengid_001`

	对第二步数据合并后的一张大表中,使用map构建(uid-value)的键值对,其中value就是该用户所满足的所有三级标签(形如1270012:1)
	
	然后将所有的Uid以及根据行号算出来的Gid构建写入到hive表 `stf_yx_uid_gid_mapping_001`中
	
	```
	insert overwrite table hdp_jinrong_qiangui_defaultdb.stf_yx_uid_gid_mapping_001
	 partition(type="all", daystr=${today})
	 select uid, gid from hdp_jinrong_qiangui_defaultdb.stf_yx_uid_gid_mapping_001
	 where type="all" and daystr=${lastDay}
	 union
	 select uid, gid from hdp_jinrong_qiangui_defaultdb.stf_yx_uid_gid_mapping_001
	 where type="incr-gid" and daystr=${today}
	```
	
	hive表`stf_yx_gid_data_001` 保存uid 对应value 这里需要注意的是 该表的路径为
	
	`"location":"viewfs://58-cluster/home/hdp_jinrong_qiangui/resultdata/fsmart/gid_data"`
	
	
4. `stf_user_fsmart_uid2imei_001` 

	根据`stf_yx_uid_gid_mapping_001` 关联  `stf_yx_gid_params_001`
	
	然后关联其他相关表获取 phone, imei ,params 到表 `stf_user_fsmart_uid2imei_001` 


5. `itf_fsmart_indexbuilder_gidmapping_001` 

	使用`stf_user_fsmart_uid2imei_001 `作为入参,输出到路径为:
	
	`/home/hdp_jinrong_qiangui/resultdata/fsmart/hbase_gid_mapping/${todaySuffix}`
	
	是Hbase表  gid-mapping 的数据来源, 保存了 gid和其对应的uid,phone,imei,params
	
	1058562   1059029
	
	














