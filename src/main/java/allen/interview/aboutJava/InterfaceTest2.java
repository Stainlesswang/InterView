package allen.interview.aboutJava;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月14日 17:16
 */
public interface InterfaceTest2 {
    //用来测试两个接口都有default方法的会话,实现类就无法使用该test方法,实现类必须重写该方法才可以通过编译
	default void test() {
		System.out.println("interface default method");
	}
}
