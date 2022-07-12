package player;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import graphic.EntityGraphic;
import main.GamePanel;
import projectile.Waterball;

public class Player extends Entity {

	public Player(GamePanel gp) {

		super(gp);

		// INFO
		defaultSpeed = 5;
		speed = defaultSpeed;
		direction = "down";
		maxHP = 10;
		currentHP = maxHP;
		maxMP = 4;
		currentMP = maxMP;
		normalAttack = 3;
		defense = 1;

		// SOLID AREA
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		// PROJECTILE
		projectile = new Waterball(gp);
		projectile.setEntityGraphic(new EntityGraphic(projectile));

		// ATTACK AREA
		attackArea.width = 36;
		attackArea.height = 36;
	}

	public void setImage() {

		try {
			// WALKING
			this.entityGraphic.up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			this.entityGraphic.up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			this.entityGraphic.down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			this.entityGraphic.down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			this.entityGraphic.left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			this.entityGraphic.left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			this.entityGraphic.right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			this.entityGraphic.right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

			// ATTACKING
			this.entityGraphic.attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_1.png"));
			this.entityGraphic.attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_2.png"));
			this.entityGraphic.attackDown1 = ImageIO
					.read(getClass().getResourceAsStream("/player/boy_attack_down_1.png"));
			this.entityGraphic.attackDown2 = ImageIO
					.read(getClass().getResourceAsStream("/player/boy_attack_down_2.png"));
			this.entityGraphic.attackLeft1 = ImageIO
					.read(getClass().getResourceAsStream("/player/boy_attack_left_1.png"));
			this.entityGraphic.attackLeft2 = ImageIO
					.read(getClass().getResourceAsStream("/player/boy_attack_left_2.png"));
			this.entityGraphic.attackRight1 = ImageIO
					.read(getClass().getResourceAsStream("/player/boy_attack_right_1.png"));
			this.entityGraphic.attackRight2 = ImageIO
					.read(getClass().getResourceAsStream("/player/boy_attack_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		// CHECK HP
		if (currentHP <= 0) {
			gp.stopMusic();
			gp.ui.commandNum = 0;
			gp.gameState = gp.deadState;
		}

		// RECOVER MP
		if (currentMP < maxMP) {

			counter.mpRecoverCounter++;

			if (counter.mpRecoverCounter == 120) {

				currentMP++;
				counter.mpRecoverCounter = 0;
			}
		}

		// DASHING KEY PRESSED
		if (gp.keyH.dashPressed == true && isIdle() == true && counter.dashAvailableCounter == 0) {

			dashing = true;

			counter.frameCounter = 0;
			speed = 12;
			entityGraphic.spriteNum = 2;

			counter.dashAvailableCounter = 300;
		}

		if (counter.dashAvailableCounter > 0) {

			counter.dashAvailableCounter--;
		}

		// INTERACT WITH NPC OR ATTACK
		if (gp.keyH.enterPressed == true && isIdle() == true) {

			// INTERACT WITH NPC
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			boolean interactWithNPC = interactNPC(npcIndex);

			// ATTACKING
			if (interactWithNPC == false) {
				counter.frameCounter = 0;
				attacking = true;
			}
		}

		// DASHING
		if (dashing == true) {
			dashing();
		}
		// ATTACKING
		else if (attacking == true) {
			attacking();
		}
		// MOVEMENT KEYS
		else if (gp.keyH.upPressed == true || gp.keyH.downPressed == true || gp.keyH.leftPressed == true
				|| gp.keyH.rightPressed == true) {

			// DIRECTION
			if (gp.keyH.upPressed == true) {
				direction = "up"; // update direction
			} else if (gp.keyH.downPressed == true) {
				direction = "down";
			} else if (gp.keyH.leftPressed == true) {
				direction = "left";
			} else if (gp.keyH.rightPressed == true) {
				direction = "right";
			}

			// COLLISION
			collisionOn = false; // reset collision status
			// tile
			boolean isWater = gp.cChecker.checkTile(this);
			if (isWater == true) { drinkWater(); }
			// object
			int objectIndex = gp.cChecker.checkObject(this);
			pickUpObject(objectIndex);
			// npc
			gp.cChecker.checkEntity(this, gp.npc);
			// monster
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			interactMonster(monsterIndex);

			// EVENT
			gp.eHandler.checkEvent();

			// MOVEMENT
			// if collisionOn is false, player can move
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed; // update position
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
			}

			// SWITCH SPRITE
			counter.movingCounter++;

			if (counter.movingCounter > 10) {

				if (entityGraphic.spriteNum == 1) { // change the image
					entityGraphic.spriteNum = 2;
				} else {
					entityGraphic.spriteNum = 1;
				}

				counter.movingCounter = 0; // reset counter
			}
		} else {
			// STAND SPRITE
			// switch to stand still animation when no key is pressed
			counter.standCounter++;

			if (counter.standCounter == 20) {
				entityGraphic.spriteNum = 1;
				counter.standCounter = 0;
			}
		}

		// PROJECTILE (not when dashing)
		if (gp.keyH.shotPressed == true && projectile.alive == false && counter.shotAvailableCounter == 0
				&& projectile.checkMP(this) == true && isIdle() == true) {

			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			gp.playSE(5);
			counter.shotAvailableCounter = 60;
			projectile.subMP(this);
		}

		if (counter.shotAvailableCounter > 0) {
			counter.shotAvailableCounter--;
		}

		// INVINCIBLE TIME
		if (invincible == true) {

			counter.invincibleCounter++;

			if (counter.invincibleCounter == 60) {
				invincible = false;
				counter.invincibleCounter = 0;
			}
		}
	}

	// attacking
	public void attacking() {

		counter.frameCounter++;

		if (counter.frameCounter <= 5) {
			entityGraphic.spriteNum = 1;
		}

		if (5 < counter.frameCounter && counter.frameCounter <= 25) {
			entityGraphic.spriteNum = 2;

			// save player's worldX, worldY and solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			// adjust player's worldX, worldY to represent attackArea's
			switch (direction) {
			case "up":
				worldY -= attackArea.height;
				break;
			case "down":
				worldY += attackArea.height;
				break;
			case "left":
				worldX -= attackArea.width;
				break;
			case "right":
				worldX += attackArea.width;
				break;
			}

			// adjust player's solidArea to represent attackArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			// check collision between player's attackArea and monster's solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

			damageMonster(monsterIndex, normalAttack);

			// restore player's worldX, worldY and solidArea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}

		if (25 < counter.frameCounter) {
			entityGraphic.spriteNum = 1;
			counter.frameCounter = 0;
			attacking = false;
		}
	}

	// dashing
	public void dashing() {

		// COLLISION
		collisionOn = false; // reset collision status
		// tile
		boolean isWater = gp.cChecker.checkTile(this);
		if (isWater == true) {
			drinkWater();
		}
		// object
		int objectIndex = gp.cChecker.checkObject(this);
		pickUpObject(objectIndex);
		// npc
		gp.cChecker.checkEntity(this, gp.npc);
		// monster
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		interactMonster(monsterIndex);

		// EVENT
		gp.eHandler.checkEvent();

		// MOVEMENT
		// if collisionOn is false, player can move
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed; // update position
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
		}

		counter.frameCounter++;

		if (counter.frameCounter == 10) {

			dashing = false;

			counter.frameCounter = 0;
			speed = defaultSpeed;
			entityGraphic.spriteNum = 1;
		}
	}

	// pick up object
	public void pickUpObject(int index) {

		if (index != 999) {

		}
	}

	// interact with NPC
	public boolean interactNPC(int index) {

		if (index != 999) {

			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				// npc speaks
				gp.ui.currentDialogue = "Hello!";
			}

			return true;
		} else {

			return false;
		}
	}

	// interact with monster
	public void interactMonster(int index) {

		if (index != 999) {

			damagePlayer(gp.monster[index].normalAttack);
		}
	}

	// give damage to monster
	public void damageMonster(int index, int attack) {

		if (index != 999) {

			if (gp.monster[index].invincible == false) {
				if (attack > gp.monster[index].defense) {
					gp.monster[index].currentHP -= (attack - gp.monster[index].defense);

				}
				gp.monster[index].invincible = true;
				if (gp.monster[index].currentHP <= 0) {
					gp.monster[index] = null;
				}
			}
		}
	}

	// drink water to heal
	public void drinkWater() {
		currentHP = maxHP;
	}
}
