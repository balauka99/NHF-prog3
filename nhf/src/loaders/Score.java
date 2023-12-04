package loaders;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Egy felhasználó/játékos nevét és összegyüjtött pontját tárolja
 */
public class Score implements Serializable{
	private String name;
	private int point;
	public Score(String name, int point) {
		this.name = name;
		this.point = point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
