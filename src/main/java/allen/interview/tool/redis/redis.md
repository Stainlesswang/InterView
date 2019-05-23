# Redis Question 
1.  **RedisTemplate使用问题** 
 
  - 默认的值序列化方式 为：JdkSerializationRedisSerializer 是包含java类的各种信息的字符串 例如 包路径和类信息
  
  - using GenericToStringSerializer And StringRedisSerializer 序列化的方式直接将字符串转化为字节数组进行存储，支持自增操作

  - using GenericJackson2JsonRedisSerializer And Jackson2JsonRedisSerializer 是将字符串转化为JSON字符串然后进行存储
2. **redis 集群相关理解**

> Redis 集群是一个可以在多个 Redis节点之间进行数据共享的设施（installation）。 Redis集群不支持那些需要同时处理多个键的 Redis 命令，因为执行这些命令需要在多个 Redis 节点之间移动数据，并且在高负载的情况下， 这些命令将降低 Redis 集群的性能， 并导致不可预测的错误。 Redis 集群通过分区（partition）来提供一定程度的可用性（availability）： 即使集群中有一部分节点失效或者无法进行通讯， 集群也可以继续处理命令请求。

**redis集群提供了一下两种好处:**

- 将数据自动切分(split)到多个节点的能力:采用不同的hash槽来保存数据,计算hash值跳转对应节点

- 当集群中一些节点失效或者无法通讯时,仍然可以保证集群继续处理命令的能力:master-slaver
  copy模型
  