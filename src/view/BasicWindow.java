package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable {
	protected Display display;
	protected Shell shell;
	
	public abstract void InitWidgets();

	@Override
	public void run() {
		display = new Display();
		shell = new Shell(display);
		
		InitWidgets();
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		display.dispose();
	}
}
