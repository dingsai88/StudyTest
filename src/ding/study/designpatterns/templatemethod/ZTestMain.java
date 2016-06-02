package ding.study.designpatterns.templatemethod;
/**
模板方法模式：定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
优点

通过吧不变的行为搬移到父类，去掉子类重复代码。
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-1 上午10:18:55
 */
public class ZTestMain {
	/**
	 * @author daniel
	 * @time 2016-6-1 上午10:18:00
	 * @param args
	 */
	public static void main(String[] args) {
		   System.out.println("小名问卷答案:");
		   TestPaper studentA=new TestPaperXiaoMing();
		   studentA.testQuestion1();
		   studentA.testQuestion2();
		   System.out.println("晓红问卷答案:");
		   TestPaper studentB=new TestPaperXiaoHong();
		   studentB.testQuestion1();
		   studentB.testQuestion2();
	}

}
