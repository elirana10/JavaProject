package algorithms.search;

import java.util.List;

/**
* Depth-first search (DFS) is an generic algorithm for traversing or searching tree or graph data structures. 
* One starts at the root and explores as far as possible along each branch before backtracking.
* This class extends CommonSearcher<T> class.
*   
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public class DFS<T> extends CommonSearcher<T> {
	
	/**
	   * The DFS method is used to implement the DFS algorithm.
	   * This method gets a Searchable<T> and State<T> state objects and builds a solution
	   * @param s The Searchable object
	   * @param state The State object
	   * @return Nothing.
	   */
	private void AlgDFS(Searchable<T> s, State<T> state) {
		evaluatedNodes++;
		if (state.equals(s.getGoalState())) {
			s.setGoalState(state);
			return;
		}
		List<State<T>> neighbors = s.getAllPossibleStates(state);
		for (State<T> neighbor : neighbors) {
			if (!openList.contains(neighbor)) {
				neighbor.setCameFrom(state);
				neighbor.setCost(s.getMoveCost(state, neighbor));
				openList.add(neighbor);
				AlgDFS(s,neighbor);
			}
		}
	}
	
	/**
	   * The search method calls the AlgDFS method.
	   * This method gets a Searchable<T> object and returns Solution<T>
	   * @param s The Searchable object
	   * @return Solution<T> The solution for the data structure.  
	   */
	@Override
	public Solution<T> search(Searchable<T> s) {
		AlgDFS(s,s.getStartState());
		return backTrace(s.getGoalState());
	}

}
