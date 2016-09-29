package boot;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class RunTemp {

	public static void main(String[] args) {
		Properties p = new Properties();
		p.setNumOfThreads(10);
		p.setAlgorithm_Generate("GrowingTree");
		p.setAlgorithm_Solve("BFS");
		p.setInterface_mode("GUI");
		
		XMLEncoder xml = null;
		try {
			xml = new XMLEncoder(new FileOutputStream("properties.xml"));
//			xml = new XMLEncoder(getClass().getClassLoader().getResourceAsStream("properties.xml"));
			xml.writeObject(p);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			xml.close();
		}
	}

}
