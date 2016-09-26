package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.SearchableMaze3d;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import presenter.ProperiesLoader;
import presenter.Properties;
/**
* MyModel class implements the controller interface for Maze3d.
*   
* @author  Eliran Amar & Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public class MyModel extends Observable implements Model {
	private ExecutorService executor;
	private Map<String, Maze3d> mazeList = new HashMap<String,Maze3d>();
	private Map<String,Solution<Position>> solutionList = new HashMap<String,Solution<Position>>();
	private Map<Maze3d,Solution<Position>> solutionCache = new HashMap<Maze3d,Solution<Position>>();
	private Properties properties;
	
	public MyModel() {
		this.properties = ProperiesLoader.getInstance().getProperties();
		this.executor = Executors.newFixedThreadPool(properties.getNumOfThreads());
		loadSolutions();
	}

	@Override
	public void generateMaze(String name, int method, int floors, int rows, int cols) {
//		generateMazeRunnable mazeRunnableGenerator = new generateMazeRunnable(name, method, floors, rows, cols);
		
		executor.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				GrowingTreeGenerator gen = new GrowingTreeGenerator();
				Maze3d maze = gen.generate(method, floors, rows, cols);
				mazeList.put(name, maze);
				setChanged();
				notifyObservers("Maze " + name + " is ready");
				return maze;
			}
		});
	}

	public void solveMaze(String name, String algorithm) {
//		solveMazeRunnable solveMazeRunnable = new solveMazeRunnable(name, algorithm);
		if (solutionList.containsKey(name)) {
			setChanged();
			notifyObservers("Solution for " + name + " is ready (no computing required :)");
		} else {
		executor.submit(new Callable<Solution<Position>>() {

			@Override
			public Solution<Position> call() throws Exception {
				Maze3d maze = mazeList.get(name);
				SearchableMaze3d searchable = new SearchableMaze3d(maze);
				Searcher<Position> alg = getAlgorithms().get(algorithm);
				Solution<Position> sol = alg.search(searchable);
				solutionList.put(name, sol);
				solutionCache.put(maze, sol);
				setChanged();
				notifyObservers("Solution for " + name + " is ready");
				return sol;
			}
		});
		}
	}

	public HashMap<String, Searcher<Position>> getAlgorithms() {
		HashMap<String, Searcher<Position>> algorithms = new HashMap<>();
		algorithms.put("BFS", new BFS<Position>());
		algorithms.put("DFS", new DFS<Position>());
		
		return algorithms;
	}
	
	@Override
	public Maze3d getMaze(String name) {
		return this.mazeList.get(name);
	}
	
	public void addMazetoList(String name, Maze3d maze) {
		this.mazeList.put(name, maze);
	}
	
	public Solution<Position> getSolution(String name) {
		return this.solutionList.get(name);
	}
	
	@SuppressWarnings("unchecked")
	private void loadSolutions() {
		File file = new File("mySolutions.dat");
		if (!file.exists())
			return;
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("mySolutions.dat")));
			mazeList = (Map<String, Maze3d>)ois.readObject();
			solutionList = (Map<String, Solution<Position>>)ois.readObject();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private void saveSolutions() {
		ObjectOutputStream oos = null;
		try {
		    oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("mySolutions.dat")));
			oos.writeObject(mazeList);
			oos.writeObject(solutionList);			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void exit() {
		executor.shutdownNow();
		saveSolutions();
	}

//	@Override
//	public void update(Observable arg0, Object arg1) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
