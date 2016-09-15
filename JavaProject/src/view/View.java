package view;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

public interface View {
	
	void setController(Controller c);
	void setCommands(HashMap<String, Command> commands);
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void display2dMaze(int[][] maze);
	public void displayFile(File file);
	void notifySolutionIsReady(String name);
	void displaySolution(Solution name);
	void displayHelp(HashMap<String, String> commandDescription);
	void start();
	
	
}
