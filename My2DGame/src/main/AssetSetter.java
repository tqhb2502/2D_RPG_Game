package main;

import monster.GreenSlime;
import npc.OldMan;
import object.Door;
import object.Key;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {	// set objects that appear in map and their positions
		
//		gp.obj[0] = new OBJ_Door(gp);
//		gp.obj[0].worldX = gp.tileSize * 10;
//		gp.obj[0].worldY = gp.tileSize * 9;
//		
//		gp.obj[1] = new OBJ_Door(gp);
//		gp.obj[1].worldX = gp.tileSize * 22;
//		gp.obj[1].worldY = gp.tileSize * 21;
//		
//		gp.obj[2] = new OBJ_Door(gp);
//		gp.obj[2].worldX = gp.tileSize * 23;
//		gp.obj[2].worldY = gp.tileSize * 21;
//		
//		gp.obj[3] = new OBJ_Door(gp);
//		gp.obj[3].worldX = gp.tileSize * 24;
//		gp.obj[3].worldY = gp.tileSize * 21;
//		
//		gp.obj[4] = new OBJ_Door(gp);
//		gp.obj[4].worldX = gp.tileSize * 25;
//		gp.obj[4].worldY = gp.tileSize * 21;
//		
//		gp.obj[5] = new OBJ_Key(gp);
//		gp.obj[5].worldX = gp.tileSize * 9;
//		gp.obj[5].worldY = gp.tileSize * 9;
//		
//		gp.obj[6] = new OBJ_Key(gp);
//		gp.obj[6].worldX = gp.tileSize * 24;
//		gp.obj[6].worldY = gp.tileSize * 23;
//		
//		gp.obj[7] = new OBJ_Key(gp);
//		gp.obj[7].worldX = gp.tileSize * 25;
//		gp.obj[7].worldY = gp.tileSize * 23;
//		
//		gp.obj[8] = new OBJ_Key(gp);
//		gp.obj[8].worldX = gp.tileSize * 22;
//		gp.obj[8].worldY = gp.tileSize * 23;
//		
//		gp.obj[9] = new OBJ_Key(gp);
//		gp.obj[9].worldX = gp.tileSize * 21;
//		gp.obj[9].worldY = gp.tileSize * 23;
	}
	
	public void setNPC() {
		
		gp.npc[0] = new OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 23;
		gp.npc[0].worldY = gp.tileSize * 22;
	}
	
	public void setMonster() {
		
		gp.monster[0] = new GreenSlime(gp);
		gp.monster[0].worldX = gp.tileSize * 24;
		gp.monster[0].worldY = gp.tileSize * 22;
		
		gp.monster[1] = new GreenSlime(gp);
		gp.monster[1].worldX = gp.tileSize * 21;
		gp.monster[1].worldY = gp.tileSize * 22;
	}
}
