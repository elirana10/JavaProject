package controller;

public interface Command {
	public void doCommand();
	public void doCommand(String[] args);
}
