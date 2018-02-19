/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model.serializable;

import java.util.ArrayList;
/**
 *
 * @author heniroger
 */
public class ArcSerializable extends ROComponentSerializable{
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double controlX;
    private double controlY;
    private double vij;
    
    private ArrayList<SommetSerializable> connectedNodeList = new ArrayList<>();
    
    public ArcSerializable(double startX,double startY,double endX,double endY,double controlX,double controlY,double vij){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.controlX = controlX;
        this.controlY = controlY;
        this.vij = vij;
     
    }
    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }


    public double getVij() {
        return vij;
    }
   
    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public void setVij(double vij) {
        this.vij = vij;
    }


   

    /**
     * @return the controlX
     */
    public double getControlX() {
        return controlX;
    }

    /**
     * @param controlX the controlX to set
     */
    public void setControlX(double controlX) {
        this.controlX = controlX;
    }

    /**
     * @return the controlY
     */
    public double getControlY() {
        return controlY;
    }

    /**
     * @param controlY the controlY to set
     */
    public void setControlY(double controlY) {
        this.controlY = controlY;
    }

    /**
     * @return the connectedNodeList
     */
    public ArrayList< SommetSerializable> getConnectedNodeList() {
        return connectedNodeList;
    }

    /**
     * @param connectedNodeList the connectedNodeList to set
     */
    public void setConnectedNodeList(ArrayList<SommetSerializable> connectedNodeList) {
        this.connectedNodeList = connectedNodeList;
    }
    
    
    
}
