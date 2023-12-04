package main;

import menu.MainMenu;

/**
 * A Main függvény, ami gyakorlatilag csak készít egy MainMenu ablakot amit megjelenít
 */
public class Main{
	public static void main(String[] arg) {
		MainMenu mmenu = new MainMenu();
		mmenu.setVisible(true);
	}
}
