package ding.study.designpatterns.memento;

/**
 * 奎爷类
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午3:27:20
 */
public class KuiYe {
	// 生命
	private Integer vit;
	// 攻击力
	private Integer atk;
	// 防御力
	private Integer def;

	/**
	 * 人物初始值
	 */
	public KuiYe() {
		this.vit = 100;
		this.atk = 10;
		this.def = 10;
	}

	/**
	 * 展示对象属性
	 * @author daniel
	 * @time 2016-6-18 下午3:35:47
	 */
	public void showKuiYe() {
		System.out.println("奎爷生命" + this.vit);
		System.out.println("奎爷攻击" + this.atk);
		System.out.println("奎爷防御" + this.def);
	}
	
	
	 /**
	  * 保存战神进度
	  * @return
	  */
	 public RoleStateMemento saveState(){
	  
	  return new RoleStateMemento(this.getVit(),this.getAtk(),this.getDef());
	 }
	 /**
	  * 恢复奎爷状态
	  * @param memento
	  */
	 public void recoveryState(RoleStateMemento memento){
	  this.vit=memento.getVit();
	  this.atk=memento.getAtk();
	  this.def=memento.getDef();
	 }

	public Integer getVit() {
		return vit;
	}

	public void setVit(Integer vit) {
		this.vit = vit;
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}
	 
	
	
	
	
	
	
	
	
}
