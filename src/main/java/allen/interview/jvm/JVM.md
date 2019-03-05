# JVM Question 
1.  理解JAVA存储模型(JMM)的Happens-Before规则  
    JVM中存在一种现象

2.  **java类执行顺序**
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
      >继承的情况： 
      >父类--静态变量 >
      >父类--静态初始化块 >
      >子类--静态变量>
      >子类--静态初始化块 >
      >子类main方法 >
      >父类--变量 >
      >父类--初始化块 >
      >父类--构造器>
      >i=9, j=0 >
      >子类--变量 >
      >子类--初始化块 >
      >子类--构造器 >
      >i=9,j=20
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
    
