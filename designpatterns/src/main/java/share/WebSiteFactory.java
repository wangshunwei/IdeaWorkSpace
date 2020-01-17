package share;

import java.util.Hashtable;

/**
 * 网站的工厂类
 * @author user
 *
 */
public class WebSiteFactory {

	private Hashtable<String,ConcreteWebSite> flyweights = new Hashtable<>();
	
	// 获得网站的分类
	public WebSite getWebSiteCategory(String key) {
		if (!flyweights.containsKey(key)) {
			flyweights.put(key, new ConcreteWebSite(key));
		}
		return flyweights.get(key);
	}
	
	// 获得网站分类的总数
	public int getWebSiteCount() {
		return flyweights.size();
	}
	
}
