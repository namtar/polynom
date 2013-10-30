package de.htw.berlin.student.polynom.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import de.htw.berlin.student.polynom.i18n.I18nResolver;
import de.htw.berlin.student.polynom.model.Polynom;

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

	public Polynom getPolynomial() {

		scanner = new Scanner(System.in);
		List<BigDecimal> coefficients = new ArrayList<BigDecimal>();

		// TODO: MPI: Frage nach Grad des Polynoms und frage dann entsprechend des Grades die einzelnen Koeffizienten in geordneter Form x^0 ... ab.

		System.out.print(I18nResolver.getString("resolvePolynomial"));

		return new Polynom(coefficients);
	}
}
