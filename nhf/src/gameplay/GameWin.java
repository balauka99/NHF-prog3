package gameplay;

import javax.swing.JFrame;

public class GameWin extends JFrame{
	public GameWin() {
		setTitle("Dungeon Run");
		GamePanel gameP = new GamePanel();
		add(gameP);
		gameP.setupGame();
		gameP.startMainT();
		pack();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
