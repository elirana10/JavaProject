package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class CommandsManager {

	private View v;
	private Model m;
		
	public CommandsManager(View v, Model m) {
		this.v = v;
		this.m = m;
	}
	
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int floors = Integer.parseInt(args[1]);
			int rows = Integer.parseInt(args[2]);
			int cols = Integer.parseInt(args[3]);
			m.generateMaze(name, floors, rows, cols);
		}		
	}
	
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = m.getMaze(name);
			v.displayMaze(maze);
		}
		
	}
	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		
		return commands;
	}
	
	

}
