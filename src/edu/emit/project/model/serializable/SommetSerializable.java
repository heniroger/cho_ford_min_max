package edu.emit.project.model.serializable;

import java.util.ArrayList;

/**
 *
 * @author heniroger
 */
public class SommetSerializable extends ROComponentSerializable{
    private  double centerX ;
    private  double centerY ;
    private double lambda;
    private int noeudType;
    private int noeudIteration;
    private ArrayList<SommetSerializable> listSommetSuivants = new ArrayList<>();
    private ArrayList<SommetSerializable> listSommetPrecedents = new ArrayList<>();
    
    public static int X1=0;
    public static int XI=1;
    public static int XN=2;
    
    private SommetSerializable(){}
    public SommetSerializable(double centerX,double centerY,double lambda,int noeudType,int noeudIteration){
        this.centerX=centerX;
        this.centerY=centerY;
        this.lambda = lambda;
        this.noeudType =noeudType;
        this.noeudIteration= noeudIteration;
  }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getLambda() {
        return lambda;
    }

    public int getNoeudType() {
        return noeudType;
    }

    public int getNoeudIteration() {
        return noeudIteration;
    }


    public static int getX1() {
        return X1;
    }

    public static int getXI() {
        return XI;
    }

    public static int getXN() {
        return XN;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setNoeudType(int noeudType) {
        this.noeudType = noeudType;
    }

    public void setNoeudIteration(int noeudIteration) {
        this.noeudIteration = noeudIteration;
    }

   

  
    
    @Override
    public String toString(){
        String str = "centerX = "+this.centerX+";";
        str += "centerY = "+this.centerY+";";
        str += "lambda = "+this.lambda+";";
        str += "noeudType = "+this.noeudType+";";
        str += "noeudIteration = "+this.noeudIteration+";";
        return str;
    }

    /**
     * @return the listSommetSuivants
     */
    public ArrayList<SommetSerializable> getListSommetSuivants() {
        return listSommetSuivants;
    }

    /**
     * @param listSommetSuivants the listSommetSuivants to set
     */
    public void setListSommetSuivants(ArrayList<SommetSerializable> listSommetSuivants) {
        this.listSommetSuivants = listSommetSuivants;
    }

    /**
     * @return the listSommetPrecedents
     */
    public ArrayList<SommetSerializable> getListSommetPrecedents() {
        return listSommetPrecedents;
    }

    /**
     * @param listSommetPrecedents the listSommetPrecedents to set
     */
    public void setListSommetPrecedents(ArrayList<SommetSerializable> listSommetPrecedents) {
        this.listSommetPrecedents = listSommetPrecedents;
    }
    
    
    
}
