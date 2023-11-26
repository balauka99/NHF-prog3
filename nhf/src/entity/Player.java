package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import gameplay.GamePanel;
import gameplay.KeyHandler;
import gameplay.Map_Status;
import loaders.SheetLoader;
import object.OBJ_Barrel;
import object.OBJ_Chest;

public class Player extends Entity{
	private transient GamePanel gameP;
	private transient KeyHandler keyH;
	private int point = 0;
	
	private String character_name;
	
	public Player(GamePanel gP, KeyHandler kH, String character) {
		gameP = gP;
		keyH = kH;
		
		hitbox = new Rectangle(8, 16, 32, 32);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		character_name = character;
		
		switch(character_name){
		case "viking":
			healt = 1001;
			maxHealt = 1001;
			weapon = new Weapon("ground_attack", move_dir);
			break;
		case "robot":
			healt = 501;
			maxHealt = 501;
			weapon = new Weapon("wave_attack", move_dir);
			break;
		default:
			healt = 1001;
			maxHealt = 1001;
			weapon = new Weapon("ground_attack", move_dir);
			break;
		}
		defaultPos();
		setGamerSkin();
	}
	
	public void defaultPos() {
		setX(9*gameP.tileSize);
		setY(12*gameP.tileSize);
		setSpeed(4);
		move_dir = "down";
	}
	
	public void setGamerSkin() {
		// Down, Left, Right, Up
		sheet = new SheetLoader("/gamer/" + character_name + ".png", 8, 1, 16, 16);
	}
	
	@Override
	public void damaged(int damage) {
		super.damaged(damage);
		int actual_healt_ratio;
		if(character_name == "viking") actual_healt_ratio = healt/100;
		else actual_healt_ratio = healt/50;
		int heart_ratio = 0;
		for(int i = 0; i < gameP.player_healt.size(); i++) {
			heart_ratio += gameP.player_healt.get(i).getHeartState();
		}
		if(heart_ratio > actual_healt_ratio) {
			for(int i = (gameP.player_healt.size()-1); i > -1; i--) {
				int tmp = gameP.player_healt.get(i).getHeartState();
				if(tmp == 2 || tmp == 1) {
					gameP.player_healt.get(i).damaged();
					break;
				}
			}
		}
	}
	
	public void heal(int plus) {
		if(healt < maxHealt) healt += plus;
		int actual_healt_ratio;
		if(character_name == "viking") actual_healt_ratio = healt/100;
		else actual_healt_ratio = healt/50;
		int heart_ratio = 0;
		for(int i = 0; i < gameP.player_healt.size(); i++) {
			heart_ratio += gameP.player_healt.get(i).getHeartState();
		}
		if(heart_ratio < actual_healt_ratio) {
			for(int i = 0; i < gameP.player_healt.size(); i++) {
				int tmp = gameP.player_healt.get(i).getHeartState();
				if(tmp == 0 || tmp == 1) {
					gameP.player_healt.get(i).heal();
					break;
				}
			}
		}
		if(healt > maxHealt) healt = maxHealt;
		if(healt == maxHealt) {
			for(int i = 0; i < gameP.player_healt.size(); i++) {
				gameP.player_healt.get(i).heal();
				gameP.player_healt.get(i).heal();
			}
		}
	}
	
