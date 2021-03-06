package allen.interview.java8Learn;

import java.util.Random;

/**
 *
 * 函数式编程：可以将方法当做数据来传递，更加灵活方便
 */
public class FunctionInterfaceDemo {


	//定义了一个接口。此处就是函数接口精髓，参考Runnable和Thread的关系
	interface Predicate<T> {
		boolean test(T t);
	}


	/**
	 * 执行Predicate判断
	 *
	 * @param age       年龄
	 * @param predicate Predicate函数式接口
	 * @return 返回布尔类型结果
	 */
	private static boolean doPredicate(int age, Predicate<Integer> predicate) {
		System.out.println(age);
		return predicate.test(age);
	}

	//测试方法
	public static void main(String[] args) {
		int randomInt = new Random().nextInt(10);
		System.out.println("the current number is :" + randomInt);
		boolean isAdult = doPredicate(randomInt, x -> x > 18);
		System.out.println(isAdult);
	}
}
