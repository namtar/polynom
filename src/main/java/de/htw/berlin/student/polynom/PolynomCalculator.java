package de.htw.berlin.student.polynom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
	 * @see http://sol.cs.hm.edu/dpunkt-java-praktikum/polynomial/Polynomial.java
	 * 
	 * @param poly1 the first polynom
	 * @param poly2 the second polynom
	 * @return the result
	 */
	public Polynom multiply(Polynom poly1, Polynom poly2) {

		if (poly1 == null || poly2 == null) {
			throw new IllegalArgumentException("None of the given polynoms may be null.");
		}

		// check for special cases
		if (poly1.getCoefficients().isEmpty() || poly2.getCoefficients().isEmpty()) {
			return new Polynom(Collections.<BigDecimal> emptyList());
		}
		// if a polynom has one element which value is one then return the other polynom.
		if (poly1.getCoefficients().size() == 1 && poly1.getCoefficients().get(0).equals(new BigDecimal(1))) {
			return poly2;
		}
		if (poly2.getCoefficients().size() == 1 && poly2.getCoefficients().get(0).equals(new BigDecimal(1))) {
			return poly1;
		}

		// do calculation
		BigDecimal[] coeff1 = (BigDecimal[]) poly1.getCoefficients().toArray();
		BigDecimal[] coeff2 = (BigDecimal[]) poly2.getCoefficients().toArray();

		BigDecimal[] multiplied = new BigDecimal[Math.max(coeff1.length, coeff2.length) + 1];
		for (int i = 0; i < coeff1.length; i++) {
			for (int j = 0; j < coeff2.length; j++) {
				int newIndex = i + j;
				if (multiplied[newIndex] == null) {
					multiplied[newIndex] = new BigDecimal(0);
				}
				multiplied[newIndex].add(coeff1[i].multiply(coeff2[j]));
			}
		}

		return new Polynom(Arrays.asList(multiplied));
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

		List<BigDecimal> substractedCoeff = new ArrayList<BigDecimal>();

		// determine the number of iterations to do
		int maxLength = poly1.getCoefficients().size() >= poly2.getCoefficients().size() ? poly1.getCoefficients().size() : poly2.getCoefficients().size();

		for (int i = 0; i < maxLength; i++) {

			BigDecimal coef1 = new BigDecimal(0); // is the coeff of listOne for the index if list has one else zero BigDecimal.
			BigDecimal coef2 = new BigDecimal(0);// is the coeff of listOne for the index if list has one else zero BigDecimal.

			if (i < poly1.getCoefficients().size()) {
				coef1 = poly1.getCoefficients().get(i);
			}
			if (i < poly2.getCoefficients().size()) {
				coef2 = poly2.getCoefficients().get(i);
			}
			substractedCoeff.add(coef1.subtract(coef2));
		}
		result = new Polynom(substractedCoeff);

		return result;
	}

	/**
	 * Adds two given polynoms.
	 * 
	 * @param poly1 the first polynom
	 * @param poly2 the second polynom to be added to the first one
	 * @return the result
	 */
	public Polynom add(Polynom poly1, Polynom poly2) {

		Polynom result = null;

		List<BigDecimal> addedCoeff = new ArrayList<BigDecimal>();

		// determine which polynom has more coefficients
		List<BigDecimal> listOne;
		List<BigDecimal> listTwo;
		if (poly1.getCoefficients().size() >= poly2.getCoefficients().size()) {
			listOne = poly1.getCoefficients();
			listTwo = poly2.getCoefficients();
		} else {
			listOne = poly2.getCoefficients();
			listTwo = poly1.getCoefficients();
		}

		for (int i = 0; i < listOne.size(); i++) {
			BigDecimal coef1 = listOne.get(i);
			BigDecimal coef2 = new BigDecimal(0);
			if (i < listTwo.size()) {
				coef2 = listTwo.get(i);
			}
			addedCoeff.add(coef1.add(coef2));
		}
		result = new Polynom(addedCoeff);

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
