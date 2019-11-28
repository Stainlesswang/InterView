### Hive 学习记录

Hive是一个数据仓库的工具,可以简化程序员开发MapReduce的任务,使用的方式是SQL的方式,下边简要介绍下如何上手HiveQL进行简单的任务创建,让查询和分析变得非常容易

#### Hive 不具备以下特性

- 一个关系型数据库
- 一个用于在线事务分析(Online Transaction Processing)OLTP
- 一个用于实时查询和行级别更新的语言

#### Hive的特性

- 将数据的结构存储在数据库但是实际处理数据是在HDFS当中
- 是设计用来执行 联机分析处理 OLAP(Online analytical processing)
- 提供SQL类型的语言以供开发者使用,称为HiveQL 或者 HQL
- 友好,快速,可伸缩 可扩展的
git 
#### Hive的运行方式:

下图描述了从Hive->HDFS的工作流

![](https://www.tutorialspoint.com/hive/images/how_hive_works.jpg)

下边的表格详细介绍每个步骤的含义

步骤序号 | 名称 | 描述
---- | --- | ---
1 | Execute Query | Hive用户接口例如命令行或者网页发送查询到驱动器(例如JDBC或者ODBC等等)去执行
2 |  Get Plan | 驱动器(Drive)在查询编译器的解析和分析语法和查询计划以及一些执行查询的必要条件是否满足
3 | Get Metadata | 编译器发送元数据请求到元数据存储的地方(任意的数据库)
4 | Send Metadata | 元数据存储发送元数据给编译器作为返回结果
5 | Send Plan | 编译器检查请求并且返回Plan给驱动器(Driver).至此解析和编译一个query结束
6 | Execute Plan | Driver发送执行计划到执行器
7 | Execute Job | 真正执行的是在Hadoop行的一个MapReduce任务,Job Tracker位于Name Node中并且为到来的该job分配一个Task Tracker(就是 DataNode),至此 一个用户query才真正的被执行为MapReduce Job 
7.1 | Metadata Ops | 在执行器执行的同时,它也可以同时执行元数据的操作
8 | Fetch Result | 执行器接收从Data nodes传过来的结果
9 | Send Result | 执行器发送结果到Driver
10 | Send Result | Driver发送结果给用户接口

