### 初始Hadoop
Hadoop是用来在成百上千的集群节点上将任务进行分而治之然后得出结果的一个生态圈，其中重要的思想包括MapReduce计算思想，将数据分片分不到不同的节点上进行map任务，提取出关注的维度作为key，然后需要的数据作为value，并且在这个过程中进行下Order数据，然后将结果给reduce任务进行汇总得到最终需要的结果

- MapReduce:是一个在大型廉价商品服务器组成的集群上处理大量结构化,半结构化,非结构化数据的并行处理模型
- HDFS:分布式文件系统是Hadoop框架的重要组成,用来存储和处理数据集,并且在普通商用的服务器集群上提供容错机制的可靠文件系统



