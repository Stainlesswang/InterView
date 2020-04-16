# Redis设计与实现

1. redis的基本类型有哪些
    
    - 字符串对象
    - 列表对象(list Object)
    - 哈希对象(hash Object)
    - 集合对象(set Object)
    - 有序集合对象(sorted set Object)
    
2. redis中的对象

  redis上边的基本类型都是由redis封装实现的各种对象,各种对象在底层的实现会根据类型的不同
  选择相对合适的高效的数据类型
  
-    字符串对象(三种数据类型)
       
       当保存为整数的时候 `object encoding key` 类型为 int 
       
       当保存的非整数的且<=32字节的时候 `object encoding key` 类型为embstr 
       
       当>32字节的字符串的时候 编码类型为 raw(普通的redis封装的动态字符串)
        