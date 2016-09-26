package view;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

public class MazeWindow extends BasicWindow implements View {
	private MazeDisplay mazeDisplay;
	
	@Override
	public void start() {
		this.run();
	}

	@Override
	public void InitWidgets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
//		mazeDisplay.setMazeData(maze.getMaze());
		
	}

	@Override
	public void display2dMaze(int[][] maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayFile(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHelp(HashMap<String, String> commandDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMSG(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeAllFiles() {
		// TODO Auto-generated method stub
		
	}

}
