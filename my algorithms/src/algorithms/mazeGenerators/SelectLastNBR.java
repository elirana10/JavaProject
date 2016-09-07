package algorithms.mazeGenerators;

import java.util.List;

public class SelectLastNBR implements Selectable {

	public SelectLastNBR() {
	}

	@Override
	public int selectNBR(List<Position> neighbors) {
		return neighbors.size()-1;
	}

}
