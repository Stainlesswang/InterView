package allen.interview.thread;

public class TemplateMethod {
	private void print(String message){
		System.out.println("############");
		wrapPrint(message);
		System.out.println("############");
	}

	protected void wrapPrint(String message){

	}
	public static void main(String[] args){
		TemplateMethod templateMethod=new TemplateMethod(){
			protected void  wrapPrint(String message){
				System.out.println("****"+message+"*****");
			}
		};
		templateMethod.print("模板方法测试方法");
	}
}
