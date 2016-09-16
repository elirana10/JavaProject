package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import model.Model;
import view.View;

public class CommandsManager {

	private View v;
	private Model m;
		
	public CommandsManager(View v, Model m) {
		this.v = v;
		this.m = m;
	}
	
	public class generateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int method = Integer.parseInt(args[1]);
			int floors = Integer.parseInt(args[2]);
			int rows = Integer.parseInt(args[3]);
			int cols = Integer.parseInt(args[4]);
			m.generateMaze(name, method, floors, rows, cols);
		}		
	}
	
	public class displayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = m.getMaze(name);
			v.displayMaze(maze);
		}	
	}
	
	public class displayCrossSection implements Command {

		@Override
		public void doCommand(String[] args){
			String name = args[0];
			int section = Integer.parseInt(args[1]);
			String index = args[2];
			
			Maze3d maze = m.getMaze(name);
			int[][] maze2d = null;
			
			try {
				switch(index) {
				case "X":maze2d = maze.getCrossSectionByX(section);
				case "Y":maze2d = maze.getCrossSectionByY(section);
				case "Z":maze2d = maze.getCrossSectionByZ(section);
				default:throw new Exception("Invalid section inserted");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			v.display2dMaze(maze2d);
			
		}
	}
	
	public class dirCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			String path = args[0];
			getFiles(new File(path));
		}
		public void getFiles(File file) {
			if (file.isFile())
				v.displayFile(file);
			else {
				File[] directories = file.listFiles();
				for(File currFile : directories)
					getFiles(currFile);
			}
		}
	}
	
	public class saveMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String file_name = args[1];
			Maze3d maze = m.getMaze(name);
			try {
				MyCompressorOutputStream mcos = new MyCompressorOutputStream(new FileOutputStream(file_name));
				mcos.write(maze.toByteArray());
				mcos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class loadMazeCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			String name = args[1];
			String file_name = args[0];
			
			try {
				MyDecompressorInputStream mdis = new MyDecompressorInputStream(new FileInputStream(new File(file_name)));
				byte b[] = new byte[mdis.read()];
				mdis.read(b);
				mdis.close();
				
				Maze3d loaded = new Maze3d(b);
				m.addMazetoList(name, loaded);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public class SolveMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String algorithm = args[1];
			
			m.solveMaze(name,algorithm);
		}
	}
	
	public class displaySolutionCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Solution<Position> sol = m.getSolution(name);
			
			v.displaySolution(sol);
		}
		
	}
	
	public class displayHelpCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			v.displayHelp(getCommandDescription());
		}
		
	}
	
	public class ExitProgram implements Command {

		@Override
		public void doCommand(String[] args) {
			m.disposeAllThreads();
			v.closeAllFiles();
			
		}
		
	}
	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("dir", new dirCommand());
		commands.put("generate_3d_maze", new generateMazeCommand());
		commands.put("display", new displayMazeCommand());
		commands.put("display_cross_section", new displayCrossSection());
		commands.put("save_maze", new saveMazeCommand());
		commands.put("load_maze", new loadMazeCommand());
		commands.put("solve", new SolveMazeCommand());
		commands.put("display_solution", new displaySolutionCommand());
		commands.put("help", new displayHelpCommand());
		commands.put("exit", new ExitProgram());
		
		return commands;
	}
	
	public HashMap<String,String> getCommandDescription() {
		HashMap<String, String> description = new HashMap<String, String>();
		description.put("dir", "<path> \t\t\t\t\t\t- View files and folders in given path");
		description.put("generate_3d_maze", "<name> <method> <floors> <rows> <cols>\t- generate 3d maze with values, where mothod  is 1-LastCell 2-RandomCell");
		description.put("display", "<name>\t\t\t\t\t\t- display maze");
		description.put("display_cross_section", "<name> <index> <section>\t\t- display cross section by X,Y,Z");
		description.put("save_maze", "<name> <file_path>\t\t\t\t- save compressed maze to file");
		description.put("load_maze", "<file_path> <name>\t\t\t\t- load decompressed maze from file");
		description.put("solve", "<name> <algorithm>\t\t\t\t- solve maze using given Algorithm");
		description.put("display_solution", "<name>\t\t\t\t\t- display solution for a maze");
		description.put("help", "\t\t\t\t\t\t\t- displays help menu");
		description.put("exit", "\t\t\t\t\t\t\t- exists the program");
		
		return description;
	}

}
