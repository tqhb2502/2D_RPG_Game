package path_finder;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {
	
	GamePanel gp;
	
	Node[][] node;
	public ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	
	boolean goalReached = false;
	int step = 0;

	// CONSTRUCTOR
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		makeNodes();
	}

	// instantiate nodes
	public void makeNodes() {
		
		node = new Node[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0, row = 0;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			node[col][row] = new Node(col, row);
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	// reset path finder data
	public void resetNodes() {
		
		int col = 0, row = 0;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			node[col][row].open = false;
			node[col][row].check = false;
			node[col][row].solid = false;
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}

	// set START NODE, GOALNODE and SOLID NODE
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
		
		resetNodes();
		
		// out of border
		if(startCol >= gp.maxWorldCol) startCol = gp.maxWorldCol - 1;
		if(startRow >= gp.maxWorldRow) startRow = gp.maxWorldRow - 1;
		if(startCol < 0) startCol = 0;
		if(startRow < 0) startRow = 0;
		
		startNode = node[startCol][startRow];
		currentNode = startNode;
		
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);

		// solid nodes
		int col = 0, row = 0;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			// check titleNum
			int tileNum = gp.currentMap.data[col][row];
			if (gp.currentMap.tile[tileNum].collision == true && entity.name != "Dragon") {

				node[col][row].solid = true;
			}

			// set cost
			getCost(node[col][row]);
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	// CALCULATE COST
	public void getCost(Node node) {
		// gCost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		// hCost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		// fCost
		node.fCost = node.hCost + node.gCost;

	}

	public boolean search() {
		
		while(goalReached == false &&  step < 600) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			// check current node
			currentNode.check = true;
			openList.remove(currentNode);
			
			// add openlist
			//up
			if(row-1 >=0 ) {
				openNode(node[col][row-1]);
			}
			//down
			if(row+1 < gp.maxWorldRow ) {
				openNode(node[col][row+1]);
			}
			//left
			if(col-1 >=0 ) {
				openNode(node[col-1][row]);
			}
			//right
			if(col+1 < gp.maxWorldCol ) {
				openNode(node[col+1][row]);
			}
			
			// find best node
			int bestNodeIndex = 0;
			int bestNodefCost = 1000;
			
			for(int i = 0; i< openList.size(); i++) {
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
					bestNodeIndex = i;
					}
				}
			}
			
			currentNode = openList.get(bestNodeIndex);
			
			// check condition
			if(openList.size() == 0) {
				break;
			}
				// found
			if(currentNode == goalNode) {
				goalReached = true;
				trackPath();
			}
			
			step++;
		}
		
		return goalReached;
	}
	
	public void openNode(Node node) {
		if(node.open == false && node.check == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackPath() {
		Node currentNode = goalNode;
		while(currentNode != startNode) {
			pathList.add(0,currentNode); // add to head
			currentNode = currentNode.parent;
		}
	}
}
