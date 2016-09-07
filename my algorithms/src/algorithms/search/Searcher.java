package algorithms.search;

/**
* This interface is used to implement a Searcher object with a search algorithm
* 
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/


public interface Searcher<T> {
    public Solution<T> search(Searchable<T> s);
    public int getNumberOfNodesEvaluated();
}