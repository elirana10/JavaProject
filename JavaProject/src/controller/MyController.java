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
//	public Maze3dGenerator getMazeType() {
//		return v.getMazeType();
//	}
}
