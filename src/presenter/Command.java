package presenter;
/**
* Command interface used to create a user command which runs from the CLI.
*   
* @author  Eliran Amar and Johnathan Kfir
* @version 1.0
* @since   2016-13-09 
*/
public interface Command {
		
	void doCommand(String[] args);
}
