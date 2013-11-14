package de.htw.berlin.student.polynom.model;

import java.math.BigDecimal;

/**
 * Helper class that holds the result polynom of a division and the Rest.
 * 
 * @author Matthias Drummer
 */
public class DividedPolynom {

	private Polynom polynom;
	private BigDecimal rest;

	/**
	 * Constructor.
	 * 
	 * @param polynom the polynom which was the result of the division.
	 * @param rest the rest of the division by horner
	 */
	public DividedPolynom(Polynom polynom, BigDecimal rest) {
		this.polynom = polynom;
		this.rest = rest;
	}

	public Polynom getPolynom() {
		return polynom;
	}

	public BigDecimal getRest() {
		return rest;
	}

	@Override
	public String toString() {
		return "DividedPolynom [polynom=" + polynom + ", rest=" + rest + "]";
	}

}
