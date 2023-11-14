package gameplay;

import object.OBJ_Chest;
import object.OBJ_Door;

public class AssetSetter {
	GamePanel gameP;
	public AssetSetter(GamePanel gp) {
		gameP = gp;	
	}
	public void setObject() {	//TODO átgondolni
		gameP.objects[0] = new OBJ_Door();
		gameP.objects[0].x = 12*gameP.tileSize;
		gameP.objects[0].y = 6*gameP.tileSize;
		
		gameP.objects[1] = new OBJ_Chest();
		gameP.objects[1].x = 5*gameP.tileSize;
		gameP.objects[1].y = 10*gameP.tileSize;
	}
}
