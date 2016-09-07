package algorithms.mazeGenerators;

public abstract class CommonGenerator implements Maze3dGenerator {

	public CommonGenerator() {
	}

	@Override
	public abstract Maze3d generate(int sizeX, int sizeY, int sizeZ);

	@Override
	public String measureAlgorithmTime(int sizeX, int sizeY, int sizeZ) {
		long bTime = System.currentTimeMillis();
		this.generate(sizeX, sizeY, sizeZ);
		long aTime = System.currentTimeMillis();
		long fTime = aTime-bTime;
		
		return Long.toString(fTime);
	}
}
