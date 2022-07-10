package move;

public class Node {
	Node parent;
	
	public int col;
	public int row;
	public int gCost;
	public int hCost;
	public int fCost;
	
	boolean solid;
	boolean open;
	boolean check;
	
	
	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}

}
