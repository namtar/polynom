package de.htw.berlin.student.polynom.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Pojo that represents one polynom.
 * 
 * @author Matthias Drummer
 */
public class Polynom {

	private List<BigDecimal> coefficients;

	/**
	 * Constructor.
	 * 
	 * @param coefficients a list of sorted coefficients. x^0 ... x^n
	 * @param x the value for x
	 */
	public Polynom(List<BigDecimal> coefficients) {
		this.coefficients = coefficients;
	}

	/**
	 * Gets the list of coefficients.
	 * 
	 * @return a list of coefficients
	 */
	public List<BigDecimal> getCoefficients() {
		return coefficients;
	}

	@Override
	public String toString() {
		return "Polynom [coefficients=" + coefficients + "]";
	}

}
