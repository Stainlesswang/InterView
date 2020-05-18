package allen.interview.spring.proxy;

/**
 * 实现了对应接口的类
 * 需要对该类中的add()方法做增强操作
 */
public  class  UserServiceImpl implements UserService {
    /**
     * 该例子是为了执行add()方法的时候输出额外的信息
     */
    @Override
    public void add() {
        System.out.println("add-----------");
    }
}
