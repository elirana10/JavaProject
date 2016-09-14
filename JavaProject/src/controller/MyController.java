package controller;

import model.Model;
import view.View;

public class MyController implements Controller {
	private View view;
	private Model model;
	
	public MyController(View v, Model m) {
		this.view = v;
		this.model = m; 
	}
}
