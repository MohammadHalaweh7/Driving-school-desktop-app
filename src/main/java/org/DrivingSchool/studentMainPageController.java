package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ResourceBundle;


import javafx.fxml.Initializable;


public class studentMainPageController
{

    //****************************************************************************************************
    @FXML
    public void DrivingCourse(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/DrivingCourse.fxml"));
        stage.setScene(root.getScene());
    }


    @FXML
    public void RoadSafety(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/RoadSafety.fxml"));
        stage.setScene(root.getScene());
    }
    @FXML
    public void SignAndSignal(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/SignAndSignal.fxml"));
        stage.setScene(root.getScene());
    }

    @FXML
    public void Exams(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/ExamForms.fxml"));
        stage.setScene(root.getScene());
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }
//****************************************************************************************************

   /*

@FXML
    public void GoFacebook(MouseEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/mohamad.halaweh/"));

    }

    @FXML
    public void GoInstegram(MouseEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://www.instagram.com/"));

    }

    @FXML
    public void GoYoutupe(MouseEvent event) throws IOException {
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UC3gf4o1mZEBIuLFCLPDGRYQ?view_as=subscriber"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void GoTwitter(MouseEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://twitter.com/"));

    }

    @FXML
    public void GoGmail(MouseEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("https://mail.google.com/mail/u/0/#inbox"));

    }

*/





    public void GoInstegram(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException, IOException
    {
        Desktop.getDesktop().browse(new URI("https://www.instagram.com/"));
    }


    public void GoTwitter(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException, IOException
    {
        Desktop.getDesktop().browse(new URI("https://twitter.com/"));
    }


    public void GoGmail(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException, IOException
    {
        Desktop.getDesktop().browse(new URI("mailto:drivingschool1812@gmail.com"));
    }

    public void GoFacebook(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException, IOException
    {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/mohamad.halaweh/"));
    }

    public void GoYoutupe(javafx.scene.input.MouseEvent mouseEvent) throws URISyntaxException, IOException
    {
        Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UC3gf4o1mZEBIuLFCLPDGRYQ?view_as=subscriber"));
    }


}
