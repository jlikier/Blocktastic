/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocktastic.client;

import blocklib.gui.graphics.Window;
import blocklib.gui.input.KeyEvent;
import blocklib.gui.input.KeyListener;

public class Client implements KeyListener {
	boolean run;

	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		Window w = new Window();
		w.kb.addKeyListener(this);
		
		run = true;
		while(run) {
			
			w.render();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKey() == 1) { //ESC
			run = false;
		}
		System.out.println(k.getKey());
	}

	@Override
	public void keyReleased(KeyEvent k) {
		
	}
}
