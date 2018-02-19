/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import static edu.emit.project.view.shape.SommetFXOld.i;
import javafx.scene.control.Label;
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
public class X1 extends SommetFXOld{
    
    public X1(){
        super();
        circSommet = new Circle(40.0f);
        circSommet.setCenterX(100.0f);
        circSommet.setCenterY(100.0f);
        circSommet.setStroke(Color.BLACK);
        circSommet.setStrokeType(StrokeType.CENTERED);
        circSommet.setStrokeWidth(5.0);
        circSommet.setSmooth(true);
        circSommet.setFill(Paint.valueOf("#009688"));
        circSommet.setStrokeWidth(3.0);
        
        
        iteration =1;
        lblLambda = new Label("\u03BB"+iteration+"=");
        lblIteration =new Label("X"+iteration);
        
        lblLambda.setTextFill(Paint.valueOf("#FF0000"));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(circSommet);
        stackPane.getChildren().add(lblIteration);
        
        BorderPane borderPane = new BorderPane(stackPane);
        borderPane.setTop(lblLambda);
        this.getChildren().addAll(borderPane);
        
        //makeDraggable(this,circSommet);
        super.addToXiList(this);
    }
}
