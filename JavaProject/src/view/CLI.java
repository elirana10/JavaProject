package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CLI {
	private BufferedReader in;
	private PrintWriter out;
	private static final String EXITKEY = "exit";
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.out = out;
		this.in = in;
	}
	public void start() throws IOException {
		String choice = in.readLine();
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				String choice = null;
				try {
					choice = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				while (!choice.equals(EXITKEY)) {
					
					
				}
			}
		});
		
		t.start();

	}
}
