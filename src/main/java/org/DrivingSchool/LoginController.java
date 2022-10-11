package org.DrivingSchool;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {
    public Stage LoginStage;
    public TextField emailField;
    public PasswordField passwordField;
    public Button loginButton;
    public Button newAccountButton;
    Alert alert;

    public String id;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String myId) {
//        this.id = myId;
//    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        //TODO connect to the database to check and see if the user exist and what type of it
        //check if all field are not empty
        //boolean successLogin = false;
        String email = emailField.getText().toLowerCase();
        String password = passwordField.getText();

        String checkPassword;
        if (!email.isEmpty() && !password.isEmpty()) {

            try {
                OracleDataSource oracleDataSource = new OracleDataSource();
                String url = "jdbc:oracle:thin:@localhost:1521/orcl";
                oracleDataSource.setURL(url);
                oracleDataSource.setUser("school");
                oracleDataSource.setPassword("123456");
                Connection connection = oracleDataSource.getConnection();

                PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT SSN,PASSWORD from EMPLOYEE" +
                        " where EMAIL=?");
                preparedStatement1.setString(1, email);

                ResultSet resultSet1 = preparedStatement1.executeQuery();

                PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT SID,PASSWORD from STUDENT" +
                        " where EMAIL=?");
                preparedStatement2.setString(1, email);

                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet1.next()) {
                    id = resultSet1.getString("SSN");
                    checkPassword = resultSet1.getString("PASSWORD");

                    if (checkPassword.equals(password)) {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/admin.fxml"));
                        Stage parent = root.load();
                        stage.setScene(parent.getScene());
                        AdminController adminController = root.getController();
                        adminController.setMyID(id);
                        adminController.initialize();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("");
                        alert.setHeaderText(null);
                        alert.setContentText("The password is wrong\nPlease try again!");
                        alert.showAndWait();
                        passwordField.clear();
                        passwordField.requestFocus();
                    }
                } else if (resultSet2.next()) {
                    id = resultSet2.getString("SID");
                    checkPassword = resultSet2.getString("PASSWORD");

                    if (checkPassword.equals(password)) {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Stage root = FXMLLoader.load(getClass().getResource("/FXML/studentMainPage.fxml"));
                        stage.setScene(root.getScene());
//                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/studentMainPage.fxml"));
//                        Stage parent = root.load();
//                        stage.setScene(parent.getScene());
//                        studentMainPageController student = root.getController();
//                        student.setMyId(id);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("");
                        alert.setHeaderText(null);
                        alert.setContentText("The password is wrong\nPlease try again!");
                        alert.showAndWait();
                        passwordField.clear();
                        passwordField.requestFocus();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("This email does not exist !");
                    alert.showAndWait();
                    passwordField.clear();

                }
            } catch (SQLException ex) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled in !");
            alert.showAndWait();
        }
    }

    public void newAccount(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
        stage.setScene(root.getScene());
    }
    public void exit(ActionEvent event)throws IOException{
        System.exit(0);

    }
}
