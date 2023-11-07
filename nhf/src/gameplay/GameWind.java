package gameplay;

import javax.swing.JFrame;

public class GameWind extends JFrame{
	public GameWind() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Dungeon Run");
		
		GamePanel gameP = new GamePanel();
		add(gameP);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
