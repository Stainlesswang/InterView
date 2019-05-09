package allen.interview.designPatterns.singleton;

/**
 * @author WangJianQiang
 * @Description:
 * 饿汉模式，没有延迟加载，立马创建一个对象返回
 * 该方法最简单，但是效果不太好. 我们希望用到的时候才初始化该类
 * @date 2019年03月26日 15:45
 */
public class SingleTon {
    private static SingleTon singleTon = new SingleTon();


	private SingleTon() {
	}

	public static SingleTon getInstance() {
		return singleTon;
	}
}
