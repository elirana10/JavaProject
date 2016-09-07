package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;

public class SelectRandNBR implements Selectable {

	public SelectRandNBR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int selectNBR(List<Position> neighbors) {
		Random rand = new Random();
		int i = rand.nextInt(neighbors.size());
		return i;
	}

}
