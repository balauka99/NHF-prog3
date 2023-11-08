package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bestrounds.BRPanel;
import gameplay.*;
import load.LoadPanel;

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
	private void setActualPan(JPanel tmp) {
		getContentPane().removeAll();
		actual_pan = tmp;
		add(tmp);
		pack();
	}
	private class BackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent back) {
			ResetMenuPanel();
			add(actual_pan);
		}
	}
	private class NewGameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent newgame) {
			GameWin gameW = new GameWin();
			setVisible(false);
			gameW.setVisible(true);
		}
		
	}
	private class LoadButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent loadgame) {
			setActualPan(new LoadPanel());
		}
	}
	private class BestRoundsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent bestrounds) {
			setActualPan(new BRPanel());
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
