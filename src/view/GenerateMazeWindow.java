package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class GenerateMazeWindow extends DialogWindow {

	@Override
	protected void initWidgets() {
		this.shell.setText("Generate Maze Window");
		this.shell.setSize(300, 200);
		
		this.shell.setLayout(new GridLayout(2, false));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblMethod = new Label(shell, SWT.NONE);
		lblMethod.setText("Method: ");
		
		Text txtMethod = new Text(shell, SWT.BORDER);
		txtMethod.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblFloors = new Label(shell, SWT.NONE);
		lblFloors.setText("Floors: ");
		
		Text txtFloors = new Text(shell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		
		Text txtRows = new Text(shell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		
		Text txtCols = new Text(shell, SWT.BORDER);
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button generateMazeBtn = new Button(shell, SWT.PUSH);
		generateMazeBtn.setText("Generate!");
		shell.setDefaultButton(generateMazeBtn);
		generateMazeBtn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		
		generateMazeBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("generate_3d_maze " + txtName.getText() + " " + Integer.parseInt(txtMethod.getText()) + " "
						+ Integer.parseInt(txtFloors.getText()) + " " + Integer.parseInt(txtRows.getText()) + " " + Integer.parseInt(txtCols.getText()));
				MessageBox msg = new MessageBox(shell);
				msg.setMessage("Generating maze...");
				msg.open();
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
