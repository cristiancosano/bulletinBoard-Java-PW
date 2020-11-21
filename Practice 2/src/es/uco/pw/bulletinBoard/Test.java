package es.uco.pw.bulletinBoard;

import java.util.Scanner;

import es.uco.pw.bulletinBoard.controllers.BulletinBoardController;
import es.uco.pw.bulletinBoard.controllers.LoginController;
import es.uco.pw.bulletinBoard.controllers.UserManagerController;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.views.ad.AdCreate;
import es.uco.pw.bulletinBoard.views.util.ConsoleUtils;

/**
 * The Class Main.
 */
public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws DAOException 
	 */
	public static void main(String[] args) throws DAOException {
		
	
		while(!LoginController.isLogged()) {
			LoginController.login();
			
			if(!LoginController.isLogged()) {
				System.out.println("User not found in the system. Try again");
			}	
		}
		
			
			ConsoleUtils.clear();

			System.out.println("Welcome to the system "+LoginController.getUser().getName());
			System.out.println();
			
			Boolean exit = false;
			Scanner sc = new Scanner(System.in);

			
			while(!exit) {
				ConsoleUtils.clear();
				
				System.out.println("=== BULLETIN BOARD MANAGER ===");
				System.out.println("1. Create ad");
				System.out.println("2. Publish ad");
				System.out.println("3. Edit ad");
				System.out.println("4. Archive ad");
				System.out.println("5. Search ad by date");
				System.out.println("6. Search ad by interest");
				System.out.println("7. Search ad by owner");
				System.out.println("8. Search ad by recipient");
				System.out.println("9. Subscribe to topics of interest");
				System.out.println("10. Show my bulletin board");
				System.out.println("11. Exit");
				System.out.print("Select an option: ");
				Integer option = sc.nextInt();			
				
				
				ConsoleUtils.clear();
				
				
				switch(option) {
					case 1:
						BulletinBoardController.create(AdCreate.view());
						break;
					case 2:
						BulletinBoardController.publish();
						break;
					case 3:
						BulletinBoardController.edit();
						break;
					case 4:
						BulletinBoardController.archived();
						break;
					case 5:
						BulletinBoardController.searchByDate();
						break;
					case 6:
						BulletinBoardController.searchByInterest();
						break;
					case 7:
						BulletinBoardController.searchByOwner();
						break;
					case 8:
						BulletinBoardController.searchByRecipient();
						break;
					case 9:
						UserManagerController.updateInterest(LoginController.getUser());
						break;
					case 10:
						BulletinBoardController.index();
						break;
					case 11:
						System.out.println("Thank you for use me. Come back soon!");
						exit = true;
						break;	
					default:
						System.out.println("Invalid option");
						break;
				}
				if(!exit) ConsoleUtils.pause();
			}
			sc.close();
		}
	}

