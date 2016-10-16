package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Properties;
/**
* MyView class implements the controller interface for Maze3d.
*   
* @author  Eliran Amar and Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public class MyView extends Observable implements View,Observer {
	
	private CLI cli;
	private BufferedReader in;
	private PrintWriter out;
	
	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		
		this.cli = new CLI(in, out);
		cli.addObserver(this);
	}

	@Override
	public void displayMaze(Maze3d maze) {
		out.println(maze);
		out.flush();
	}
	public void display2dMaze(int[][] maze) {		
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < maze.length; x++) {
				for (int y = 0; y < maze[0].length; y++) {
						sb.append(maze[y][x]);
				}
				sb.append("\n");
			}
			out.println(sb.toString());
			out.flush();
	}
	public void displayFile(File file) {
		out.println(file);
		out.flush();
	}
	
	@Override
	public void start() {
		cli.start();
	}
	public void displayMSG(String msg) {
		out.println(msg);
		out.flush();
	}

	@Override
	public void displaySolution(Solution name) {
		out.println(name);
		out.flush();
	}
	
	public void closeAllFiles() {
		this.out.close();
		try {
			this.in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void displayHelp(HashMap<String, String> commandDescription) {
			for(String key: commandDescription.keySet())
				out.println(key + " " + commandDescription.get(key));
			out.flush();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			setChanged();
			notifyObservers(arg);
		}
	}
}
