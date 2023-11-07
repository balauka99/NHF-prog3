package bestrounds;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class BRPanel extends JPanel{
	public BRPanel() {
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.green);
		setDoubleBuffered(true);
	}
}
