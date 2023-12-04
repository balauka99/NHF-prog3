package loaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Az osztály képes tárolni egy felhasználónak/játékosnak a nevét és pontját ami összegyűjtött
 */
@SuppressWarnings("serial")
public class BestRounds extends ArrayList<Score> implements Serializable{
	public void reset() {
		this.removeRange(0, this.size());
	}
	/**
	 * Az aki éppen most játszik az mindig az utolsó indexen van, mert egymás után kerülnek be az adatok
	 * @return
	 */
	public Score getCurrent() {
		return this.get(size()-1);
	}
	public static void saveBests(BestRounds bests, String fName) {
		try (ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream(fName))) {
            saver.writeObject(bests);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static BestRounds loadBests(String fName) {
		try (ObjectInputStream loader = new ObjectInputStream(new FileInputStream(fName))) {
            Object bests = loader.readObject();
            if (bests instanceof BestRounds) {
                return (BestRounds) bests;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
	}
}
