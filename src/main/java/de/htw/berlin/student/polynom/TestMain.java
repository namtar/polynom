/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.student.polynom;

import de.htw.berlin.student.polynom.io.ConsoleCommunicator;
import de.htw.berlin.student.polynom.model.Polynom;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MP
 */
public class TestMain {

    public static void main(String[] args) {
        ConsoleCommunicator communicator = new ConsoleCommunicator();
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

//        Polynom polynom = communicator.polyInput();
//        communicator.polyOutput(polynom);

    }
    
    public static void menueTwo() {
        ConsoleCommunicator communicator = new ConsoleCommunicator();
        List<Polynom> polis = new ArrayList<Polynom>();
        while(true) {
            // TODO: polynome eingeben.
            Polynom pol = communicator.polyInput();
            communicator.polyOutput(pol);
            polis.add(pol);
            // TODO: was willst du machen?
            int op = communicator.opperation();
            if(op == 0) {
                // zurück zu Hauptmenue
                break;
            }
        }
    }
}
