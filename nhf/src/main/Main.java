package main;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import menu.MainMenu;

/**
 * A Main függvény, ami gyakorlatilag csak készít egy MainMenu ablakot amit megjelenít
 */
public class Main{
	private static MainMenu mmenu;
	/**
	 * Legenerálja a fájlokat amikre szüksége van a programnak, ha már létezik a fájl nem csinál semmit
	 * @throws IOException Visszadobja, ha valami gond adódott a fájlok elkészítésénél
	 */
	private static void createNeededFiles() throws IOException {
		Path saveFile = Paths.get("source/save_file/last_save.txt");
		Path bestsFile = Paths.get("source/save_file/bestRounds.txt");
		try {
			Files.createDirectories(bestsFile.getParent());
			Files.createFile(bestsFile);
		} catch(FileAlreadyExistsException e) {
		} catch (IOException e) {
			throw e;
		}
		try {
			Files.createDirectories(saveFile.getParent());
			Files.createFile(saveFile);
		} catch(FileAlreadyExistsException e) {
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static void main(String[] arg) {
		try {
			createNeededFiles();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR when creating needed Files!");
			return;
		}
		mmenu = new MainMenu();
		mmenu.setVisible(true);
	}
}
