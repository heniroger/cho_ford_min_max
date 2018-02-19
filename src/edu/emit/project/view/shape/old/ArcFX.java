/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape.old;


import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 *
 * @author heniroger
 */
public class ArcFX extends Polygon{
    private Line line;
    private double radius = 40.0f;
    private Double[] points =new Double[]{0.0, 10.0, -10.0, -10.0, 10.0, -10.0};
    
     public ArcFX(){
        this.getPoints().addAll(points);
        this.line = new Line();
        initialize();
    }
    /**
     * 2  noeuds 
     */
     public ArrayList<SommetFX> sommetFXs = new ArrayList<>();
     
    public ArcFX(DoubleProperty startXProperty,DoubleProperty startYProperty,DoubleProperty endXProperty,DoubleProperty endYProperty){
        this.getPoints().addAll(points);
        this.line = new Line(startXProperty.get(),startYProperty.get(),endXProperty.get(),endYProperty.get());
        this.radius *= 2;
        initialize();
    }

    public Line getLine() {
        return line;
    }

    public double getRadius() {
        return radius;
    }


    public void setLine(Line line) {
        this.line = line;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setPoints(Double[] points) {
        this.points = points;
    }
    private void initialize(){
       double angle = Math.atan2(getLine().getEndY()-getLine().getStartY(),getLine().getEndX()-getLine().getStartX())*180/3.14;
       double height = getLine().getEndY()-getLine().getStartY();
       double width = getLine().getEndX()-getLine().getStartX();
       double length = Math.sqrt(Math.pow(height, 2)+Math.pow(width, 2));
       
       double substractWidth = getRadius()*width/length;
       double substractHeight = getRadius()*height/length;
       
       this.setRotate(angle-90);
       this.setTranslateX(getLine().getStartX());
       this.setTranslateY(getLine().getStartY());
       this.setTranslateX(getLine().getEndX()-substractWidth);
       this.setTranslateX(getLine().getEndY()-substractHeight);
   }
    
   public void update(){this.initialize();}
   
   public void setStartX(Double x){
       this.line.setStartX(x);
   }
   public void setStartY(Double y){
       this.line.setStartY(y);
   }
   public void setEndX(Double x){
       this.line.setEndX(x);
   }
   public void setEndY(Double y){
       this.line.setEndY(y);
   }
   public Double getStartX(){
      return this.line.getStartX();
   }
   public Double getStartY(){
      return this.line.getStartY();
   }
   public Double getEndX(){
      return this.line.getEndX();
   }
   public Double getEndY(){
      return this.line.getEndY();
   }

    public ArrayList<SommetFX> getSommetFXs() {
        return sommetFXs;
    }

    public void setSommetFXs(ArrayList<SommetFX> sommetFXs) {
        this.sommetFXs = sommetFXs;
    }
    public boolean sommetFXsIsFull(){
        
        if (this.sommetFXs.size() ==2) {
            return true;
        }
       
        return false;
    
    }

    public void handleSommetFx() {
        DoubleProperty startXProperty = null;
        DoubleProperty endXProperty =   null;
        DoubleProperty startYProperty = null;
        DoubleProperty endYProperty =   null;
        SommetFX sommetFX1 = sommetFXs.get(0);
        SommetFX sommetFX2 = sommetFXs.get(1);
                   
                   startXProperty=sommetFX1.centerXProperty();
                   startYProperty=sommetFX1.centerYProperty();
                   
                   this.setStartX(startXProperty.get());
                   this.setStartY(startYProperty.get());
                   
                   
                   sommetFX1.bind(this.getLine().startXProperty(),this.getLine().startYProperty());
                        
                       
                   /**/
                   
                   System.err.println(" startXProperty :"+ startXProperty.get());
                   System.err.println(" startYProperty :"+startYProperty.get());
                    String linkable = null;
                   System.err.println("linkable :"+linkable);

                  
                  endXProperty=sommetFX2.centerXProperty();
                  endYProperty=sommetFX2.centerYProperty();
                  
                  this.setStartX(startXProperty.get());
                   this.setStartY(startYProperty.get());
                   this.setEndX(endXProperty.get());
                   this.setEndY(endYProperty.get());
                   
                  
                  sommetFX2.bind(this.getLine().endXProperty(),this.getLine().endYProperty());
  
                 
                   
                  
                                    
                   
                    //controller.getArcFXs().add(arcFX);

                   
               
               ((Group) this.getParent()).getChildren().addAll(this,this.line);
               
    }
}
