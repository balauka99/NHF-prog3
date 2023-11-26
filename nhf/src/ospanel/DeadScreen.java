package ospanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

import gameplay.UtilityTools;

public class DeadScreen extends OsPanel{
	private BufferedImage backGround;
	private JLabel info = new JLabel();
	private JButton backMainMenu = new JButton("Back To Main Menu");
	public DeadScreen(String name, int point) {
		super();
		UtilityTools uT = new UtilityTools();
		try {
			backGround = uT.scaleImage(ImageIO.read(getClass().getResourceAsStream("/menu/deadScreen.png")), 960, 720);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);
		info.setBounds(250, 360, 540, 50);
		info.setBackground(Color.GRAY);
		info.setForeground(Color.CYAN);
		info.setFont(defFont);
		info.setText("You are DEAD " + name + " and your point is " + point);
		
		backMainMenu.setBounds(280, 560, 400, 50);
		backMainMenu.setBackground(Color.GRAY);
		backMainMenu.setForeground(Color.CYAN);
		backMainMenu.setFont(defFont);
		backMainMenu.addActionListener(new BackToMainMenu());
		
		add(info);
		add(backMainMenu);
	}
	
	private class BackToMainMenu implements ActionListener{
		public void actionPerformed(ActionEvent newgame) {
			menuWin.changeCard("Menu");
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grap2 = (Graphics2D) g;
        grap2.drawImage(backGround, 0, 0, null);
    }
}
