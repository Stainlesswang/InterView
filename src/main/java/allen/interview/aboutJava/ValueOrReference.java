package allen.interview.aboutJava;


import java.util.Arrays;

/**
 * @author WangJianQiang
 * @Description:
 * 关于java值传递或者引用传递的理解
 * @date 2019年04月12日 15:34
 */
public class ValueOrReference {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student("小张");
		Student s2 = new Student("小李");
		//这个时候传递的不是s1和s2的引用,而是引用的拷贝.这里要注意了哇
		Student s3=ValueOrReference.swap(s1, s2);
		System.out.println("s1:" + s1.getName());
		System.out.println("s2:" + s2.getName());
		System.out.println(s3.getName());
	}

	private static Student swap(Student x, Student y) {
		Student temp = x;
		x = y;
		y = temp;
		System.out.println("x:" + x.getName());
		System.out.println("y:" + y.getName());
		return x;
	}

	private static class Student {
		private String name;
		Student(String name){
			this.name=name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
