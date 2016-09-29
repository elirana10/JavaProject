package view;

import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

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
			// TODO
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showDisplayWindow();
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

	protected void showDisplayWindow() {
		Shell shellMazeDisplayWindow = new Shell(display);
		shellMazeDisplayWindow.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
		shellMazeDisplayWindow.setText("Display Maze");
		shellMazeDisplayWindow.setSize(300, 200);
		
		shellMazeDisplayWindow.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(shellMazeDisplayWindow, SWT.NONE);
		lblName.setText("Name: ");
		
		Text txtName = new Text(shellMazeDisplayWindow, SWT.NONE);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button displayBtn = new Button(shellMazeDisplayWindow, SWT.PUSH);
		displayBtn.setText("Display");
		displayBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		displayBtn.addSelectionListener(new SelectionListener() {
		
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox msg = new MessageBox(shellMazeDisplayWindow,SWT.OK);
				msg.setMessage("Displaying " + txtName.getText() + " maze");
				msg.open();
				
				setChanged();
				notifyObservers("display " + txtName.getText());
			

				shellMazeDisplayWindow.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		shellMazeDisplayWindow.open();
	}

	protected void showGenerateMazeOption() {
		Shell generateMazeShell = new Shell(display);
		generateMazeShell.setText("Generate Maze Window");
		generateMazeShell.setSize(300, 200);
		
		generateMazeShell.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(generateMazeShell, SWT.NONE);
		lblName.setText("Name: ");
		
		Text txtName = new Text(generateMazeShell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblMethod = new Label(generateMazeShell, SWT.NONE);
		lblMethod.setText("Method: ");
		
		Text txtMethod = new Text(generateMazeShell, SWT.BORDER);
		txtMethod.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblFloors = new Label(generateMazeShell, SWT.NONE);
		lblFloors.setText("Floors: ");
		
		Text txtFloors = new Text(generateMazeShell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblRows = new Label(generateMazeShell, SWT.NONE);
		lblRows.setText("Rows: ");
		
		Text txtRows = new Text(generateMazeShell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblCols = new Label(generateMazeShell, SWT.NONE);
		lblCols.setText("Cols: ");
		
		Text txtCols = new Text(generateMazeShell, SWT.BORDER);
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button generateMazeBtn = new Button(generateMazeShell, SWT.PUSH);
		generateMazeBtn.setText("Generate!");
		generateMazeShell.setDefaultButton(generateMazeBtn);
		generateMazeBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		
		generateMazeBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("generate_3d_maze " + txtName.getText() + " " + Integer.parseInt(txtMethod.getText()) + " " + 
							Integer.parseInt(txtRows.getText()) + " " + Integer.parseInt(txtCols.getText()) + " " +
							Integer.parseInt(txtFloors.getText()));
				MessageBox msg = new MessageBox(shell);
				msg.setMessage("Generating maze...");
				msg.open();
				
				generateMazeShell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		generateMazeShell.open();
	}

	@Override
	public void displayMaze(Maze3d maze) {
		int[][] maze2d = maze.getCrossSectionByZ(0);
		mazeDisplay.setMazeData(maze2d);
		mazeDisplay.redraw();
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
