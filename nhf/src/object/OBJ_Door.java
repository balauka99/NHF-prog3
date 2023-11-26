package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends OsObject{
	public OBJ_Door() {
		setName("Door");
		this.setupSkin("door");
		unBreakAble = true;
	}
	@Override
	public void useObject() {
		super.useObject();
		this.setupSkin("door_opened");
	}
	@Override
	public void reUseObject() {
		super.reUseObject();
		this.setupSkin("door");
	}
	@Override
	public void reLoad() {
		setName("Door");
		if(!this.isObjectUsed()) this.setupSkin("door");
		else this.setupSkin("door_opened");
	}
}
