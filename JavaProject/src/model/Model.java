package model;

import algorithms.mazeGenerators.Maze3d;
import controller.Controller;

public interface Model {
	void setController(Controller c);
	void generateMaze(String name, int floors, int rows, int cols);
	Maze3d getMaze(String name);
	
}
