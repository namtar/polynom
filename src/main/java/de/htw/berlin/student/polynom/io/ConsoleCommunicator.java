package de.htw.berlin.student.polynom.io;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import de.htw.berlin.student.polynom.Main;
import de.htw.berlin.student.polynom.controller.Operation;
import de.htw.berlin.student.polynom.model.Polynom;
import de.htw.berlin.student.polynom.model.PolynomTuple;

/**
 * A communicator class for console input and output handling.
 *
 * @author Matthias Drummer
 * @author Marcel Piater
 */
public class ConsoleCommunicator {

    private PrintStream out;

    public ConsoleCommunicator() {
        try {
            // Special case for windows console
            // out = new PrintStream(System.out, true, "CP850");
            out = new PrintStream(System.out, true, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // This is fatal. Kill program.
            System.exit(Main.EXIT_ERROR);
        }
    }

    public void doGreetings() {
        System.out.println("####################################################");
        System.out.println("####################################################");
        System.out.println("######Herzlich Willkommen zum Polynomrechener#######");
        System.out.println("####################################################");
        System.out.println("####################################################");
        System.out.println();
    }

    /**
     * Input method for the operations of the base menu.
     *
     * @return the chosen {@link Operation}
     */
    public Operation choose() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            out.println("Bitte wählen Sie: ");
            out.println(" 1 = Polynomrechner ");
            out.println(" 2 = Polynom eingeben");
            out.println(" 3 = Gespeicherte Polynome ausgeben ");
            out.println(" 4 = Programende ");
            out.println();

            try {
                int choose = scanner.nextInt();
                out.println("Gewählt " + choose);
                switch (choose) {
                    case 1:
                        return Operation.CALCULATE;
                    case 2:
                        return Operation.INPUT_POLY;
                    case 3:
                        return Operation.OUTPUT_SAVED_POLY;
                    case 4:
                        return Operation.EXIT;
                    default:
                        out.println("Falsche Eingabe, bitte versuchen Sie es erneut!");
                }
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte geben Sie eine Ganzzahl ein.");
                scanner = new Scanner(System.in);
            }

        }
    }

    /**
     * Method to input one polynom.
     *
     * @return a new {@link Polynom}
     */
    public Polynom polyInput() {

        Scanner scanner = new Scanner(System.in);
        int grad = 0;
        while (true) {

            out.println("Bitte geben Sie den Grad Ihres Polynoms ein: ");
            try {
                grad = scanner.nextInt();
                // perhaps check here that no grade may be chosen greater than 6
                if (grad < 0) {
                    out.println("Der eingegebene Grad darf nicht negativ sein");
                } else {
                    out.println("Ihr Grad: " + grad);
                    break;
                }
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte geben Sie eine Ganzzahl ein.");
                scanner = new Scanner(System.in);
            }

        }
        double ko[] = new double[grad + 1];

        for (int i = grad; i >= 0; i--) {

            // loop to ensure the correct input of one coefficient. One will remain in this loop until the input is
            // valid.
            while (true) {
                try {
                    out.println("Bitte geben Sie den Koeffizienten für x^" + i + " ein: ");
                    ko[i] = scanner.nextDouble();
                    break; // break input loop if no exception was thrown and proceed to next coefficient.
                } catch (InputMismatchException e) {
                    out.println("Falsche Eingabe. Bitte geben Sie eine Ganzzahl oder Kommazahl ein.");
                    scanner = new Scanner(System.in);
                }
            }
        }

        List<BigDecimal> bdCoeffs = new ArrayList<BigDecimal>();
        for (int i = 0; i < ko.length; i++) {
            bdCoeffs.add(new BigDecimal(ko[i]));
        }

        Polynom polynom = new Polynom(bdCoeffs);

        return polynom;
    }

    /**
     * Baut die einzelnen Polynome zu einer Kette zusammen und gibts sie
     * zusammenhängend aus
     *
     * @param polynom the given {@link Polynom}
     */
    public void polyOutput(Polynom polynom) {
        StringBuilder sb = new StringBuilder("Ihr Polynom: ");
        BigDecimal zeroDecimal = new BigDecimal(0);

        List<BigDecimal> coeffs = polynom.getCoefficients();
        for (int i = coeffs.size() - 1; i >= 0; i--) {

            BigDecimal coeff = coeffs.get(i);

            if (coeff.compareTo(zeroDecimal) >= 0) {
                sb.append("+");
            }
            sb.append(coeff);
            sb.append("x^");
            sb.append(i);
        }
        out.println(sb.toString());
        out.println();
    }

    /**
     * Displays a bye bye message.
     */
    public void close() {
        out.println("Vielen dank, auf Wiedersehen ");
    }

    /**
     * Method to choose a mathematical operation.
     *
     * @return the chosen {@link Operation}
     */
    public Operation opperation() {

        Scanner scanner = new Scanner(System.in);

        out.println("Bitte wählen Sie Ihre Rechenoperation aus: ");
        out.println(" 0 = Hauptmenü ");
        out.println(" 1 = Addition ");
        out.println(" 2 = Subtraktion ");
        out.println(" 3 = Multiplikation ");
        out.println(" 4 = Division mittels Horner");
        out.println(" 5 = Substitution von x ");
        out.println(" 6 = 1. Ableitung ");
        out.println(" 7 = Polynom eingeben");
        out.println();

        Operation operation = null;

        boolean loop = true;
        while (loop) {
            try {
                int op = scanner.nextInt();
                out.println("Gewählt " + op);
                switch (op) {
                    case 0:
                        operation = Operation.LEAVE_SUBMENU;
                        loop = false;
                        break;
                    case 1:
                        operation = Operation.ADD;
                        loop = false;
                        break;
                    case 2:
                        operation = Operation.SUBSTRACT;
                        loop = false;
                        break;
                    case 3:
                        operation = Operation.MULITPLY;
                        loop = false;
                        break;
                    case 4:
                        operation = Operation.DIVIDE;
                        loop = false;
                        break;
                    case 5:
                        operation = Operation.EVALUATE;
                        loop = false;
                        break;
                    case 6:
                        operation = Operation.DERIVE;
                        loop = false;
                        break;
                    default:
                        out.println("Keine korrekte Eingabe. Bitte wiederholen.");
                }
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte geben Sie eine Ganzzahl ein, die den Möglichkeiten der Aufzählung entspricht.");
                scanner = new Scanner(System.in);
            }
        }
        return operation;

    }

    /**
     * Method to output all saved polynoms for calculation choice.
     *
     * @param polynoms the polynoms to output
     * @return the chosen polynom or null if no polynom was chosen
     */
    public Polynom chooseOnePolynomForCalculation(List<Polynom> polynoms) {

        Polynom chosenOne = null;

        out.println("Bitte wählen sie ein Polynom aus mit dem gerechnet werden soll.");
        for (int i = 0; i < polynoms.size(); i++) {
            out.print(i + "\t");
            polyOutput(polynoms.get(i));

        }
        Scanner scanner = new Scanner(System.in);
        while (true) {

            try {
                int choice = scanner.nextInt();
                // validatate that no choice has been made that doesen match the posibilities.
                if (choice < 0 || choice >= polynoms.size()) {
                    out.println("Keine gültige Auswahl. Bitte erneut eingeben.");
                    out.println();
                } else {
                    chosenOne = polynoms.get(choice);
                    break;
                }
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte geben sie eine Ganzzahl ein, die den Möglichkeiten der Liste entspricht.");
                out.println();
                scanner = new Scanner(System.in);
            }
        }

        return chosenOne;
    }

    public void ouptutSavedPolynoms(List<Polynom> polynoms) {

        if (polynoms.isEmpty()) {
            out.println("Keine gespeicherten Polynome vorhanden.");
            return;
        }

        out.println("Gespeicherte Polynome:");
        out.println(); // add an empty line

        for (int i = 0; i < polynoms.size(); i++) {
            out.print(i + "\t");
            polyOutput(polynoms.get(i));

        }
    }

    /**
     * Method that returns 2 Polynoms for calculation. <br>
     * TODO: error handling for wrong inputs is redundant. Fix later.
     *
     * @param polynoms a list of saved {@link Polynom}
     * @return a {@link PolynomTuple} that contains exactly two polynoms
     */
    public PolynomTuple chooseTwoPolynomsForCalculation(List<Polynom> polynoms) {

        Polynom poly1 = null;
        Polynom poly2 = null;
        ouptutSavedPolynoms(polynoms);

        boolean finished = false;
        Scanner scanner = new Scanner(System.in);
        while (!finished) {
            out.println("Bitte wählen Sie das erste Polynom");

            try {
                int choice = scanner.nextInt();
                // validatate that no choice has been made that doesen match the posibilities.
                if (choice < 0 || choice >= polynoms.size()) {
                    out.println("Keine gültige Auswahl. Bitte erneut eingeben.");
                    out.println();
                } else {
                    poly1 = polynoms.get(choice);
                    out.println("Gewählt " + choice);
                    finished = true;
                }
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte erneut versuchen.");
                out.println();
                scanner = new Scanner(System.in);
            }

        }
        finished = false;
        ouptutSavedPolynoms(polynoms);
        while (!finished) {
            out.println();
            out.println("Bitte wählen Sie das zweite Polynom");

            try {
                int choice = scanner.nextInt();
                // validatate that no choice has been made that doesen match the posibilities.
                if (choice < 0 || choice >= polynoms.size()) {
                    out.println("Keine gültige Auswahl. Bitte erneut eingeben.");
                    out.println();
                } else {
                    poly2 = polynoms.get(choice);
                    out.println("Gewählt " + choice);
                    finished = true;
                }
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte erneut versuchen.");
                out.println();
                scanner = new Scanner(System.in);
            }
        }

        return new PolynomTuple(poly1, poly2);
    }

    /**
     * Method outputs a text for the result of the given mathematical operation.
     *
     * @param operation the {@link Operation}
     */
    public void outputCalculationResultMessage(Operation operation) {

        StringBuilder sb = new StringBuilder("Das Ergebnis Ihrer ");

        switch (operation) {
            case ADD:
                sb.append("Addition ");
                break;
            case SUBSTRACT:
                sb.append("Substraction ");
                break;
            case MULITPLY:
                sb.append("Multiplikation ");
                break;
            case DIVIDE:
                sb.append("Division ");
                break;
            case EVALUATE:
                sb.append("Substitution ");
                break;
            case DERIVE:
                sb.append("1. Ableitung ");
                break;
            default:
                throw new UnsupportedOperationException("Operation is not supported yet: " + operation.name());

        }

        sb.append("ist:");
        out.println(sb.toString());
        System.out.println();
    }

    /**
     * Method that allows to input a value specified by the given message
     *
     * @param message the message to display
     * @return the input value
     */
    public BigDecimal inputValue(String message) {

        System.out.println(message);
        BigDecimal result = null;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                result = scanner.nextBigDecimal();
                break;
            } catch (InputMismatchException e) {
                out.println("Falsche Eingabe. Bitte erneut versuchen.");
                out.println();
                scanner = new Scanner(System.in);
            }
        }
        return result;
    }

    /**
     * Method to output a given message.
     *
     * @param message the message
     */
    public void outputMessage(String message) {
        out.println(message);
        out.println();
    }

}
