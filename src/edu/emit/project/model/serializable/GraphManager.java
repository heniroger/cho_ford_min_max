/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model.serializable;

import edu.emit.project.Main;
import edu.emit.project.controller.FordFulkersonFXController;
import edu.emit.project.view.shape.ArcFX;
import edu.emit.project.view.shape.Pointe;
import edu.emit.project.view.shape.SommetFX;
import edu.emit.project.view.shape.Tetha;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 *
 * @author heniroger
 */
public class GraphManager {
    private ArrayList<SommetFX> sommetFXList = new ArrayList<>();
    private ArrayList<ArcFX> arcFXList = new ArrayList<>();
    
    private DataSerializable dataSerializable ;
    private FordFulkersonFXController controller;
    public Main main;
    
    public GraphManager(){
        this.dataSerializable = new DataSerializable();
    }
    public void displayToGraph(Group groupGraph){
        main = new Main();
        groupGraph.getChildren().clear();
        
        ArrayList<SommetFX> sommetFXs = getSommetFXList();
        ArrayList<ArcFX> arcFXs = getArcFXList();
        
        SommetFX sommetFX;
        int size =sommetFXs.size();
        for (int j = 0; j < size; j++) {
            sommetFX = sommetFXs.get(j);
            sommetFX.setApp(main);
            sommetFX.update();
            groupGraph.getChildren().add(sommetFX);
            groupGraph.getChildren().add(sommetFX.getLblName());
            groupGraph.getChildren().add(sommetFX.getLblLambda());
            groupGraph.getChildren().add(sommetFX.getTxfLambda());

        }
        for (int i = 0; i < arcFXs.size(); i++) {
            ArcFX arcFX = arcFXs.get(i);
            for (int j = 0; j < size; j++) {
                    sommetFX = sommetFXs.get(j);
                    if (arcFX.getListSommets().get(0).getID()==sommetFX.getID()) {
                              Tetha.startSommet = sommetFX;

                    }
                    
            }
                
            for (int j = 0; j < size; j++) {
                   sommetFX = sommetFXs.get(j);
                    if (arcFX.getListSommets().get(1).getID()==sommetFX.getID()) {
                              Tetha.endSommet = sommetFX;
                    }
            }
            
          
          Pointe pointe = new Pointe();
          pointe.linkTo(arcFX, 40.0f);
          main.pointes.add(pointe);

          arcFX.setPointe(pointe);
          arcFX.lier(Tetha.startSommet.centerXProperty(), Tetha.startSommet.centerYProperty(), Tetha.endSommet.centerXProperty(), Tetha.endSommet.centerYProperty());
          arcFX.setController(getController());
           
          Tetha.startSommet.getNextSommets().add(new Object[]{ Tetha.endSommet,arcFX});
          Tetha.endSommet.getPreviewSommets().add(new Object[]{ Tetha.startSommet,arcFX});
          Tetha.startSommet.getListNextSommets().add(Tetha.endSommet);
          Tetha.endSommet.getListPreviewSommets().add(Tetha.startSommet);
          Tetha.startSommet.getListLiens().add(arcFX);
          Tetha.endSommet.getListLiens().add(arcFX);
          arcFX.getListSommets().add(Tetha.startSommet);
          arcFX.getListSommets().add(Tetha.endSommet);
          arcFX.getTxfVij().setText(String.valueOf(arcFX.getV_ij()));
          //  System.err.println(arcFX);
         groupGraph.getChildren().addAll(pointe,arcFX,arcFX.getVij(),arcFX.getTxfVij());
         pointe.update();
          arcFX.toBack();
                
           Tetha.startSommet =null;
           Tetha.endSommet =null;
                    
        }
        
        
    
    }

    /**
     * @return the sommetFXList
     */
    public ArrayList<SommetFX> getSommetFXList() {
        return sommetFXList;
    }

    /**
     * @param sommetFXList the sommetFXList to set
     */
    public void setSommetFXList(ArrayList<SommetFX> sommetFXList) {
        this.sommetFXList = sommetFXList;
    }

