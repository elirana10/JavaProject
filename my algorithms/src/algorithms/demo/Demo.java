package algorithms.demo;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searcher;
import algorithms.search.Solution;

/**
* The Demo class includes the main method which generates a maze3d object with Growing Tree algorithm,
* Prints the maze, and then solve it with BFS and DFS algorithms. 
* The solution and the evaluated nodes of each algorithm are printed to the screen.
* 
* @author  Eliran Amar
* @version 1.0
* @since   2016-08-27 
*/

public class Demo {

	public void run() {
		Maze3dGenerator gn = new GrowingTreeGenerator();
		Maze3d maze = gn.generate(5,30,30);
		SearchableMaze3d sm=new SearchableMaze3d(maze);
		System.out.println(maze.toString());
		
		Searcher<Position> searcher1 = new BFS<Position>();
		Searcher<Position> searcher2 = new DFS<Position>();
		Solution<Position> sol1 = searcher1.search(sm);
		System.out.println(sol1.toString());
		System.out.println(searcher1.getNumberOfNodesEvaluated());
		Solution<Position> sol2 = searcher2.search(sm);
		System.out.println(sol2.toString());
		System.out.println(searcher2.getNumberOfNodesEvaluated());
	}

}
