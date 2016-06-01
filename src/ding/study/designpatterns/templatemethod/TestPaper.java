package ding.study.designpatterns.templatemethod;

/**
 * 问卷模版 和答题模板
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-1 上午10:15:08
 */
public abstract class TestPaper {

	/**
	 * 题目一
	 * 
	 * @author daniel
	 * @time 2016-6-1 上午10:15:25
	 */
	public void testQuestion1() {
		System.out.println("题目一");
		System.out.println("答案：" + getAnswer1());
	}

	/**
	 * 答案一
	 * 
	 * @author daniel
	 * @time 2016-6-1 上午10:15:31
	 * @return
	 */
	protected String getAnswer1() {

		return "";
	}

	/**
	 * 题目二
	 * 
	 * @author daniel
	 * @time 2016-6-1 上午10:15:44
	 */
	public void testQuestion2() {
		System.out.println("题目二");
		System.out.println("答案：" + getAnswer2());
	}

	/**
	 * 答案二
	 * 
	 * @author daniel
	 * @time 2016-6-1 上午10:15:51
	 * @return
	 */
	protected String getAnswer2() {
		return "";
	}
}
