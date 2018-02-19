/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape.event;

import edu.emit.project.view.shape.DragContext;
import edu.emit.project.view.shape.SommetFXOld;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author heniroger
 */
public class SommetDragEvent extends DragEvent{
    Circle circStart ;
    Line lineArc;
    private SommetDragEvent(){}
    public SommetDragEvent(SommetFXOld  sommetFX){
        EventHandler<MouseEvent> mousePressedEventHandler =null;
        EventHandler<MouseEvent> mouseDraggedEventHandler =null;
        EventHandler<MouseEvent> mouseRealeseddEventHandler =null;
        
        DragContext dragContext =new DragContext();
        Group groupGraph = (Group) sommetFX.getParent();
        
        mousePressedEventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                  dragContext.dx = mouseEvent.getX() - sommetFX.getCircSommet().getCenterX();
                  dragContext.dy = mouseEvent.getY() - sommetFX.getCircSommet().getCenterY();
                  //   System.out.println(dragContext.dx+","+dragContext.dy);            
                  
                  if (mouseEvent.isControlDown() && mouseEvent.isPrimaryButtonDown()) {
      
                    }else if (mouseEvent.isPrimaryButtonDown()) {
                           
                           
                           lineArc = new Line();
                           if((mouseEvent.getSource()).getClass().equals(SommetFXOld.class)){
                                    circStart = ((SommetFXOld) mouseEvent.getSource()).getCircSommet();

                                    double lineXStart =circStart.getCenterX()+mouseEvent.getX();
                                    double lineYStart = circStart.getCenterY()+mouseEvent.getY();
                                    
                                    DoubleProperty lineXStartProperty = circStart.centerXProperty();
                                    DoubleProperty lineYStartProperty = circStart.centerYProperty();
                                    System.out.println(lineXStart+" "+lineYStart);

                                    groupGraph.getChildren().add(lineArc);
                                  //  lineArc.setStartX(lineXStart);
                                    //lineArc.setStartY(lineYStart);
                                    
                                     lineArc.startXProperty().bind(lineXStartProperty);
                                     lineArc.startYProperty().bind(lineYStartProperty);
                                    
                           }
                          
                    }

                }
        };
        mouseDraggedEventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.isControlDown() && mouseEvent.isPrimaryButtonDown()) {

                            sommetFX.setLayoutX(mouseEvent.getSceneX()+dragContext.dx);
                            sommetFX.setLayoutY(mouseEvent.getSceneY()+dragContext.dy);
                               
                        }else if (mouseEvent.isPrimaryButtonDown()) {
                            if (lineArc!=null) {
                                lineArc.setEndX(mouseEvent.getSceneX()+dragContext.dx);
                                lineArc.setEndY(mouseEvent.getSceneY()+dragContext.dy);
                                
                            }

                        }


                }
        };
        mouseRealeseddEventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.isControlDown() && mouseEvent.isPrimaryButtonDown()) {


                                sommetFX.setLayoutX(mouseEvent.getSceneX()+dragContext.dx);
                                sommetFX.setLayoutY(mouseEvent.getSceneY()+dragContext.dy);
                                sommetFX.setCenter(mouseEvent.getSceneX()+dragContext.dx, mouseEvent.getSceneX()+dragContext.dy);

                      }else if (mouseEvent.isPrimaryButtonDown()) {
                        if((mouseEvent.getSource()).getClass().equals(SommetFXOld.class)){
                                                        System.err.println(mouseEvent.getSource().getClass());

                                            SommetFXOld sommetFXEnd = (SommetFXOld) mouseEvent.getSource();
                                            double lineXEnd = sommetFXEnd.getCenterX();
                                            double lineYEnd = sommetFXEnd.getCenterY();
                                            DoubleProperty lineXEndProperty = sommetFXEnd.centerXProperty();
                                            DoubleProperty lineYEndProperty = sommetFXEnd.centerYProperty();

                                            lineArc.endXProperty().bind(lineXEndProperty);
                                            lineArc.endYProperty().bind(lineYEndProperty);
                                            


                                }
                    }
                    
                     if(!(mouseEvent.getSource()).getClass().equals(SommetFXOld.class)){
                                    groupGraph.getChildren().remove(lineArc);
                                
                     }
                    
                    

                }
        };
                sommetFX.addEventFilter(MouseEvent.MOUSE_PRESSED,mousePressedEventHandler);
                sommetFX.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDraggedEventHandler);
                sommetFX.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseRealeseddEventHandler);
      

    }
}
