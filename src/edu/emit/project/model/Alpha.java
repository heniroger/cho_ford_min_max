/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author heniroger
 */
public class Alpha {
    private Integer iteration = 0 ;
    private HashMap<Integer,Double> vijMap;
    private ArrayList<Integer> intArrayList;

    public Alpha() {
        this.vijMap = new HashMap<>();
        this.intArrayList = new ArrayList<>();
    }

    /**
     * @return the intArrayList
     */
    public ArrayList<Integer> getIntArrayList() {
        return intArrayList;
    }

    /**
     * @param intArrayList the intArrayList to set
     */
    public void setIntArrayList(ArrayList<Integer> intArrayList) {
        this.intArrayList = intArrayList;
    }

    /**
     * @return the iteration
     */
    public Integer getIteration() {
        return iteration;
    }

    /**
     * @param iteration the iteration to set
     */
    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    /**
     * @return the vijMap
     */
    public HashMap<Integer,Double> getVijMap() {
        return vijMap;
    }

    /**
     * @param vijMap the vijMap to set
     */
    public void setVijMap(HashMap<Integer,Double> vijMap) {
        this.vijMap = vijMap;
    }

  
    
}
