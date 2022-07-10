package projectile;

import entity.Entity;
import graphic.EntityGraphic;
import main.GamePanel;

public class Projectile extends Entity {
	
	Entity user;

	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY  = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.currentHP = maxHP;
		
	}
	
	public void update() {
		
		// INTERACT
		if(user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, projectileAttack);
				alive = false; // trung la mat vien dan
			}
		}
		else{
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(projectileAttack);
				alive = false;
			}
		}
		
		// DIRECTION
		switch (direction) {
		case "up":
			worldY -= speed;	// update position
			break;
		case "down":
			worldY += speed;
			break;
		case "left":
			worldX -= speed;
			break;
		case "right":
			worldX += speed;
			break;
		}
		
		// DURATION
		currentHP--;
		if(currentHP <= 0) {
			alive = false;
		}
		
		// SWITCH SPRITE
		this.entityGraphic.spriteCounter++;	// everytime a frame passed, increase the counter
		
		if (this.entityGraphic.spriteCounter > 10) {
			if (this.entityGraphic.spriteNum == 1) {	// change the image
				this.entityGraphic.spriteNum = 2;
			} else {
				this.entityGraphic.spriteNum = 1;
			}
			
			this.entityGraphic.spriteCounter = 0;		// reset counter
		}
	}
		public boolean checkMP(Entity user) {
			
				if(user.currentMP >= mpCost) return true;
				else return false;
	}
		
		public void subMP(Entity user) {
			user.currentMP -= mpCost;
		}
}
