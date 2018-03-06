/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import edu.emit.project.Main;
import edu.emit.project.controller.FordFulkersonFXController;
import edu.emit.project.model.serializable.SommetSerializable;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author heniroger
 */
public class SommetFX extends Circle{
    Main app;
    private int id=0;
    private final SommetFX context = this;
    private FordFulkersonFXController controller;
    
    private Label lblName;
    private TextField txfLambda;                        
    private Label lblLambda;
    private double lambda;
    private boolean deletable = false;
    
    public static int SOMMET_TYPE_X1 =0;
    public static int SOMMET_TYPE_XI =1;
    public static int SOMMET_TYPE_XN =2;
    private int typeSommet = SOMMET_TYPE_XI;
    
    private boolean alreadyBounded = false;
    private ArrayList<Object[]>  previewSommets = new ArrayList<>();
    private ArrayList<Object[]>  nextSommets = new ArrayList<>();
    private ArrayList<SommetFX>  listPreviewSommets = new ArrayList<>();
    private ArrayList<SommetFX>  listNextSommets = new ArrayList<>();
    private ArrayList<ArcFX>  listLiens = new ArrayList<>();
    
    private SommetSerializable sommetSerializable;
    
    
    public SommetFX() {
        super();
        setFill(Color.WHITE);
        setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
        setStrokeWidth(2);
        setStrokeType(StrokeType.CENTERED);
        setCenterX(150);
        setCenterY(150);
        setRadius(30.0f);
        
        lblName = new Label("X1");
        lblLambda= new Label("\u03BB=");
        txfLambda = new TextField("0");
        txfLambda.setPrefWidth(50);
        txfLambda.setEditable(false);
        
        initialize();
        enableDrag();
        enableMouseClickToBind();
    }
    
