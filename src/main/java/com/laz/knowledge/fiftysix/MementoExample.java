package com.laz.knowledge.fiftysix;

//备忘录模式 https://www.cnblogs.com/adamjwh/p/11018268.html
public class MementoExample {
	public class GameRole {

	    private int vit;    //生命力
	    private int atk;    //攻击力
	    private int def;    //防御力

	    public int getVit() {
	        return vit;
	    }
	    public void setVit(int vit) {
	        this.vit = vit;
	    }
	    public int getAtk() {
	        return atk;
	    }
	    public void setAtk(int atk) {
	        this.atk = atk;
	    }
	    public int getDef() {
	        return def;
	    }
	    public void setDef(int def) {
	        this.def = def;
	    }

	    //状态显示
	    public void stateDisplay() {
	        System.out.println("角色当前状态：");
	        System.out.println("体力：" + this.vit);
	        System.out.println("攻击力：" + this.atk);
	        System.out.println("防御力： " + this.def);
	        System.out.println("-----------------");
	    }

	    //获得初始状态
	    public void getInitState() {
	        this.vit = 100;
	        this.atk = 100;
	        this.def = 100;
	    }

	    //战斗后
	    public void fight() {
	        this.vit = 0;
	        this.atk = 0;
	        this.def = 0;
	    }

	    //保存角色状态
	    public RoleStateMemento saveState() {
	        return (new RoleStateMemento(vit, atk, def));
	    }

	    //恢复角色状态
	    public void recoveryState(RoleStateMemento memento) {
	        this.vit = memento.getVit();
	        this.atk = memento.getAtk();
	        this.def = memento.getDef();
	    }

	}
	
	public class RoleStateMemento {

	    private int vit;    //生命力
	    private int atk;    //攻击力
	    private int def;    //防御力

	    public RoleStateMemento(int vit, int atk, int def) {
	        this.vit = vit;
	        this.atk = atk;
	        this.def = def;
	    }

	    public int getVit() {
	        return vit;
	    }

	    public void setVit(int vit) {
	        this.vit = vit;
	    }

	    public int getAtk() {
	        return atk;
	    }

	    public void setAtk(int atk) {
	        this.atk = atk;
	    }

	    public int getDef() {
	        return def;
	    }

	    public void setDef(int def) {
	        this.def = def;
	    }

	}
	public class RoleStateCaretaker {

	    private RoleStateMemento memento;

	    public RoleStateMemento getMemento() {
	        return memento;
	    }

	    public void setMemento(RoleStateMemento memento) {
	        this.memento = memento;
	    }

	}
	public static void main(String[] args) {
		MementoExample m = new MementoExample();
        //打boss前
        GameRole gameRole = m.new GameRole();
        gameRole.getInitState();
        gameRole.stateDisplay();

        //保存进度
        RoleStateCaretaker caretaker = m.new RoleStateCaretaker();
        caretaker.setMemento(gameRole.saveState());

        //打boss失败
        gameRole.fight();
        gameRole.stateDisplay();

        //恢复状态
        gameRole.recoveryState(caretaker.getMemento());
        gameRole.stateDisplay();
    }
}
