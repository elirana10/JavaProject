package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.MyPresenter;
import view.MazeWindow;
import view.MyView;
import view.View;
/**
* The Run class includes the main method which creates the MVC
* 
* @author  Eliran Amar and Johnathan Kfir
* @version 1.0
* @since   2016-08-27 
*/
public class Run {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
//		View view = null;
//		if (model.getInterface().equals("GUI"))
//			view = new MazeWindow();
//		else
//			view = new MyView(in, out);
		MazeWindow view = new MazeWindow();
//		MyView view = new MyView(in, out);
		MyModel model = new MyModel();
		
		
		MyPresenter presenter = new MyPresenter(view, model);
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.start();
	}

}