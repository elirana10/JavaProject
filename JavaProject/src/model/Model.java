package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Controller;

public interface Model {
	void setController(Controller c);
	void generateMaze(String name, int method, int floors, int rows, int cols);
	void solveMaze(String name, String Algorithm);
	Maze3d getMaze(String name);
	public Solution getSolution(String name);
	public void addMazetoList(String name, Maze3d maze);
	public void disposeAllThreads();
}
