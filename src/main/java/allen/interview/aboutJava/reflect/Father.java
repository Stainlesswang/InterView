package allen.interview.aboutJava.reflect;

public class Father implements Person{
    private String house;
    private long money;
    public String mFatherName;
    public int mFatherAge;

    public void printFatherMsg(){}
    @Override
    public void live() {
        System.out.println("Father live");
    }

    public void out(){
        System.out.println("Father");
    }
    public final void take(){
        System.out.println("take money");
    }
}
