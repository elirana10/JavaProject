package view;

import java.io.File;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

public class MazeWindow extends BasicWindow implements View {
	private MazeDisplay mazeDisplay;
	private Character character;

	@Override
	public void InitWidgets() {
		shell.setText("Maze Generator");
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		
		Menu menu = new Menu(shell, SWT.BAR);

	    Menu fileMenu = new Menu(menu);

	    MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
	    fileItem.setText("File");
	    fileItem.setMenu(fileMenu);

	    MenuItem openItem = new MenuItem(fileMenu, SWT.NONE);
	    openItem.setText("Open Properties");
		
		openItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Load Properties");
				fd.setFilterExtensions(new String[] {"*.xml"});
				File f = new File(fd.open());
				setChanged();
				notifyObservers(f);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("Exit");
		exitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		shell.setMenuBar(menu);
		
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
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnDisplayMaze = new Button(buttons, SWT.PUSH); 
		btnDisplayMaze.setText("Display Maze");
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplayMazeWindow win = new DisplayMazeWindow(mazeDisplay);
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
		this.mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.mazeDisplay.setFocus();
	}

	protected void showGenerateMazeOption() {
		GenerateMazeWindow win = new GenerateMazeWindow();
		win.start(display);
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
		MessageBox msgb = new MessageBox(shell);
		msgb.setMessage(msg);
		msgb.open();
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
