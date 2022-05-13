package co.mia.farm.game.print;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardService implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
