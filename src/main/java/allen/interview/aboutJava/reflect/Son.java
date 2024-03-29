package allen.interview.aboutJava.reflect;

/**
 * @author wangjianqiang
 */
public class Son extends Father {
    static {
        System.out.println("this is Son static block method");
    }

    private String sonName;


    private int sonAge;

    @Override
    public void out() {
        System.out.println("Son Out method");
    }

    public void out(String place) {
        System.out.println("Son Out method" + place);
    }

    @Override
    public void printSonMsg() {
        System.out.println("Son Msg - name : "
                + sonName + "; age : " + sonAge);
    }

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public int getSonAge() {
        return sonAge;
    }

    public void setSonAge(int sonAge) {
        this.sonAge = sonAge;
    }

    @Override
    public String toString() {
        return super.toString() + "Son{" +
                "sonName='" + sonName + '\'' +
                ", sonAge=" + sonAge +
                '}';
    }
}
