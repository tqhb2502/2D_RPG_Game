package entity;

import java.awt.Rectangle;

import graphic.EntityGraphic;
import main.Counter;
import main.GamePanel;
import projectile.Projectile;

public abstract class Entity { // parent class for every entity in the game
	
	public GamePanel gp;
	public EntityGraphic entityGraphic;
	public Counter counter;

	public int worldX, worldY; // position of entity in world map
	public int defaultWorldX, defaultWorldY;
	public String name;
	public int type; // 0 = player, 1 = object, 2 = NPC, 3 = monster
	public final int type_player = 0;
	public final int type_object = 1;
	public final int type_NPC = 2;
	public final int type_monster = 3;

	public int speed;
	public int defaultSpeed;
	public String direction = "none"; // the direction of entity
	public boolean attacking = false;
	public boolean dashing = false;
	public boolean alive;

	public boolean invincible = false; // can receive damage from monsters or not

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // the solid area of entity, if it hit a tile that is not
																// passable, entity can not move
	public int solidAreaDefaultX = 0, solidAreaDefaultY = 0; // store default value of solidArea, because we may change
																// the x and y of solidArea later
	public boolean collisionOn = false; // check if entity is in collision or not
	public boolean collision = false; // this entity can be pass through or not
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0); // damage area when attacking

	public boolean onPath = false;

	// entity status
	public int maxHP;
	public int currentHP;
	public int maxMP;
	public int currentMP;
	public int normalAttack;
	public Projectile projectile;

	// CONSTRUCTOR
	public Entity(GamePanel gp) {
		this.gp = gp;
		counter = new Counter();
	}

	public void setEntityGraphic(EntityGraphic entityGraphic) {
		this.entityGraphic = entityGraphic;
		setImage();
	}

	public void setAction() {
		// to be overridden
	}

	public void setImage() {
		// to be overridden
	}

	public void checkCollision() {
		collisionOn = false;
		// tile
		gp.cChecker.checkTile(this);
		// object
		gp.cChecker.checkObject(this);
		// npc
		gp.cChecker.checkEntity(this, gp.npc);
		// monster
		gp.cChecker.checkEntity(this, gp.monster);
		// player
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if (this.type == type_monster && contactPlayer == true) {
			damagePlayer(normalAttack);
		}
	}

	public void update() {
		// DIRECTION
		setAction();
		// COLLISION
		checkCollision();
		// MOVEMENT
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

		// INVINCIBLE TIME
		if (invincible == true) {

			counter.invincibleCounter++;

			if (counter.invincibleCounter == 60) {
				invincible = false;
				counter.invincibleCounter = 0;
			}

		}

		if (counter.shotAvailableCounter < 30) {
			counter.shotAvailableCounter++;
		}
	}

	public void damagePlayer(int attack) {
		if (gp.player.invincible == false) {
			gp.player.currentHP -= attack;
			gp.player.invincible = true;
		}
	}
	
	public boolean isIdle() {
		
		boolean isIdle = true;
		
		if (attacking == true) { isIdle = false; }
		if (dashing == true) { isIdle = false; }
		
		return isIdle;
	}

	public void searchPath(int goalCol, int goalRow) {
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if(gp.pFinder.search() == true) {
			//System.out.println(1);
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			int leftX = worldX + solidArea.x;
			int rightX = worldX + solidArea.x + solidArea.width;
			
			int topY = worldY + solidArea.y;
			int downY = worldY + solidArea.y + solidArea.height;
			
			if(topY > nextY && leftX >= nextX && rightX < nextX+gp.tileSize) {
				direction = "up";
			}
			else if(topY < nextY && leftX >= nextX && rightX < nextX+gp.tileSize) {
				direction = "down";
			}
			else if(topY >= nextY  && downY < nextY+gp.tileSize) {
				if(leftX>nextX) direction = "left";
				else if(leftX < nextX) direction = "right";
			}
			
			else if(topY > nextY && leftX > nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true)
				direction = "left";
			}
			else if(topY > nextY && leftX < nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true)
				direction = "right";
			}
			else if(topY < nextY && leftX > nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true)
				direction = "left";
			}
			else if(topY < nextY && leftX < nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true)
				direction = "right";
			}
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			
//			if(nextCol == goalCol && nextRow == goalRow) {
//				
//				onPath = false;
//			}
		}

	}
	public void setDefault(int x, int y) {
		defaultWorldX = x;
		defaultWorldY = y;
	}
}
