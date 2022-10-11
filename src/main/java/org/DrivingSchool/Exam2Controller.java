package org.DrivingSchool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;




public class Exam2Controller
{
    private BreakIterator label;

    public JRadioButton RadioButton13Exam ;
    public JRadioButton RadioButton14Exam ;
    public JRadioButton RadioButton15Exam ;
    public JRadioButton RadioButton16Exam ;

    public JRadioButton RadioButton17Exam ;
    public JRadioButton RadioButton18Exam ;
    public JRadioButton RadioButton19Exam ;
    public JRadioButton RadioButton20Exam ;

    public JRadioButton RadioButton21Exam ;
    public JRadioButton RadioButton22Exam ;
    public JRadioButton RadioButton23Exam ;
    public JRadioButton RadioButton24Exam ;

    @FXML
    public void Previous(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Exam.fxml"));
        stage.setScene(root.getScene());
    }


    @FXML
    public void FinishExam(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/FinishExam1.fxml"));
        stage.setScene(root.getScene());



    }

//----------------------------------------------------------------------------------------------
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
//----------------------------------------------------------------------------------------------


    //************************************************************************************************************
    //************************************************************************************************************
    public void HomeExam2(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
        stage.setScene(root.getScene());
    }

    public void DrivingCourseExam2(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }

    public void SignsAndSignalsExam2(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }

    public void RoadSafetyExam2(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }

    public void ExamsExam2(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }

    public void LogOutExam2(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }
    //************************************************************************************************************
    //************************************************************************************************************




}




