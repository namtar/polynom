package de.htw.berlin.student.polynom;

import java.math.BigDecimal;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.htw.berlin.student.polynom.model.Polynom;

/**
 * Testcase for the {@link PolynomCalculator}.
 * 
 * @author Matthias Drummer
 */
public class PolynomCalculatorTest {

	private Polynom p1 = new Polynom(Arrays.asList(new BigDecimal(15), new BigDecimal(-6), new BigDecimal(5), new BigDecimal(-20)));
	private Polynom p2 = new Polynom(Arrays.asList(new BigDecimal(-8), new BigDecimal(9), new BigDecimal(4)));

	private PolynomCalculator testInstance;

	@Before
	public void before() {
		this.testInstance = new PolynomCalculator();
	}

	@Test
	public void dummyTest() {

	}

	/**
	 * Tests the {@link PolynomCalculator#add(Polynom, Polynom)}.ÏÍ
	 */
	@Test
	public void testAddition() {

		Polynom result = testInstance.add(p1, p2);

		Assert.assertEquals(new BigDecimal(7), result.getCoefficients().get(0));
		Assert.assertEquals(new BigDecimal(-20), result.getCoefficients().get(3));
	}

	/**
	 * Tests the {@link PolynomCalculator#derivate(de.htw.berlin.student.polynom.model.Polynom)}.
	 */
	@Test
	public void testDerivation() {

		Polynom result = testInstance.derivate(p1);

		Assert.assertEquals(new BigDecimal(-6), result.getCoefficients().get(0));
		Assert.assertEquals(new BigDecimal(10), result.getCoefficients().get(1));
		Assert.assertEquals(new BigDecimal(-60), result.getCoefficients().get(2));
	}
}
