package de.htw.berlin.student.polynom.model;

/**
 * A tuple class that holds exaclty two polynoms for mathematical operations.
 * 
 * @author Matthias Drummer
 */
public class PolynomTuple {

	private Polynom poly1;
	private Polynom poly2;

	/**
	 * Constructor.
	 * 
	 * @param poly1 the first {@link Polynom}
	 * @param poly2 the second {@link Polynom}
	 */
	public PolynomTuple(Polynom poly1, Polynom poly2) {

		if (poly1 == null || poly2 == null) {
			throw new IllegalArgumentException("Both given polynoms may not be null.");
		}

		this.poly1 = poly1;
		this.poly2 = poly2;
	}

	public Polynom getPoly1() {
		return poly1;
	}

	public Polynom getPoly2() {
		return poly2;
	}

	@Override
	public String toString() {
		return "PolynomTuple [poly1=" + poly1 + ", poly2=" + poly2 + "]";
	}

}
