package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

import presenter.Command;
/**
* CLI class creates the Command Line Interface for the user
*   
* @author  Eliran Amar & Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public class CLI extends Observable{
	private BufferedReader in;
	private PrintWriter out;
	
	private static final String EXITKEY = "exit";
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.out = out;
		this.in = in;
	}
	
	private void printMenu() {
		out.println("Menu:\nPlease choose a command: ");
		/*for (String command : commands.keySet()) {
			out.print(command + ", ");
		}
		*/
		out.print("\n>> ");
		out.flush();
	}
	
	public void start() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				printMenu();
				
				while (true) {
					try {
						String commandLine = in.readLine();
						setChanged();
						notifyObservers(commandLine);
							
						if (commandLine.equals(EXITKEY)) {
							break;
							}
						}

					catch (IOException e) {
						e.printStackTrace();
					} 
					out.print(">> ");
				}
			}
		});
		
		t.start();

	}
}
