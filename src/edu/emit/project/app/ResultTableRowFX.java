/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project.app;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author heniroger
 */
public class ResultTableRowFX {
    private final SimpleStringProperty i;
    private final SimpleStringProperty j;
    private final SimpleStringProperty li;
    private final SimpleStringProperty lj;
    private final SimpleStringProperty vij;
    private final SimpleStringProperty nlj;
    private final SimpleStringProperty diffLjLi;
    
    public ResultTableRowFX(String[] row){
        String i2 = row[0];
        String j2 = row[1];
        String li2 = row[2];
        String lj2 = row[3];
        String vij2 = row[4];
        String nlj2 = row[5];
        String repeated2 = row[6];
        
        Double dLi = Double.valueOf(li2);
        Double dLj = Double.valueOf(lj2);
        String str= (dLj-dLi==1.0/0.0)?"M":""+(dLj-dLi);
        String strNlj = (nlj2.equals(""))?"": " \u03BB"+j2+"=\u03BB"+i2+"+v(x"+i2+",x"+j2+")="+(li2+"+"+vij2)+"="+nlj2;
        
        
        li= new SimpleStringProperty(li2);
        lj= new SimpleStringProperty(lj2);
            
        i=new SimpleStringProperty((repeated2.equals("y"))?i2:"");
        j=new SimpleStringProperty(j2);
        diffLjLi=new SimpleStringProperty("\u03BB"+j2+"-\u03BB"+i2+"="+((lj2.equals("Infinity"))?"M":lj2)+"-"+((li2.equals("Infinity"))?"M":li2)+"="+str);
        vij=new SimpleStringProperty(vij2);
        nlj =new SimpleStringProperty(strNlj);
        
        //System.out.println(i.get()+"***"+j.get()+"***"+diffLjLi.get()+"***"+vij.get()+"***"+nlj.get());
            
    }

    public String getI() {
        return i.get();
    }

    public String getJ() {
        return j.get();
    }

    public String getLi() {
        return li.get();
    }

    public String getLj() {
        return lj.get();
    }

    public String getVij() {
        return vij.get();
    }

    public String getNlj() {
        return nlj.get();
    }

    public String getDiffLjLi() {
        return diffLjLi.get();
    }

    public void setI(String i) {
        this.i.set(i);
    }

    public void setJ(String j) {
        this.j.set(j);
    }

    public void setLi(String li) {
        this.li.set(li);
    }

    public void setLj(String lj) {
        this.lj.set(lj);
    }

    public void setVij(String vij) {
        this.vij.set(vij);
    }

    public void setNlj(String nlj) {
        this.nlj.set(nlj);
    }

    public void setDiffLjLi(String diffLjLi) {
        this.diffLjLi.set(diffLjLi);
    }

    public SimpleStringProperty iProperty(){return i;};
    public SimpleStringProperty jProperty(){return j;};
    public SimpleStringProperty liProperty(){return li;};
    public SimpleStringProperty ljProperty(){return lj;};
    public SimpleStringProperty vijProperty(){return vij;};
    public SimpleStringProperty nljProperty(){return nlj;};
    public SimpleStringProperty diffLjLiProperty(){return diffLjLi;};
    
    
}
