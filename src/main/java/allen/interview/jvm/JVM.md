# JVM Question 
### 1.进程和线程的区别
- 进程是什么?

  进程就是计算机开启了一个应用程序,是一次应用程序执行过程.典型例子就是windows下的任务管理器看到的每一个就是一个进程.是计算机执行的基本单位,一个进程包含多个线程
- 线程是撒? 
  
  线程是和进程类似的东西,但是线程更加的轻量级,是更小的执行单位. java中线程共享**堆**和**方法区**,每个线程有自己独自的**程序计数器,虚拟机栈,本地方法栈**
  
  一张图表明进程和线程的关系:![进程线程区别](https://diycode.b0.upaiyun.com/photo/2019/ff96fed0e2a354bb16bbc84dcedf503a.png)
  
  线程共享的区域是:
	- **堆:** Heap,GC的主要区域,所有线程共享,保存java对象和数组.
	- **方法区:**主要用于存储类的信息、常量池、方法数据、方法代码等,逻辑上属于堆的组成 (java8后移除了方法区,转而使用元空间Metaspace代替)
  
  
  线程私有的部分:
  
   - 虚拟机栈(VM Stack):每个线程有一个私有的栈，随着线程的创建而创建。栈里面存着的是一种叫“栈帧”的东西，每个方法会创建一个栈帧，栈帧中存放了局部变量表（基本数据类型和对象引用）、操作数栈、方法出口等信息。
   - 本地方法栈 (Native Method Stack):虚拟机用到的 Native 方法相关
   - 程序计数器(Program Counter Register):PC 是指向指令执行到哪里的关键记录员 **主要任务:线程切换后能恢复到正确的执行位置**



### 2.理解JAVA存储模型(JMM)以及相关的Happens-Before规则




### 3.JVM类加载机制

  - (静态变量 静态代码块) > (变量 代码块) > (构造函数)
  
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

    

