package allen.interview.designPatterns.singleton;

/**
 * @author WangJianQiang
 * @Description:
 * 单元素的枚举类可以完美的实现一个单例(但是具体情况具体分析,枚举类在Android中不推荐使用,因为会降低速度)
 * 不仅可以保证单个实例,还可以解决序列化与反序列化生成不同的对象
 * @date 2019年03月27日 18:11
 */
public enum  SingleTonEnum {
	INSTANCE
}
