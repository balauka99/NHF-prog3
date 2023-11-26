package object;

import loaders.SheetLoader;

public class Heart extends OsObject{
	private int index = 0;
	public Heart() {
		setName("heart");
		sheet = new SheetLoader("/objects/healt_sheet.png", 3, 1, 16, 16);
		skin = sheet.get(index);
	}
	public void damaged() {
		index++;
		if(index == sheet.size()) index--;
		skin = sheet.get(index);
	}
	public void heal() {
		index--;
		if(index == -1) index++;
		skin = sheet.get(index);
	}
	public int getHeartState() {
		if(index == 0) return 2;
		if(index == 1) return 1;
		return 0;
	}
	@Override
	public void reLoad() {
		sheet = new SheetLoader("/objects/healt_sheet.png", 3, 1, 16, 16);
		skin = sheet.get(index);
	}
}
