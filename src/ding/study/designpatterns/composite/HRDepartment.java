package ding.study.designpatterns.composite;

/**
 * HR部门类
 * @author daniel
 *
 */
public class HRDepartment extends Company {
 public HRDepartment (String name){
  super(name);
 }

 @Override
 public void add(Company c) {
  // TODO Auto-generated method stub

 }

 @Override
 public void display(int i) {
  StringBuffer sb=new StringBuffer();
  for(int j=0;j<i;j++){
   sb.append("-");
  }
  System.out.println(sb.toString()+this.name);
 }

 @Override
 public void lineOfDuty() {
  // TODO Auto-generated method stub
  System.out.println(this.name+"   员工管理培训");
 }

 @Override
 public void remove(Company c) {
  // TODO Auto-generated method stub

 }

}