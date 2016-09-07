package algorithms.search;

import java.util.List;
import java.util.PriorityQueue;

/**
* This is an abstract class for generic common searcher. 
* This class implements the interface Searcher<T>
*   
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	protected int evaluatedNodes;

	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes=0;
	}
	
	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}

	/**
	   * The search method is an abstract method used to implement the search algorithm.
	   * This method gets a Searchable<T> object and returns Solution<T>
	   * @param s The Searchable object
	   * @return Solution<T> The solution for the data structure.  
	   */
	@Override
	public abstract Solution<T> search(Searchable<T> s);

	 protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

	 /**
	   * This method gets a goal state and returns the solution by back tracing the solution states. 
	   * This method gets a State<T> object and returns Solution<T>
	   * @param goalState The goal State of the data structure
	   * @return Solution<T> The solution for the data structure.  
	   */
	public Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		while (currState != null) {		
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		return sol;
	}
}