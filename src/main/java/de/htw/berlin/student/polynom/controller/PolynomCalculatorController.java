package de.htw.berlin.student.polynom.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

	public void go() {

		ConsoleCommunicator consoleReader = new ConsoleCommunicator();

		consoleReader.getLocale();
		consoleReader.doGreetings();
		String polynomialString = consoleReader.getPolynomial();

		LOGGER.info("PolyString: " + polynomialString);

		Polynom polynom = parsePolynomialStringToPolynom(polynomialString);

		// TODO: ask for value for x
		// ask for operations between polys
		// save polys
		// delete polys
	}

	/**
	 * TODO: there is probably a better way to do so. For example with real regex. TODO: write unit test for this
	 * 
	 * @param polynomialString the string to be parsed
	 * @return a {@link Polynom}
	 */
	private Polynom parsePolynomialStringToPolynom(String polynomialString) {
		LOGGER.info("Start parsing of string: " + polynomialString);

		Polynom polynom = null;
		// remove whitespaces
		String poly = polynomialString.trim();
		poly = poly.replace(" ", "");
		poly = poly.replace("-", "+-");

		LOGGER.info("Poly: " + poly);
		String[] splitted = StringUtils.split(poly, "+");

		Map<Integer, BigDecimal> valuesForExponent = new HashMap<Integer, BigDecimal>();

		for (int i = 0; i < splitted.length; i++) {

			String val = splitted[i];

			// split by x if present
			String[] splittedVal = StringUtils.split(val, "x");

			// if no x is present or appendix is x^0 the value represents x^0.
			if (splittedVal.length > 1) {

				// TODO: awwwwwwwww. not very nice.
				String lastChar = String.valueOf(splittedVal[1].toLowerCase().charAt(splittedVal[1].length() - 1));
				int exponent = Integer.valueOf(lastChar);

				valuesForExponent.put(exponent, new BigDecimal(splittedVal[0]));
			} else {
				valuesForExponent.put(0, new BigDecimal(splittedVal[0]));
			}
		}

		// sort ascending. n^0 .... n^n
		List<BigDecimal> coefficients = new ArrayList<BigDecimal>();
		List<Integer> sortedKeys = new ArrayList<Integer>(valuesForExponent.keySet());
		Collections.sort(sortedKeys);
		for (Integer key : sortedKeys) {
			coefficients.add(valuesForExponent.get(key));
		}
		polynom = new Polynom(coefficients);

		LOGGER.info("Polynom: " + polynom);
		return polynom;
	}
}
