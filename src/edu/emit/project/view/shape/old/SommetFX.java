/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape.old;

import edu.emit.project.controller.FordFulkersonFXController;
import edu.emit.project.model.serializable.SommetSerializable;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author heniroger
 */
public class SommetFX extends Circle{
    private int iteration=0;
    private Label lblIteration;
    
    private double lambdaValue=0.0;
    private Label lblLambda; 
    private static int i=0;
    
   public static int SOMMET_TYPE_X1 =0;
   public static int SOMMET_TYPE_XI =1;
   public static int SOMMET_TYPE_XN =2;
    
    private static ArrayList<SommetFX> xiList = new ArrayList<>();
    
    private ArrayList<SommetFX> listSommetPrecedent = new ArrayList<>();
    private ArrayList<SommetFX> listSommetSuivant = new ArrayList<>();
    private SommetSerializable sommetSerializable;
    
    private double x,y;
    private FordFulkersonFXController controller;
    
    public static boolean  linkable = false;
     DoubleProperty startXProperty = null;
     DoubleProperty endXProperty =   null;
     DoubleProperty startYProperty = null;
     DoubleProperty endYProperty =   null;
    
    public SommetFX(FordFulkersonFXController controller,Integer... type){
      super();
                
      this.setRadius(40.0f);
       this.setCenterX(100.0f);
       this.setCenterY(100.0f);
       this.setStroke(Color.BLACK);
       this.setStrokeType(StrokeType.CENTERED);
       this.setStrokeWidth(5.0);
       this.setSmooth(true);
       this.setStrokeWidth(3.0);
        
        iteration = ++i;
        
        if(iteration == 1){
               this.setFill(Paint.valueOf("#009688"));
        }else if(iteration >1 ){this.setFill(Paint.valueOf("#FFFFFF"));}
        
        System.out.println(type.length);
        if (type.length ==1 ) {if ( type[0] == SOMMET_TYPE_X1) {this.setFill(Paint.valueOf("#009688"));}}
        if (type.length ==1) {if ( type[0] == SOMMET_TYPE_XI) {this.setFill(Paint.valueOf("#FFFFFF"));}}
        if (type.length ==1) {if ( type[0] == SOMMET_TYPE_XN) {this.setFill(Paint.valueOf("#009688"));}}
        
        
        

        lblLambda = new Label("\u03BB"+getIteration()+"=");
        lblIteration =new Label("X"+getIteration());
        
        
        lblLambda.setTextFill(Paint.valueOf("#FF0000"));
        
        this.controller =controller;
        addToXiList(this);
                
        setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
              x = getCenterX()-mouseEvent.getX();
              y = getCenterY()-mouseEvent.getY();
              
              handleArcFX(mouseEvent);
          }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
              setCenterX(mouseEvent.getSceneX()-x);
              setCenterY(mouseEvent.getSceneY()-y);
              
              updateArcFXs();
          }
      });


    }

    public int getIteration() {
        return iteration;
    }

    public Label getLblIteration() {
        return lblIteration;
    }

    public double getLambdaValue() {
        return lambdaValue;
    }

    public Label getLblLambda() {
        return lblLambda;
    }

    public static int getI() {
        return i;
    }

   
    public static ArrayList<SommetFX> getXiList() {
        return xiList;
    }

    public ArrayList<SommetFX> getListSommetPrecedent() {
        return listSommetPrecedent;
    }

    public ArrayList<SommetFX> getListSommetSuivant() {
        return listSommetSuivant;
    }

    public SommetSerializable getSommetSerializable() {
        return sommetSerializable;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public FordFulkersonFXController getController() {
        return controller;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public void setLblIteration(Label lblIteration) {
        this.lblIteration = lblIteration;
    }

    public void setLambdaValue(double lambdaValue) {
        this.lambdaValue = lambdaValue;
    }

    public void setLblLambda(Label lblLambda) {
        this.lblLambda = lblLambda;
    }

    public static void setI(int aI) {
        i = aI;
    }

    
    public static void setXiList(ArrayList<SommetFX> aXiList) {
        xiList = aXiList;
    }

    public void setListSommetPrecedent(ArrayList<SommetFX> listSommetPrecedent) {
        this.listSommetPrecedent = listSommetPrecedent;
    }

    public void setListSommetSuivant(ArrayList<SommetFX> listSommetSuivant) {
        this.listSommetSuivant = listSommetSuivant;
    }

    public void setSommetSerializable(SommetSerializable sommetSerializable) {
        this.sommetSerializable = sommetSerializable;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setController(FordFulkersonFXController controller) {
        this.controller = controller;
    }
    public void addToXiList(SommetFX sommetFX){ getXiList().add(sommetFX);}
    public int getLastIteration(){return xiList.size();}
    public static void clearXiList(){xiList.clear();}

   private void handleArcFX(MouseEvent mouseEvent){
       if (mouseEvent.isControlDown() && mouseEvent.isPrimaryButtonDown()) {
           
           if (mouseEvent.getSource().getClass().equals(SommetFX.class)) {
               
               ArcFX arcFX = null;
               
               if (this.controller.lastArcFXIsFull()) {
                    arcFX = new ArcFX();
                   // this.controller.arcFXList.add(arcFX);
               }else{
                   System.out.println(this.controller.lastArcFXIsFull());
                   //arcFX = this.controller.getLastArcFX();
               }
               
               
            
               if (!arcFX.sommetFXsIsFull()) {
                      arcFX.sommetFXs.add(this);
               }
               
                if (arcFX.sommetFXsIsFull()){
                      arcFX.handleSommetFx();
               }
               
               
              
               
               

              
           }else{
           
                System.err.println(mouseEvent.getSource().getClass());
           }
       }
   
   }
   private void updateArcFXs(){
       for (edu.emit.project.view.shape.ArcFX arcFX : this.controller.getArcFXs()) {
          // arcFX.update();
       }
   
   }
   public void bind(DoubleProperty xProperty,DoubleProperty yProperty){
       xProperty.bind(this.centerXProperty());
       yProperty.bind(this.centerYProperty());
   }
}