    public void setApp(Main app){this.app = app;}
    public void lier(DoubleProperty x, DoubleProperty y){
        try {
            if (app == null) {
             throw new AppNotDefinedException();
            }
        } catch (Exception ex) {
            Logger.getLogger(SommetFX.class.getName()).log(Level.SEVERE, null, ex);
        }

       bindSommetFXComponent();
        
        
        x.bind(centerXProperty());
        y.bind(centerYProperty());
    
    }
    public void bindSommetFXComponent(){
         lblName.layoutXProperty().bind(centerXProperty());
        lblName.layoutYProperty().bind(centerYProperty());
         lblLambda.layoutXProperty().bind(centerXProperty());
        lblLambda.layoutYProperty().bind(centerYProperty());
        txfLambda.layoutXProperty().bind(centerXProperty());
        txfLambda.layoutYProperty().bind(centerYProperty());
    }
     private void enableDrag() {
      final Delta dragDelta = new Delta();
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          // record a delta distance for the drag and drop operation.
          dragDelta.x = getCenterX() - mouseEvent.getX();
          dragDelta.y = getCenterY() - mouseEvent.getY();
          getScene().setCursor(Cursor.MOVE);
          if(mouseEvent.isControlDown() && mouseEvent.isSecondaryButtonDown()){deletable = true; }
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
          double newX = mouseEvent.getX() + dragDelta.x;
          if (newX > 0 && newX < getScene().getWidth()) {
            setCenterX(newX);
          }  
          double newY = mouseEvent.getY() + dragDelta.y;
          if (newY > 0 && newY < getScene().getHeight()) {
            setCenterY(newY);
          }  
          
            for (Pointe pointe : app.getPointes()) {
                pointe.update();
            }
            
            update();
        }
      });
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.HAND);
            setStroke(Color.BLUE);
          }
        }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.DEFAULT);
             setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.5));
          }
        }
      });
    }
    
    public class AppNotDefinedException extends Exception{
     
        public AppNotDefinedException()throws Exception{
        
            System.err.println("App is null or not defined");
        
        }
    
    }

    /**
     * @return the lblName
     */
    public Label getLblName() {
        return lblName;
    }

    /**
     * @param lblName the lblName to set
     */
    public void setLblName(Label lblName) {
        this.lblName = lblName;
    }

    /**
     * @return the txfLambda
     */
    public TextField getTxfLambda() {
        return txfLambda;
    }

    /**
     * @param txfLambda the txfLambda to set
     */
    public void setTxfLambda(TextField txfLambda) {
        this.txfLambda = txfLambda;
    }
    
    public void initialize(){
        lblName.setLayoutX(getCenterX()-10);
        lblName.setLayoutY(getCenterY()-10);
        lblLambda.setLayoutX(getCenterX()-45);
        lblLambda.setLayoutY(getCenterY()-70);
        txfLambda.setLayoutX(getCenterX()-25);
        txfLambda.setLayoutY(getCenterY()-75);
    }
    public void update(){
        initialize();
    }
   private void enableMouseClickToBind(){
       setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               if (event.isControlDown()) {
                   
                   if (Tetha.startSommet != null) {
                       if (Tetha.startSommet.equals(context)) {
                           Tetha.startSommet = null;
                           return;
                       }
                       Tetha.endSommet = context;
                       
                        ArcFX lien = new ArcFX();
                        Pointe pointe = new Pointe();
                        pointe.linkTo(lien, 40.0f);
                        app.pointes.add(pointe);

                        lien.setPointe(pointe);
                        lien.lier(Tetha.startSommet.centerXProperty(), Tetha.startSommet.centerYProperty(), Tetha.endSommet.centerXProperty(), Tetha.endSommet.centerYProperty());
                        lien.setControlY(getCenterY());
                        lien.setController(getController());
                        
                        
                        Tetha.startSommet.getNextSommets().add(new Object[]{ Tetha.endSommet,lien});
                        Tetha.endSommet.getPreviewSommets().add(new Object[]{ Tetha.startSommet,lien});
                        Tetha.startSommet.getListNextSommets().add(Tetha.endSommet);
                        Tetha.endSommet.getListPreviewSommets().add(Tetha.startSommet);
                        Tetha.startSommet.getListLiens().add(lien);
                        Tetha.endSommet.getListLiens().add(lien);
                        lien.getListSommets().add(Tetha.startSommet);
                        lien.getListSommets().add(Tetha.endSommet);
                        lien.setV_ij(Double.parseDouble(lien.getTxfVij().getText()));
                        getController().getGraphManager().getArcFXList().add(lien);
                                              
                        Group group = (Group) context.getParent();
                        group.getChildren().addAll(pointe,lien,lien.getVij(),lien.getTxfVij());
                        pointe.update();
                        lien.toBack();
                        
                        Tetha.startSommet =null;
                        Tetha.endSommet =null;
                        
                   }else{
                       Tetha.startSommet = context;
                   }
               }
           }
       });
   }

    /**
     * @return the alreadyBounded
     */
    public boolean isAlreadyBounded() {
        return alreadyBounded;
    }
    


    /**
     * @param alreadyBounded the alreadyBounded to set
     */
    public void setAlreadyBounded(boolean alreadyBounded) {
        this.alreadyBounded = alreadyBounded;
    }

    /**
     * @return the previewSommets
     */
    public ArrayList<Object[]> getPreviewSommets() {
        return previewSommets;
    }

    /**
     * @param previewSommets the previewSommets to set
     */
    public void setPreviewSommets(ArrayList<Object[]> previewSommets) {
        this.previewSommets = previewSommets;
    }

    /**
     * @return the nextSommets
     */
    public ArrayList<Object[]> getNextSommets() {
        return nextSommets;
    }

    /**
     * @param nextSommets the nextSommets to set
     */
    public void setNextSommets(ArrayList<Object[]> nextSommets) {
        this.nextSommets = nextSommets;
    }

    /**
     * @return the listPreviewSommets
     */
    public ArrayList<SommetFX> getListPreviewSommets() {
        return listPreviewSommets;
    }

    /**
     * @param listPreviewSommets the listPreviewSommets to set
     */
    public void setListPreviewSommets(ArrayList<SommetFX> listPreviewSommets) {
        this.listPreviewSommets = listPreviewSommets;
    }

    /**
     * @return the listNextSommets
     */
    public ArrayList<SommetFX> getListNextSommets() {
        return listNextSommets;
    }

    /**
     * @param listNextSommets the listNextSommets to set
     */
    public void setListNextSommets(ArrayList<SommetFX> listNextSommets) {
        this.listNextSommets = listNextSommets;
    }
   
    public void delete(){
        deleteFromGraphManager();
        
        Group group = (Group) context.getParent();
       
        for (int i = 0; i < getListLiens().size(); i++) {
            ArcFX lien = getListLiens().get(i);
            for (int j = 0; j < getListNextSommets().size(); j++) {
                SommetFX s = getListNextSommets().get(j);
                for (int k = 0; k < s.getListLiens().size(); k++) {
                    
                    if (s.getListLiens().get(k).equals(lien)) {
                        s.getListLiens().remove(k);
                    }
                    
                }
            }
            
             for (int j = 0; j < getListPreviewSommets().size(); j++) {
                SommetFX s = getListPreviewSommets().get(j);
                for (int k = 0; k < s.getListLiens().size(); k++) {
                    
                    if (s.getListLiens().get(k).equals(lien)) {
                        s.getListLiens().remove(k);
                    }
                    
                }
            }
             if (lien != null ){
                group.getChildren().remove(lien.getVij());
                group.getChildren().remove(lien.getTxfVij());
                group.getChildren().remove(lien.getPointe());
                group.getChildren().remove(lien);
            }
        }
        getListLiens().clear();
        
       group.getChildren().remove(context);
       group.getChildren().remove(context.txfLambda);
       group.getChildren().remove(context.lblLambda);
       group.getChildren().remove(context.lblName);
    }

    /**
     * @return the listLiens
     */
    public ArrayList<ArcFX> getListLiens() {
        return listLiens;
    }

    /**
     * @param listLiens the listLiens to set
     */
    public void setListLiens(ArrayList<ArcFX> listLiens) {
        this.listLiens = listLiens;
    }

    /**
     * @return the typeSommet
     */
    public int getTypeSommet() {
        return typeSommet;
    }

    /**
     * @param typeSommet the typeSommet to set
     */
    public void setTypeSommet(int typeSommet) {
        this.typeSommet = typeSommet;
    }

    /**
     * @return the id
     */
    public int getID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * @return the lambda
     */
    public double getLambda() {
        return lambda;
    }

    /**
     * @param lambda the lambda to set
     */
    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    /**
     * @return the sommetSerializable
     */
    public SommetSerializable getSommetSerializable() {
        return sommetSerializable;
    }

    /**
     * @param sommetSerializable the sommetSerializable to set
     */
    public void setSommetSerializable(SommetSerializable sommetSerializable) {
        this.sommetSerializable = sommetSerializable;
    }
    
     public void convertToSerializable(){
    
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
        ArrayList<SommetFX>  sommetFXList = getController().getGraphManager().getSommetFXList();
        for (int i = 0; i < sommetFXList.size(); i++) {
            if (sommetFXList.get(i).equals(context)) {
                sommetFXList.remove(i);
            }
        }
    }

    /**
     * @return the lblLambda
     */
    public Label getLblLambda() {
        return lblLambda;
    }

    /**
     * @param lblLambda the lblLambda to set
     */
    public void setLblLambda(Label lblLambda) {
        this.lblLambda = lblLambda;
    }
}
