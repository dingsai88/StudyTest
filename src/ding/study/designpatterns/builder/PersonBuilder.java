package ding.study.designpatterns.builder;
/**
 * 造人的接口
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 上午10:23:01
 */
public interface PersonBuilder {

	void buildHead();

	void buildBody();

	void buildFoot();

	Person buildPerson();
}