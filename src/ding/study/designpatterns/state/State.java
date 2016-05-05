package ding.study.designpatterns.state;
/**
 * 抽象状态类
 * @author daniel
 * @version 正式版
 */
public abstract class State {
 /**
  * 显示当前状态
  * @param w
  */
 public abstract void writeProgram(Work w);
}