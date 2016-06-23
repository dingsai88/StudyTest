package ding.study.designpatterns.memento;
/**
 * 游戏属性状态 保存类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午3:37:02
 */
public class RoleStateMemento {
	// 生命
	private Integer vit;
	// 攻击力
	private Integer atk;
	// 防御力
	private Integer def;

	 public RoleStateMemento(int vit,int atk,int def){
		  this.vit=vit;
		  this.atk=atk;
		  this.def=def;
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
