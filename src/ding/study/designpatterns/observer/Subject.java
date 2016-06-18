package ding.study.designpatterns.observer;
import java.util.ArrayList;
import java.util.List;
/**
 * 发布者抽象类
 * @author daniel
 *
 */
abstract class Subject {
      //订阅者列表
 private List<Observer> observers=new ArrayList<Observer>();
 /**
  * 添加订阅者
  * @param observer
  */
 public void addObserver(Observer observer){
  observers.add(observer);
 }
 /**
  * 删除订阅者
  * @param observer
  */
 public void deleteObserver(Observer observer){
  observers.remove(observer);
 }
 /**
  * 发布给所有订阅者
  */
 public void Notify(){
  for(Observer observer : observers){
   observer.update();
  }  
 } 
}