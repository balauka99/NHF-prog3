package object;

public class OBJ_Chest extends OsObject{
	public OBJ_Chest() {
		setName("Chest");
		this.setupSkin("chest");
	}
	@Override
	public void useObject() {
		super.useObject();
		this.setupSkin("chest_opened");
	}
	@Override
	public void reLoad() {
		setName("Chest");
		if(this.isObjectUsed()) {
			this.setupSkin("chest_opened");
		} else this.setupSkin("chest");
	}
}
