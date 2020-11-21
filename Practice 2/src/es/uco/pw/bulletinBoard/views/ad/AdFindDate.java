package es.uco.pw.bulletinBoard.views.ad;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * The Class AdFindDate.
 */
public class AdFindDate {
	
	/**
	 * View.
	 *
	 * @return the date
	 */
	public static LocalDate view() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the date for search: (dd/MM/yyyy) ");
		String fechaComoTexto = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(fechaComoTexto, formatter);
		
	}
}
