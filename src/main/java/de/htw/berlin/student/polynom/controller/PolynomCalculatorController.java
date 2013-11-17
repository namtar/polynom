package de.htw.berlin.student.polynom.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.htw.berlin.student.polynom.io.ConsoleCommunicator;
import de.htw.berlin.student.polynom.model.DividedPolynom;
import de.htw.berlin.student.polynom.model.Polynom;
import de.htw.berlin.student.polynom.model.PolynomTuple;

/**
 * Main Controller for the program flow.
 * 
 * @author Matthias Drummer
 * @author Marcel Piater
 */
public class PolynomCalculatorController {

	private static final Logger LOGGER = Logger.getLogger(PolynomCalculatorController.class);
	private static final int EXIT_SUCCESS = 0;
	private static final int EXIT_ERROR = 1;

	private final List<Polynom> polynoms = new ArrayList<Polynom>();
	private final ConsoleCommunicator communicator = new ConsoleCommunicator();
	private final PolynomCalculator calculator = new PolynomCalculator();

	/**
	 * Go method controls the lifecycle of the application.
	 */
	public void go() {

		communicator.doGreetings();
		while (true) {
			Operation choose = communicator.choose();

			switch (choose) {
				case CALCULATE:
					menueTwo();
					break;
				case INPUT_POLY:
					polynoms.add(communicator.polyInput());
					break;
				case EXIT:
					communicator.close();
					System.exit(EXIT_SUCCESS);
					break;
				default:
					throw new UnsupportedOperationException("The chosen operation is not yet supported: " + choose.name());
			}

		}

		/**
		 * Menue posibilities: Base Menu: - Enter Polynom - Calculation Submenu - Exit Program Calulation Submenu: -
		 * Enter Polynom - Add - Substract - Mulitply - Divide - Evaluate - Derive - Leave Submenu
		 */

	}

	private void menueTwo() {

		boolean finished = false;
		while (!finished) {
			Operation op = communicator.opperation();

			PolynomTuple tuple = null;
			switch (op) {
				case LEAVE_SUBMENU:
					// back to main menü
					finished = true;
					break;
				case INPUT_POLY:
					Polynom inputPoly = communicator.polyInput();
					polynoms.add(inputPoly);
					communicator.polyOutput(inputPoly);
					break;
				case ADD:
					tuple = communicator.chooseTwoPolynomsForCalculation(polynoms);
					Polynom added = calculator.add(tuple.getPoly1(), tuple.getPoly2());
					communicator.outputCalculationResultMessage(Operation.ADD);
					communicator.polyOutput(added);
					break;
				case SUBSTRACT:
					tuple = communicator.chooseTwoPolynomsForCalculation(polynoms);
					Polynom substracted = calculator.subtract(tuple.getPoly1(), tuple.getPoly2());
					communicator.outputCalculationResultMessage(Operation.SUBSTRACT);
					communicator.polyOutput(substracted);
					break;
				case MULITPLY:
					tuple = communicator.chooseTwoPolynomsForCalculation(polynoms);
					Polynom multiplied = calculator.multiply(tuple.getPoly1(), tuple.getPoly2());
					communicator.outputCalculationResultMessage(Operation.MULITPLY);
					communicator.polyOutput(multiplied);
					break;
				case DIVIDE:
					// chose one polynom
					Polynom polynom = communicator.chooseOnePolynomForCalculation(polynoms);
					// chose value for x
					BigDecimal value = communicator.inputValue("Geben Sie einen Wert für die Division ein (x-value)");
					DividedPolynom divided = calculator.divideWithHorner(polynom, value);
					communicator.outputCalculationResultMessage(Operation.DIVIDE);
					communicator.polyOutput(divided.getPolynom());
					communicator.outputMessage("Der Rest Ihrer Division beträgt: " + divided.getRest());
					break;
				case EVALUATE:
					Polynom polynom1 = communicator.chooseOnePolynomForCalculation(polynoms);
					BigDecimal v1 = communicator.inputValue("Geben Sie einen Wert für x ein.");
					BigDecimal evaluated = calculator.evaluate(polynom1, v1);
					communicator.outputCalculationResultMessage(Operation.EVALUATE);
					communicator.outputMessage("Der Skalar Ihrere Substitution von X beträgt: " + evaluated);
					break;
				case DERIVE:
					Polynom polynom2 = communicator.chooseOnePolynomForCalculation(polynoms);
					Polynom derived = calculator.derivate(polynom2);
					communicator.outputCalculationResultMessage(Operation.DERIVE);
					communicator.polyOutput(derived);
					break;
				default:
					throw new UnsupportedOperationException("Operation is not supported yet: " + op.name());
			}

		}

	}
}
