package allen.interview.thread;

/**
 * @author AllenWong
 * @date 2020/4/9 10:38 PM
 */

public class TemplateMethod {
    //相当于Thread类中的start()方法
    private void print(String message){
        System.out.println("############");
        wrapPrint(message);
        System.out.println("############");
    }

    //相当于Thread类中的run()方法,需要用户去定义方法中的具体实现
    public void wrapPrint(String message){

    }

    public static void main(String[] args){
        TemplateMethod templateMethod=new TemplateMethod(){
            @Override
            public void wrapPrint(String message) {
                System.out.println("****自定义2"+message+"*****");
            }
        };
        //执行print 方法的时候里边调用的是 wrapPrint()方法
        templateMethod.print("模板方法测试方法");
    }
}

