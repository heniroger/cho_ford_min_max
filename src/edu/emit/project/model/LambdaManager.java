/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import edu.emit.project.model.serializable.GraphManager;
import edu.emit.project.view.shape.SommetFX;
import java.util.ArrayList;

/**
 *
 * @author luka
 */
public class LambdaManager {
    private final ArrayList<Sommet> sommets;
    private final GraphManager graphManager;
    
    public LambdaManager(ArrayList<Sommet> sommets, GraphManager graphManager){
        this.sommets        = sommets;
        this.graphManager   = graphManager;
    }
    public void update(){
        Sommet s = null;
        SommetFX sx = null;
        
        for (int i = 0; i < sommets.size(); i++) {
            s = sommets.get(i);
            this.graphManager.getSommetFXList().get(i);
            for (int j = 0; j < graphManager.getSommetFXList().size(); j++) {
                sx = graphManager.getSommetFXList().get(j);
                if(s.getIteration()== sx.getID()){
                    System.err.println("Iteration"+s.getIteration());
                    sx.getTxfLambda().setText(String.valueOf(s.getLambda()));
                }
                
            }

            
        }
        
        
    
    
    
    }
    
}
