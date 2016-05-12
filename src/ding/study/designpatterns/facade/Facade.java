package ding.study.designpatterns.facade;
/**
 * 外观类
 * @author daniel
 *
 */
public class Facade {
 private SystemOne one;
 private SystemTwo two;
 private SystemThree three;
 private SystemFour four;
 /**
  * 构造函数初始化属性
  */
 public Facade(){
  one =new SystemOne();
  two =new SystemTwo();
  three=new SystemThree();
  four=new SystemFour();
 }

 /**
  * 1 2 4类的方法调用
  */
 public void methodA(){
  one.methodOne();
  two.methodTwo();
  four.methodFour();
 }
 /**
  * 123类的方法调用
  */
 public void methodB(){
  one.methodOne();
  two.methodTwo();
  three.methodThree();
 }
}