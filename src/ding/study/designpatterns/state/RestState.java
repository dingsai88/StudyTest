package ding.study.designpatterns.state;
/**
 * 休息的状态
 * @author daniel
 * @version 正式版
 */
public class RestState extends State {

 @Override
 public void writeProgram(Work w) {
  // TODO Auto-generated method stub
  System.out.println("当前时间"+w.getHour()+",下班回家。");

 }

}