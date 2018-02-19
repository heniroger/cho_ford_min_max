/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project;

/**
 *
 * @author heniroger
 */
public class Log {
    public static void displayInstatiateObjectName(Object o){
         System.out.println("Instanciation de la classe : "+o.getClass().getName());

    }
    public static void error(Object o){
        System.err.println("Panique dans la classe : "+o.getClass().getName());
    }
     
}
