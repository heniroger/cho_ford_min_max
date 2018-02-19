/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import edu.emit.project.model.serializable.ROComponentSerializable;
import edu.emit.project.view.shape.event.DragEvent;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 *
 * @author heniroger
 */
public class ArcFXOld extends ROComponentFX{
        /**
     * Coordonnée du pointe de la fleche
     */
    double[] points ={0.0, 10.0,-10.0,-10.0,10.0,-10.0};
    private Polygon trianglePolygon = new Polygon(points);
    double radius =30;
    private ArrayList<DragEvent>     shapeDragEventList = new ArrayList<>();

    private TextField txfVij ;
    private Line line;
   public ArcFXOld(){
        super();
        
        this.radius *=2;
        
        txfVij = new TextField("1");
        txfVij.setStyle("-fx-background-color:red;");
        txfVij.setPrefSize(30, 10);
        
        line = new Line();
        line.setStartX(20);
        line.setStartY(20);
        line.setEndX(250);
        line.setEndY(20);

           
     
        
        BorderPane borderPane = new BorderPane();
        
        Pane leftPane = new Pane();
        Pane rightPane = new Pane();
        
        leftPane.setPrefWidth(55);
        rightPane.setPrefWidth(55);
        
        
        BorderPane bp = new BorderPane(txfVij);
        bp.setRight(rightPane);
        bp.setLeft(leftPane);
        
        initialize();
        
        
        borderPane.setCenter(bp);
        //borderPane.setBottom(shape);
        
        
        //this.getChildren().addAll(borderPane);
        
        
    }
   
   private void initialize(){
       double angle = Math.atan2(line.getEndY()-line.getStartY(),line.getEndX()-line.getStartX())*180/3.14;
       double height = line.getEndY()-line.getStartY();
       double width = line.getEndX()-line.getStartX();
       double length = Math.sqrt(Math.pow(height, 2)+Math.pow(width, 2));
       
       double substractWidth = radius*width/length;
       double substractHeight = radius*height/length;
       
       trianglePolygon.setRotate(angle-90);
       trianglePolygon.setTranslateX(line.getStartX());
       trianglePolygon.setTranslateY(line.getStartY());
       trianglePolygon.setTranslateX(line.getEndX()-substractWidth);
       trianglePolygon.setTranslateX(line.getEndY()-substractHeight);
   
   }
    
   public void update(){this.initialize();}
   
/**       public ArcFX(){
        super();
        txfVij = new TextField("1");
        txfVij.setStyle("-fx-background-color:red;");
        txfVij.setPrefSize(30, 10);
        
        line = new Line();
        line.setStartX(20);
        line.setStartY(20);
        line.setEndX(250);
        line.setEndY(20);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                line.getEndX(),10.0,
                line.getEndX()+10,15.0,
                line.getEndX(),20.0,
            }
        );

        
        
        makeDraggable(this, line);
        //makeRotate(line);
        
        BorderPane borderPane = new BorderPane();
        
        Pane leftPane = new Pane();
        Pane rightPane = new Pane();
        
        leftPane.setPrefWidth(55);
        rightPane.setPrefWidth(55);
        
        
        BorderPane bp = new BorderPane(txfVij);
        bp.setRight(rightPane);
        bp.setLeft(leftPane);
        
        
        
        
        borderPane.setCenter(bp);
        //borderPane.setBottom(shape);
        
        
        //this.getChildren().addAll(borderPane);
        
        
        
    }
    */


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
    public void addShapeDragEvent(DragEvent dragEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setShapeDragEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOnControlClickListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void clearShapeDragEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





     public void setStartX(Double x){ line.setStartX(x); }
     public void setStartY(Double y){ line.setStartY(y); }
     public void setEndX(Double x){ line.setEndX(x); }
     public void setEndY(Double y){ line.setEndY(y); }
     public Double getStartX(){ return line.getStartX(); }
     public Double getStartY(){ return line.getStartY(); }
     public Double getEndX(){   return line.getEndX(); }
     public Double getEndY(){   return line.getEndY(); }
    
}
