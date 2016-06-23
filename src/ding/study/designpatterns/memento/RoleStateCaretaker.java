package ding.study.designpatterns.memento;
/**
 * 游戏属性状态管理类
 * @author daniel
 *
 */
public class RoleStateCaretaker {
	 //游戏属性状态
	 private RoleStateMemento memento;

	 /**
	  * @return the memento
	  */
	 public RoleStateMemento getMemento() {
	  return memento;
	 }

	 /**
	  * @param memento the memento to set
	  */
	 public void setMemento(RoleStateMemento memento) {
	  this.memento = memento;
	 }
}
