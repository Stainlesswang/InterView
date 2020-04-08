package allen.interview.aboutJava;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月14日 16:52
 */
public interface InterfaceTest {
	//变量默认是  static final的
	int a = 9;
	int b = 9;

	//只能由接口名称来调用static方法
	static void tre() {
        System.out.println("interface static method running");
    }

	//必须是实现类来使用default方法，并且当多继承的时候继承了两个含有相同default的话实现类必须重写该方法
	default void test() {
		System.out.println("interface default method");
	}

	void interMethod();
}
