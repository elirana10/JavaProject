package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import controller.Controller;

public class MyModel implements Model {
	private Controller c;
	private List<Thread> threads = new ArrayList<Thread>();
	private Map<String, Maze3d> mazeList = new HashMap<String,Maze3d>();
	
	class generateMazeRunnable implements Runnable {
		
		private String name;
		private int floors;
		private int rows;
		private int cols;
		private Maze3dGenerator generator;
		private Maze3d maze;
		
		public generateMazeRunnable(String name, int floors, int rows, int cols) {
			this.name = name;
			this.floors = floors;
			this.rows = rows;
			this.cols = cols;
			
			this.generator = new GrowingTreeGenerator(); 
		}
		
		@Override
		public void run() {
			this.maze = generator.generate(floors, rows, cols);
			mazeList.put(name, maze);
			c.notifyMazeIsReady(name);
		}
	}
	
	public void setController(Controller c) {
		this.c = c;
	}

	public MyModel() {
	}

	@Override
	public synchronized void generateMaze(String name, int floors, int rows, int cols) {
		generateMazeRunnable mazeRunnableGenerator = new generateMazeRunnable(name, floors, rows, cols);
		Thread t = new Thread(mazeRunnableGenerator);
		threads.add(t);
		t.start();
	}

	@Override
	public Maze3d getMaze(String name) {
		return this.mazeList.get(name);
	}
	
}
