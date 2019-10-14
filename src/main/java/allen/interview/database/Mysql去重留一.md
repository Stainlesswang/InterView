# 一条语句删除数据库中的重复记录(数据量较大,百万级数据,注意性能问题)
此类问题比较常见,在平常的开发当中,如何使用一条语句就能够做到呢? 并且效率还要很快,下边给出一种删除重复数据保留一条的方案. 希望大家有好的思路可以评论分享.这边为了方便理解,给出我本地使用的sql案例,方便大家测试和修改

首先说下思路:

1. 把重复数据查出来作为一个临时表temp
2. 保留一组重复数据中主键id最小的记录(删除的时候要用)
3. 两个表连接进行删除操作,删除Where条件:重复条件相同,并且id>min(id)

### 数据表字段及说明
表结构如下:

id主键,注意重复的列为**user_id(用户id),monetary(消费金额),consume_time(消费时间)**,也就是说,同一个顾客同一个时间消费金额数是不可能存在多条数据的, 我们要对数据库中记录坐下排重

其中摘录出来几条重复数据供大家理解题设

|id|user_id|monetary|consume_time|consume_type| 
|:-----:|:----:|:----:|:----:|:----:|
|2552|321|4|1495075537|1|
|2553|321|4|1495075537|1|
|2554|321|4|1495079932|1|
|2555|111|6|1495079932|1|
|2556|111|6|1495079932|1|
|2557|156|12|1495079939|1|
|2558|156|12|1495079939|1|
|2559|156|12|1495079939|1|		


### SQL展示

```
DELETE consum_record
FROM
    consum_record, 
    (
        SELECT
            min(id) id,
            user_id,
            monetary,
            consume_time
        FROM
            consum_record
        GROUP BY
            user_id,
            monetary,
            consume_time
        HAVING
            count(*) > 1
    ) temp
WHERE
    consum_record.user_id = temp.user_id 
    and consum_record.monetary = temp.monetary
    and consum_record.consume_time  = temp.consume_time
AND consum_record.id > temp.id;
```

### 思路:

- 首先就是将user_id,monetary,consume_time这三个字段进行group by,将`count(*)>1`筛选出来

**min(id) id** 每个重复组当中的最小id很关键,删除数据的时候做筛选使用 也可以使用max(id)

```
        SELECT
            min(id) id,#每个重复组当中的最小id很关键,删除数据的时候做筛选使用 也可以使用max(id)
            user_id,
            monetary,
            consume_time
        FROM
            consum_record
        GROUP BY
            user_id,
            monetary,
            consume_time
        HAVING
            count(*) > 1
```

- 接下来使用基础表,和查出来的重复数据做关联(根据三个重复字段做关联), 然后根据每组重复数据`consumer_record.id> temp.id` 删除相应数据


### 启发


