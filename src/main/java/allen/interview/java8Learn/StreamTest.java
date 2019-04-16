package allen.interview.java8Learn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
				.filter(i->i.startsWith("a"))
				.map(String::toUpperCase)
				.sorted()
				.forEach(System.out::println);
		IntStream.range(1, 4)
				.mapToObj(i -> "a" + i)
				.forEach(System.out::println);
	}
}
