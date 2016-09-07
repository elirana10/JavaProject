package algorithms.mazeGenerators;

public class Position {
	int x, y, z;
	
	public Position(int x, int y, int z) {
		setPosition(x,y,z);
	}
	
	public void setPosition(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "{" + x + "," + y + "," + z + "}";
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public boolean equals(Object obj) {
		Position pos = (Position)obj;
		return (this.x == pos.x && this.y == pos.y && this.z == pos.z);
	}
	
}
