package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.text.BreakIterator;

public class FinishExam1Controller




{
    int count=0;


    public Label grade;
    public void initialize(){
        grade.setText(count+"");

    }

    public void Again(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }

    public void Exit(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }







}
