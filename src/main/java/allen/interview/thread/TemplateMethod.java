package allen.interview.thread;

public class TemplateMethod {
	public final void print(String message){
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
		templateMethod.print("niajsdfljasdf");
	}
}
