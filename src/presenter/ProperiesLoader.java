package presenter;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProperiesLoader {
	private static ProperiesLoader instance;
	private Properties properties;
	
	private ProperiesLoader() {
		XMLDecoder xml = null;

			try {
				xml = new XMLDecoder(new FileInputStream("properties.xml"));
//				xml = new XMLDecoder(getClass().getClassLoader().getResourceAsStream("/JavaProject_D/properties.xml"));
				
				properties = (Properties)xml.readObject();
//			} catch (FileNotFoundException e) {
			} catch (Exception e) {
				e.printStackTrace(System.out);
//				e.printStackTrace();
			} finally {
				xml.close();
			}
	}
	
	public ProperiesLoader(File f) {
		XMLDecoder xml = null;

		try {
			xml = new XMLDecoder(new FileInputStream(f.getAbsolutePath()));
			properties = (Properties)xml.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			xml.close();
		}
		
	}
	public Properties getProperties() {
		return this.properties;
	}
	
	public static ProperiesLoader getInstance() {
		if (instance == null)
			instance = new ProperiesLoader();
		return instance;
	}
}
