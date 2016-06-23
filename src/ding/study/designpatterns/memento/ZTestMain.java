package ding.study.designpatterns.memento;
/**
 * 备忘录模式（memento）：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态。
 * 
 * 输出
 * 
 * 战神开始
奎爷生命100
奎爷攻击10
奎爷防御10
奎爷挂了
奎爷生命0
奎爷攻击10
奎爷防御10
战神恢复
奎爷生命100
奎爷攻击10
奎爷防御10

 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午3:40:06
 */
public class ZTestMain {

	/**
	 * @author daniel
	 * @time 2016-6-18 下午3:39:21
	 * @param args
	 */
	public static void main(String[] args) { // TODO Auto-generated method stub
		  KuiYe zhanshen=new KuiYe();
		  System.out.println("战神开始");
		  zhanshen.showKuiYe();
		  //进度管理类
		  RoleStateCaretaker log=new RoleStateCaretaker();
		  //保存战神状态
		  log.setMemento(zhanshen.saveState());
		        //战神死了
		  System.out.println("奎爷挂了");
		  zhanshen.setVit(0);
		  zhanshen.showKuiYe();
		  System.out.println("战神恢复");
		  //恢复战神
		  zhanshen.recoveryState(log.getMemento());
		  zhanshen.showKuiYe();
		  }

}
