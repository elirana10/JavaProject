package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import controller.Controller;

public class MyModel implements Model {
	private Controller c;
	private List<Thread> threads = new ArrayList<Thread>();
	private Map<String, Maze3d> mazeList = new HashMap<String,Maze3d>();
	private Map<String,Solution> solutionList = new HashMap<String,Solution>();
	
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
		t.start();
		threads.add(t);
	}

	public void solveMaze(String name, String algorithm) {
		solveMazeRunnable solveMazeRunnable = new solveMazeRunnable(name, algorithm);
		Thread t = new Thread(solveMazeRunnable);
		t.start();
		threads.add(t);
	}

	public HashMap<String, Searcher> getAlgorithms() {
		HashMap<String, Searcher> algorithms = new HashMap<>();
		algorithms.put("BFS", new BFS());
		algorithms.put("DFS", new DFS());
		
		return algorithms;
	}
	
	class solveMazeRunnable implements Runnable {
		private String name;
		private Searcher algorithm;
		SearchableMaze3d searchable;
		private Maze3d maze;
		
		public solveMazeRunnable(String name, String algorithm) {
			this.name = name;
			this.maze = getMaze(name);
			try {
				this.algorithm = getAlgorithms().get(algorithm);
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			SearchableMaze3d searchable = new SearchableMaze3d(maze);	
		}
		
		@Override
		public void run() {
			Solution sol = algorithm.search(searchable);
			solutionList.put(name, sol);
			c.notifySolutionIsReady(name);
		}
	}
	
	@Override
	public Maze3d getMaze(String name) {
		return this.mazeList.get(name);
	}
	public Solution getSolution(String name) {
		return this.solutionList.get(name);
	}
	
	public void disposeAllThreads() {
		for (Thread t : threads) {
			t.stop();
		}
	}
	
}
