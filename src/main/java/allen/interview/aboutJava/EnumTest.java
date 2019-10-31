package allen.interview.aboutJava;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月27日 16:05
 */
public enum  EnumTest {
	/**
	 *
	 */
	INSTANCE("cao");


	private String name;
	EnumTest(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void print(){
		System.out.println("print");
	}

	public static void main(String[] args) {
		EnumTest.INSTANCE.print();
		System.out.println(EnumTest.INSTANCE ==EnumTest.INSTANCE);
	}
}
