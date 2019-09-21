# JVM Question 
1. **进程和线程的区别** 
  - 进程是什么?

  进程就是计算机开启了一个应用程序,是一次应用程序执行过程.典型例子就是windows下的任务管理器看到的每一个就是一个进程.是计算机执行的基本单位,一个进程包含多个线程
 
  - 线程是撒? 
  
  线程是和进程类似的东西,但是线程更加的轻量级,是更小的执行单位. java中线程共享**堆**和**方法区**,每个线程有自己独自的**程序计数器,虚拟机栈,本地方法栈**
  
  一张图表明进程和线程的关系:![进程线程区别](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9teS1ibG9nLXRvLXVzZS5vc3MtY24tYmVpamluZy5hbGl5dW5jcy5jb20vMjAxOS0zL0pWTSVFOCVCRiU5MCVFOCVBMSU4QyVFNiU5NyVCNiVFNiU5NSVCMCVFNiU4RCVBRSVFNSU4QyVCQSVFNSU5RiU5Ri5wbmc?x-oss-process=image/format,png)
  
  线程共享的区域是:
	
   - **堆:** Heap,GC的主要区域,所有线程共享,保存java对象和数组.
   - **方法区:** 主要用于存储类的信息、常量池、方法数据、方法代码等,逻辑上属于堆的组成 (java8后移除了方法区,转而使用元空间Metaspace代替)
  
  
  线程私有的部分:
  
   - **虚拟机栈(VM Stack):** 每个线程有一个私有的栈，随着线程的创建而创建。栈里面存着的是一种叫“栈帧”的东西，每个方法会创建一个栈帧，栈帧中存放了局部变量表（基本数据类型和对象引用）、操作数栈、方法出口等信息。
   - **本地方法栈 (Native Method Stack):** 虚拟机用到的 Native 方法相关
   - **程序计数器(Program Counter Register):**PC 是指向指令执行到哪里的关键记录员 **主要任务:线程切换后能恢复到正确的执行位置**


2. 理解JAVA存储模型(JMM)以及相关的Happens-Before规则

   JMM叫做Java Memery Model,java内存模型,更确切来说它是java虚拟机在针对不同种类的操作系统差异背景下,采用的一种和物理机数据交互的一种规范. 每个线程使用的变量都是JVM主存的一份拷贝,当修改的时候首先修改这份拷贝,然后在同步到JVM的堆中. 这也是为什么多线程在操作同一个资源的时候没有考虑线程的安全情况下数据总是出现奇怪的错误的直接原因.
  
  后边做下好的总结,涉及到点,为什么要这样 为什么这么重要,理解JMM可以解决什么问题,以及如何通过举例子来牢记这个情况


4. JVM类加载机制
   - 加载顺序:(静态变量 静态代码块) > (变量 代码块) > (构造函数)
  
    ```
        public class InitialOrderTest {
            	/* 静态变量 */
            	public static String staticField = "静态变量";
            	/* 变量 */
            	public String field = "变量";
            	/* 静态初始化块 */
            	static {
            		System.out.println( staticField );
            		System.out.println( "静态初始化块" );
            	}
            	/* 初始化块 */
            	{
            		System.out.println( field );
            		System.out.println( "初始化块" );
            	}
            	/* 构造器 */
            	public InitialOrderTest()
            	{
            		System.out.println( "构造器" );
            	}
            
            
            	public static void main( String[] args )
            	{
            		new InitialOrderTest();
            	}
            }
    ```
          
    ```java
    class Parent {
            /* 静态变量 */
        public static String p_StaticField = "父类--静态变量";
             /* 变量 */
        public String    p_Field = "父类--变量";
        protected int    i    = 9;
        protected int    j    = 0;
            /* 静态初始化块 */
        static {
            System.out.println( p_StaticField );
            System.out.println( "父类--静态初始化块" );
        }
            /* 初始化块 */
        {
            System.out.println( p_Field );
            System.out.println( "父类--初始化块" );
        }
            /* 构造器 */
        public Parent()
        {
            System.out.println( "父类--构造器" );
            System.out.println( "i=" + i + ", j=" + j );
            j = 20;
        }
    }
    
    public class SubClass extends Parent {
             /* 静态变量 */
        public static String s_StaticField = "子类--静态变量";
             /* 变量 */
        public String s_Field = "子类--变量";
            /* 静态初始化块 */
        static {
            System.out.println( s_StaticField );
            System.out.println( "子类--静态初始化块" );
        }
           /* 初始化块 */
        {
            System.out.println( s_Field );
            System.out.println( "子类--初始化块" );
        }
           /* 构造器 */
        public SubClass()
        {
            System.out.println( "子类--构造器" );
            System.out.println( "i=" + i + ",j=" + j );
        }
    
    
            /* 程序入口 */
        public static void main( String[] args )
        {
            System.out.println( "子类main方法" );
            new SubClass();
        }
    }
    ```
    执行结果:
      - 父类--静态变量 
      - 父类--静态初始化块 
      - 子类--静态变量
      - 子类--静态初始化块 
      -  子类main方法 
      - 父类--变量 
      - 父类--初始化块 
      - 父类--构造器
      - i=9, j=0 
      - 子类--变量
      - 子类--初始化块 
      - 子类--构造器 
      - i=9,j=20

