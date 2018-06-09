/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import edu.emit.project.controller.FordFulkersonFXController;
import edu.emit.project.model.serializable.GraphManager;
import edu.emit.project.view.shape.ArcFX;
import edu.emit.project.view.shape.SommetFX;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author heniroger
 */
public class GraphColorifier {
    private ArrayList<Sommet>  sommets;
    private ArrayList<ArcFX> arcFXs ;
    private ArrayList<SommetFX> sommetFXs ;
    private Group group ;
    private FordFulkersonFXController fordFulkersonFXController;
    
    
   private ArcFX arcx1= null;
   private  ArcFX arcx2 = null;
   private  SommetFX sommx =null;
   private HashSet<Integer> sommetID = new HashSet<>();
    public GraphColorifier(ArrayList<Sommet> sommets,GraphManager graphManager){
        this.sommets = sommets;
        arcFXs = graphManager.getArcFXList();
        sommetFXs = graphManager.getSommetFXList();
        fordFulkersonFXController = graphManager.getController();
                 
    }
    
    public void colorify(){
        int a= 0;
         Sommet s1;
         Sommet s2;
         ArrayList<Sommet[]> somms = new ArrayList<>();
         
         
        for (int i = 0; i < sommets.size(); i++) {
            a= i+1;
            if (a <sommets.size()) {
                s1= sommets.get(i);
                s2= sommets.get(a);
              //  System.out.println(s1.getIteration()+"-->"+s2.getIteration());
                somms.add(new Sommet[]{s1,s2});
                
                
            }
            
        }
        setGroup(getFordFulkersonFXController().getGroupGraph());
        ArrayList<ArcFX> list = sortArcFXLst(somms);
        ArrayList<ArcFX> newlist = new ArrayList<>();
        
         ArcFX afx =null ;
        for (int i = 0; i < list.size(); i++) {
            afx = list.get(i);
            afx.getListSommets().get(0).setFill(Color.RED);
            afx.getListSommets().get(1).setFill(Color.RED);
            newlist.add(afx);
            
            
        }
        
          getGroup().getChildren().contains(afx);
          
          
          
            for (int j = 0; j < newlist.size(); j++) {
                ArcFX arcx3  = newlist.get(j);
                for (int k = 0; k < arcx3.getListSommets().size(); k++) {
                    sommetID.add(arcx3.getListSommets().get(k).getID());

                }

            }

            
            ArrayList<SommetFX> sommxs = new ArrayList<>();
            for (int i = 0; i < getGroup().getChildren().size(); i++) {
               if (getGroup().getChildren().get(i).getClass().equals(SommetFX.class)) {
                    sommx = (SommetFX)getGroup().getChildren().get(i);
                    sommxs.add(sommx);


               }
           }
            ArrayList<Integer> intList = new ArrayList<>();
            Iterator iterator = sommetID.iterator();
            while (iterator.hasNext()) {
                int e = (int)iterator.next();
               // System.out.println(e);
                intList.add(e);

            }
            
            Collections.sort(intList, Comparator.reverseOrder());
            
            for (int i = 0; i < sommxs.size(); i++) {
                SommetFX sx = sommxs.get(i);
                for (int j = 0; j < intList.size(); j++) {
                    Integer integr = intList.get(j);
                    if (sx.getID()== integr) {
                       sx.setStroke(Color.RED.deriveColor(0, 1, 1, 0.5));
                       sx.getTxfLambda().setStyle("-fx-border-color: red;");
                       sx.getTxfLambda().setStyle("-fx-background-color: red;");
                        if (sx.getFill() == Color.WHITE) {
                            sx.setFill(Color.RED);
                        }
                       
                       sx.getLblLambda().setTextFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                     }
                    
                }
           }
          for (int i = 0; i < getGroup().getChildren().size(); i++) {

              if (getGroup().getChildren().get(i).getClass().equals(ArcFX.class)) {
                   arcx2 = (ArcFX)getGroup().getChildren().get(i);

              }
            for (int j = 0; j < newlist.size(); j++) {
                arcx1= newlist.get(j);

                if (arcx1.getListSommets().get(0).getID()==arcx2.getListSommets().get(0).getID() 
                        && arcx1.getListSommets().get(1).getID()==arcx2.getListSommets().get(1).getID() ) {
                    
                      arcx2.setStroke(Color.RED);
                      arcx2.getPointe().setStroke(Color.RED.deriveColor(0, 1, 1, 0.5));
                      arcx2.getTxfVij().setStyle("-fx-border-color: red;");
                      arcx2.getTxfVij().setStyle("-fx-background-color: #aaaaaa;");
                      arcx2.getVij().setTextFill(Color.BLACK.deriveColor(0, 1, 1, 0.5));
                      arcx2.setStrokeLineCap(StrokeLineCap.BUTT);
                       arcx2.getStrokeDashArray().setAll(10.0,5.0);
                       arcx2.getPointe().setFill(Color.RED);

                      
                }

                        
            }
              


            
              
            
        }
            
            getFordFulkersonFXController().getGraphManager().displayToGraph(getGroup());
            
        

        
        
    
    }
    
