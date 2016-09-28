package model;

import java.io.File;
import java.util.Map;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
* The model interface represents an object or JAVA POJO carrying data. It can also have logic to update controller if its data changes.
*   
* @author  Eliran Amar & Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public interface Model {
	void generateMaze(String name, int method, int floors, int rows, int cols);
	void solveMaze(String name);
	Maze3d getMaze(String name);
	Solution<Position> getSolution(String name);
	void addMazetoList(String name, Maze3d maze);
	void loadProperties(File f);
	void exit();
}
