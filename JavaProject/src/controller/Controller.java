/**
* The controller interface acts on both model and view. It controls the data flow into model object and updates the view whenever data changes.
* It keeps view and model separate.
*   
* @author  Eliran Amar & Yoni Kfir
* @version 1.0
* @since   2016-13-09 
*/

package controller;

public interface Controller {
	
	void notifyMazeIsReady(String name);
	void notifySolutionIsReady(String name);
}
