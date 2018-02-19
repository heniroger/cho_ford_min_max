/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import edu.emit.project.Log;
import java.util.ArrayList;

/**
   *  <h1> Description</h1> <br/>
   * <p>
   * "Sommet" : Xi<br/>
   *  Une "Graphe" contient plusieurs Sommet cad : X1 ... Xn<br/>
   *  Chaque ou un Sommet contient une Valeur "Lambda"<br/>
   *       Pour Xi -> Lambdai<br/>
   *  On appelle "Arc" la valeur entre le Sommet Xi et Xj : v(xi,xj);<br/>
   *  </p>
   *   <br/>
   *   <h1> Algorithme</h1> <br/>
   *    <p>
   *    Declaration de X1,X2, X3,...,Xn<br/>
   *    Association de X1 a une valeur lambda L1=0 et Xi a une valeur Li=M (Tel que M est une Valeur plus grande)<br/>
   *    Pour tout Xj : Si Lj-Li > V(xi,xj): Lj = Li + V(xi,xj)<br/>
   *    Xp: Lp = Lp-1+ V(xp-1,xp)
   *    </p>
   *    <br/>
   * 
   *
   * 
   */
public class FordFulkerson extends AbstractModel{
    public final static int MINIMIZATION_TYPE =0;
    public final static int MAXIMIZATION_TYPE =1;
    public FordFulkerson(){
        Log.displayInstatiateObjectName(this);

    }

   
    
    
   
    
    
}
