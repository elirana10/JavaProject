package algorithms.search;

/**
* This class is used to create a generic State object with key, cost, value and cameFrom state.
* This class implement the Comparable interface
* 
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public class State<T> implements Comparable<State<T>> {
	private State<T> cameFrom;
	private double cost;
	private T value;
	private String key;
	
	public State(String key, State<T> cameFrom, double cost, T value) {
		this.setKey(key);
		this.setCameFrom(cameFrom);
		this.setCost(cost);
		this.setValue(value);
	}
	
	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		State<T> s = (State<T>)obj;
		return (s.getValue().equals(this.getValue()));
	}
	
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	
	}
	
}