5. **Java创建对象过程**

   1.  类加载检查: 当遇到`new`关键字的时候,首先要去检查该指令的参数是否能够在常量池中定位到该类的符号引用. 如果能够定位到, 还要检查该符号引用对应的类是否已经被加载过,解析过,初始化过,若没有,要执行相应的类加载过程
   2. 分配内存空间: 类加载检查通过后,就可以确定该类占用多少空间,然后就是去堆上划分一个相应大小的空间,按照堆上的空间是否规整 分为两种;

      如果是使用`标记-清除`会导致堆内存十分的散乱, 这时候分配的方式是**空闲三列:**JVM会维护一个列表来映射空闲的内存块,然后选取一个足够大的块来指定; 

      如果是使用`标记-整理`或者`拷贝-复制`的方式,堆中的空间相对规整,这时候分配方式采用**指针碰撞:**在已使用和未使用的分界点有一个指针,指针向未分配的区域移动即可分配出来对应的内存
     
      分配内存需要考虑的并发的问题,因为在同一时间上会有很多对象的创建,那么对于共享的堆内存空间就存在并发的安全性问题,JVM采用的策略是**CAS和失败重试保证操作的原子性**以及**TLAB(Thread Local Allocation Buffer）类似ThreadLocal,堆先给每个线程预先分配一些空间,这样就不存在竞争的问题(由党和国家统一划分,谁也别抢)**
  
   3. 初始化零值:这个过程就是我们的变量赋初始值的过程
   4. 设置对象头:对象头就是虚拟机给该对象的定位,比如属于什么年代的?如何定位该对象的信息?还有哈希码啥的东西,要给你贴个标签,方便随时拖出来五十大板
   5. 调用init方法:经过上边的四个步骤呢,对于虚拟机来讲这个对象就是存在的,但是对象是程序员使用的,所以需要做些初始化的工作,也就是调用init方法吧



6.  JVM中对象的内存布局

对象在HotSpot的JVM中主要分三部分:1.对象头 2.实例数据 3.对齐填充

1. 对象头:第一部分用于存储对象自身的**自身运行时数据**（哈希码、GC分代年龄、锁状态标志等等），另一部分是**类型指针**即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是那个类的实例。


6. 对象的访问方式
---
我们知道当一个对象保存另一个对象的时候,保存的是一个Reference,一个引用,作用是如何找到存放在堆中的**对象类型实例,对象数据实例**

1. 句柄: reference和真实数据之间多了个代理商! 我们需要的时候首先找代理商,然后代理商去找真实数据,这样的好处是,引用的对象变动的时候reference不需要老是变动,只需要代理商去找不同的厂家进货即可满足我们的需求
2. 直接指针:没有中间商赚差价,直接去堆上找,方便直接迅速,但是存在一定的不稳定性,修改对象的时候reference要改变存储的数据
