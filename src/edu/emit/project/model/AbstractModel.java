 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heniroger
 */
public abstract class AbstractModel {
    private ArrayList<Observer> listObservers = new ArrayList<Observer>();
    
    private ArrayList<Sommet> listSommet = new ArrayList<Sommet>();
    private ArrayList<String[]> resultRows = new ArrayList<String[]>();
    
    private int optimisationType = 2;
    

    
    /*******************************/
     public void setNombreSommet(int nombre) throws OptimizationTypeException{
        Sommet sommet ;
        ArrayList<Sommet> sommets = new ArrayList<>();
        int optimisationType = this.getOptimisationType();
        for (int i = 0; i < nombre; i++) {
            sommet = new Sommet(i);
            switch(optimisationType){
                case FordFulkerson.MINIMIZATION_TYPE:
                    if (i==0) {
                        sommet.setLambda(0.0);
                    }else{
                        sommet.setLambda(Double.POSITIVE_INFINITY);
                    }
                    break;
                case FordFulkerson.MAXIMIZATION_TYPE:
                    sommet.setLambda(0.0);
                    break;
                default:
                    
                    throw new OptimizationTypeException("Optimization Type not defined");
                    
            }
            sommets.add(sommet);
            
            
        }
        this.setListSommet(sommets);
        
     }
     
    public ArrayList<Sommet> getListSommet() {
        return listSommet;
    }

    public void setListSommet(ArrayList<Sommet> listSommet) {
        this.listSommet = listSommet;
    }
    public void calculerMinimisation(){
        ArrayList<Sommet> sommets = getListSommet();
        ArrayList<Sommet> noeuds;
        Sommet xi;
        Sommet xj;
        Double lambdai;
        Double lambdaj;
        Double vij;
        
        boolean restart =false;
        int startIndex=0;
        this.resultRows.clear();
        
        for (int i = 0; i < sommets.size(); i++) {
            xi= getListSommet().get(i);
            noeuds =  xi.getNoeuds();
            for(int j = 0; j < noeuds.size(); j++){
                
                xj= xi.getXj(j);
                lambdai =  xi.getLambda();
                lambdaj =  xj.getLambda();
                vij = xi.getVij(j);
                int a = i+1; // car  A l'itération 0 c'est x1

                if ((lambdaj - lambdai) <= vij) {
                   this.resultRows.add(new String[]{""+a, ""+xj.getIteration(),""+lambdai, ""+lambdaj, ""+vij,"",(j<1)?"y":"n"});
                  //System.out.println(FordFulkersonConsoleView.tableRow(i+1, xj.getIteration(),lambdai, lambdaj, vij));                
                   continue;
                }
                if (a > xj.getIteration() && (lambdaj - lambdai) > vij) {
                    restart=true;
                    startIndex=xi.returnToIndex(xj.getIteration()-1);
                    
                    this.resultRows.add(new String[]{""+a, ""+xj.getIteration(),""+lambdai, ""+lambdaj, ""+vij,""+(lambdai+vij),(j<1)?"y":"n"});
                    //System.out.println(FordFulkersonConsoleView.tableRow(a, xj.getIteration(),lambdai, lambdaj, vij, lambdai+vij));                
                    lambdaj = lambdai+vij;
                    xj.setLambdaForNextSommet(lambdaj);
                    break;
                
                }
                this.resultRows.add(new String[]{""+ a, ""+xj.getIteration(),""+lambdai, ""+lambdaj, ""+vij,""+(lambdai+vij),(j<1)?"y":"n"});                
                //System.out.println(FordFulkersonConsoleView.tableRow(a, xj.getIteration(),lambdai, lambdaj, vij, lambdai+vij));                
                lambdaj = lambdai+vij;
                xj.setLambdaForNextSommet(lambdaj);
            }
            if (restart==true) {
                i=startIndex;
                restart=false;
            }
        }
        
        System.err.println("this.resultRows:"+this.resultRows.size());
    }
    public void calculerMaximisation(){
        ArrayList<Sommet> sommets = getListSommet();
        ArrayList<Sommet> noeuds;
        Sommet xi;
        Sommet xj;
        Double lambdai;
        Double lambdaj;
        Double vij;
        
        boolean restart =false;
        int startIndex=0;
        this.resultRows.clear();
        
        for (int i = 0; i < sommets.size(); i++) {
            xi= getListSommet().get(i);
            noeuds =  xi.getNoeuds();
            for(int j = 0; j < noeuds.size(); j++){
                
                xj= xi.getXj(j);
                lambdai =  xi.getLambda();
                lambdaj =  xj.getLambda();
                vij = xi.getVij(j);
                int a = i+1; // car  A l'itération 0 c'est x1
                
                if ((lambdaj - lambdai) >= vij) {
                    this.resultRows.add(new String[]{""+ a, ""+xj.getIteration(),""+lambdai, ""+lambdaj, ""+vij,"",(j<1)?"y":"n"});                
                   //System.out.println(FordFulkersonConsoleView.tableRow(i+1, xj.getIteration(),lambdai, lambdaj, vij));                
                  continue;
                }
                if (a > xj.getIteration() && (lambdaj - lambdai) < vij) {
                    restart=true;
                    startIndex=xi.returnToIndex(xj.getIteration()-1);
                    
                    this.resultRows.add(new String[]{""+ a, ""+xj.getIteration(),""+lambdai, ""+lambdaj, ""+vij,""+(lambdai+vij),(j<1)?"y":"n"});                
                    //System.out.println(FordFulkersonConsoleView.tableRow(a, xj.getIteration(),lambdai, lambdaj, vij, lambdai+vij));                
                    lambdaj = lambdai+vij;
                    xj.setLambdaForNextSommet(lambdaj);
                    break;
                
                }
                this.resultRows.add(new String[]{""+ a, ""+xj.getIteration(),""+lambdai, ""+lambdaj, ""+vij,""+(lambdai+vij),(j<1)?"y":"n"});                
                //System.out.println(FordFulkersonConsoleView.tableRow(a, xj.getIteration(),lambdai, lambdaj, vij, lambdai+vij));                
                lambdaj = lambdai+vij;
                xj.setLambdaForNextSommet(lambdaj);
            }
            if (restart==true) {
                i=startIndex;
                restart=false;
            }
        }
        
        System.err.println("this.resultRows:"+this.resultRows.size());

    }
   
     

