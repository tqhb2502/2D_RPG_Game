package map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLoader {
	
	Map map;
	
	public MapLoader(Map map) {
		this.map = map;
	}
	
	public void loadMap(String filePath) {
		// read map info from .txt map data file
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (row < map.gp.maxWorldRow) {
				String line = br.readLine();	// read a single line in text file
				String numbers[] = line.split(" ");		// split the string at a space
				
				while (col < map.gp.maxWorldCol) {
					
					int num = Integer.parseInt(numbers[col]);		// convert String to Integer
					
					map.data[col][row] = num;		// store the tile number
					col++;
				}
				
				if (col == map.gp.maxWorldCol) {	// move to new line
					col = 0;
					row++;
				}
			}
			
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
