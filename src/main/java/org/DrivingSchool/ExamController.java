package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class ExamController {


    public Label grade;

    public RadioButton solution1;
    public RadioButton solution2;
    public RadioButton solution3;
    public RadioButton solution4;

    public RadioButton solution5;
    public RadioButton solution6;
    public RadioButton solution7;
    public RadioButton solution8;

    public RadioButton solution9;
    public RadioButton solution10;
    public RadioButton solution11;
    public RadioButton solution12;

    public RadioButton solution13;
    public RadioButton solution14;
    public RadioButton solution15;
    public RadioButton solution16;

    public RadioButton solution17;
    public RadioButton solution18;
    public RadioButton solution19;
    public RadioButton solution20;

    public RadioButton solution21;
    public RadioButton solution22;
    public RadioButton solution23;
    public RadioButton solution24;

    int count = 0;


    public void Next(ActionEvent event) throws IOException
    {

        if (solution1.isSelected())
        {
            count++;
        }

        if (solution5.isSelected())
        {
            count++;
        }
        if (solution9.isSelected())
        {
            count++;
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/Exam2.fxml"));
        Stage parent = root.load();
        stage.setScene(parent.getScene());
        ExamController examController = root.getController();
        examController.count = count;
    }


    //************************************************************************************************************
    //************************************************************************************************************

    public void HomeExam(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
        stage.setScene(root.getScene());
    }


    public void DrivingCourseExam(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }

    public void SignsAndSignalsExam(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }

    public void RoadSafetyExam(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }

    public void ExamsExam(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }


    public void LogOutExam(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }


    //************************************************************************************************************
    //************************************************************************************************************


    //----------------------------------------------------------------------------------------------
    public void Again(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }

    public void Exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }
//----------------------------------------------------------------------------------------------


    //    @FXML
    public void Previous(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/Exam.fxml"));
        stage.setScene(root.getScene());
    }

    //    @FXML
    public void FinishExam(ActionEvent event) throws IOException {


        if (solution13.isSelected())
            count++;

        if (solution17.isSelected())
            count++;

        if (solution21.isSelected())
            count++;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Your mark is : "+count + "/6");
        alert.showAndWait();



        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());



    }
    //lable.setText(" ");
}
