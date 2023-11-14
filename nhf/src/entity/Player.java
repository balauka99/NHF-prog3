package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameplay.GamePanel;
import gameplay.KeyHandler;

enum Player_states{Fight, Explore};

public class Player extends Entity{
	GamePanel gameP;
	KeyHandler keyH;
	Player_states state;
	
	public Player(GamePanel gP, KeyHandler kH) {
		gameP = gP;
		keyH = kH;
		
		hitbox = new Rectangle(8, 16, 32, 32);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		
		defaultPos();
		setGamerSkin();
	}
	public void defaultPos() {
		setX(100);
		setY(100);
		setSpeed(4);
		move_dir = "down";
		state = Player_states.Explore;
	}
	public void setGamerSkin() {
		try {
			//TODO készíteni up sheet-et
			up1 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/gamer/robot_right2.png"));			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(keyH.buttonPressed()) {	//Csak akkor kell nézzen valami és frissítse a sheet-et, ha meg lett nyomva gomb
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
	}
	public void interactObj(int ind) {
		if(ind != 999) {	//Ha 999 nem értünk semmihez
			gameP.objects[ind] = null;
		}
	}
	
	public void draw(Graphics2D grap2) {
		BufferedImage skin = null;
		switch(move_dir) {
		case "up":
			if(sheetNum == 1) {
				skin = up1;
			} else {
				skin = up2;
			}
			break;
		case "down":
			if(sheetNum == 1) {
				skin = down1;
			} else {
				skin = down2;
			}
			break;
		case "left":
			if(sheetNum == 1) {
				skin = left1;
			} else {
				skin = left2;
			}
			break;
		case "right":
			if(sheetNum == 1) {
				skin = right1;
			} else {
				skin = right2;
			}
			break;
		}
		grap2.drawImage(skin, getX(), getY(), gameP.tileSize, gameP.tileSize, null);
	}
}
