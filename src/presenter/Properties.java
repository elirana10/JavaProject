package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numOfThreads;
	private String algorithm_Solve;
	private String  algorithm_Generate;
	private String interface_mode;

	public Properties() {}

	public int getNumOfThreads() {
		return numOfThreads;
	}

	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

	public String getAlgorithm_Solve() {
		return algorithm_Solve;
	}

	public void setAlgorithm_Solve(String algorithm_Solve) {
		this.algorithm_Solve = algorithm_Solve;
	}

	public String getAlgorithm_Generate() {
		return algorithm_Generate;
	}

	public void setAlgorithm_Generate(String algorithm_Generate) {
		this.algorithm_Generate = algorithm_Generate;
	}
	
	public String getInterface_mode() {
		return interface_mode;
	}

	public void setInterface_mode(String interface_mode) {
		this.interface_mode = interface_mode;
	}
}
