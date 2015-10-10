import java.util.*;

public class Map {
	
	public int myMapWidth = 0;
	public int myMapHeight = 0;
	public int myNumIslands = 0;
	public int myIslandAreaLimit = 0;
	
	public int islandSize = 0;
	public int islandNumber = 0;
	public int totalArea = 0;
	
	public char[][] myMap;
	
	public Map(int mapWidth, int mapHeight, int numIslands, int islandLimit) {
			
		Random mass = new Random();
		myMapWidth = mapWidth;
		myMapHeight = mapHeight;
		myNumIslands = numIslands;
		
		myMap = new char[myMapHeight][myMapWidth];
		
		//initializes the array
		for (int y = 0; y < myMapHeight; y++) {
			//System.out.println("array line gets initialized");
			for (int x = 0; x < myMapWidth; x++) {
				//System.out.println("array character gets initialized");
				myMap[y][x] = '.';
			}
		}
		
		//creates each island
		for (int n = 0; n < myNumIslands; n++) {
		
			myIslandAreaLimit = islandAreaLimit(islandLimit, myNumIslands, n, totalArea);
			islandSize = mass.nextInt(myIslandAreaLimit) + 50;
			myMap = sourceIsland(myMap);
			myMap = spreadIsland(myMap, islandLimit, n);
		}
		
		//generates the terrain
		myMap = terrainGenerate(myMap);
	}
	
	//produces island sources with enough room to start growing
	public char[][] sourceIsland(char chunk[][]) {
				
		Random seed = new Random();
				
		int column = 0;
		int row = 0;
		boolean enoughSpace = false;
				
		//starts the conditional process and is ended once a valid source is found.
        while (!enoughSpace) {
					
			enoughSpace = true;
					
			column = seed.nextInt(myMapWidth-33) + 16;
			row = seed.nextInt(myMapHeight-17) + 8;
					
			//runs through each character within a distance of the source to check for other sources
			//if a source is found the test fails and a new source is generated, starting the process again.
			for (int i = (row-4); i <= (row+4); i++) {
						
				for (int j = (column-8); j <= (column+8); j++) {
				
					if (chunk[i][j] == '#') {
								
						enoughSpace = false;
					}
				}
			}
		}
			
		//if the test passes the source is generated and the process continues.
		if (enoughSpace) {
			
			chunk[row][column] = '+';
			islandSize += setOrientation(chunk, row, column, islandSize);
			System.out.println("source is located at: " + row + ", " + column);
		}
		
		return chunk;
	}
			
	//spreads island sources
	public char[][] spreadIsland(char chunk[][], int totalIslands, int islandCount) {

		Random mass = new Random();
		
		int x = 0, y = 0;
				
		System.out.println("the island size is " + islandSize);
		totalArea += islandSize;
				
		for (int i = 1; i < islandSize;) {
			
			//System.out.println("reaches first loop ");
			
			x = mass.nextInt(myMapHeight-7) + 3;
			y = mass.nextInt(myMapWidth-11) + 5;
					
			//System.out.println(x + ", " + y);
			
			boolean enoughSpace = true;
				
			if (chunk[x][y] == '+') {
				
				for (int z = (x-3); z < (x+3); z++) {
						
					//System.out.println("reaches second loop");
					for (int j = (y-5); j < (y+5); j++) {
							
						//System.out.println("reaches third loop");
						if (chunk[z][j] == '#') {
								
							enoughSpace = false;
							//System.out.println(enoughSpace);
						}
					}
				}	
				if (enoughSpace) {
				
					if (chunk[x+1][y] == '.') {
						
						//System.out.println("add to i");
						chunk[x+1][y] = '+';
						i++;
					}
					
					if ((chunk[x-1][y] == '.')) {
					
						//System.out.println("2add to i");
						chunk[x-1][y] = '+';
						i++;
						}
					
					if ((chunk[x][y+1] == '.')) {
					
						//System.out.println("3add to i");
						chunk[x][y+1] = '+';
						i++;
					}
					if ((chunk[x][y-1] == '.')) {
					
						//System.out.println("4add to i");
						chunk[x][y-1] = '+';
						i++;
					}
				}
			}
		}
				
		//converts land to untouchable land so that other islands cannot grow on it
		for (int i = 0; i < myMapHeight; i++) {
					
			for (int j = 0; j < myMapWidth; j++) {
						
				if (chunk[i][j] == '+') {
							
					chunk[i][j] = '#';
				}
			}
		}
				
		return chunk;
	}
	
