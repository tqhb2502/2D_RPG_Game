package main;

import graphic.EntityGraphic;
import item.Door;
import monster.Dragon;
import monster.GreenSlime;
import npc.OldMan;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
//		gp.obj[1] = new Door(gp);
//		gp.obj[1].setEntityGraphic(new EntityGraphic(gp.obj[1]));
//		gp.obj[1].worldX = gp.tileSize * 22;
//		gp.obj[1].worldY = gp.tileSize * 21;
//		
//		gp.obj[3] = new Door(gp);
//		gp.obj[3].setEntityGraphic(new EntityGraphic(gp.obj[3]));
//		gp.obj[3].worldX = gp.tileSize * 24;
//		gp.obj[3].worldY = gp.tileSize * 21;
	}
	
	public void setNPC() {
		
		gp.npc[0] = new OldMan(gp);
		gp.npc[0].setEntityGraphic(new EntityGraphic(gp.npc[0]));
		gp.npc[0].worldX = gp.tileSize * 23;
		gp.npc[0].worldY = gp.tileSize * 22;
	}
	
	public void setMonster() {
		
		gp.monster[0] = new GreenSlime(gp);
		gp.monster[0].setEntityGraphic(new EntityGraphic(gp.monster[0]));
		gp.monster[0].worldX = gp.tileSize * 24;
		gp.monster[0].worldY = gp.tileSize * 22;
		
		gp.monster[1] = new GreenSlime(gp);
		gp.monster[1].setEntityGraphic(new EntityGraphic(gp.monster[1]));
		gp.monster[1].worldX = gp.tileSize * 21;
		gp.monster[1].worldY = gp.tileSize * 22;
		
		gp.monster[2] = new Dragon(gp);
		gp.monster[2].setEntityGraphic(new EntityGraphic(gp.monster[2]));
		gp.monster[2].worldX = gp.tileSize * 26;
		gp.monster[2].worldY = gp.tileSize * 22;
		
		gp.monster[3] = new Dragon(gp);
		gp.monster[3].setEntityGraphic(new EntityGraphic(gp.monster[3]));
		gp.monster[3].worldX = gp.tileSize * 29;
		gp.monster[3].worldY = gp.tileSize * 22;
		
		gp.monster[3] = new Dragon(gp);
		gp.monster[3].setEntityGraphic(new EntityGraphic(gp.monster[3]));
		gp.monster[3].worldX = gp.tileSize * 0;
		gp.monster[3].worldY = gp.tileSize * 34;
	}
}
