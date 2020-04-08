# Java Interview Question 

### 1.为什么String 要设计为final 不可变 
String的源代码：

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
       private final char value[];  
}
```
final修饰的 value[] 数组是final的只能说明它是地址不可变，并不代表该数组内容不可变。另一个关键的修饰符是**private**限定了value数组的访问限制。
       
   - String 不可变保证了常量池的使用
   - String 不可变才可以利用它作为不可变的key
   - 线程安全的

### 2. Java异常
   - Throwable子类分为  Exception（异常）And Error（错误）囊括了java中会遇到的各种情况
   - Exception一般是程序出现的错误，是修改我们的代码可以修改的情况，但是Error就是一些我们处理不了的情况，例如：内存溢出异常或者虚拟机异常我们无法处理
   - Exception运行时异常(RuntimeException)是指可以不强制进行try-catch就可以编译通过。例如常见的空指针异常，数组越界异常等
   - 非运行时异常，或者叫编译异常 从程序语法角度讲是必须进行处  理的异常，如果不处理，
   程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。
   

### 3. Java反射
1. 反射的获取Class对象方式有三种：      
  
  ```
  //第一种方式 通过Class类的静态方法——forName()来实现 必须使用全限定名
  class1 = Class.forName("com.lvr.reflection.Person");
  //第二种方式 通过类的class属性
  class1 = Person.class;
   //第三种方式 通过对象getClass方法 调用某个对象的getClass()方法。该方法是java.lang.Object类中的一个方法
   Person person = new Person();
   Class<?> class1 = person.getClass();
  ```
   
   
2. **Class.forName()和ClassLoader获取类的区别**:

  只要记住了web项目在加载Mysql驱动的时候使用的是Class.forName("com.mysql.Driver"),
  因为需要执行Driver类中的静态代码块来 *DriverManager.registerDriver(new Driver());*
  注册驱动.[blog about this](http://www.importnew.com/29389.html)
  
  ```java
  public class ReflectTest{ 
    
    public static void main(String[] args){ 
        ClassLoader loader=ClassLoader.getSystemClassLoader();
	
        //loadClass只是把类的二进制流加载到JVM中，不进行初始化
        Class n=loader.loadClass("allen.interview.jvm.InitialOrderTest");
	    
        //forName方法会初始化static变量和static块代码
	    Class n2=Class.forName("allen.interview.jvm.InitialOrderTest");
    }
}
  ```
  


### 4.接口和抽象类的区别

1. 抽象类概念：含有抽象方法的类必须声明为抽象类抽象类就是半成品的类，不能实例化只能被继承
      - 抽象类不能被实例化
      - 抽象类的抽象方法必须是 非private

2. 接口：更加抽象的行为抽象
      - 变量默认都是 static final 类型的
      - 方法必须都是public的
      - Java8之后增强了接口：可以定义default方法（只能通过实现类使用并且可以重载）
        和static方法（只能通过接口使用）


### 5. Java基础类型及大小

|类型|大小|
|:-----:|:-----:|
|byte|1字节|
|char|2字节|
|short|2字节|
|int|4字节|
|long|8字节|
|float|4字节|
|double|8字节|
|boolean|JVM规范中，boolean变量作为int处理，也就是4字节；boolean数组当做byte数组处理|

### 6. Java中为啥只存在值传递???

   > 总结:**Java中只存在值传递** 传进去的参数都是copy
   > > 这里注意了注意了,一直一来我们都有一个误区--->**java中存在引用传递**但是这是不存在的兄弟
   1. 首先如果方法里边传的是基本类型数据,很好理解,假如int
      a=3,现在有方法要使用a这个参数,就是另外一个3数值了 原来的的a不会改变  
     
   2. 参数是数组的时候,作为参数是将该数组拷贝了一个引用(但是指向的是内存中同一个位置,所以修改这个拷贝
      引用的时候,直接修改的是物理上的存储数组.此时另外一个数组的引用自然也被改变了)
     
   3. 一个方法不能让对象参数引用一个新的对象(怎么折腾,传到方法里的这个引用只是拷贝.外部的原来的引用一直
      是不变的.但是对象的属性是可以被引用的拷贝修改的.就像方法2中的一样)
      
      
### 7. Java内存溢出和内存泄漏

  - **内存泄漏(Memory Leak):**是指程序动态分配内存给一些临时变量,但是它始终不能够被GC,即*该对象可达但已经无用*
  - **内存溢出(Out Of Memory):**是指程序在申请内存的时候,没有足够的空间来提供,通常发生在老年代和永久代在进行垃圾回收以后,仍然没有足够的空间就会出现OOM

  所以综上所述的概念我们总结出来: **内存泄漏时造成内存泄漏的一种诱因,但不是唯一的因素**
  
  导致OOM错误出现后需要排查的地点:
  
  1. 检查代码中是否有死循环或递归调用。

  2. 检查是否有大循环重复产生新对象实体。
  3. 检查对数据库查询中，是否有一次获得全部数据的查询。一般来说，如果一次取十万条记录到内存，就可能引起内存溢出。这个问题比较隐蔽，在上线前，数据库中数据较少，不容易出问题，上线后，数据库中数据多了，一次查询就有可能引起内存溢出。因此对于数据库查询尽量采用分页的方式查询。

  4. 检查List、MAP等集合对象是否有使用完后，未清除的问题。List、MAP等集合对象会始终存有对对象的引用，使得这些对象不能被GC回收。

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
