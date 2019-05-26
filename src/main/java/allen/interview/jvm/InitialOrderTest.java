package allen.interview.jvm;

/**
 * @author WangJianQiang
 * @Description:

 * @date 2019年03月05日 13:47
 */
public class InitialOrderTest {
	public  boolean top;
	/* 静态变量 */
	private static String staticField = "静态变量";
	/* 变量 */
    private String field = "变量";
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
		InitialOrderTest initialOrderTest=new InitialOrderTest();
	}
}
