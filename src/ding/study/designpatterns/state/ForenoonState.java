package ding.study.designpatterns.state;

/**
 * 上午状态类
 * 
 * @author daniel
 * @version 正式版
 */
public class ForenoonState extends State {
	/**
	 * 写状态
	 */
	@Override
	public void writeProgram(Work w) {
		if (w.getHour() < 12) {
			System.out.println("当前时间" + w.getHour() + "点,上午工作，精神不错");
		} else {
			w.setCurrent(new NoonState());
			w.writeProgram();
		}

	}

}