package allen.interview.aboutJava.reflect;

public class Son extends Father{
    private String mSonName;
    protected int mSonAge;
    public String mSonBirthday;
    static {
        System.out.println("this is static block method");
    }

    public void printSonMsg(){
        System.out.println("Son Msg - name : "
                + mSonName + "; age : " + mSonAge);
    }

    @Override
    public void out() {
        System.out.println("Son");
    }

    private void setSonName(String name){
        mSonName = name;
    }

    private void setSonAge(int age){
        mSonAge = age;
    }

    private int getSonAge(){
        return mSonAge;
    }

    private String getSonName(){
        return mSonName;
    }

}
