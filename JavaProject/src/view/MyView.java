package view;

import java.io.BufferedReader;
import java.io.PrintWriter;

import controller.Controller;

public class MyView implements View {
	Controller c;
	private BufferedReader in;
	private PrintWriter out;
	
	public MyView(Controller c, BufferedReader in, PrintWriter out) {
		this.c = c;
		this.in = in;
		this.out = out;
	}
}
