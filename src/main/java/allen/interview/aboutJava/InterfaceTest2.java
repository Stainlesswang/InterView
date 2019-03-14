package allen.interview.aboutJava;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月14日 17:16
 */
public interface InterfaceTest2 {
	default void test() {
		System.out.println("interface default method");
	}
}
