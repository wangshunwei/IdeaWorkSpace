package responsibilitychain;

public class Test {
	
	public static void main(String[] args) {
		
		Handler one = new RequestHandlerOne();
		Handler tow = new RequestHandlerTow();
		Handler three = new RequestHandlerThree();
		
		one.setSuccessor(tow);
		tow.setSuccessor(three);
		one.handleRequest(20);
	}
	

}
