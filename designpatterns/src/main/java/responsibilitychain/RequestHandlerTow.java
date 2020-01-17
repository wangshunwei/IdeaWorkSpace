package responsibilitychain;

public class RequestHandlerTow extends Handler{

	@Override
	public void handleRequest(int request) {
		if (request >= 11 && request < 20) {
			// 处理 11-20的请求
			System.out.println("处理 11-20的请求");
		} else if (successor != null) {
			successor.handleRequest(request);
		}
	}
}
