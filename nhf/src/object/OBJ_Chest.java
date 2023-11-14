package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends OsObject{
	public OBJ_Chest() {
		name = "Chest";
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
