package algorithms.mazeGenerators;

public interface Maze3dGenerator {

	Maze3d generate(int method, int sizeX, int sizeY, int sizeZ);
	String measureAlgorithmTime(int method, int sizeX, int sizeY, int sizeZ);
	
}
