package share;

public class Test {
	
	public static void main(String[] args) {
		
		
		WebSiteFactory f = new WebSiteFactory();
		WebSite fx = f.getWebSiteCategory("产品展示");
		fx.Use(new User("小菜"));
		
		
		
		
	}

}
