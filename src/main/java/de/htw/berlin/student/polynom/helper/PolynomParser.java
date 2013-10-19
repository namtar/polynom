package de.htw.berlin.student.polynom.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.htw.berlin.student.polynom.model.Polynom;

/**
 * Helper class to parse a given polynom.
 * 
 * @author Matthias Drummer
 */
public final class PolynomParser {

	private static final Logger LOGGER = Logger.getLogger(PolynomParser.class);

	/**
	 * Private Constructor to prevent unwanted instantiation.
	 */
	private PolynomParser() {
	}

	/**
	 * TODO: there is probably a better way to do so. For example with real regex. TODO: write unit test for this
	 * 
	 * @param polynomialString the string to be parsed
	 * @return a {@link Polynom}
	 */
	public static Polynom parsePolynomialStringToPolynom(String polynomialString) {

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
				// TODO: there is a problem, when only x^1 is given with no leading number. Fix it.
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
