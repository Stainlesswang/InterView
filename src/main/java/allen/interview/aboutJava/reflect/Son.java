package allen.interview.aboutJava.reflect;

public class Son extends Father{
    private static String Name="SON";
    private String gun;

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    static void learn(){
        System.out.println(Name + "learn something");
    }
}
