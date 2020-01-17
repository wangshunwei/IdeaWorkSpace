package responsibilitychain;

public class RequestHandlerThree extends Handler {

	@Override
	public void handleRequest(int request) {
		if (request >= 20 && request < 30) {
			// 处理 20-30的请求
			System.out.println("处理 20-30的请求");
		} else if (successor != null) {
			successor.handleRequest(request);
		}
	}
}
