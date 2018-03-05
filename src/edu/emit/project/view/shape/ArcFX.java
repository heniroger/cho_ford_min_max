/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import edu.emit.project.controller.FordFulkersonFXController;
import edu.emit.project.model.serializable.ArcSerializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author heniroger
 */
public class ArcFX extends QuadCurve{
    private final ArcFX context = this;
    private FordFulkersonFXController controller;

    private  Label vij = new Label("V(xi,xj)=");
    private  TextField txfVij = new TextField("0");
    private  double v_ij; 
    private Pointe pointe;
    
    private ArrayList<SommetFX> listSommets = new ArrayList<>();
    private boolean up =true;
    private boolean deletable = false;
    
    private ArcSerializable arcSerializable;
    
    public ArcFX(){
        setStrokeWidth(2);
        setFill(Color.WHITE.deriveColor(1, 1, 1, 0));
        setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
        setStrokeLineCap(StrokeLineCap.BUTT);
      //  getStrokeDashArray().setAll(10.0,5.);
        setMouseTransparent(false);
        
        
        vij.setLayoutX(getControlX()-75);
        vij.setLayoutY(getControlY()+75);
        txfVij.setPrefWidth(50);
        txfVij.setLayoutX(getControlX()-75);
        txfVij.setLayoutY(getControlY()+75);
    } 
    public void lier(DoubleProperty startX,DoubleProperty startY,DoubleProperty endX,DoubleProperty endY){
        try {
            if (pointe == null) {
                throw new PointeClassNotSetException();
            }
        } catch (PointeClassNotSetException e) {
            Logger.getLogger(ArcFX.class.getName()).log(Level.SEVERE, null, e);
            return;
        
        }
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
       // controlXProperty().bind(startX.add(endX));
        //controlXProperty().bind(startY.add(endY));
        getVij().layoutXProperty().bind(controlXProperty().add(15));
        getVij().layoutYProperty().bind(controlYProperty().subtract(5));
        getTxfVij().layoutXProperty().bind(controlXProperty().add(75));
        getTxfVij().layoutYProperty().bind(controlYProperty().subtract(10));
     
         enableClickEvent();
         enableDrag();
    }
    public  class  PointeClassNotSetException extends Exception{
        public PointeClassNotSetException(){
            System.err.println("Pointe Object is not set");
                        
        }
    }
    /**
     * @return the pointe
     */
    public Pointe getPointe() {
        return pointe;
    }

    /**
     * @param pointe the pointe to set
     */
    public void setPointe(Pointe pointe) {
        this.pointe = pointe;
    }
    
    private void enableClickEvent(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
    }
    private void enableDrag(){
          final Delta dragDelta = new Delta();
            setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = getControlX() - mouseEvent.getX();
                dragDelta.y = getControlY()- mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
                if (mouseEvent.isSecondaryButtonDown()) { deletable = true; }

                

              }
            });
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getScene().setCursor(Cursor.HAND);
            if (deletable) {
                   delete();
            }
        }
      });
      setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.isPrimaryButtonDown()) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    if (newX > 0 && newX < getScene().getWidth()) {
                      setControlX(newX);
                    }  
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < getScene().getHeight()) {
                      setControlY(newY);
                    }  
                
            }
          
        }
      });
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.HAND);
           setStroke(Color.GREEN);
           txfVij.setStyle("-fx-border-color:green;");
           vij.setTextFill(Color.GREEN);

          }
        }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.DEFAULT);
            setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
            txfVij.setStyle("-fx-border-color:gray;");
           vij.setTextFill(Color.BLACK);

          }
        }
      });
    }

    /**
     * @return the up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * @return the listSommets
     */
    public ArrayList<SommetFX> getListSommets() {
        return listSommets;
    }

    /**
     * @param listSommets the listSommets to set
     */
    public void setListSommets(ArrayList<SommetFX> listSommets) {
        this.listSommets = listSommets;
    }
    public  void delete(){
        setStroke(Color.BLACK);
        
        deleteFromGraphManager();
        Group group = (Group) context.getParent();
        if (context.getVij() == null) {
            return;
        }
        group.getChildren().remove(context.getVij());
        group.getChildren().remove(context.getTxfVij());
        group.getChildren().remove(context.getPointe());
        group.getChildren().remove(context);
    }

    /**
     * @return the vij
     */
    public Label getVij() {
        return vij;
    }

    /**
     * @param vij the vij to set
     */
    public void setVij(Label vij) {
        this.vij = vij;
    }

    /**
     * @return the v_ij
     */
    public double getV_ij() {
        return v_ij;
    }

    /**
     * @param v_ij the v_ij to set
     */
    public void setV_ij(double v_ij) {
        this.v_ij = v_ij;
    }

    /**
     * @return the arcSerializable
     */
    public ArcSerializable getArcSerializable() {
        return arcSerializable;
    }

    /**
     * @param arcSerializable the arcSerializable to set
     */
    public void setArcSerializable(ArcSerializable arcSerializable) {
        this.arcSerializable = arcSerializable;
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
    
       public void deleteFromGraphManager(){
           ArrayList<ArcFX> arcFXList = getController().getGraphManager().getArcFXList();
           for (int i = 0; i < arcFXList.size(); i++) {
               if (arcFXList.get(i).equals(context)) {
                   arcFXList.remove(i);
               }
               
               
           }
       }
       @Override
       public String toString(){
           String str = "vij = "+String.valueOf(vij);
          
           str += this.getListSommets().get(0).getID()+" =====> "+this.getListSommets().get(1).getID();
           return str;
       }

    /**
     * @return the txfVij
     */
    public TextField getTxfVij() {
        return txfVij;
    }

    /**
     * @param txfVij the txfVij to set
     */
    public void setTxfVij(TextField txfVij) {
        this.txfVij = txfVij;
    }
}
