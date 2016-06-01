package ding.study.designpatterns.templatemethod;
/**
 * 晓红的答卷
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-1 上午10:17:37
 */
public class TestPaperXiaoHong  extends TestPaper {
	/**
	 * 重写父类方法
	 */
	public String getAnswer1() {
		return "c";
	}

	public String getAnswer2() {
		return "d";
	}
}
