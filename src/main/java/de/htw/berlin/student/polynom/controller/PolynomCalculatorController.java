package de.htw.berlin.student.polynom.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.htw.berlin.student.polynom.io.ConsoleCommunicator;
import de.htw.berlin.student.polynom.model.Polynom;

/**
 * Main Controller for the program flow.
 * 
 * @author Matthias Drummer
 */
public class PolynomCalculatorController {

	private static final Logger LOGGER = Logger.getLogger(PolynomCalculatorController.class);

	private final List<Polynom> polynoms = new ArrayList<Polynom>();

	/**
	 * Go method controls the lifecycle of the application.
	 */
	public void go() {

		ConsoleCommunicator consoleReader = new ConsoleCommunicator();
                

		// consoleReader.getLocale();
		// consoleReader.doGreetings();
		// String polynomialString = consoleReader.getPolynomial();

		// LOGGER.info("PolyString: " + polynomialString);

		// Polynom polynom = PolynomParser.parsePolynomialStringToPolynom(polynomialString);

		// TODO: ask for value for x
		// ask for operations between polys
		// save polys
		// delete polys
	}
}
