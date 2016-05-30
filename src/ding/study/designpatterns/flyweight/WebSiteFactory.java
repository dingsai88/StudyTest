package ding.study.designpatterns.flyweight;

import java.util.Hashtable;

/**
 * 网站工厂
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 上午10:52:25
 */
public class WebSiteFactory {
	private Hashtable<String, WebSite> flyweights = new Hashtable<String, WebSite>();

	/**
	 * 获得网站分类
	 * 这里可以有多个网站实现类
	 * @param key
	 * @return
	 */
	public WebSite getWebSiteCategory(String key) {
		if (!flyweights.containsKey(key)) {
			flyweights.put(key, new WebSiteImpl(key));
		}
		return (WebSite) flyweights.get(key);
	}

	/**
	 * 获得网站分类总数
	 * 
	 * @return
	 */
	public int getWebSiteCount() {
		return flyweights.size();
	}
}
