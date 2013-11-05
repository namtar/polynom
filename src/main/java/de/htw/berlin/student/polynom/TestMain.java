/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.student.polynom;

import de.htw.berlin.student.polynom.io.ConsoleCommunicator;
import de.htw.berlin.student.polynom.model.Polynom;

/**
 *
 * @author MP
 */
public class TestMain {

    public static void main(String[] args) {
        ConsoleCommunicator communicator = new ConsoleCommunicator();
        communicator.doGreetings();
        communicator.choose();
        Polynom polynom = communicator.polyInput();
        communicator.polyOutput(polynom);
        //communicator.close();
    }
}
