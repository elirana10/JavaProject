package algorithms.search;

import java.util.List;

/**
* This interface is used to implement a generic Searchable object with start and goal states
* 
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public interface Searchable<T> {
	State<T> getStartState();
	State<T> getGoalState();
	void setStartState(State<T> state);
	void setGoalState(State<T> state);
	List<State<T>> getAllPossibleStates(State<T> s);
	double getMoveCost(State<T> currState, State<T> neighbor);
}