package tile;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage image;
	
	// determine you can pass this tile or not, if true -> you can not pass, false -> passable
	public boolean collision = false;
}
