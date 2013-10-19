package de.htw.berlin.student.polynom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.htw.berlin.student.polynom.model.Polynom;

/**
 * Class that does the calculation for a given polynom.
 * 
 * @author Matthias Drummer
 */
public class PolynomCalculator {

	/**
	 * Evaluates the polynom via the horner schema.
	 * 
	 * @param polynom the {@link Polynom} to evaluate
	 * @param x the value for x used for the evaluation
	 * @see http://rosettacode.org/wiki/Horner's_rule_for_polynomial_evaluation#Java
	 * 
	 * @return the value for the evaluated polynom
	 */
	public BigDecimal evaluate(Polynom polynom, BigDecimal x) {

		BigDecimal result = null;
		List<BigDecimal> coefficients = new ArrayList<BigDecimal>();
		coefficients.addAll(polynom.getCoefficients());

		Collections.reverse(coefficients);

		result = coefficients.get(0);
		// start with the x^n coefficient
		for (int i = 1; i < coefficients.size(); i++) {
			result = (result.multiply(x)).add(coefficients.get(i));
		}

		return result;
	}

	/**
	 * Multiplies two given polynoms.
	 * 
	 * @param poly1 the first polynom
	 * @param poly2 the second polynom
	 * @return the result
	 */
	public Polynom multiply(Polynom poly1, Polynom poly2) {

		Polynom result = null;

		// TODO: implement

		return result;
	}

	/**
	 * Subtracts two poylnoms.
	 * 
	 * @param poly1 the first polynom
	 * @param poly2 the second polynom to substract from the first one
	 * @return the result
	 */
	public Polynom subtract(Polynom poly1, Polynom poly2) {

		Polynom result = null;

		// TODO: implement
		return result;
	}

	/**
	 * Divides two polynoms.
	 * 
	 * @param poly1 the first polynom
	 * @param poly2 the second polynom the first is divided by
	 * @return the result of division
	 */
	public Polynom divideWithHorner(Polynom poly1, Polynom poly2) {

		Polynom result = null;

		// TODO: implement
		return result;
	}

	public void derivate(Polynom polynom) {
		// TODO: implement...... How to derivate a polynom??
		// x^1 => 1x^1-1, x^2 => 2x^2-1
	}
}
