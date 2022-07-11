package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphic.EntityGraphic;
import item.Heart;
import item.Mana;

public class UIMaker {
	
	GamePanel gp;
	Graphics2D g2;
	
	public int commandNum = 0;
	
	public String currentDialogue;
	BufferedImage heart_full, heart_half, heart_blank,mana_full,mana_blank;
	
	public UIMaker(GamePanel gp) {
		this.gp = gp;
		
		Heart heart = new Heart(gp);
		heart.setEntityGraphic(new EntityGraphic(heart));
		heart_full = heart.entityGraphic.image;
		heart_half = heart.entityGraphic.image2;
		heart_blank = heart.entityGraphic.image3;
		
		Mana mana = new Mana(gp);
		mana.setEntityGraphic(new EntityGraphic(mana));
		mana_full = mana.entityGraphic.image;
		mana_blank = mana.entityGraphic.image2;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		
		// PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
		}
		
		// DIALOGUE STATE
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		
		// DEAD STATE
		if (gp.gameState == gp.deadState) {
			drawDeadScreen();
		}
		
		// FINISHED STATE
		if (gp.gameState == gp.finishedState) {
			drawFinishedScreen();
		}
	}
	
	private void drawPlayerLife() {
		// DRAW MAX LIFE
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		while (i < gp.player.maxHP/2) {
			g2.drawImage(heart_blank, x, y,gp.tileSize,gp.tileSize,null);
			i++;
			x+= gp.tileSize;
		}
		
		//	DRAW CURRENT LIFE
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		while (i < gp.player.currentHP) {
			g2.drawImage(heart_half, x, y,gp.tileSize,gp.tileSize,null);
			i++;
			if(i<gp.player.currentHP) {
				g2.drawImage(heart_full, x, y,gp.tileSize,gp.tileSize,null);
			}
			i++;
			x+= gp.tileSize;
		}
		// DRAW MAX MP
		x = gp.tileSize/2-5;
		y =  (int)(gp.tileSize*1.5);
		i = 0;
		while(i<gp.player.maxMP) {
			g2.drawImage(mana_blank, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			x+=35;
		}
		
		// DRAW CURRENT MP
		// DRAW MAX MANA
				x = gp.tileSize/2 -5;
				y =  (int)(gp.tileSize*1.5);
				i = 0;
				while(i<gp.player.currentMP) {
					g2.drawImage(mana_full, x, y, gp.tileSize, gp.tileSize, null);
					i++;
					x+=35;
				}
		
	}

	public void drawTitleScreen() {
		// screen color
		g2.setColor(new Color(70, 150, 80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "GROUP 9";
		int x = getXForCenteredText(text);
		int y = gp.tileSize * 3;
		
		// shadow title
		g2.setColor(Color.black);
		g2.drawString(text, x + 5, y + 5);
		
		// main title
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// display character
		x = gp.screenWidth / 2 - gp.tileSize;
		y += gp.tileSize * 2;
		BufferedImage blueBoyImage = null;
		try {
			blueBoyImage = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(blueBoyImage, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
		
		// menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
		
		text = "NEW GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	
	public void drawPauseScreen() {
		
		// screen
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6F));
		g2.setColor(Color.gray);
		g2.fillRect(0,  0,  gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
		// text
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		g2.setColor(Color.white);
		
		String text = "PAUSED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
		
		// menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		g2.setColor(Color.white);
		
		text = "Resume";
		x = getXForCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
		
		text = "Quit";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);	
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
	}
	
	public void drawDialogueScreen() {
		// WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - gp.tileSize * 4;
		int height = gp.tileSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		// DIALOGUE
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for (String line: currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}
	
	public void drawDeadScreen() {
		
		// screen
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6F));
		g2.setColor(Color.gray);
		g2.fillRect(0,  0,  gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));
		g2.setColor(Color.black);
		g2.fillRect(0, gp.screenHeight / 2 - gp.tileSize, gp.screenWidth, gp.tileSize * 2);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
		// text
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		g2.setColor(Color.red);
		
		String text = "YOU DIED";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight / 2 + 28;
		g2.drawString(text, x, y);
		
		// menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		g2.setColor(Color.white);
		
		text = "Retry";
		x = getXForCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
		
		text = "Quit";
		x = getXForCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);	
		if (commandNum == 1) {
			g2.drawString(">", x - gp.tileSize / 2, y);
		}
	}
	
	public void drawFinishedScreen() {
		
		// screen
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6F));
		g2.setColor(Color.gray);
		g2.fillRect(0,  0,  gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));
		g2.setColor(Color.black);
		g2.fillRect(0, gp.screenHeight / 2 - gp.tileSize, gp.screenWidth, gp.tileSize * 2);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
		// text
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		g2.setColor(Color.yellow);
		
		String text = "YOU WIN";
		int x = getXForCenteredText(text);
		int y = gp.screenHeight / 2 + 28;
		g2.drawString(text, x, y);
		
		// menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		g2.setColor(Color.white);
		
		text = "Back to main menu";
		x = getXForCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);
		g2.drawString(">", x - gp.tileSize / 2, y);
	}
	
	public int getXForCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return gp.screenWidth / 2 - length / 2;
	}
}
