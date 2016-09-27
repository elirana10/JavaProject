package view;

import java.io.File;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

public class MazeWindow extends BasicWindow implements View {
	private MazeDisplay mazeDisplay;

	@Override
	public void InitWidgets() {
		// TODO Auto-generated method stub
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		
		Composite buttons = new Composite(shell, SWT.BORDER);
		RowLayout row = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(row);
		
		Button GenerateMazebtn = new Button(buttons, SWT.PUSH);
		GenerateMazebtn.setText("Generate Maze");
		
		GenerateMazebtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showGenerateMazeOption();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button SolveMazebtn = new Button(buttons, SWT.PUSH); 
		SolveMazebtn.setText("Solve Maze");
		
		SolveMazebtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	protected void showGenerateMazeOption() {
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		
	}

	@Override
	public void display2dMaze(int[][] maze) {
		mazeDisplay.setMazeData(maze);
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

	@Override
	public void start() {
		this.run();
	}

}
