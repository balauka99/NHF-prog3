package menu;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bestrounds.BRwin;
import gameplay.GamePanel;
import gameplay.GameWind;

public class MainMenu extends JFrame{
	private JPanel actual_pan;
	private void ResetMenuPanel() {
		setTitle("Main Menu");
		setSize(new Dimension(960, 720));
		getContentPane().removeAll();
		actual_pan = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_cent = new GridBagConstraints();
		gbc_cent.gridx = 0;
		gbc_cent.gridy = 0;
		gbc_cent.weightx = 1.0;
		gbc_cent.anchor = GridBagConstraints.CENTER;
		
		JButton ngb = new JButton("New Game");
		ngb.addActionListener(new NewGameButtonListener());
		
		JButton lgb = new JButton("Load Game");
		lgb.addActionListener(new LoadButtonListener());
		
		JButton brb = new JButton("Best Rounds");
		brb.addActionListener(new BestRoundsButtonListener());
		
		JButton xtb = new JButton("EXIT");
		xtb.addActionListener(new ExitButtonListener());
		
		actual_pan.add(ngb, gbc_cent);
		gbc_cent.gridy++;
		actual_pan.add(lgb, gbc_cent);
		gbc_cent.gridy++;
		actual_pan.add(brb, gbc_cent);
		gbc_cent.gridy++;
		actual_pan.add(xtb, gbc_cent);
		gbc_cent.gridy++;
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private class BackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent back) {
			ResetMenuPanel();
			add(actual_pan);
			//pack();	Ha bent van az a sor, akkor összeugrik minden
		}
	}
	private class NewGameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent newgame) {
			getContentPane().removeAll();
			actual_pan = new GamePanel();
			JButton back = new JButton("Back");
			back.addActionListener(new BackButtonListener());
			actual_pan.add(back);
			add(actual_pan);
			pack();
		}
		
	}
	private class LoadButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent loadgame) {
			System.out.println("Load Game");
		}
	}
	private class BestRoundsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent bestrounds) {
			BRwin brwin = new BRwin();
		}
		
	}
	private class ExitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent exit) {
			System.exit(0);
		}
	}
	
	public MainMenu() {
		ResetMenuPanel();
		add(actual_pan);
	}
}