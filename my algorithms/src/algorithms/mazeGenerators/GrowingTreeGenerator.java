package algorithms.mazeGenerators;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GrowingTreeGenerator extends CommonGenerator {
	private Random rand = new Random();
	private Scanner in;
	
	public GrowingTreeGenerator() {
	}

	@Override
	public Maze3d generate(int sizeX, int sizeY, int sizeZ) {
		Maze3d maze = new Maze3d(sizeX, sizeY, sizeZ);
		List<Position> cells = new ArrayList<Position>();
		Init(maze); //Fill the maze with walls
		Selectable UserSelect=null;		
		Position startPos = chooseRandomPosition(maze); //Choose a random start cell
		maze.setStartPosition(startPos);
		maze.setFree(startPos.getX(), startPos.getY(), startPos.getZ());
		cells.add(startPos);
		
		System.out.println("Please enter select method:");
		System.out.println("1. Select Last Neighbor");
		System.out.println("2. Select Random Neighbor");
		in = new Scanner(System.in);
		int choice=in.nextInt();
		if(choice == 1)
			UserSelect = new SelectLastNBR();
		else if(choice == 2)
			UserSelect = new SelectRandNBR();

		while (!cells.isEmpty()) { // TODO: Enable the user to choose the selection method
			
			Position p = cells.get(cells.size() - 1);	
			List<Position> neighbors = findUnvisitedNeighbors(maze, p);	
			if (!neighbors.isEmpty()) {
				int i = UserSelect.selectNBR(neighbors);
				Position nbr = neighbors.get(i);
				delWallBetweenCells(maze, p, nbr); // Delete the wall between the current cell and the neighbor			
				cells.add(nbr);
			} 
			else 
				cells.remove(p);
		}		
		
		Position goalPosition = chooseRandomGoalPosition(maze);
		maze.setGoalPosition(goalPosition);
		
		return maze;
	}

	private void Init(Maze3d maze) {
		for (int x = 0; x < maze.getFloors(); x++) {
			for (int y = 0; y < maze.getRows(); y++) {
				for (int z = 0; z < maze.getCols(); z++) {
					maze.setWall(x, y, z);
				}
			}
		}
	}
	
	private Position chooseRandomPosition(Maze3d maze) {	
		int x = rand.nextInt(maze.getFloors());
		while (x % 2 != 0) {
			x = rand.nextInt(maze.getFloors());
		}
		int y = rand.nextInt(maze.getRows());
		while (y % 2 != 0) {
			y = rand.nextInt(maze.getRows());
		}
		int z = rand.nextInt(maze.getCols());
		while (z % 2 != 0) {
			z = rand.nextInt(maze.getCols());
		}
		return new Position(x, y, z);
	}
	
	private List<Position> findUnvisitedNeighbors(Maze3d maze, Position p) {
		int x=p.getX(), y=p.getY(), z=p.getZ();
		int[][][] m = maze.getMaze();
		List<Position> neighbors = new ArrayList<Position>();
		
		if (z-2 >= 1 && m[x][y][z-2] == 1) 
			neighbors.add(new Position(x,y,z-2));
		if (z+2 < maze.getCols()-1 && m[x][y][z+2] == 1)
			neighbors.add(new Position(x,y,z+2));
		if (y-2 >= 1 && m[x][y-2][z] == 1)
			neighbors.add(new Position(x,y-2,z));
		if (y+2 < maze.getRows()-1 && m[x][y+2][z] == 1)
			neighbors.add(new Position(x,y+2,z));
		if (x-2 >= 1 && m[x-2][y][z] == 1)
			neighbors.add(new Position(x-2,y,z));
		if (x+2 < maze.getFloors() && m[x+2][y][z] == 1)
			neighbors.add(new Position(x+2,y,z));
		return neighbors;
	}	
	
	private void delWallBetweenCells(Maze3d maze, Position p, Position nbr) {
		int nX=nbr.getX(), nY=nbr.getY(), nZ=nbr.getZ();
		int pX=p.getX(), pY=p.getY(), pZ=p.getZ();
		if (nZ == pZ+2) {
			maze.setFree(pX,pY,pZ+1);
			maze.setFree(pX,pY,pZ+2);
		}
		else if (nZ == pZ-2) {
			maze.setFree(pX,pY,pZ-1);
			maze.setFree(pX,pY,pZ-2);
		}
		else if (nY == pY+2) {
			maze.setFree(pX,pY+1,pZ);
			maze.setFree(pX,pY+2,pZ);
		}
		else if (nY == pY-2) {
			maze.setFree(pX,pY-1,pZ);
			maze.setFree(pX,pY-2,pZ);
		}
		else if (nX == pX+2) {
			maze.setFree(pX+1,pY,pZ);
			maze.setFree(pX+2,pY,pZ);
		}
		else if (nX == pX-2) {
			maze.setFree(pX-1,pY,pZ);
			maze.setFree(pX-2,pY,pZ);
		}
	}
	
	private Position chooseRandomGoalPosition(Maze3d maze) {	
		int[][][] m = maze.getMaze();
		int x = rand.nextInt(maze.getFloors());
		int y = rand.nextInt(maze.getRows());
		int z = rand.nextInt(maze.getCols());
		while (m[x][y][z] == 1 || (x==maze.getStartPosition().x && y==maze.getStartPosition().y && z==maze.getStartPosition().z)) {
			x = rand.nextInt(maze.getFloors());
			y = rand.nextInt(maze.getRows());
			z = rand.nextInt(maze.getCols());
		}				
		return new Position(x,y,z);	
	}
}
