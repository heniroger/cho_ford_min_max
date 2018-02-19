/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import edu.emit.project.model.serializable.ROComponentSerializable;
import edu.emit.project.model.serializable.SommetSerializable;
import edu.emit.project.view.shape.event.DragEvent;
import edu.emit.project.view.shape.event.SommetDragEvent;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author heniroger
 */
public class SommetFXOld extends ROComponentFX{
    protected int iteration=0;
    protected Label lblIteration;
    
    private double lambdaValue=0.0;
    protected Label lblLambda;
    
    protected Circle circSommet;
    
    protected static int i=0;
    private boolean movable=true;
    
    public final static int SOMMET_TYPE_X1 =0;
    public final static int SOMMET_TYPE_XI =1;
    public final static int SOMMET_TYPE_XN =2;
    
    private ArrayList<SommetFXOld> listSommetPrecedent = new ArrayList<>();
    private ArrayList<SommetFXOld> listSommetSuivant = new ArrayList<>();
    private SommetSerializable sommetSerializable;
    private ArrayList<DragEvent>     shapeDragEventList = new ArrayList<>();
    
    private SommetDragEvent sommetDragEvent;
    private static ArrayList<SommetFXOld> xiList = new ArrayList<>();
        
    public SommetFXOld(Integer... type){
        super();
                
        circSommet = new Circle(40.0f);
        circSommet.setCenterX(100.0f);
        circSommet.setCenterY(100.0f);
        circSommet.setStroke(Color.BLACK);
        circSommet.setStrokeType(StrokeType.CENTERED);
        circSommet.setStrokeWidth(5.0);
        circSommet.setSmooth(true);
        circSommet.setStrokeWidth(3.0);
        
        iteration = ++i;
        
        if(iteration == 1){
                circSommet.setFill(Paint.valueOf("#009688"));
        }else if(iteration >1 ){circSommet.setFill(Paint.valueOf("#FFFFFF"));}
        
        System.out.println(type.length);
        if (type.length ==1 ) {if ( type[0] == SOMMET_TYPE_X1) {circSommet.setFill(Paint.valueOf("#009688"));}}
        if (type.length ==1) {if ( type[0] == SOMMET_TYPE_XI) {circSommet.setFill(Paint.valueOf("#FFFFFF"));}}
        if (type.length ==1) {if ( type[0] == SOMMET_TYPE_XN) {circSommet.setFill(Paint.valueOf("#009688"));}}
        
        
        

        lblLambda = new Label("\u03BB"+iteration+"=");
        lblIteration =new Label("X"+iteration);
        
        
        lblLambda.setTextFill(Paint.valueOf("#FF0000"));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(circSommet);
        stackPane.getChildren().add(lblIteration);
        
        BorderPane borderPane = new BorderPane(stackPane);
        borderPane.setTop(lblLambda);
        this.getChildren().addAll(borderPane);
        
        addToXiList(this);
        setOnControlClickListener();
    
    }

    public Label getLblIteration() {
        return lblIteration;
    }

    public Label getLblLambda() {
        return lblLambda;
    }

    public void setLblIteration(Label lblIteration) {
        this.lblIteration = lblIteration;
    }

    public void setLblLambda(Label lblLambda) {
        this.lblLambda = lblLambda;
    }

    public Circle getCircSommet() {
        return circSommet;
    }

    public void setCircSommet(Circle circSommet) {
        this.circSommet = circSommet;
    }
    public void setCenterX(double value){this.circSommet.setCenterX(value);}
    public void setCenterY(double value){this.circSommet.setCenterY(value);}
    public void setCenter(double x, double y){
        this.circSommet.setCenterX(x);
        this.circSommet.setCenterY(y);
    }
    public DoubleProperty centerXProperty(){ return this.circSommet.centerXProperty();}
    public DoubleProperty centerYProperty(){return this.circSommet.centerYProperty();}

    public void addToXiList(SommetFXOld sommetFX){ xiList.add(sommetFX);}
    public int getLastIteration(){return xiList.size();}
    public static void clearXiList(){xiList.clear();}



    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
    public  void resetIteration(){ i=1;iteration=0;}

    public double getCenterX(){return circSommet.getCenterX();}
    public double getCenterY(){return circSommet.getCenterY();}

    public double getLambdaValue() {
        return lambdaValue;
    }

    public void setLambdaValue(double lambdaValue) {
        this.lambdaValue = lambdaValue;
    }

    @Override
    public void addSerializable(ROComponentSerializable rOComponentSerializable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSerializable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ROComponentSerializable getSerializable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addShapeDragEvent(DragEvent dragEvent) {shapeDragEventList.add(dragEvent);}
    
    public void setShapeDragEvent(){      
            sommetDragEvent= new SommetDragEvent(this);
            addShapeDragEvent(sommetDragEvent);
    }

    @Override
    public void setOnControlClickListener() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                             setShapeDragEvent();

            }
        });
    }

    @Override
    protected void clearShapeDragEvent() {
            sommetDragEvent= new SommetDragEvent(this);
            addShapeDragEvent(sommetDragEvent);
            shapeDragEventList.clear();
    }


    

    

}
