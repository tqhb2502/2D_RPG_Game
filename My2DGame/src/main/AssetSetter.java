package main;

import graphic.EntityGraphic;
import graphic.MapGraphic;
import map.MapLoader;
import monster.Dragon;
import monster.GreenSlime;
import npc.OldMan;
import player.Player;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setMap() {
		gp.map1.setMapGraphic(new MapGraphic(gp.map1));
		gp.map1.setMapLoader(new MapLoader(gp.map1));
		gp.map1.mapLoader.loadMap("/maps/world02.txt");
		
		gp.map2.setMapGraphic(new MapGraphic(gp.map2));
		gp.map2.setMapLoader(new MapLoader(gp.map2));
		gp.map2.mapLoader.loadMap("/maps/world03.txt");
		
		gp.map3.setMapGraphic(new MapGraphic(gp.map3));
		gp.map3.setMapLoader(new MapLoader(gp.map3));
		gp.map3.mapLoader.loadMap("/maps/world04.txt");
		
		gp.currentMap = gp.map1;
	}
	
	public void setPlayer() {
		gp.player = new Player(gp, gp.keyH);
		gp.player.setEntityGraphic(new EntityGraphic(gp.player));
		
		// position when game starts
		gp.player.worldX = gp.tileSize * 2;
		gp.player.worldY = gp.tileSize * 1;
	}
	
	public void setObject() {
		
		// reset
		for (int i = 0; i < gp.obj.length; i++) {
			gp.obj[i] = null;
		}
		
		// map 1
		if (gp.currentMap == gp.map1) {
			
		}
		
		// map 2
		else if (gp.currentMap == gp.map2) {
			
		}
		
		// map 3
		else if (gp.currentMap == gp.map3) {
			
		}
	}
	
	public void setNPC() {
		
		// reset
		for (int i = 0; i < gp.npc.length; i++) {
			gp.npc[i] = null;
		}
		
		// map 1
		if (gp.currentMap == gp.map1) {
			gp.npc[0] = new OldMan(gp);
			gp.npc[0].setEntityGraphic(new EntityGraphic(gp.npc[0]));
			gp.npc[0].worldX = gp.tileSize * 14;
			gp.npc[0].worldY = gp.tileSize * 28;
		}
		
		// map 2
		else if (gp.currentMap == gp.map2) {
			
		}
		
		// map 3
		else if (gp.currentMap == gp.map3) {
			
		}
	}
	
	public void setMonster() {
		
		// reset
		for (int i = 0; i < gp.monster.length; i++) {
			gp.monster[i] = null;
		}
		
		// map 1
		if (gp.currentMap == gp.map1) {
			gp.monster[0] = new GreenSlime(gp);
			gp.monster[0].setEntityGraphic(new EntityGraphic(gp.monster[0]));
			gp.monster[0].worldX = gp.tileSize * 5;
			gp.monster[0].worldY = gp.tileSize * 36;
			
			gp.monster[1] = new GreenSlime(gp);
			gp.monster[1].setEntityGraphic(new EntityGraphic(gp.monster[1]));
			gp.monster[1].worldX = gp.tileSize * 11;
			gp.monster[1].worldY = gp.tileSize * 38;
			
			gp.monster[2] = new GreenSlime(gp);
			gp.monster[2].setEntityGraphic(new EntityGraphic(gp.monster[2]));
			gp.monster[2].worldX = gp.tileSize * 46;
			gp.monster[2].worldY = gp.tileSize * 21;
			
			gp.monster[3] = new GreenSlime(gp);
			gp.monster[3].setEntityGraphic(new EntityGraphic(gp.monster[3]));
			gp.monster[3].worldX = gp.tileSize * 36;
			gp.monster[3].worldY = gp.tileSize * 43;
			
			gp.monster[4] = new GreenSlime(gp);
			gp.monster[4].setEntityGraphic(new EntityGraphic(gp.monster[4]));
			gp.monster[4].worldX = gp.tileSize * 17;
			gp.monster[4].worldY = gp.tileSize * 28;
			
			gp.monster[5] = new Dragon(gp);
			gp.monster[5].setEntityGraphic(new EntityGraphic(gp.monster[5]));
			gp.monster[5].worldX = gp.tileSize * 16;
			gp.monster[5].worldY = gp.tileSize * 45;
			
			gp.monster[6] = new Dragon(gp);
			gp.monster[6].setEntityGraphic(new EntityGraphic(gp.monster[6]));
			gp.monster[6].worldX = gp.tileSize * 25;
			gp.monster[6].worldY = gp.tileSize * 24;
			
			gp.monster[7] = new Dragon(gp);
			gp.monster[7].setEntityGraphic(new EntityGraphic(gp.monster[7]));
			gp.monster[7].worldX = gp.tileSize * 42;
			gp.monster[7].worldY = gp.tileSize * 19;
			
			gp.monster[8] = new Dragon(gp);
			gp.monster[8].setEntityGraphic(new EntityGraphic(gp.monster[8]));
			gp.monster[8].worldX = gp.tileSize * 35;
			gp.monster[8].worldY = gp.tileSize * 48;
			
			gp.monster[9] = new Dragon(gp);
			gp.monster[9].setEntityGraphic(new EntityGraphic(gp.monster[9]));
			gp.monster[9].worldX = gp.tileSize * 28;
			gp.monster[9].worldY = gp.tileSize * 42;
		}
		
		// map 2
		else if (gp.currentMap == gp.map2) {
			gp.monster[0] = new GreenSlime(gp);
			gp.monster[0].setEntityGraphic(new EntityGraphic(gp.monster[0]));
			gp.monster[0].worldX = gp.tileSize * 6;
			gp.monster[0].worldY = gp.tileSize * 12;
			
			gp.monster[1] = new GreenSlime(gp);
			gp.monster[1].setEntityGraphic(new EntityGraphic(gp.monster[1]));
			gp.monster[1].worldX = gp.tileSize * 2;
			gp.monster[1].worldY = gp.tileSize * 32;
			
			gp.monster[2] = new GreenSlime(gp);
			gp.monster[2].setEntityGraphic(new EntityGraphic(gp.monster[2]));
			gp.monster[2].worldX = gp.tileSize * 5;
			gp.monster[2].worldY = gp.tileSize * 39;
			
			gp.monster[3] = new GreenSlime(gp);
			gp.monster[3].setEntityGraphic(new EntityGraphic(gp.monster[3]));
			gp.monster[3].worldX = gp.tileSize * 2;
			gp.monster[3].worldY = gp.tileSize * 47;
			
			gp.monster[4] = new GreenSlime(gp);
			gp.monster[4].setEntityGraphic(new EntityGraphic(gp.monster[4]));
			gp.monster[4].worldX = gp.tileSize * 41;
			gp.monster[4].worldY = gp.tileSize * 22;
			
			gp.monster[5] = new Dragon(gp);
			gp.monster[5].setEntityGraphic(new EntityGraphic(gp.monster[5]));
			gp.monster[5].worldX = gp.tileSize * 18;
			gp.monster[5].worldY = gp.tileSize * 12;
			
			gp.monster[6] = new Dragon(gp);
			gp.monster[6].setEntityGraphic(new EntityGraphic(gp.monster[6]));
			gp.monster[6].worldX = gp.tileSize * 18;
			gp.monster[6].worldY = gp.tileSize * 22;
			
			gp.monster[7] = new Dragon(gp);
			gp.monster[7].setEntityGraphic(new EntityGraphic(gp.monster[7]));
			gp.monster[7].worldX = gp.tileSize * 8;
			gp.monster[7].worldY = gp.tileSize * 48;
			
			gp.monster[8] = new Dragon(gp);
			gp.monster[8].setEntityGraphic(new EntityGraphic(gp.monster[8]));
			gp.monster[8].worldX = gp.tileSize * 41;
			gp.monster[8].worldY = gp.tileSize * 5;
			
			gp.monster[9] = new Dragon(gp);
			gp.monster[9].setEntityGraphic(new EntityGraphic(gp.monster[9]));
			gp.monster[9].worldX = gp.tileSize * 42;
			gp.monster[9].worldY = gp.tileSize * 31;
		}
		
		// map 3
		else if (gp.currentMap == gp.map3) {
			gp.monster[0] = new GreenSlime(gp);
			gp.monster[0].setEntityGraphic(new EntityGraphic(gp.monster[0]));
			gp.monster[0].worldX = gp.tileSize * 23;
			gp.monster[0].worldY = gp.tileSize * 12;
			
			gp.monster[1] = new GreenSlime(gp);
			gp.monster[1].setEntityGraphic(new EntityGraphic(gp.monster[1]));
			gp.monster[1].worldX = gp.tileSize * 15;
			gp.monster[1].worldY = gp.tileSize * 30;
			
			gp.monster[2] = new GreenSlime(gp);
			gp.monster[2].setEntityGraphic(new EntityGraphic(gp.monster[2]));
			gp.monster[2].worldX = gp.tileSize * 9;
			gp.monster[2].worldY = gp.tileSize * 27;
			
			gp.monster[3] = new GreenSlime(gp);
			gp.monster[3].setEntityGraphic(new EntityGraphic(gp.monster[3]));
			gp.monster[3].worldX = gp.tileSize * 25;
			gp.monster[3].worldY = gp.tileSize * 47;
			
			gp.monster[4] = new GreenSlime(gp);
			gp.monster[4].setEntityGraphic(new EntityGraphic(gp.monster[4]));
			gp.monster[4].worldX = gp.tileSize * 35;
			gp.monster[4].worldY = gp.tileSize * 41;
			
			gp.monster[5] = new Dragon(gp);
			gp.monster[5].setEntityGraphic(new EntityGraphic(gp.monster[5]));
			gp.monster[5].worldX = gp.tileSize * 29;
			gp.monster[5].worldY = gp.tileSize * 12;
			
			gp.monster[6] = new Dragon(gp);
			gp.monster[6].setEntityGraphic(new EntityGraphic(gp.monster[6]));
			gp.monster[6].worldX = gp.tileSize * 6;
			gp.monster[6].worldY = gp.tileSize * 31;
			
			gp.monster[7] = new Dragon(gp);
			gp.monster[7].setEntityGraphic(new EntityGraphic(gp.monster[7]));
			gp.monster[7].worldX = gp.tileSize * 28;
			gp.monster[7].worldY = gp.tileSize * 42;
			
			gp.monster[8] = new Dragon(gp);
			gp.monster[8].setEntityGraphic(new EntityGraphic(gp.monster[8]));
			gp.monster[8].worldX = gp.tileSize * 32;
			gp.monster[8].worldY = gp.tileSize * 47;
			
			gp.monster[9] = new Dragon(gp);
			gp.monster[9].setEntityGraphic(new EntityGraphic(gp.monster[9]));
			gp.monster[9].worldX = gp.tileSize * 40;
			gp.monster[9].worldY = gp.tileSize * 13;
		}
	}
}
