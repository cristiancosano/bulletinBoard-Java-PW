package es.uco.pw.bulletinBoard.business.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * The Class PropsManager manage props from a filename placed in the root folder of project.
 */
public class PropsManager extends Properties{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1693976433050347831L;
	
	/** The file path. */
	String filePath;
	
	
	/**
	 * Instantiates a new props manager.
	 *
	 * @param fileName the file name
	 */
	public PropsManager(String fileName){
		loadProps(fileName);
	}
	
	/**
	 * Load props from a file.
	 *
	 * @param fileName the file name
	 */
	private void loadProps(String fileName) {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		
		try {
			rootPath = URLDecoder.decode(rootPath, "UTF-8");
			this.filePath = rootPath+fileName;
			this.load(new FileInputStream(this.filePath));
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
