# Redis设计与实现

redis 涉及到的关键数据结构

- SDS 简单动态字符串,

int len; int free, char buf[];

1). 常数复杂度获取字符串长度,c语言需要遍历字符计数
2). 杜绝缓冲区溢出,不需要手动扩容,sds帮着操作了
3). 减少修改字符串长度时所需的内存分配次数, 预分配策略,小于1M 扩展后多长,再double一下,大于1M,扩展后再多分配1M free空间
4). 二进制安全,存什么取什么,不会像C一样,遇到空字符就算字符结束了
5). 兼容部分c字符串函数,因为尾部保留了\0结束符


1. redis的基本类型有哪些
    
    - 字符串对象 type=string object encoding= int | embstr | raw
    - 列表对象(list Object) type=list  object encoding= ziplist 或者 linkedlist
    - 哈希对象(hash Object) 
    - 集合对象(set Object)
    - 有序集合对象(sorted set Object)
    
2. redis中的对象

  redis上边的基本类型都是由redis封装实现的各种对象,各种对象在底层的实现会根据类型的不同
  选择相对合适的高效的数据类型
  
 对象类型           编码方式
 1. OBJ_STRING       
 
     OBJ_ENCODING_RAW
     
     OBJ_ENCODING_INT
     
     OBJ_ENCODING_EMBSTR
2. OBJ_LIST          
    OBJ_ENCODING_LINKEDLIST
    
    OBJ_ENCODING_ZIPLIST
                  
    OBJ_ENCODING_QUICKLIST
3. OBJ_SET

     OBJ_ENCODING_INTSET
     
     OBJ_ENCODING_HT          
4. OBJ_ZSET  
    OBJ_ENCODING_ZIPLIST 
           
    OBJ_ENCODING_SKIPLIST
5. OBJ_HASH 

    OBJ_ENCODING_ZIPLIST         
    
    OBJ_ENCODING_HT               

d
  
-    字符串对象(三种数据类型)
       
       当保存为整数的时候 `object encoding key` 类型为 int 
       
       当保存的非整数的且<=32字节的时候 `object encoding key` 类型为embstr 
       
       当>32字节的字符串的时候 编码类型为 raw(普通的redis封装的动态字符串)
        