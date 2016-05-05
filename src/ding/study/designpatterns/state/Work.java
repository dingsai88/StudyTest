package ding.study.designpatterns.state;
/**
 *  工作状态类
 * @author daniel
 * @version 正式版
 */
public class Work {
 private State current;//状态
 private double hour;//时间
 private boolean finish=false;//是否完成
 /**
  * 初始化上午
  */
 public Work(){
  current=new ForenoonState();
 }
 /**
  * 输出当前员工状态
  */
 public void writeProgram(){
  current.writeProgram( this);
 }
 
 /**
  * daniel_自动生成的get方法
  * 
  * @return the current
  */
 public State getCurrent() {
  return current;
 }
 /**
  * daniel_自动生成的set方法
  *  
  * @param current the current to set
  */
 public void setCurrent(State current) {
  this.current = current;
 }
 /**
  * daniel_自动生成的get方法
  * 
  * @return the hour
  */
 public double getHour() {
  return hour;
 }
 /**
  * daniel_自动生成的set方法
  *  
  * @param hour the hour to set
  */
 public void setHour(double hour) {
  this.hour = hour;
 }
 /**
  * daniel_自动生成的get方法
  * 
  * @return the finish
  */
 public boolean isFinish() {
  return finish;
 }
 /**
  * daniel_自动生成的set方法
  *  
  * @param finish the finish to set
  */
 public void setFinish(boolean finish) {
  this.finish = finish;
 }
}