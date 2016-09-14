package model;

import controller.Controller;

public class MyModel implements Model {
	Controller c;
	
	public MyModel(Controller c) {
		this.c = c;
	}
}