    /**
     * @return the arcFXList
     */
    public ArrayList<ArcFX> getArcFXList() {
        return arcFXList;
    }

    /**
     * @param arcFXList the arcFXList to set
     */
    public void setArcFXList(ArrayList<ArcFX> arcFXList) {
        this.arcFXList = arcFXList;
    }

    /**
     * @return the dataSerializable
     */
    public DataSerializable getDataSerializable() {
        return dataSerializable;
    }

    /**
     * @param dataSerializable the dataSerializable to set
     */
    public void setDataSerializable(DataSerializable dataSerializable) {
        this.dataSerializable = dataSerializable;
    }
    /**
     *  To  be rectified : Redundant Object
     * @param sommetFXList 
     */
    public void convertToSommetSerializableList(ArrayList<SommetFX> sommetFXList){
         dataSerializable.setListSommetSerializable(new ArrayList<>());
        
        for (int i = 0; i < sommetFXList.size(); i++) {
            SommetFX sommetFX = sommetFXList.get(i);
            SommetSerializable sommetSerializable = new SommetSerializable(sommetFX.getCenterX(), sommetFX.getCenterY(),
                    sommetFX.getLambda(), sommetFX.getTypeSommet(), sommetFX.getID());
            sommetSerializable.setLambda(Double.parseDouble(sommetFX.getTxfLambda().getText()));
            for (int j = 0; j < sommetFX.getListNextSommets().size(); j++) {
                SommetFX nextSommet  = sommetFX.getListNextSommets().get(j);
                SommetSerializable nextSommetSerializable = new SommetSerializable(nextSommet.getCenterX(), nextSommet.getCenterY(),
                        nextSommet.getLambda(),nextSommet.getTypeSommet(), nextSommet.getID());
                sommetSerializable.getListSommetSuivants().add(nextSommetSerializable);
            }
            for (int j = 0; j < sommetFX.getListPreviewSommets().size(); j++) {
                SommetFX previewSommet  = sommetFX.getListPreviewSommets().get(j);
                SommetSerializable previewSommetSerializable = new SommetSerializable(previewSommet.getCenterX(), previewSommet.getCenterY(),
                        previewSommet.getLambda(),previewSommet.getTypeSommet(), previewSommet.getID());
                sommetSerializable.getListSommetPrecedents().add(previewSommetSerializable);
            }
            
            dataSerializable.getListSommetSerializable().add(sommetSerializable);
        }
        
    }
    
