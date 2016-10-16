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
public class Run_CLI {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

//		MazeWindow view = new MazeWindow();
		MyView view = new MyView(in, out);
		MyModel model = new MyModel();
		
		
		MyPresenter presenter = new MyPresenter(view, model);
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.start();
	}

}