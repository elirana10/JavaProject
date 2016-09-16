/**
* MyController class implements the controller interface for Maze3d.
*   
* @author  Eliran Amar & Yoni Kfir
* @version 1.0
* @since   2016-13-09 
*/

package controller;

import algorithms.mazeGenerators.Maze3dGenerator;
import model.Model;
import view.View;

public class MyController implements Controller {
	private View v;
	private Model m;
	private CommandsManager commandsManager;
	
	public MyController(View v, Model m) {
		this.v = v;
		this.m = m;
		commandsManager = new CommandsManager(v, m);
		v.setCommands(commandsManager.getCommandsMap());
	}

	@Override
	public void notifyMazeIsReady(String name) {
		v.notifyMazeIsReady(name);
	}

	@Override
	public void notifySolutionIsReady(String name) {
		v.notifySolutionIsReady(name);
		
	}
}
