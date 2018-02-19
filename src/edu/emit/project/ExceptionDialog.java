/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emit.project;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author heniroger
 */
public class ExceptionDialog {
    private ExceptionDialog(){}
    public ExceptionDialog(Exception ex ,String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ex.getClass().getSimpleName());
        alert.setHeaderText("Veuillez resoudre l'erreur suivant");
        alert.setContentText(message);
        alert.setWidth(400);
        alert.setHeight(200);
        
        StringWriter stringWriter =new StringWriter();
        PrintWriter printWriter =new PrintWriter(stringWriter);
        
        ex.printStackTrace(printWriter);
        
        String exceptionText = stringWriter.toString();
        Label label =new Label("L'exception levée est :");
        
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
    
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    
    
    
    }
    
}
