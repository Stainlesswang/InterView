package allen.interview.aboutJava.reflect;

/**
 * 反射测试类-父类
 */
public class Father implements Person{
    static {
        System.out.println("this is Father static block method");
    }
    /**
     * house地址
     */
    private String house;
    /**
     * 钱
     */
    private long money;
    /**
     * 父亲的姓名和年龄
     */
    private String fatherName;
    private int FatherAge;

    @Override
    public void live() {
        System.out.println("Father live");
    }

    public void out(){
        System.out.println("Father Out Method");
    }

    public final void take(){
        System.out.println("take money");
    }

    public void printSonMsg(){
        System.out.println("Father Msg - name : "
                + fatherName + "; age : " + FatherAge);
    }

    @Override
    public String toString() {
        return "Father{" +
                "house='" + house + '\'' +
                ", money=" + money +
                ", fatherName='" + fatherName + '\'' +
                ", FatherAge=" + FatherAge +
                '}';
    }
}
