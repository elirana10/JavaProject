package presenter;

import java.io.File;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;
/**
* MyController class implements the controller interface for Maze3d.
*   
* @author  Eliran Amar & Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public class MyPresenter implements Presenter,Observer {
	private View v;
	private Model m;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;
	
	public MyPresenter(View v, Model m) {
		this.v = v;
		this.m = m;

		commandsManager = new CommandsManager(v, m);
		this.commands = commandsManager.getCommandsMap();
	}

	@Override
	public void update(Observable o, Object arg1) {
			if ((o == v)&&(arg1.getClass().getName().equals("String"))) {
				String commandLine = (String) arg1;
				String[] commandArr = commandLine.split(" ");
				String command = commandArr[0];
				if (!commands.containsKey(command))
					v.displayMSG("Command not found");

				else {
					Command cmd = commands.get(command);
					String[] args = null;

					if (commandArr.length > 1) {
						String commandArgs = commandLine.substring(commandLine.indexOf(" ") + 1);
						args = commandArgs.split(" ");
					}
					cmd.doCommand(args);
				} 
			}
			if (o == m) {
				String msg = (String)arg1;
				v.displayMSG((String)arg1);
			}
	
			if ((o.getClass().getName().equals("view.MazeWindow") && (arg1.getClass().getSimpleName().equals("File")))) {
				m.loadProperties((File)arg1);
				
			}
			if (o.getClass().getSimpleName().equals("GenerateMazeWindow")) {
				String cmd = (String)arg1;
				String[] cmdArr = cmd.split(" ");
				String name = cmdArr[1];
				int method = Integer.parseInt(cmdArr[2]);
				int floors = Integer.parseInt(cmdArr[3]);
				int rows = Integer.parseInt(cmdArr[4]);
				int cols = Integer.parseInt(cmdArr[5]);

				m.generateMaze(name, method, floors, rows, cols);
			}
	}
}
