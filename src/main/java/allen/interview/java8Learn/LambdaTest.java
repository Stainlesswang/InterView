package allen.interview.java8Learn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 格式： （参数 -> 函数主题）
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月08日 14:10
 */
public class LambdaTest {


	public static void main(String[] args) {
		List<String> names = Arrays.asList("peter", "anna", "mike", "center");
		//原来比较一个list使用的方法
		names.sort(Comparator.reverseOrder());
		System.out.println(names);
		//使用lambda表达式
//		names.sort(names,(String a,String b)->{return b.compareTo(a);});
	}
}
