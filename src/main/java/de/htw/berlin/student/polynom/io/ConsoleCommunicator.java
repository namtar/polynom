package de.htw.berlin.student.polynom.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import de.htw.berlin.student.polynom.i18n.I18nResolver;
import de.htw.berlin.student.polynom.model.Polynom;
import java.util.InputMismatchException;

/**
 * A communicator class for console input and output handling.
 *
 * @author Matthias Drummer
 */
public class ConsoleCommunicator {

    Scanner scanner;

    public ConsoleCommunicator() {
    }

    public void getLocale() {

        this.scanner = new Scanner(System.in);

        System.out.println("Please Select your Language.");
        System.out.println("Press 1 for German.");
        System.out.println("Press 2 for Englisch");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Locale.setDefault(Locale.GERMAN);
                break;
            case 2:
            default:
                Locale.setDefault(Locale.US);

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

    public int choose() {

        this.scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Bitte wählen Sie: ");
            System.out.println(" 1 = Polynomrechner ");
            System.out.println(" 2 = Programende ");
            System.out.println();

            try {
                int choose = scanner.nextInt();
                System.out.println("Gewählt " + choose);
                switch (choose) {
                    case 1:
                    case 2:
                        return choose;
                    default:
                        System.out.println("Falsche Eingabe, bitte versuchen Sie es erneut!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Falsche Eingabe. Bitte geben Sie eine Ganzzahl ein.");
                this.scanner = new Scanner(System.in);
//                e.printStackTrace();
            }

        }
    }

    public Polynom getPolynomial() {

        scanner = new Scanner(System.in);
        List<BigDecimal> coefficients = new ArrayList<BigDecimal>();

        // TODO: MPI: Frage nach Grad des Polynoms und frage dann entsprechend des Grades die einzelnen Koeffizienten in geordneter Form x^0 ... ab.
        System.out.print(I18nResolver.getString("resolvePolynomial"));

        return new Polynom(coefficients);
    }

    public Polynom polyInput() {

        scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Grad Ihres Polynoms ein: ");
        int grad = scanner.nextInt();
        System.out.println("Ihr Grad: " + grad);

        double ko[] = new double[grad + 1];

        for (int i = grad; i >= 0; i--) {
            System.out.println("Bitte geben Sie den Koeffizienten für x^" + i + " ein: ");
            ko[i] = scanner.nextDouble();
        }
        //for (int i = 0; i < ko.length; i++) {
        //    System.out.print("Test: ");
        //    if (ko[i] >= 0) {
        //        System.out.print("+");
        //   }
        //    System.out.print(ko[i]);
        //    System.out.println();
        //}

        //for (int i = 0; i < ko.length; i++) {
        //   String koeffi = "Test1: ";
        //   if (ko[i] >= 0) {
        //        koeffi += "+";
        //    }
        //    koeffi += ko[i];
        //    System.out.println(koeffi);
        //}
        //for (int i = 0; i < ko.length; i++) {
        //    StringBuilder sb = new StringBuilder("Test2 :");
        //    if (ko[i] >= 0) {
        //        sb.append("+");
        //    }
        //    sb.append(ko[i]);
        //    System.out.println(sb.toString());
        //}
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
     * @param polynom
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
        System.out.println(sb.toString());

    }

    public void close() {
        System.out.println("Vielen dank, auf Wiedersehen ");

    }

    public int opperation() {

        this.scanner = new Scanner(System.in);

        System.out.println("Bitte wählen Sie Ihre Rechenoperation aus: ");
        System.out.println(" 0 = Hauptmenü ");
        System.out.println(" 1 = Addition ");
        System.out.println(" 2 = Subtraktion ");
        System.out.println(" 3 = Multiplikation ");
        System.out.println(" 4 = Division ");
        System.out.println();
        int op = scanner.nextInt();
        return op;
//        switch (op) {
//            case 1:
//                add;
//                break;
//            case 2:
//                sub;
//                break;
//            case 3:
//                multi;
//                break;
//            case 4:
//                div;
//                break;
//        }
    }

    public int outputSavedPolynoms(List<Polynom> polynoms) {

        int chosenOne = -1;

        System.out.println("Bitte wählen sie ein Polynom aus mit dem gerechnet werden soll.");
        for (int i = 0; i < polynoms.size(); i++) {
            System.out.print(i + "\t");
            polyOutput(polynoms.get(i));

        }

        return chosenOne;
    }

}
