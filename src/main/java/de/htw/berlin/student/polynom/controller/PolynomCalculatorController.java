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
    private final ConsoleCommunicator communicator = new ConsoleCommunicator();
    private final PolynomCalculator calculator = new PolynomCalculator();

    /**
     * Go method controls the lifecycle of the application.
     */
    public void go() {

        communicator.doGreetings();
        while (true) {
            int choose = communicator.choose();
            if (choose == 1) {
                menueTwo();

                // calculation here
                // frage: poly eingeben, oder mit vorhandenen rechnen
            } else if (choose == 2) {
                communicator.close();
                System.exit(0);
            }
        }

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

    private void menueTwo() {
        while (true) {
            // TODO: polynome eingeben.
            Polynom pol = communicator.polyInput();
            communicator.polyOutput(pol);
            polynoms.add(pol);
            // TODO: was willst du machen?
            int op = communicator.opperation();
            if (op == 0) {
                // zurück zu Hauptmenue
                break;
            } else {
                Polynom added = calculator.add(pol, pol);
                int choseOne = communicator.outputSavedPolynoms(polynoms);
                calculator.add(pol, polynoms.get(choseOne));
            }
        }

    }
}
