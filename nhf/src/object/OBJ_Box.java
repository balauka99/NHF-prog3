package object;

import java.util.Random;

public class OBJ_Box extends OsObject{
	private Random rndNum = new Random();
	
	public OBJ_Box() {
		setName("Box");
		this.setupSkin("box");
		collision = true;
	}
	@Override
	public void useObject() {
		super.useObject();
		if(this.getName() == "Heal") {
			this.setupSkin("barrelBoom/barrel_boomed");
		}
	}
	
	@Override
	public void destroy() {
		if(!this.isDestroyed()) {
			int whatInTheBox = rndNum.nextInt(10);
			switch(whatInTheBox) {
			case 5:
				setName("Heal");
				this.setupSkin("heart");
				break;
			default:
				this.setupSkin("barrelBoom/barrel_boomed");
				break;
			}
		}
		super.destroy();
	}
	@Override
	public void reLoad() {
		setName("Box");
		if(this.isDestroyed()) {
			if(getName() == "Heal" && !this.isObjectUsed()) {
				this.setupSkin("heart");
			} else this.setupSkin("barrelBoom/barrel_boomed");
		}
		else this.setupSkin("box");
	}
}
