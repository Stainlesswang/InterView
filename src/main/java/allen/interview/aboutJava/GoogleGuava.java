package allen.interview.aboutJava;

import com.google.common.base.Joiner;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.*;
import java.util.*;

/**
 * @author WangJianQiang
 * @Description: Google Guava包使用记录
 * @date 2019年04月26日 16:45
 */
public class GoogleGuava {
//	private static int size = 1000000;

//	private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.0001);

    private  native void start0();
	/**
	 * Optional 可以令模棱两可的null值
	 *
	 * @param
	 * @return void
	 * @author WangJianQiang
	 * @date 2019年04月26日 下午04:51:33
	 */
	public static void OptionalTest() {
		Optional<Integer> optional = Optional.of(5);
		System.out.println(optional.isPresent());
		System.out.println(optional.get());
	}

	public  String  StringUtilTest() {
		Set<Integer> set = new LinkedHashSet<Integer>();
		set.add(1583);
		set.add(1584);
		set.add(1585);
		set.add(1586);
		set.add(1587);
        start0();
		System.out.println(Joiner.on(",").join(set));
		return Joiner.on(",").join(set);
	}

//	public static void main(String[] args) throws IOException {
//		for (int i = 0; i < size; i++) {
//			bloomFilter.put(i);
//		}
//
//		for (int i = 0; i < size; i++) {
//			if (!bloomFilter.mightContain(i)) {
//                System.out.println("有坏人逃脱了");
//			}
//
//
//		}
//
//		List<Integer> list = new ArrayList<Integer>(1000);
//		for (int i = size + 10000; i < size + 20000; i++) {
//			if (bloomFilter.mightContain(i)) {
//				list.add(i);
//			}
//		}
//		System.out.println("有误伤的数量：" + list.size());
//
//
//	}


}
