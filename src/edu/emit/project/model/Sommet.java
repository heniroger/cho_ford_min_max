/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import java.util.ArrayList;

/**
 *
 * @author heniroger
 */
public class Sommet {
    private Double lambda;
    private final static String LAMBDA = "\u03BB";
    private int iteration;
    
    private ArrayList<Object> noeuds = new ArrayList<>();
    private ArrayList<Sommet> noeudsPrecedent = new ArrayList<>();
    
    public Sommet(){}
    public Sommet(int iteration){
        this.iteration = iteration+1;
    }

    
    public Double getLambda() {
        return lambda;
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }

    public int getIteration() {
        //System.out.println("X"+iteration);
        return iteration;
    }
    public String displayIteration() {
        return this+"(L"+iteration+"="+((getLambda()==1.0/0.0)?"M":getLambda())+")";
        
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
    
    @Override
    public String toString(){
        return "X"+iteration;
    }
    public void addNoeud(Sommet s,double v){
        Object[] noeud = {s,v};
        this.noeuds.add(noeud);
        s.getNoeudsPrecedent().add(this);
        //System.out.println(this+"->"+s+" and v("+this+","+s+") ="+v);
    
    }
     public ArrayList getNoeuds(){
        return this.noeuds;
        
    
    }
    public Sommet getXj(int i){
        Object[] o = (Object[])this.noeuds.get(i);
        return (Sommet) o[0] ;
        
    
    }
    public Double getVij(int i){
        Object[] o = (Object[])this.noeuds.get(i);
        return (Double) o[1] ;
        
    
    }
    public void setLambdaForNextSommet(double lambda){
        this.setLambda(lambda);
    }
    
    public int returnToIndex(int iterationIndex){
        return iterationIndex-1;
    }

    public ArrayList<Sommet> getNoeudsPrecedent() {
        return noeudsPrecedent;
    }

    public void setNoeudsPrecedent(ArrayList<Sommet> noeudsPrecedent) {
        this.noeudsPrecedent = noeudsPrecedent;
    }
    
    public boolean isPredecesseurOf(Sommet xp,int iterat){
        boolean b = false;
        Sommet xpMoins1 =this;
        double vij = xpMoins1.getVij(iterat);
        
        if(xp.getLambda()== xpMoins1.getLambda()+ vij){
            b=true;
        }
        
        return b;
    }
    
}
