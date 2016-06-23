package ding.study.designpatterns.observer;

import java.util.Observable;
/**
 * 白银价格类
 * @author daniel
 *
 */
public class AgPrice extends Observable {
  
   private double price;
   public AgPrice(double price){
    this.price=price;    
   }
   
   public void setPrice(double price){
       super.setChanged();
        super.notifyObservers(price);
    this.price=price;
   }   
   
   public String showPrice(){    
    return "白银价格为:"+this.price;
   }
}