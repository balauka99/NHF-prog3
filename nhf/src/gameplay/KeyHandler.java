package gameplay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean wP, sP, aP, dP;	// wasd valamelyik gombja meg lett nyomva
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Még nem kell ide semmi
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kcode = e.getKeyCode();
		
		if(kcode == KeyEvent.VK_W) {
			wP = true;
		}
		if(kcode == KeyEvent.VK_S) {
			sP = true;
		}
		if(kcode == KeyEvent.VK_A) {
			aP = true;
		}
		if(kcode == KeyEvent.VK_D) {
			dP = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int kcode = e.getKeyCode();
		
		if(kcode == KeyEvent.VK_W) {
			wP = false;
		}
		if(kcode == KeyEvent.VK_S) {
			sP = false;
		}
		if(kcode == KeyEvent.VK_A) {
			aP = false;
		}
		if(kcode == KeyEvent.VK_D) {
			dP = false;
		}
	}

}
