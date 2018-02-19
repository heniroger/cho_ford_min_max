/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project;

import edu.emit.project.app.FordFullkersonFX;
import edu.emit.project.view.shape.Pointe;
import java.util.ArrayList;
import javafx.application.Application;

/**
 *
 * @author heniroger
 */
public class Main {
    public ArrayList<Pointe> pointes = new ArrayList<>();
    public ArrayList<Boolean> sommetIndexEmpty = new ArrayList<>();
    private  final Main context = this;
    static {
        System.setProperty("file.encoding", "unicode");
    
    }
    public static void main(String[] args) {
         
         Application.launch(FordFullkersonFX.class);

    }
    public ArrayList<Pointe> getPointes(){
        return pointes;
    }
    

}
