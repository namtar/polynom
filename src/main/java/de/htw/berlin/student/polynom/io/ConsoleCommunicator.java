package de.htw.berlin.student.polynom.io;

import java.util.Locale;
import java.util.Scanner;

import de.htw.berlin.student.polynom.i18n.I18nResolver;

/**
 * A communicator class for console input and output handling.
 * 
 * @author Matthias Drummer
 */
public class ConsoleCommunicator {

	Scanner scanner;

	public ConsoleCommunicator() {
	}

	public void getLocale() {

		this.scanner = new Scanner(System.in);

		System.out.println("Please Select your Language.");
		System.out.println("Press 1 for German.");
		System.out.println("Press 2 for Englisch");
		int choice = scanner.nextInt();
		switch (choice) {
			case 1:
				Locale.setDefault(Locale.GERMAN);
				break;
			case 2:
			default:
				Locale.setDefault(Locale.US);

		}
	}

	public void doGreetings() {
		System.out.println("Hello");
	}

	public String getPolynomial() {

		scanner = new Scanner(System.in);

		System.out.print(I18nResolver.getString("resolvePolynomial"));
		String result = "";
		result = scanner.nextLine();

		return result;
	}
}
