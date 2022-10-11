package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class RoadSafety2Controller {


    public void Next(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety3.fxml"));
        stage.setScene(root.getScene());
    }


    public void Previous(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }


    //************************************************************************************************************
    public void HomeRoadSafety2(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
        stage.setScene(root.getScene());
    }


    public void DrivingCourseRoadSafety2(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }


    public void SignAndSignalRoadSafety2(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }


    public void RoadSafetyRoadSafety2(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }

    public void ExamsRoadSafety2(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }


    public void LogOutRoadSafety2(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }





//***************************************************************************************************************







}
