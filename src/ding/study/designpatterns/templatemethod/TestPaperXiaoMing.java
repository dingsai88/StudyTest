package ding.study.designpatterns.templatemethod;

/**
 * 小名的卷子答卷
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-1 上午10:17:02
 */
public class TestPaperXiaoMing extends TestPaper {
	/**
	 * 重写父类方法
	 */
	public String getAnswer1() {
		return "a";
	}

	public String getAnswer2() {
		return "b";
	}
}
