package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameplay.GamePanel;
import loaders.SheetLoader;

public class Shadow extends Entity implements Runnable{
	GamePanel gameP;
	Thread animation;
	SheetLoader sheet;
	private boolean spawned = false;
	
	public Shadow(GamePanel gp) {
		gameP = gp;
		sheet = new SheetLoader("/entity/shadow_sheet.png", 4, 5, 70, 80);
		
		setX(200);
		setY(200);
	}
	@Override
	public void draw(Graphics2D grap2) {
		if(!spawned) {
			startSpawnAnimation();
			spawned = true;
		}
		grap2.drawImage(skin, getX(), getY(), null); //Az up2-be rakjuk bele a kivágott sheet-et
	}
	public void startSpawnAnimation() {
		animation = new Thread(this);
		animation.start();
	}
	@Override
	public void run() {
		for(int i = 0; i < 12; i++) {
			skin = sheet.nextSheetImage();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(animation != null) {
			try {
				skin = sheet.get(16);
				Thread.sleep(100);
				skin = sheet.get(17);
				Thread.sleep(100);
				skin = sheet.get(18);
				Thread.sleep(100);
				skin = sheet.get(19);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}