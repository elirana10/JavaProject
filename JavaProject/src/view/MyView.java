package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;
import controller.Controller;

public class MyView implements View {
	
	private CLI cli;
	private Controller c;
	private BufferedReader in;
	private PrintWriter out;
	
	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		
		this.cli = new CLI(in, out);
	}
	
	@Override
	public void setController(Controller c) {
		this.c = c;
	}
	
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		cli.setCommands(commands);
	}
	
	@Override
	public void notifyMazeIsReady(String name) {
		out.println("Maze " + name + " is ready");
		out.flush();
	}
	
	@Override
	public void displayMaze(Maze3d maze) {
		out.println(maze);
		out.flush();
	}
	public void display2dMaze(int[][] maze) {		
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < maze.length; x++) {
				for (int y = 0; x < maze[0].length; y++) {
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

}
