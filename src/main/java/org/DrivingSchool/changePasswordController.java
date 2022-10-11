package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class changePasswordController {

    public PasswordField oldPassword;
    public PasswordField newPassword;
    public PasswordField confirmPassword;
    public Alert alert;

    public static String myId;

    public static String getMyId() {
        return myId;
    }

    public static void setMyId(String myId) {
        changePasswordController.myId = myId;
    }
    public void employee(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
        stage.setScene(root.getScene());
    }

    public void student(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/student.fxml"));
        stage.setScene(root.getScene());
    }

    public void vehicle(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
        stage.setScene(root.getScene());
    }

    public void meeting(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
        stage.setScene(root.getScene());
    }
    public void change(ActionEvent event) throws IOException {

        String oldPass=oldPassword.getText();
        String newPass=newPassword.getText();
        String confirm=confirmPassword.getText();
        boolean checkOld=false;
        boolean checkNew=false;
        if(!oldPass.isEmpty()&&!newPass.isEmpty()&&!confirm.isEmpty()){
            OracleDataSource oracleDataSource = null;
            try {
                oracleDataSource = new OracleDataSource();
                String url = "jdbc:oracle:thin:@localhost:1521/orcl";
                oracleDataSource.setURL(url);
                oracleDataSource.setUser("school");
                oracleDataSource.setPassword("123456");
                Connection connection = oracleDataSource.getConnection();



                PreparedStatement statement2=
                        connection.prepareStatement("SELECT * FROM EMPLOYEE " +
                                "WHERE SSN =? and PASSWORD=?");
                statement2.setString(1,myId);
                statement2.setString(2,oldPass);
                ResultSet resultSet=statement2.executeQuery();
                if(resultSet.next()){
                    checkOld=true;
                }

                if(newPass.equals(confirm)){
                    checkNew=true;
                }
                if(checkOld&&checkNew) {
                    PreparedStatement statement = connection.prepareStatement("update EMPLOYEE set PASSWORD=?" +
                            " where SSN=?");
                    statement.setString(1, newPass);
                    statement.setString(2, myId);
                    statement.executeUpdate();

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("password changed");
                    alert.showAndWait();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Stage root = FXMLLoader.load(getClass().getResource("/FXML/admin.fxml"));
                    stage.setScene(root.getScene());

                }else if(!checkOld){
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong password ");
                    alert.showAndWait();

                    oldPassword.clear();
                    oldPassword.requestFocus();
                    newPassword.clear();
                    confirmPassword.clear();

                }else if(!checkNew){
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("try again");
                    alert.showAndWait();
                    newPassword.clear();
                    newPassword.requestFocus();
                    confirmPassword.clear();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled in ");
            alert.showAndWait();
        }




    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/admin.fxml"));
        stage.setScene(root.getScene());
    }
    public void home(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/admin.fxml"));
        stage.setScene(root.getScene());

    }


    public void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());

    }

}
