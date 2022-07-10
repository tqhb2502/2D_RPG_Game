package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotPressed;
	
	// CONSTRUCTOR
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			
			if (code == KeyEvent.VK_ENTER) {
				switch (gp.ui.commandNum) {
				case 0:
					gp.playMusic(0);
					gp.gameState = gp.playState;
					break;
				case 1:
					// add later
					break;
				case 2:
					System.exit(0);
					break;
				}
			}
		}
		
		// PLAY STATE
		else if (gp.gameState == gp.playState) {
			
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			
			if (code == KeyEvent.VK_P) {
				gp.ui.commandNum = 0;
				gp.gameState = gp.pauseState;
			}
			
			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if (code == KeyEvent.VK_F) {
				shotPressed = true;
			}
		}
		
		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			
			if (code == KeyEvent.VK_ENTER) {
				
				switch (gp.ui.commandNum) {
				case 0:
					gp.gameState = gp.playState;
					break;
				case 1:
					System.exit(0);
					break;
				}
			}
		}
		
		// DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
		// DEAD STATE
		else if (gp.gameState == gp.deadState) {
			
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			
			if (code == KeyEvent.VK_ENTER) {
				
				switch (gp.ui.commandNum) {
				case 0:
					gp.setupGame();
					gp.playMusic(0);
					gp.gameState = gp.playState;
					break;
				case 1:
					System.exit(0);
					break;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if (code == KeyEvent.VK_F) {
			shotPressed = false;
		}
	}

}
