package allen.interview.aboutJava;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月14日 17:17
 */
public class ImplementInterface implements InterfaceTest, InterfaceTest2 {


    @Override
    public void test() {
        System.out.println("Implements test method running!");
    }

    @Override
	public void interMethod() {
        test();
	}

    public static void main(String[] args) {
        ImplementInterface implementInterface =new ImplementInterface();
        implementInterface.interMethod();
        InterfaceTest.tre();
    }
}
