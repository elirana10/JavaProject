package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import algorithms.mazeGenerators.GrowingTreeGenerator;

public class DisplayMazeWindow extends DialogWindow {

	private MazeDisplay mazeDisplay;
	public DisplayMazeWindow(MazeDisplay mazeDisplay) {
		this.mazeDisplay = mazeDisplay;
	}
	@Override
	protected void initWidgets() {
		shell.setText("Display Maze");
		shell.setSize(300, 200);
		
		shell.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name: ");
		
		List lstMazes = new List(shell, SWT.SINGLE | SWT.BORDER);
		lstMazes.setItems("a b c".split(" "));
		
		Button display = new Button(shell, SWT.PUSH);
		display.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		display.addSelectionListener(new SelectionListener() {
		
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeDisplay.setMazeData(new GrowingTreeGenerator().generate(1, 3, 3, 3).getCrossSectionByX(1));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