    public void decolorify(){
        ArcFX arcx = null;
        SommetFX sommetx = null;
        setGroup(getFordFulkersonFXController().getGroupGraph());

          for (int i = 0; i < getGroup().getChildren().size(); i++) {
               if (getGroup().getChildren().get(i).getClass().equals(SommetFX.class)) {
                    sommetx = (SommetFX)getGroup().getChildren().get(i);
                    sommetx.setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
                    sommetx.getTxfLambda().setStyle("-fx-border-color: #212121;");
                    sommetx.getTxfLambda().setStyle("-fx-background-color: #ffffff;");
                    if (sommetx.getFill() == Color.RED) {
                        sommetx.setFill(Color.WHITE);
                    }
                    sommetx.getLblLambda().setTextFill(Color.BLACK.deriveColor(0, 1, 1, 0.5));

               }
           }
          for (int i = 0; i < getGroup().getChildren().size(); i++) {

              if (getGroup().getChildren().get(i).getClass().equals(ArcFX.class)) {
                   arcx = (ArcFX)getGroup().getChildren().get(i);
                   arcx.setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
                   arcx.getPointe().setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
                   arcx.getTxfVij().setStyle("-fx-border-color: #212121;");
                   arcx.getTxfVij().setStyle("-fx-background-color: yellow;");
                   arcx.setStrokeLineCap(StrokeLineCap.BUTT);
                   arcx.getStrokeDashArray().setAll(0.1,0.1);
                   arcx.getPointe().setFill(Color.BLACK);

                   arcx.getVij().setTextFill(Color.BLACK.deriveColor(0, 1, 1, 0.5));
              }
           

         }
    
    }
    private ArrayList<ArcFX> sortArcFXLst(  ArrayList<Sommet[]> sommetsArray){

        ArrayList<ArcFX> arcFXsBis = new ArrayList<>();
        for (int i = 0; i < sommetsArray.size(); i++) {
            Sommet[] sommetArr = sommetsArray.get(i);
            for (int j = 0; j < arcFXs.size(); j++) {
                if (arcFXs.get(j).getListSommets().get(0).getID()== sommetArr[1].getIteration() &&
                        arcFXs.get(j).getListSommets().get(1).getID()== sommetArr[0].getIteration()) {
                       arcFXsBis.add(  arcFXs.get(j));
                }
                
            }
            
        }
        return arcFXsBis;
    }
    private ArrayList<SommetFX> sortSommetFXLst(){
        return null;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the fordFulkersonFXController
     */
    public FordFulkersonFXController getFordFulkersonFXController() {
        return fordFulkersonFXController;
    }

    /**
     * @param fordFulkersonFXController the fordFulkersonFXController to set
     */
    public void setFordFulkersonFXController(FordFulkersonFXController fordFulkersonFXController) {
        this.fordFulkersonFXController = fordFulkersonFXController;
    }
    
}
