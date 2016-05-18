package ding.study.designpatterns.decorator;
 
/**
 * 意图： 
动态地给一个对象添加一些额外的职责。就增加功能来说，Decorator 模式相比生成子类更为灵活。 
适用性： 


在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。 


处理那些可以撤消的职责。 


当不能采用生成子类的方法进行扩充时。一种情况是，可能有大量独立的扩展，为支持每一种组合将产生大量的子类，使得子类数目呈爆炸性增长。另一种情况可能是因为类定义被隐藏，或类定义不能用于生成子类。 


输出:
穿T恤
 Finery:show
穿毛裤
 Finery:show
穿滑板鞋
 Finery:show
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-18 上午10:24:31
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-18 上午9:48:25
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
       //鞋
		ThuaBanXie huabanxie = new ThuaBanXie();
		//毛裤
		TmaoKu maoku = new TmaoKu();
		//上衣
		TShirts tshirt = new TShirts();		
		
		
		
		//滑板鞋被装入
		maoku.decorate(huabanxie);
		//毛裤被装入
		tshirt.decorate(maoku);
		//T恤被装入
		tshirt.show();
		 
	 
	}
}
