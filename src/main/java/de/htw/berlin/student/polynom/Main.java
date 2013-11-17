package de.htw.berlin.student.polynom;

import de.htw.berlin.student.polynom.controller.PolynomCalculatorController;

/**
 * Main class for the polynom program.
 * 
 * @author Matthias Drummer
 */
public class Main {

	public static final int EXIT_SUCCESS = 0;
	public static final int EXIT_ERROR = 1;

	public static void main(String[] args) {

		PolynomCalculatorController controller = new PolynomCalculatorController();
		controller.go();
	}

}
