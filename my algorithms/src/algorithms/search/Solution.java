package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
* This class is used to create a generic Solution object with a list of the soultion's states
* 
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/


public class Solution<T> {
	private List<State<T>> states = new ArrayList<State<T>>();

	public List<State<T>> getStates() {
		return states;
	}

	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}