    public int getOptimisationType() {
        return optimisationType;
    }

    public void setOptimisationType(int optimisationType) {
        this.optimisationType = optimisationType;
    }

    public static class OptimizationTypeException extends Exception {

        public OptimizationTypeException(String optimization_Type_not_defined) {
            System.err.println(optimization_Type_not_defined);
        }
    }
    
    /**
     *  Lp = Lp-1 + V(Xp-1,Xp)
     */
    public ArrayList<Sommet> calculerCheminMinimal(ArrayList<Sommet> sommets){
        //Sommet Xp = sommets.get(sommets.size()-1);
        //getPredecesseur(sommets, Xp);
        
            return getPredecesseur(sommets);
            
        
    
    }
    public ArrayList<Sommet> getPredecesseur(ArrayList<Sommet> sommets){
        Sommet xp = sommets.get(sommets.size()-1);
        Sommet xpMoins1 ;
        ArrayList<Sommet> smts = new ArrayList<>();
        smts.add(xp);
        //lister les predecesseurs de xp = x16
        for (int j =sommets.size()-1; j >=0 ; j--) {

            for (int i=0; i < xp.getNoeudsPrecedent().size(); i++) {
                xpMoins1 = xp.getNoeudsPrecedent().get(i);
                for (int k = 0; k < xpMoins1.getNoeuds().size(); k++) {
                    if (xpMoins1.isPredecesseurOf(xp, k)) {
                        xp = xpMoins1;
                        smts.add(xp);

                    }
                }

            }
        }

       
        
        
        return smts;
    }

    public ArrayList<String[]> getResultRows() {
        return resultRows;
    }

    public void setResultRows(ArrayList<String[]> resultRows) {
        this.resultRows = resultRows;
    }
    
    /**
     * 
     * @param from 
     * @param to
     * @param vij 
     * 
     * <br/>
     * La valeur de from et to doit etre la meme valeur que l'iteration des Sommets.<br/>
     * Par exemple : pour lier x1 et x2 (x1->x2),from est égale a 1 et to á 2.<br/>
     * C'est á dire<i> l'itération de la liste des sommets plus un</i>. 
     * 
     */
    public void setArc(int from,int to,double vij) throws NotZeroSommetIterationException{
        if(from==0 || to==0){
            throw new NotZeroSommetIterationException("From or To value must not be zero");
        }
       
        checkListSommet();
        
        Sommet fromSommet =this.getListSommet().get(from-1);
        Sommet toSommet =this.getListSommet().get(to-1);
        
        fromSommet.addNoeud(toSommet, vij);
        
       
      
    
    }

    /**
     *  Vérifier si le nombre de sommet est valide.
     */
    
    public void checkListSommet(){
       
        try {
          if (this.getListSommet().size()<=2) {
            throw new IllegalNumberOfSommetException("Number of Sommet instance must be more than two");
          }
        } catch (IllegalNumberOfSommetException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }
    public static class NotZeroSommetIterationException extends Exception {

        public NotZeroSommetIterationException(String from_or_To_value_must_not_be_zero) {
            System.err.println(from_or_To_value_must_not_be_zero);
        }
    }

    public static class IllegalNumberOfSommetException extends Exception {

        public IllegalNumberOfSommetException(String number_of_Sommet_instance_must_be_more_th) {
            System.err.println(number_of_Sommet_instance_must_be_more_th);
        }
    }
    /**
     * @deprecated 
     */

    public void displayAllArcs(){
        checkListSommet();
        ArrayList<Sommet> sommets = getListSommet();
        for (int i = 0; i < sommets.size()-1; i++) {
            Sommet xi = sommets.get(i);
            Sommet xn = sommets.get(sommets.size()-1);
           try {
                if ( xi.getNoeuds().size()<1) {
                  throw new NullArcException("Some Sommet class may not contain a node or there is no Arc defined.");                
                } 
            } catch (NullArcException e) {
                     e.printStackTrace();
            }
            for (int j = 0; j < xi.getNoeuds().size(); j++) {
                                  
                Sommet xj = xi.getXj(j);
                double vij = xi.getVij(j);
                System.out.println(xi+"->"+xj+" and v("+xi+","+xj+") ="+vij);

                
            }
            
            
        }
    }

    public static class NullArcException extends Exception {

        public NullArcException(String some_Sommet_class_may_not_contain_a_node_) {
            System.err.println(some_Sommet_class_may_not_contain_a_node_);
        }
    }
    
   
    
}
