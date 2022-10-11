package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class AdminController {

    public static String myID;
    public static String myName;
    public String getMyID() {
        return myID;
    }

    public void setMyID(String myID) {
        AdminController.myID = myID;
    }

    public Label nameLabel;
    public Label emailLabel;


    public void initialize() {
        try {
            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NAME,ADDRESS," +
                    "PHONE_NUMBER,EMAIL from EMPLOYEE WHERE SSN=?");

            preparedStatement.setString(1,myID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                nameLabel.setText(resultSet.getString("NAME"));
                String str=resultSet.getString("NAME");
                String string[]=str.split(" ");
                myName=string[0]+string[1];
                emailLabel.setText(resultSet.getString("EMAIL"));
            }


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void employee(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
        stage.setScene(root.getScene());
    }
    public void student(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/student.fxml"));
        stage.setScene(root.getScene());
    }
    public void vehicle(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
        stage.setScene(root.getScene());
    }
    public void meeting(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
        stage.setScene(root.getScene());
    }
    public void home(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/admin.fxml"));
        stage.setScene(root.getScene());


    }
    public void changePassword(MouseEvent event)throws IOException{

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/changePassword.fxml"));
        Stage parent = root.load();
        stage.setScene(parent.getScene());
        changePasswordController changePassword = root.getController();
        changePassword.setMyId(myID);

    }
    

    public void exit(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());

    }
}
