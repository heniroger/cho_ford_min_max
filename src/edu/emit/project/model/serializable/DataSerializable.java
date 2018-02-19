/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model.serializable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author heniroger
 */
public class DataSerializable implements Serializable{
    private ArrayList<ROComponentSerializable> listSerializable = new ArrayList<>();
    private ArrayList<ArcSerializable> listArcSerializable = new ArrayList<>();
    private ArrayList<SommetSerializable> listSommetSerializable = new ArrayList<>();
    


    public ArrayList<ROComponentSerializable> getListSerializable() {
        return listSerializable;
    }

    public ArrayList<ArcSerializable> getListArcSerializable() {
        return listArcSerializable;
    }

    public ArrayList<SommetSerializable> getListSommetSerializable() {
        return listSommetSerializable;
    }

    public void setListSerializable(ArrayList<ROComponentSerializable> listSerializable) {
        this.listSerializable = listSerializable;
    }

    public void setListArcSerializable(ArrayList<ArcSerializable> listArcSerializable) {
        this.listArcSerializable = listArcSerializable;
    }

    public void setListSommetSerializable(ArrayList<SommetSerializable> listSommetSerializable) {
        this.listSommetSerializable = listSommetSerializable;
    }
}
