package allen.interview.jvm;

/**
 * @author AllenWong
 *
 * 测试虚拟机栈会出现的 Stackoverflow error
 * VM Option设置为  -Xss128k
 * @date 2020/5/15 10:52 PM
 */
public class JavaVMStackSOF {
    private int len=1;

    public void justRun(){
        len++;
        justRun();
    }

    public static void main(String[] args) {
        JavaVMStackSOF stackSOF=new JavaVMStackSOF();
        try {
            stackSOF.justRun();
        }catch (Throwable throwable){
            System.out.println(stackSOF.len);
            throw   throwable;
        }

    }

}
