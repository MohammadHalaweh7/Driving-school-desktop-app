package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

import java.io.IOException;
import java.net.URISyntaxException;

public class DrivingCourseController
{




    //************************************************************************************************************
    public void HomeDrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
        stage.setScene(root.getScene());
    }

    public void DrivingCourseDrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }


    public void SignAndSignalDrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }


    public void RoadSafetyDrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }

    public void ExamsDrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }


    public void LogOutDrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }






//***************************************************************************************************************
@FXML
    public void Go_to_the_education_book(ActionEvent event) throws IOException, URISyntaxException {

        Desktop.getDesktop().browse(new URI("https://edi-uae.com/public/uploads/downloads/20190724133013RTA_Handbook_-_Light_Motor_Vehicle_(LMV)_-_English.pdf?fbclid=IwAR1xG5GAnQ5McQYTBEeD7-c246EVf4cM07hHnvFh7HTtUq8Fgxmkm4nQrWc"));


    }




}
