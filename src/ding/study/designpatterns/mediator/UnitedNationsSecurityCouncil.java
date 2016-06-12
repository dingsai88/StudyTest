package ding.study.designpatterns.mediator;
/**
 * 安理会
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-12 上午10:07:14
 */
public class UnitedNationsSecurityCouncil extends UnitedNations {
	// 美国
	private USA colleague1;
	// 伊拉克
	private Iraq colleague2;

	/**
	 * @param colleague1
	 *            the colleague1 to set
	 */
	public void setColleague1(USA colleague1) {
		this.colleague1 = colleague1;
	}

	/**
	 * @param colleague2
	 *            the colleague2 to set
	 */
	public void setColleague2(Iraq colleague2) {
		this.colleague2 = colleague2;
	}

	@Override
	public void declare(String message, Country colleague) {
 		if (colleague == colleague1) {
			colleague2.getMessage(message);
		} else {
			colleague1.getMessage(message);
		}

	}

}
