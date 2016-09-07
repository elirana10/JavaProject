package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Random;

public class SimpleMaze3dGenerator extends CommonGenerator {

	private Random rand = new Random();
	private static final float WALLS_RATIO = 0.5F;
	
	public SimpleMaze3dGenerator() {
	}
	
	private Position chooseRandomPosition(Maze3d maze) {
		int x,y,z;
		int[][][] m = maze.getMaze();
		do{
			x = rand.nextInt(maze.getFloors());
			y = rand.nextInt(maze.getRows());	
			z = rand.nextInt(maze.getCols());
		}while(m[x][y][z] == 1);
		
		return new Position(x,y,z);
	}

	@Override
	public Maze3d generate(int sizeX, int sizeY, int sizeZ) {
		Maze3d maze = new Maze3d(sizeX, sizeY, sizeZ);
		
		int wallsNum = (int)(WALLS_RATIO * sizeY * sizeZ);
		for (int x=0; x<sizeX; x++){
			for (int i = 0; i < wallsNum; i++) {			
				int y = rand.nextInt(sizeY);
				int z = rand.nextInt(sizeZ);					
				maze.setWall(x,y,z);
			}
		}
		// Choose a random entrance
		Position startPos =  chooseRandomPosition(maze);
		maze.setStartPosition(startPos);
		ArrayList<Position> moves=maze.getPossibleMoves(startPos);
		int i = 0, c=0;
		Position nextPos = null;
		while(moves.size()>0 && c<10)
		{
			i = rand.nextInt(moves.size());
			nextPos = moves.get(i);
			moves=maze.getPossibleMoves(nextPos);
			c++;
		}
		maze.setGoalPosition(nextPos);	
		return maze;		
	}
}
