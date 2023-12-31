package gameplay;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * Ez az osztály felelős a gombok nyomásának a detektálásáért
 */
@SuppressWarnings("serial")
public class KeyHandler implements KeyListener, Serializable{

	public boolean wP, sP, aP, dP, spaceP, escP;	// wasd valamelyik gombja meg lett nyomva
	
	@Override
	public void keyTyped(KeyEvent e) {
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
		if(kcode == KeyEvent.VK_SPACE) {
			spaceP = true;
		}
		if(kcode == KeyEvent.VK_ESCAPE) {
			escP = true;
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
		if(kcode == KeyEvent.VK_SPACE) {
			spaceP = false;
		}
		if(kcode == KeyEvent.VK_ESCAPE) {
			escP = false;
		}
	}
	public boolean moveButtonPressed() {
		return wP || aP || sP || dP;
	}
	public boolean attackButtonPressed() {
		return spaceP;
	}
	public boolean escapeButtonPressed() {
		return escP;
	}
	public int pressedButtonNum() {
		int count = 0;
		if(wP) count++;
		if(aP) count++;
		if(sP) count++;
		if(dP) count++;
		return count;
	}
}
