package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import controller.Command;

public class CLI {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;
	
	private static final String EXITKEY = "exit";
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.out = out;
		this.in = in;
	}
	
	private void printMenu() {
		out.println("Menu:\nPlease choose a command: ");
		for (String command : commands.keySet()) {
			out.print(command + ", ");
		}
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
						String[] commandArr = commandLine.split(" ");
						// OPT1 : the [0] command of the Array is the command
						String command = commandArr[0];

						if (!commands.containsKey(command))
							out.print("Command not found\n>> ");

						else {
							Command cmd = commands.get(command);
							String[] args = null;
							
							if (commandArr.length > 1) {
								String commandArgs = commandLine.substring(commandLine.indexOf(" ") + 1);
								args = commandArgs.split(" ");
							}
							cmd.doCommand(args);
							
							if (command.equals(EXITKEY)) {
								break;
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
					} 
					out.print(">> ");
				}
			}
		});
		
		t.start();

	}

	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}
}
