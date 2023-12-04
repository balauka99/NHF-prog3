package object;

/**
 * Egy hordó, ami fel tud robbani, ennek egy külön szálla van
 */
@SuppressWarnings("serial")
public class OBJ_Barrel extends OsObject implements Runnable{
	private transient Thread boom;
	
	public OBJ_Barrel() {
		setName("Barrel");
		this.setupSkin("barrel");
		collision = true;
	}
	public void boom() {
		if(!this.isDestroyed()) {
			boom = new Thread(this);
			boom.start();
		}
	}
	@Override
	public void destroy() {
		if(!unBreakAble) boom();
		super.destroy();
	}
	@Override
	public void run() {
		try {
				// Robbanás animáció
				this.setupSkin("barrelBoom/boom1");
				Thread.sleep(100);
				this.setupSkin("barrelBoom/boom2");
				Thread.sleep(200);
				this.setupSkin("barrelBoom/boom3");
				Thread.sleep(300);
				this.setupSkin("barrelBoom/boom4");
				Thread.sleep(1000);
				this.setupSkin("barrelBoom/barrel_boomed");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void reLoad() {
		setName("Barrel");
		if(this.isDestroyed()) this.setupSkin("barrelBoom/barrel_boomed");
		else {
			this.setupSkin("barrel");
		}
	}
}
