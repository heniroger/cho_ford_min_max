/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

/**
 *
 * @author heniroger
 */

public class Pointe extends Polygon{
    private final Pointe context = this;
    Double[] points = {0.0, 5.0, -5.0, -5.0, 5.0, -5.0};
    private QuadCurve lien;
    private Double radius;

    public Pointe() {
        this.getPoints().addAll(points);
      
    }
    public void linkTo(QuadCurve lien,double sommetRadius){
        this.lien = lien;
        this.radius = sommetRadius * 1.25;
        initialize();
        
    }
    
    private void initialize() {
        double angle = Math.atan2(lien.getEndY() - lien.getStartY(), lien.getEndX() - lien.getStartX()) * 180 / 3.14;

        double height = lien.getEndY() - lien.getStartY();
        double width = lien.getEndX() - lien.getStartX();
        double length = Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));

        double subtractWidth = radius * width / length;
        double subtractHeight = radius * height / length;

        setRotate(angle - 90);
        
        setTranslateX(lien.getStartX());
        setTranslateY(lien.getStartY());
        setTranslateX(lien.getEndX()- subtractWidth);
        setTranslateY(lien.getEndY() - subtractHeight);
        
        setTranslateX(lien.getControlX());
        setTranslateY(lien.getControlY());
        
    }

    public void update(){
        initialize();
    }
    
    
    
}