	public void update() throws IOException {
		if(keyH.attackButtonPressed()) {
			if(!weapon.isAttacking()) {
				weapon.attack(this.getX() - gameP.tileSize, this.getY() - gameP.tileSize, move_dir);
			}
			gameP.cChecker.checkWeaponDestroysOBJ(weapon);
			gameP.cChecker.checkWeaponDamageEnemys(this, weapon);
		}
		if(keyH.moveButtonPressed()) {	//Csak akkor kell nézzen valami és frissítse a sheet-et, ha meg lett nyomva gomb
			if(keyH.wP == true) {
				move_dir = "up";
			}
			else if(keyH.sP == true) {
				move_dir = "down";
			}
			if(keyH.aP == true) {
				move_dir = "left";
			}
			else if(keyH.dP == true) {
				move_dir = "right";
			}
			// Tile ütközést vizsgál
			collisionOn = false;
			gameP.cChecker.checkTile(this);
			
			// Object ütközés vizsgálat
			int objind = gameP.cChecker.checkObject(this, true);
			interactObj(objind);
			
			if(collisionOn == false) {
				switch(move_dir) {
				case "up":
					if((getY() > 0)) setY(getY() - getSpeed());
					else setY(0);
					break;
				case "down":
					if(getY() < gameP.screenHeight - gameP.tileSize)setY(getY() + getSpeed());
					else setY(gameP.screenHeight - gameP.tileSize);
					break;
				case "left":
					if((getX() > 0)) setX(getX() - getSpeed());
					else setX(0);
					break;
				case "right":
					if(getX() < gameP.screenWidth - gameP.tileSize) setX(getX() + getSpeed());
					else setX(gameP.screenWidth - gameP.tileSize);
					break;
				}
			}
			sheetCnt++;
			if(sheetCnt > 12) {		//Mivel másodpercenként 60szor van meghívva lelasítjuk mikor váltsa a sheet-eket, pl: up1 és up2 között
				if(sheetNum == 1) {
					sheetNum = 2;
				}else if(sheetNum == 2) {
					sheetNum = 1;
				}
				sheetCnt = 0;
			}
		}
		// Mindig meg kell nézni lát-e valaki
		gameP.cChecker.checkEnemysSeePlayer(this);
	}
	public void interactObj(int ind) throws IOException {
		if(ind != 999) {	//Ha 999 nem értünk semmihez
			String objName = gameP.objects.get(ind).getName();
			if(objName == "Chest") {
				if(!gameP.objects.get(ind).isObjectUsed() && gameP.isLevelCleared()) {
					gameP.objects.get(ind).useObject();
					point++;
				}
			}else if(objName == "Door") {
				if(!gameP.objects.get(ind).isObjectUsed() && gameP.isLevelCleared()) {
					gameP.objects.get(ind).useObject();
					if(gameP.objects.get(ind).x == 9*gameP.tileSize && gameP.objects.get(ind).y == 14*gameP.tileSize) {	// Alsó ajtónál vagyunk és megyünk be
						gameP.loadPreviousMap();
					}
					else {
						if(gameP.map_state == Map_Status.IN_NEW) {
							gameP.enterNewMap();
						}
						else if(gameP.map_state == Map_Status.IN_PREVIOUS) {
							gameP.enterPreviousMapFromPreviousMap();
						}
					}
				}
			} else if(objName == "Barrel") {
				if(!gameP.objects.get(ind).isDestroyed()) {
					this.damaged(100);
					gameP.objects.get(ind).destroy();
				}
			} else if(objName == "Heal") {
				if(!gameP.objects.get(ind).isObjectUsed() && healt != maxHealt) {
					gameP.objects.get(ind).useObject();
					this.heal(200);
				}
			}
		}
	}
	
	@Override
	public void draw(Graphics2D grap2) {
		switch(move_dir) {
		case "up":
			if(sheetNum == 1) {
				skin = sheet.get(6);;
			} else {
				skin = sheet.get(7);;
			}
			break;
		case "down":
			if(sheetNum == 1) {
				skin = sheet.get(0);
			} else {
				skin = sheet.get(1);;
			}
			break;
		case "left":
			if(sheetNum == 1) {
				skin = sheet.get(2);;
			} else {
				skin = sheet.get(3);;
			}
			break;
		case "right":
			if(sheetNum == 1) {
				skin = sheet.get(4);;
			} else {
				skin = sheet.get(5);;
			}
			break;
		}
		
		grap2.drawImage(skin, getX(), getY(), null);
		if(weapon.isAttacking()) weapon.draw(grap2);
	}
	
	public int getPoint() {
		return point;
	}
	
	public int getHealt() {
		return healt;
	}
	
	public void reLoad(GamePanel gameP, KeyHandler keyH) {
		this.gameP = gameP;
		this.keyH = keyH;
		this.setGamerSkin();
		this.weapon.getAttackHitbox().reLoad();
	}
}