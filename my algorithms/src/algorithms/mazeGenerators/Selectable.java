package algorithms.mazeGenerators;

import java.util.List;

public interface Selectable {

	int selectNBR(List<Position> neighbors);
}
