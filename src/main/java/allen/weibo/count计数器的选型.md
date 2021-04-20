### count计数器的选型

首先,新国际版微博目前的转发数,评论数,点赞数,评论数,视频播放数,视频下载数等等都是基于微博的CountService中间件来使用的.

下边我会首先介绍现有CountService的发展和历史以及现在的大致原理,然后分析如果拆出来用别的计数器是否可行 成本有多大?

#### 微博的CountService介绍

从微博计数器组件的发展来大致聊一下自己的理解,然后讲下现在微博还在使用的这套的优势是什么?

- 原始部落: MySQL

根据一个用户100008745发布的内容直接使用表里的存储数据计算出来,
在面对普通的计数场景这样的做法是简单可行的,基于保存在mysql的发博条数进行实时的查询,也能保证数据的时效性,操作维护也没啥成本,

但是缺点也是显而易见的,具体能够抗多少量呢? 感兴趣的可以自己试验一下

| uid |mid | content| creattime|
| ------ | ------ | ------ | ------ |
| 100008745 | 4617590110683142 | let us go! | 2021-03-01|
| 100008745 | 4617590156820484 | happay ending | 2021-12-30|

 `SELECT count(uid) from status WHERE uid =100008745 `
 
 - 原始部落升级版本

| uid  | status_count | updatetime|
| ------ | ------ | ------ |
| 100008745 | 130 | 2021-03-01|
| 100008745 | 200 | 2021-12-30|

`SELECT status_count from UserCount WHERE uid= 100008745 `

 - 原始部落豪华版

 UserCount拆分

| uid  | status_count | updatetime|
| ------ | ------ | ------ |
| 100008745 | 130 | 2021-03-01|

| uid  | status_count | updatetime|
| ------ | ------ | ------ |
| 100008745 | 130 | 2021-12-30|


 `SELECT status_count from UserCount_hash WHERE uid= 100008745 `
 
 **上个缓存**
 
 1. cache.get(uid)
 2.  `SELECT status_count from UserCount_hash WHERE uid= 100008745 `
 3. cache.set(uid)

  **基于缓存计数,到达一定数量回种DB**
  
 -  cache.get(uid)
 -  `SELECT status_count from UserCount_hash WHERE uid= 100008745 `
 -  cache.set(uid)

 ____
 
 - cache.incr(uid)
 - oneceCount=cache.incr(uid.onece)
 - if(oneceCount>100)
 - db.set(uid,count) && cache.del(uid,once)
