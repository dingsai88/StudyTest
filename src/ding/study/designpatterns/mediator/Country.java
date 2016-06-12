package ding.study.designpatterns.mediator;

/**
 * 国家抽象类
 * 
 * @author daniel
 * 
 */
abstract class Country {
    //联合机构
 protected UnitedNations mediator;

 public Country(UnitedNations mediator) {
  this.mediator = mediator;
 }
}