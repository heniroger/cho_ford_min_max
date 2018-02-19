/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.view.shape;

import edu.emit.project.model.serializable.DataSerializable;
import edu.emit.project.model.serializable.ROComponentSerializable;
import edu.emit.project.view.shape.event.DragEvent;
import java.util.ArrayList;
import javafx.scene.Group;

/**
 *
 * @author heniroger
 */
public abstract class ROComponentFX  extends Group{
    protected ArrayList<DragEvent> shapeDragEventList;

    public abstract void addSerializable(ROComponentSerializable rOComponentSerializable);
    public abstract void updateSerializable();
    public abstract ROComponentSerializable getSerializable();
    public abstract  void addShapeDragEvent(DragEvent dragEvent); 
    public abstract void setShapeDragEvent();
    public abstract void setOnControlClickListener();
    @Deprecated
    public  ArrayList<DragEvent> getShapeDragEvent(){return shapeDragEventList;}
    
    @Deprecated
    protected abstract void clearShapeDragEvent();
    public void addToData(DataSerializable dataSerializable,ROComponentFX rOComponentFX){
    
    }
    
}
