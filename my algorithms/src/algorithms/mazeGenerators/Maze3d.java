package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Maze3d {
	
	private int[][][] maze;
	private int  floors, rows, cols;
	private Position startPosition;
	private Position goalPosition;
		
	public Maze3d(int SizeX, int SizeY, int SizeZ) {
		this.floors = SizeX;
		this.rows = SizeY;
		this.cols = SizeZ;
		this.maze = new int[SizeX][SizeY][SizeZ];
		this.startPosition=new Position(0,0,0);
		this.goalPosition=new Position(floors-1,rows-1,cols-1);
		Init();
	}

	public Maze3d(byte[] byteArr) {
		int i = 0;
		//Initialize the maze and it's size 
		this.floors = byteArr[i++];
		this.rows = byteArr[i++];
		this.cols = byteArr[i++];
		this.maze = new int[floors][rows][cols];
		//Set the start and goal position
		this.setStartPosition(new Position(byteArr[i++],byteArr[i++],byteArr[i++]));
		this.setGoalPosition(new Position(byteArr[i++],byteArr[i++],byteArr[i++]));
		//Set the maze
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					maze[x][y][z] = byteArr[i++];
				}
			}
		}
	}
	
	private void Init() {
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					if(z==0 || z==cols-1)
						setWall(x, y, z);
					else if(y==0 || y==cols-1)
						setWall(x, y, z);
					else 
						setFree(x,y,z);
				}
			}
		}
	}
	
	public int[][][] getMaze() {
		return maze;
	}

	public void setMaze(int[][][] maze) {
		this.maze = maze;
	}
	
	public int getFloors() {
		return floors;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	public void setWall(int x, int y, int z) {
		maze[x][y][z] = 1;
	}
	
	public void setFree(int x, int y, int z) {
		maze[x][y][z] = 0;
	}
	
	public int[][] getCrossSectionByX(int x) {
		if(x>=floors || x<0)
			   throw new IndexOutOfBoundsException();
		int m[][] = new int[rows][cols];
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				m[i][j] = maze[x][i][j];
			}
		}
		return m;
	}
	
	public int[][] getCrossSectionByY(int y) {
		if(y>=rows || y<0)
			   throw new IndexOutOfBoundsException();
		int m[][] = new int[floors][cols];
		for (int i=0; i<floors; i++) {
			for (int j=0; j<cols; j++) {
				m[i][j] = maze[i][y][j];
			}
		}
		return m;
	}
	
	public int[][] getCrossSectionByZ(int z) {
		if(z>=cols || z<0)
			   throw new IndexOutOfBoundsException();
		int m[][] = new int[floors][rows];
		for (int i=0; i<floors; i++) {
			for (int j=0; j<rows; j++) {
				m[i][j] = maze[i][j][z];
			}
		}
		return m;
	}
	
	public ArrayList<Position> getPossibleMoves(Position p) {
		int x=p.getX(), y=p.getY(), z=p.getZ();
		ArrayList<Position> pArr = new ArrayList<Position>();
		
		if(z<cols-1 && maze[x][y][z+1]==0)
			pArr.add(new Position(x,y,z+1));
		
		if(y<rows-1 && maze[x][y+1][z]==0)
			pArr.add(new Position(x,y+1,z));
		
		if(x<floors-1 && maze[x+1][y][z]==0)
			pArr.add(new Position(x+1,y,z));
		
		if(z>0 && maze[x][y][z-1]==0)
			pArr.add(new Position(x,y,z-1));
		
		if(y>0 && maze[x][y-1][z]==0)
			pArr.add(new Position(x,y-1,z));
		
		if(x>0 && maze[x-1][y][z]==0)
			pArr.add(new Position(x-1,y,z));

		return pArr;
	}
	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					if (x == startPosition.getX() && y == startPosition.getY() && z == startPosition.getZ()) 
						sb.append("E");
					else if (x == goalPosition.getX() && y == goalPosition.getY() && z == goalPosition.getZ())
						sb.append("X");
					else
						sb.append(maze[x][y][z]);
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public byte[] toByteArray()
	{
		ArrayList<Byte> bArrLST = new ArrayList<Byte>();
		//Add the size of the maze 
		bArrLST.add((byte)floors);
		bArrLST.add((byte)rows);
		bArrLST.add((byte)cols);
		//Add the start and goal positions
		bArrLST.add((byte)startPosition.x);
		bArrLST.add((byte)startPosition.y);
		bArrLST.add((byte)startPosition.z);
		bArrLST.add((byte)goalPosition.x);
		bArrLST.add((byte)goalPosition.y);
		bArrLST.add((byte)goalPosition.z);
		//Add the maze
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					bArrLST.add((byte)maze[x][y][z]);
				}
			}
		}
		//Copy the ArrayList to the byte array
		byte[] byteArr = new byte[bArrLST.size()];
		for (int i = 0; i < byteArr.length; i++) {
			byteArr[i] = (byte)bArrLST.get(i);
		}
		return byteArr;
	}
	
	@Override
	public boolean equals(Object obj) {
		Maze3d m = (Maze3d)obj;
		if (!this.startPosition.equals(m.startPosition) || !this.goalPosition.equals(m.goalPosition))
			return false;
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					if (this.maze[x][y][z]!=m.maze[x][y][z])
						return false;
				}
			}
		}
		return true;
	}
}
