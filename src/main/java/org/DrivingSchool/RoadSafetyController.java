package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class RoadSafetyController
{

    public void Next(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety2.fxml"));
        stage.setScene(root.getScene());
    }

    //************************************************************************************************************

    public void HomeRoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
        stage.setScene(root.getScene());
    }



    public void DrivingCourseRoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }


    public void SignAndSignalRoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }


    public void RoadSafetyRoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }


    public void ExamsRoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }




    public void LogOutRoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }






//***************************************************************************************************************









}
