package ding.study.designpatterns.state;

/**
 * 中午的状态
 * 
 * @author daniel
 * @version 正式版
 */
public class NoonState extends State {
	@Override
	public void writeProgram(Work w) {
		if (w.getHour() < 13) {
			System.out.println("当前时间" + w.getHour() + "点,饿了：犯困。");
		} else {
			w.setCurrent(new AfternoonState());
			w.writeProgram();
		}
	}

}