package algorithms.demo;

import java.util.ArrayList;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
* This class is an object adapter for Searchable and Maze3d.
* The class implements Searchable class and includes a Maze3d datamember.
*   
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public class SearchableMaze3d implements Searchable<Position> {

	private Maze3d maze;
	State<Position> start;
	State<Position> goal;
	 
	/**
	   * The constructor method which initialize the SearchableMaze3d object 
	   * and the Start and Goal states.
	   * @param m The generated maze
	   */
	public SearchableMaze3d(Maze3d m) {
		this.maze=m;
		this.start = new State<Position>(maze.getStartPosition().toString(),null, 0, maze.getStartPosition());
		this.goal  = new State<Position>(maze.getGoalPosition().toString(),null, 0, maze.getGoalPosition());
	}

	@Override
	public State<Position> getStartState() {
		return start;
	}
	
	@Override
	public void setStartState(State<Position> state) {
		start=state;;
	}
	
	@Override
	public State<Position> getGoalState() {
		return goal;
	}
	
	@Override
	public void setGoalState(State<Position> state) {
		goal=state;
	}
	
	/**
	   * This method gets a State object and returns a list of all the possible states
	   * (or neighbors) of this State
	   * @param s The Position State
	   * @return List<State<Position>> List of all the possible Position States. 
	   */
	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {
		List<State<Position>> ls = new ArrayList<State<Position>>();
		List<Position> possibleMoves = maze.getPossibleMoves(s.getValue());
		for(Position p: possibleMoves)
		{
			if(s.getCameFrom()==null || !p.equals(s.getCameFrom().getValue()))
				ls.add(new State<Position>(p.toString(),null,0,p));
		}
		return ls;
	}

	/**
	   * This method gets a two State objects and returns the cost to move from the first to the second
	   * @param currState The Current Position State
	   * @param neighbor The Neighbor Position State
	   * @return double The cost to move between the states. 
	   */
	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {
		return currState.getCost()+10;
	}

}
