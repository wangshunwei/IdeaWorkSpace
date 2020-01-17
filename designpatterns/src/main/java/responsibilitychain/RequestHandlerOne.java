package responsibilitychain;

public class RequestHandlerOne extends Handler{

	@Override
	public void handleRequest(int request) {
		if (request >= 0 && request < 20) {
			// 处理 0-10的请求
			System.out.println("处理 0-10的请求");
		} else if (successor != null) {
			successor.handleRequest(request);
		}
	}
}
