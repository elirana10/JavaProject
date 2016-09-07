package algorithms.search;

import java.util.HashSet;
import java.util.List;

/**
* The Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
* It starts at the tree root and explores the neighbor nodes first, before moving to the next level neighbors.
* This class extends CommonSearcher<T> class.
*   
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public class BFS<T> extends CommonSearcher<T> {
	
	/**
	   * The search method is used to implement the BFS algorithm.
	   * This method gets a Searchable<T> object and returns Solution<T>
	   * @param s The Searchable object
	   * @return Solution<T> The solution for the data structure.  
	   */
	@Override
	public Solution<T> search(Searchable<T> s) {
		HashSet<State<T>> closedSet = new HashSet<State<T>>();
		
		openList.add(s.getStartState());
		
		while (openList.size()>0) {
			State<T> currState = popOpenList();
			closedSet.add(currState);
			
			if (currState.equals(s.getGoalState())) {
				return backTrace(currState);
			}
			
			List<State<T>> neighbors = s.getAllPossibleStates(currState);
			
			for (State<T> neighbor : neighbors) {
				if (!closedSet.contains(neighbor) && !openList.contains(neighbor)) {
					neighbor.setCameFrom(currState);
					neighbor.setCost(s.getMoveCost(currState, neighbor));
					openList.add(neighbor);
				}
				else {
					double newPathCost = currState.getCost() + s.getMoveCost(currState, neighbor);
					if (neighbor.getCost() > newPathCost) {
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(currState);
						
						if (!openList.contains(neighbor)) {
							openList.add(neighbor);
						} 
						else { // must notify the priority queue about the change of cost
							openList.remove(neighbor);
							openList.add(neighbor);
						}
					}
				}			
			}
		}
		return null;
	}

}
