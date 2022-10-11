package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamFormsController


{


    public void TheFirstModel(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Exam.fxml"));
        stage.setScene(root.getScene());
    }


    public void TheSecondModel(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Exam3.fxml"));
        stage.setScene(root.getScene());

    }

    public void TheThirdModel(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Exam5.fxml"));
        stage.setScene(root.getScene());
    }

    //************************************************************************************************************
    //************************************************************************************************************

    public void HomeExamForms(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
        stage.setScene(root.getScene());
    }



    public void DrivingCourseExamForms(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }

    public void SignsAndSignalsExamForms(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }

    public void RoadSafetyExamForms(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }

    public void ExamsExamForms(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }



    public void LogOutExamForms(ActionEvent event)throws IOException
    {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }








    //************************************************************************************************************
    //************************************************************************************************************










}
