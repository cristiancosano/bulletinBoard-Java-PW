package es.uco.pw.bulletinBoard.views.util;



/**
 * The Class ConsoleUtils has utilities to pause and clear console.
 */
public class ConsoleUtils {
	
	/**
	 * Pause program and wait to press enter to continue.
	 */
	public static void pause() {
		System.out.println("Press enter to continue...");
		try {
			System.in.read();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clear console.
	 */
	public static void clear() {
		String os = System.getProperty("os.name"); 
		if(os.startsWith("Linux") || os.startsWith("Mac OS")) {
			System.out.println("\033[H\033[2J"); // Prints a backspace
			System.out.flush();
		}
		else if(os.startsWith("Windows")) {
			System.out.println("\033[H\033[2J"); // Prints a backspace
			System.out.flush();
			//Runtime.getRuntime().exec("cls");
			
		}
	}
}
