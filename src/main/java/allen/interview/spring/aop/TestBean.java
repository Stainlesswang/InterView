package allen.interview.spring.aop;


public class TestBean {
    private String testStr="testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test(int a,String b){
        System.out.println(testStr);
    }
}
