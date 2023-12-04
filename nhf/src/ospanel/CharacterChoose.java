package ospanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gameplay.GamePanel;
import menu.MainMenu;

/**
 * Az osztály egy OsPanel leszármazott ami egy előre beállatott karakter választó felületet jelenít meg
 */
@SuppressWarnings("serial")
public class CharacterChoose extends OsPanel{
	private String character;
	private String player_name;
	
	private JButton vikingB = new JButton("Choose VIKING - great arom, but low damage");
	private JButton robotB = new JButton("Choose ROBOT - low armor, but great damage");
	
	private JButton selectB = new JButton("Select choosen character - START GAME");
	private JLabel textL = new JLabel("PLEAS CHOOSE A CHARACTER - click on the choosen one!", JLabel.CENTER);
	private JTextField playerName = new JTextField(10);
	
	public CharacterChoose(MainMenu win) {
		super();
		Font buttonsFont = new Font("Century Gothic", Font.BOLD, 24);
		vikingB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character = "viking";
                textL.setText("VIKING is Choosen - click on the SELECT Button!");
            }
        });
		robotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character = "robot";
                textL.setText("ROBOT is Choosen - click on the SELECT Button!");
            }
        });
		selectB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player_name = playerName.getText();
            	if(character != null && !player_name.isEmpty()) {
            		GamePanel gameP = new GamePanel(character, player_name);
            		win.setTitle("Dungeon Run");
            		win.addNewCard(gameP, "Game");
            		gameP.setMenuWin(win);
            		gameP.setupGame();
            		gameP.startMainT();
            		win.pack();
            		win.changeCard("Game");
                } else {
                	textL.setText("YOU CHOOSE NONE OF THE CHARACTERS AND ENTER YOU NAME!");
                }
            }
        });
		
		setLayout(null);
		textL.setForeground(Color.white);
		textL.setBounds(0, 0, 480, 50);
		
		playerName.setBackground(Color.gray);
		playerName.setForeground(Color.cyan);
		playerName.setFont(buttonsFont);
		playerName.setBounds(140, 50, 200, 50);
		
		vikingB.setBackground(Color.yellow);
		vikingB.setForeground(Color.black);
		vikingB.setBounds(140, 100+50, 320, 50);
		
		robotB.setBackground(Color.blue);
		robotB.setForeground(Color.white);
		robotB.setBounds(140, 100+100, 320, 50);
		
		selectB.setBackground(Color.cyan);
		selectB.setForeground(Color.black);
		selectB.setBounds(140, 100+150, 320, 50);
		
		setBackground(Color.GRAY);
		add(textL);
		add(playerName);
		add(vikingB);
		add(robotB);
		add(selectB);
		win.pack();
		win.setLocationRelativeTo(null);
	}
}
