package allen.interview.java8Learn;

/**
 * java8以后，接口中可以有非抽象的方法了。 这是接口 向抽象类靠近的趋势
 * 下边就以抽象类 和接口做下比较
 * 抽象类可做接口不可做：1.抽象类可以就是类，可任意定义属性。接口只可以定义 static final 的属性 2.接口的方法都是public的，抽象类不是
 * 接口可做，抽象类无法做： 接口可以多继承
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月08日 10:43
 */
public interface Formula {
	String formulaStr = "接口中的成员变量默认是 final static的";

	double calculate(int a);

	//接口中由default修饰的方法，继承（实现）该接口的都可以调用
	default double sqrt(int a) {
		return Math.sqrt(a);
	}

	public static void main(String[] args) {
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return 0;
			}
		};
		System.out.println(Formula.formulaStr);
	}

}