	//generates the terrain for all land
	public char[][] terrainGenerate(char chunk[][]) {
		
		Random terrain = new Random();
	
		//converts converts untouchable land back to regular land
		for (int i = 0; i < myMapHeight; i++) {
			
			for (int j = 0; j < myMapWidth; j++) {
			
				if (chunk[i][j] == '#') {
				
					chunk[i][j] = '+';
				}
			}
		}
	
		//counts through each line
		for (int i = 0; i < myMapHeight; i++) {
		
			//counts through each character
			for (int j = 0; j < myMapWidth; j++) {
			
				//checks if the character is land
				if (chunk[i][j] == '+') {
				
					int type = terrain.nextInt(20);
				
					//10% chance of mountain
					if (type < 2) {
					
						chunk[i][j] = '^';
					}
					//20% chance of tree
					else if (type < 6) {
					
						chunk[i][j] = '*';
					}
				}
			}
		}
	
		return chunk;
	}

	//limits the size of the island 
	public int islandAreaLimit(int totalIslands, int numIslands, int islandCount, int totalArea) {
		
		int maxIslandSize = 0;
		
		/*System.out.println();
		System.out.println("totalIslands: " + totalIslands);
		System.out.println("numIslands: " + numIslands);
		System.out.println("islandCount: " + islandCount);
		System.out.println("totalArea: " + totalArea);
		System.out.println("total area limit: " + totalIslands*50);
		System.out.println("area limit: " + ((numIslands-islandCount-1)*50));
		*/
		
		maxIslandSize = (totalIslands*50) - ((numIslands-islandCount-1) * 50) - totalArea;
		
		if (maxIslandSize < 451) {
			
			return maxIslandSize;
		}
		else {
			
			return 450;
		}
	}
	
	private static int setOrientation(char chunk[][], int row, int column, int size) {
		
		Random orientate = new Random();
		
		int orientation;
		
		if (size < 75) orientation = -1;
		else if (size < 150) orientation = orientate.nextInt(3);
		else orientation = orientate.nextInt(6);

		switch (orientation) {
		
			case 0:
				for (int i = 0; i <= 8; i++)
					chunk[row+i-4][column+(2*i)-8] = '+';
				return -8;
			case 1:
				for (int i = 0; i <= 16; i++)
					chunk[row][column-8+i] = '+';
				return -16;
			case 2:
				for (int i = 0; i <= 8; i++)
					chunk[row+i-4][column-(2*i)+8] = '+';
				return -8;
			case 3:
				chunk[row-4][column-8] = '+';
				chunk[row-4][column+8] = '+';
				chunk[row+4][column-8] = '+';
				chunk[row+4][column+8] = '+';
				chunk[row][column] = '.';
				return -4;
			case 4: 
				for (int i = 0; i < 8; i++) {
					chunk[row+4][column+(2*i)-8] = '+';
					chunk[row-4][column+(2*i)-8] = '+';
				}
				chunk[row][column] = '.';
				return -16;
			case 5:
				chunk[row][column-8] = '+';
				chunk[row][column+8] = '+';
				chunk[row][column] = '.';
			default: return 0;
		}
	}
	
	//prints out the map
	public void printMap() {
		
		for (int i = 0; i < myMapHeight; i++)
			System.out.println(myMap[i]);
	}
}