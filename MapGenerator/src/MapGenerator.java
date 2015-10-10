import java.util.*;

public class MapGenerator {

	public static Scanner scan = new Scanner(System.in);
	public static Random rand = new Random();
	
	public static int islandLimit = 0;
	
	public static void main(String[] args) {
		
		int mapWidth = 0;
		int mapHeight = 0;
		int numIslands = 0;
		
		mapWidth = setMapWidth();
		mapHeight = setMapHeight();
		numIslands = setNumIslands(mapWidth*mapHeight);
		
		System.out.println("Map width: " + mapWidth);
		System.out.println("Map height: " + mapHeight);
		System.out.println("Number of islands: " + numIslands);
		System.out.println();
		
		Map myMap = new Map(mapWidth, mapHeight, numIslands, islandLimit);
		
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("Here is your map!");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////");
		myMap.printMap();
	}

	public static int setMapWidth() {
		
		String answer;
		int mapWidth = 0;
		boolean validInput = false;
		
		do {
			System.out.print("Please enter a width (type random for a random width): ");
			
			if(!scan.hasNextInt() ) {
		        
				answer = scan.next();
				if (answer.equalsIgnoreCase("random")) {
					
					mapWidth = rand.nextInt(71) + 80;
					validInput = true;
				}
				else {
					
					System.out.println("Invalid input");
				}
		    }
			else {
				
				mapWidth = scan.nextInt();
				
				if ((mapWidth < 80) || (mapWidth > 150)) {
					
					System.out.println("Invalid width");
				}
				else {
					
					validInput = true;
				}
			}
		} while (!validInput);
		
		return mapWidth;
	}
	
	public static int setMapHeight() {
		
		String answer;
		int mapHeight = 0;
		boolean validInput = false;
		
		do {
			System.out.print("Please enter a height (type random for a random height): ");
			
			if(!scan.hasNextInt() ) { 
		        
				answer = scan.next();
				if (answer.equalsIgnoreCase("random")) {
					
					mapHeight = rand.nextInt(21) + 30;
					validInput = true;
				}
				else {
					
					System.out.println("invalid Input");
				}
		    } 
			else {
				
				mapHeight = scan.nextInt();
				
				if ((mapHeight < 30) || (mapHeight > 50)) {
					
					System.out.println("Invalid height");
				}
				else {
					
					validInput = true;
				}
			}
		} while (!validInput);
		
		return mapHeight;
	}

	public static int setNumIslands(int area) {
		
		String answer;
		int numIslands = 0;
		boolean validInput = false;
		
		islandLimit = islandLimit(area);
		
		
		
		do {
			System.out.print("Please enter a number of islands (type random for a random number): ");
			
			if(!scan.hasNextInt() ) { 
		        
				answer = scan.next();
				if (answer.equalsIgnoreCase("random")) {
					
					numIslands = rand.nextInt(islandLimit) + 1;
					validInput = true;
				}
				else System.out.println("Invalid input");
		    } 
			else {
				
				numIslands = scan.nextInt();
				
				if ((numIslands < 1) || (numIslands > islandLimit)) {
					
					System.out.println("Invalid number");
				}
				else validInput = true;
			}
		} while (!validInput);
		
		return numIslands;
	} 
	
	public static int islandLimit(int area) {
		
		area *= 0.3;
		
		int islandLimit;
		
		islandLimit = (int) (area/90);
		
		return islandLimit;
	}
}