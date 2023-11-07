package bestrounds;

import javax.swing.JFrame;

public class BRwin extends JFrame{
	public BRwin() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Dungeon Run");
		
		BRPanel brp = new BRPanel();
		add(brp);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
