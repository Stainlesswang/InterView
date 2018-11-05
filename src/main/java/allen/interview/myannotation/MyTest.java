package allen.interview.myannotation;

import java.lang.reflect.Method;

@MyAnnotation(name = "yoga", age = 12)
public class MyTest {
	@MyAnnotation(name = "allen", age = 10)
	public void getmyname() {

	}


	//下边展示使用自定义注解后 一般通过反射来进行逻辑的一些操作。可以得到类上边的注解，也可以得到方法上边的注解信息
	//我们可以通过注解给编译器或者jvm 传递任我们想要的行为
	public static void main(String[] args) throws ClassNotFoundException {

		Class c = Class.forName("allen.interview.myannotation.MyTest");
		if (c.isAnnotationPresent(MyAnnotation.class)) {
			MyAnnotation w = (MyAnnotation) c.getAnnotation(MyAnnotation.class);
			System.out.println("name:" + w.name() + "   age:" + w.age());
		}


		Method[] methods = c.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(MyAnnotation.class)) {
				MyAnnotation w = method.getAnnotation(MyAnnotation.class);
				System.out.println("name:" + w.name() + "   age:" + w.age());
			}
		}
	}
}
