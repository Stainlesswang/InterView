package allen.interview.thread;

public class TemplateMethod {
	//相当于Thread类中的start()方法
	private void print(String message){
		System.out.println("############");
		wrapPrint(message);
		System.out.println("############");
	}

	//相当于Thread类中的run()方法,需要用户去定义方法中的具体实现
	protected void wrapPrint(String message){

	}






	public static void main(String[] args){
		TemplateMethod templateMethod=new TemplateMethod(){
			//此处定义 被包装在里边的wrapPrint
			protected void  wrapPrint(String message){
				System.out.println("****自定义"+message+"*****");
			}
		};
		//执行print 方法的时候里边调用的是 wrapPrint()方法
		templateMethod.print("模板方法测试方法");
	}
}
