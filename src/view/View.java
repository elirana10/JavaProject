package view;

import java.io.File;
import java.util.HashMap;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Presenter;
/**
* The view interface represents the visualization of the data that model contains.
*   
* @author  Eliran Amar and Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public interface View {
	
	void displayMaze(Maze3d maze);
	void display2dMaze(int[][] maze);
	void displayFile(File file);
	void displaySolution(Solution name);
	void displayHelp(HashMap<String, String> commandDescription);
	void displayMSG(String msg);
	void closeAllFiles();
	void start();
		
}
