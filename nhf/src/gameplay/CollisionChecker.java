package gameplay;

import entity.Entity;

public class CollisionChecker {
	GamePanel gameP;
	public CollisionChecker(GamePanel gp) {
		gameP = gp;
	}
	
	public void checkTile(Entity ent) {	//It is lehetne intersectel megvizsgálni, de az túl "drága" így csak 2 tilet vizsgálunk, nem 20x15-öt
		int entLeftX = ent.getX() + ent.hitbox.x;
		int entRightX = ent.getX() + ent.hitbox.x + ent.hitbox.width;
		int entTopY = ent.getY() + ent.hitbox.y;
		int entBottomY = ent.getY() + ent.hitbox.y + ent.hitbox.height;
		
		int entLeftCol = entLeftX/gameP.tileSize;
		int entRightCol = entRightX/gameP.tileSize;
		int entTopRow = entTopY/gameP.tileSize;
		int entBottomRow = entBottomY/gameP.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(ent.move_dir) {
		case "up":
			entTopRow = (entTopY - ent.getSpeed())/gameP.tileSize;	//Megnézzük, merre lesz a player
			tileNum1 = gameP.tileMg.map[entLeftCol][entTopRow];
			tileNum2 = gameP.tileMg.map[entRightCol][entTopRow];
			if(gameP.tileMg.tiles[tileNum1].isSolid() || gameP.tileMg.tiles[tileNum2].isSolid()) {
				ent.collisionOn = true;
			}
			break;
		case "down":
			entBottomRow = (entBottomY + ent.getSpeed())/gameP.tileSize;	//Megnézzük, merre lesz a player
			tileNum1 = gameP.tileMg.map[entLeftCol][entBottomRow];
			tileNum2 = gameP.tileMg.map[entRightCol][entBottomRow];
			if(gameP.tileMg.tiles[tileNum1].isSolid() || gameP.tileMg.tiles[tileNum2].isSolid()) {
				ent.collisionOn = true;
			}
			break;
		case "left":
			entLeftCol = (entLeftX - ent.getSpeed())/gameP.tileSize;	//Megnézzük, merre lesz a player
			tileNum1 = gameP.tileMg.map[entLeftCol][entTopRow];
			tileNum2 = gameP.tileMg.map[entLeftCol][entBottomRow];
			if(gameP.tileMg.tiles[tileNum1].isSolid() || gameP.tileMg.tiles[tileNum2].isSolid()) {
				ent.collisionOn = true;
			}
			break;
		case "right":
			entRightCol = (entRightX + ent.getSpeed())/gameP.tileSize;	//Megnézzük, merre lesz a player
			tileNum1 = gameP.tileMg.map[entRightCol][entTopRow];
			tileNum2 = gameP.tileMg.map[entRightCol][entBottomRow];
			if(gameP.tileMg.tiles[tileNum1].isSolid() || gameP.tileMg.tiles[tileNum2].isSolid()) {
				ent.collisionOn = true;
			}
			break;
		}
	}
	public int checkObject(Entity ent, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gameP.objects.length; i++) {
			if(gameP.objects[i] != null) {
				// Entity hitbox poziciója kiszámítása
				ent.hitbox.x = ent.getX() + ent.hitbox.x;
				ent.hitbox.y = ent.getY() + ent.hitbox.y;
				// Object hitbox poziciója kiszámítása
				gameP.objects[i].hitbox.x += gameP.objects[i].x;
				gameP.objects[i].hitbox.y += gameP.objects[i].y;
				
				switch(ent.move_dir) {
				case "up":
					ent.hitbox.y -= ent.getSpeed();
					if(ent.hitbox.intersects(gameP.objects[i].hitbox)) {	//Ha összeérnek akkor igazat ad vissza
						if(gameP.objects[i].collision == true) {
							ent.collisionOn = true;
						}
						if(player == true) {
							index = i;	// Ha a playerért hozzá akkor visszaadjuk annak az indexét, de ha nem akkor 999-et adunk vissza, anniy objectünk biztos nem lesz egyszerre a képen
						}
					}
					break;
				case "down":
					ent.hitbox.y += ent.getSpeed();
					if(ent.hitbox.intersects(gameP.objects[i].hitbox)) {	//Ha összeérnek akkor igazat ad vissza
						if(gameP.objects[i].collision == true) {
							ent.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					ent.hitbox.x -= ent.getSpeed();
					if(ent.hitbox.intersects(gameP.objects[i].hitbox)) {	//Ha összeérnek akkor igazat ad vissza
						if(gameP.objects[i].collision == true) {
							ent.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					ent.hitbox.x += ent.getSpeed();
					if(ent.hitbox.intersects(gameP.objects[i].hitbox)) {	//Ha összeérnek akkor igazat ad vissza
						if(gameP.objects[i].collision == true) {
							ent.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				ent.resetHitboxToDefault();
				gameP.objects[i].resetHitboxToDefault();
			}
		}
		
		return index;
	}
}
