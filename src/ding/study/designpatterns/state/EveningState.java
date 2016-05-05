package ding.study.designpatterns.state;
/**
 * 傍晚的状态
 * @author daniel
 * @version 正式版
 */
public class EveningState extends State {

 @Override
 public void writeProgram(Work w) {
       // 判断工作是否完成
  if(w.isFinish()){
   w.setCurrent(new RestState());
   w.writeProgram();
  }else{
   if(w.getHour()<21){
    System.out.println("当前时间"+w.getHour()+"点,加班哦 疲惫至极。");
   }else{
    w.setCurrent(new SleepingState());
    w.writeProgram();
   }
   
  }
 }

}