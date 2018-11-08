package allen.interview.java8Learn;

import java.util.Arrays;
import java.util.List;

/**
 * @author WangJianQiang
 * @Description:java8中的新特性 Stream
 * @date 2018年11月08日 14:33
 */
public class StreamTest {
	public static void main(String[] args) {
		List<String> myList =
				Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList
				.stream()
				.filter(a -> a.startsWith("a"))
				.map(String::toUpperCase)
				.sorted()
				.forEach(System.out::println);
	}
}