    /**
     *   To  be rectified : Redundant Object
     * @param arcFXList 
     */
    public void convertToArcSerializableList(ArrayList<ArcFX> arcFXList){
        dataSerializable.setListArcSerializable(new  ArrayList<>());
        for (int i = 0; i < arcFXList.size(); i++) {
            ArcFX arcFX = arcFXList.get(i);
            ArcSerializable arcSerializable =new ArcSerializable(arcFX.getStartX(), arcFX.getStartY(), 
                    arcFX.getEndX(), arcFX.getEndY(), arcFX.getControlX(), arcFX.getControlY(), arcFX.getV_ij());
            arcSerializable.setVij(Double.parseDouble(arcFX.getTxfVij().getText()));
            SommetFX sommetFXStart =  arcFX.getListSommets().get(0);
            SommetSerializable sommetSerializableStart = new SommetSerializable(sommetFXStart.getCenterX(), sommetFXStart.getCenterY(),
                    sommetFXStart.getLambda(),sommetFXStart.getTypeSommet(), sommetFXStart.getID());
            SommetFX sommetFXEnd =  arcFX.getListSommets().get(1);
            SommetSerializable sommetSerializableEnd = new SommetSerializable(sommetFXEnd.getCenterX(), sommetFXEnd.getCenterY(),
                    sommetFXEnd.getLambda(),sommetFXEnd.getTypeSommet(), sommetFXEnd.getID());
          
            arcSerializable.getConnectedNodeList().add(sommetSerializableStart);
            arcSerializable.getConnectedNodeList().add(sommetSerializableEnd);

             
            dataSerializable.getListArcSerializable().add(arcSerializable);
        }
    }
    /**
     *   To  be rectified : Redundant Object
     */
    public void convertToSommetFXList(){
        sommetFXList = new ArrayList<>();
        ArrayList<SommetSerializable> sommetSerializables = getDataSerializable().getListSommetSerializable();
        for (int i = 0; i < sommetSerializables.size(); i++) {
            SommetSerializable sommetSerializable = sommetSerializables.get(i);
            SommetFX sommetFX =  new SommetFX();
            sommetFX.setCenterX(sommetSerializable.getCenterX());
            sommetFX.setCenterY(sommetSerializable.getCenterY());
            sommetFX.setLambda(sommetSerializable.getLambda());
            sommetFX.setApp(main);
            sommetFX.setTypeSommet(sommetSerializable.getNoeudType());
            if (sommetFX.getTypeSommet() == SommetFX.SOMMET_TYPE_X1 || sommetFX.getTypeSommet() == SommetFX.SOMMET_TYPE_XN) { 
                sommetFX.setFill(Color.DODGERBLUE); 
            }else{
                sommetFX.setFill(Color.WHITE); 
            }
            sommetFX.setID(sommetSerializable.getNoeudIteration());
            sommetFX.getLblName().setText("X"+String.valueOf(sommetFX.getID()));
            sommetFX.setController(this.getController());
            sommetFX.getTxfLambda().setText(String.valueOf(sommetSerializable.getLambda()));
            ArrayList<SommetSerializable> sommetSerializablePreviewtList  = sommetSerializable.getListSommetPrecedents();
            for (int j = 0; j < sommetSerializablePreviewtList.size(); j++) {
                SommetSerializable sommetSerializablePreview = sommetSerializablePreviewtList.get(j);
                SommetFX sommetFXPrecedent =  new SommetFX();
                sommetFXPrecedent.setCenterX(sommetSerializablePreview.getCenterX());
                sommetFXPrecedent.setCenterY(sommetSerializablePreview.getCenterY());
                sommetFXPrecedent.setLambda(sommetSerializablePreview.getLambda());
                sommetFXPrecedent.setApp(main);
                sommetFXPrecedent.setTypeSommet(sommetSerializablePreview.getNoeudType());
                if (sommetFXPrecedent.getTypeSommet() == SommetFX.SOMMET_TYPE_X1 || sommetFXPrecedent.getTypeSommet() == SommetFX.SOMMET_TYPE_XN) { 
                    sommetFXPrecedent.setFill(Color.DODGERBLUE); 
                }else{
                    sommetFXPrecedent.setFill(Color.WHITE); 
                }
                sommetFXPrecedent.setID(sommetSerializablePreview.getNoeudIteration());
                sommetFXPrecedent.getLblName().setText("X"+String.valueOf(sommetFXPrecedent.getID()));
                sommetFXPrecedent.setController(this.getController());
                
                sommetFX.getListPreviewSommets().add(sommetFXPrecedent);
            }
            ArrayList<SommetSerializable> sommetSerializableNextList  = sommetSerializable.getListSommetSuivants();
            for (int j = 0; j < sommetSerializableNextList.size(); j++) {
                SommetSerializable sommetSerializableNext = sommetSerializableNextList.get(j);
                SommetFX sommetFXSuivant =  new SommetFX();
                sommetFXSuivant.setCenterX(sommetSerializableNext.getCenterX());
                sommetFXSuivant.setCenterY(sommetSerializableNext.getCenterY());
                sommetFXSuivant.setLambda(sommetSerializableNext.getLambda());
                sommetFXSuivant.setApp(main);
                sommetFXSuivant.setTypeSommet(sommetSerializableNext.getNoeudType());
                if (sommetFXSuivant.getTypeSommet() == SommetFX.SOMMET_TYPE_X1 || sommetFXSuivant.getTypeSommet() == SommetFX.SOMMET_TYPE_XN) { 
                    sommetFXSuivant.setFill(Color.DODGERBLUE); 
                }else{
                    sommetFXSuivant.setFill(Color.WHITE); 
                }
                sommetFXSuivant.setID(sommetSerializableNext.getNoeudIteration());
                sommetFXSuivant.getLblName().setText("X"+String.valueOf(sommetFXSuivant.getID()));
                sommetFXSuivant.setController(this.getController());

                sommetFX.getListNextSommets().add(sommetFXSuivant);
            }
            sommetFXList.add(sommetFX);
        }
    
    }
    public void convertToArcFXList(){
        arcFXList = new ArrayList<>();
        ArrayList<ArcSerializable> arcSerializables = getDataSerializable().getListArcSerializable();
        for (int i = 0; i < arcSerializables.size(); i++) {
            ArcSerializable arcSerializable = arcSerializables.get(i);
            ArcFX lien = new ArcFX();
            lien.setStartX(arcSerializable.getStartX());
            lien.setStartY(arcSerializable.getStartY());
            lien.setEndX(arcSerializable.getEndX());
            lien.setEndY(arcSerializable.getEndY());
            lien.setControlX(arcSerializable.getControlX());
            lien.setControlY(arcSerializable.getControlY());
            lien.setV_ij(arcSerializable.getVij());
            lien.getTxfVij().setText(String.valueOf(lien.getV_ij()));
            lien.setController(getController());
            
            SommetSerializable sommetSerializableStart = arcSerializable.getConnectedNodeList().get(0);
            SommetFX sommetFXStart = new SommetFX();
            sommetFXStart.setCenterX(sommetSerializableStart.getCenterX());
            sommetFXStart.setCenterY(sommetSerializableStart.getCenterY());
            sommetFXStart.setLambda(sommetSerializableStart.getLambda());
            sommetFXStart.setApp(main);
            sommetFXStart.setTypeSommet(sommetSerializableStart.getNoeudType());
            if (sommetFXStart.getTypeSommet() == SommetFX.SOMMET_TYPE_X1 || sommetFXStart.getTypeSommet() == SommetFX.SOMMET_TYPE_XN) { 
                sommetFXStart.setFill(Color.DODGERBLUE); 
            }else{
                sommetFXStart.setFill(Color.WHITE); 
            }
            sommetFXStart.setID(sommetSerializableStart.getNoeudIteration());
            sommetFXStart.getLblName().setText("X"+String.valueOf(sommetFXStart.getID()));
            sommetFXStart.setController(this.getController());
            
            
          SommetSerializable sommetSerializableEnd = arcSerializable.getConnectedNodeList().get(1);
            SommetFX sommetFXEnd = new SommetFX();
            sommetFXEnd.setCenterX(sommetSerializableEnd.getCenterX());
            sommetFXEnd.setCenterY(sommetSerializableEnd.getCenterY());
            sommetFXEnd.setLambda(sommetSerializableEnd.getLambda());
            sommetFXEnd.setApp(main);
            sommetFXEnd.setTypeSommet(sommetSerializableEnd.getNoeudType());
            if (sommetFXEnd.getTypeSommet() == SommetFX.SOMMET_TYPE_X1 || sommetFXEnd.getTypeSommet() == SommetFX.SOMMET_TYPE_XN) { 
                sommetFXEnd.setFill(Color.DODGERBLUE); 
            }else{
                sommetFXEnd.setFill(Color.WHITE); 
            }
            sommetFXEnd.setID(sommetSerializableEnd.getNoeudIteration());
            sommetFXEnd.getLblName().setText("X"+String.valueOf(sommetFXEnd.getID()));
            sommetFXEnd.setController(this.getController());
            
            lien.getListSommets().add(0, sommetFXStart);
            lien.getListSommets().add(1, sommetFXEnd);
            arcFXList.add(lien);
            
        }
    }

    /**
     * @return the controller
     */
    public FordFulkersonFXController getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(FordFulkersonFXController controller) {
        this.controller = controller;
    }
}
