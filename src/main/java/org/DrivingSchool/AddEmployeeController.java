package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import java.sql.*;


public class AddEmployeeController {

    public TextField nameEmployeeField;
    public TextField idEmployeeField;
    public TextField addressEmployeeField;
    public TextField phoneEmployeeField;
    public TextField emailEmployeeField;
    public PasswordField passwordEmployeeField;
    public PasswordField confirmEmployeeField;
    public DatePicker birthEmployeeDate;
    public DatePicker startEmployeeDate;
    public RadioButton maleRadioEmployeeButton;
    public RadioButton femaleRadioEmployeeButton;
    public Alert alert;

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

    public void add(ActionEvent event) throws IOException {
        //TODO add employee and create new account
        //TODO check if all field is not empty
        String name = nameEmployeeField.getText();
        try {

            int id = Integer.parseInt(idEmployeeField.getText());

            String address = addressEmployeeField.getText();
            LocalDate birthDate = birthEmployeeDate.getValue();
            String gender = "";
            if (maleRadioEmployeeButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioEmployeeButton.isSelected()) {
                gender = "Female";
            }
            try {

                int phone = Integer.parseInt(phoneEmployeeField.getText());
                try {

                    LocalDate startDate = startEmployeeDate.getValue();

                    String email = emailEmployeeField.getText().toLowerCase();
                    String password = passwordEmployeeField.getText();
                    String confirmPassword = confirmEmployeeField.getText();
                    if (!name.isEmpty() && !address.isEmpty() && !gender.isEmpty() && !email.isEmpty()
                            && !password.isEmpty() && !confirmPassword.isEmpty() && birthDate != null && startDate != null) {

                        //TODO check if the employee exist or not
                        //TODO check if the email not unique
                        //TODO add employee to the database

                        String idString = String.valueOf(id);
                        String phoneString = "0" + String.valueOf(phone);


                        OracleDataSource oracleDataSource = new OracleDataSource();
                        String url = "jdbc:oracle:thin:@localhost:1521/orcl";
                        oracleDataSource.setURL(url);
                        oracleDataSource.setUser("school");
                        oracleDataSource.setPassword("123456");
                        Connection connection = oracleDataSource.getConnection();

                        ///////
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Name from EMPLOYEE" +
                                " where SSN=?");
                        preparedStatement.setString(1, idString);

                        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT SSN,NAME,EMAIL from EMPLOYEE" +
                                " where EMAIL=?");
                        preparedStatement1.setString(1, email);

                        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT SID,STUNAME,EMAIL from STUDENT" +
                                " where EMAIL=?");
                        preparedStatement2.setString(1, email);

                        ResultSet resultSet = preparedStatement.executeQuery();
                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                        ResultSet resultSet2 = preparedStatement2.executeQuery();

                        boolean checkId = false;
                        boolean checkEmail = false;
                        boolean checkEmail2 = false;
                        if (resultSet.next()) {
                            checkId = true;
                        }
                        if (resultSet1.next()) {
                            checkEmail = true;
                        }
                        if (resultSet2.next()) {
                            checkEmail2 = true;
                        }
                        if (checkId || checkEmail || checkEmail2) {
                            if (checkId) {
                                System.out.println(resultSet.getString("Name"));
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setHeaderText(null);
                                alert.setContentText("This employee exist !\n SSN="
                                        + idString + "\n Name is " + resultSet.getString("Name")
                                        + "\n\nPlease try again");
                                alert.showAndWait();
                                idEmployeeField.clear();
                                idEmployeeField.requestFocus();
                            } else if (checkEmail) {
                                System.out.println(resultSet1.getString("Name"));
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setHeaderText(null);
                                alert.setContentText("This email has been existed in table employee!"
                                        + "\n SSN=" + resultSet1.getString("SSN")
                                        + "\n Name is " + resultSet1.getString("NAME")
                                        + "\n Email is" + resultSet1.getString("Email")
                                        + "\n\nPlease try again");
                                alert.showAndWait();
                                emailEmployeeField.clear();
                                emailEmployeeField.requestFocus();
                            } else if (checkEmail2) {
                                System.out.println(resultSet2.getString("Stuname"));
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setHeaderText(null);
                                alert.setContentText("This email has been existed in table student!"
                                        + "\n SSN=" + resultSet2.getString("SID")
                                        + "\n Name is " + resultSet2.getString("STUNAME")
                                        + "\n Email is" + resultSet2.getString("Email")
                                        + "\n\nPlease try again");
                                alert.showAndWait();
                                emailEmployeeField.clear();
                                emailEmployeeField.requestFocus();
                            }


                        } else {
                            if (password.equals(confirmPassword)) {
                                PreparedStatement statement =
                                        connection.prepareStatement("insert into EMPLOYEE values (?,?,?,?,?,?,?,?,?)");
                                statement.setString(1, idString);
                                statement.setString(2, name);
                                statement.setString(3, address);
                                statement.setDate(4, java.sql.Date.valueOf(birthDate));
                                statement.setString(5, gender);
                                statement.setString(6, phoneString);
                                statement.setDate(7, java.sql.Date.valueOf(startDate));
                                statement.setString(8, email);
                                statement.setString(9, password);


                                int row = statement.executeUpdate();
                                if (row > 0) {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("");
                                    alert.setHeaderText(null);
                                    alert.setContentText("The employee has been added !");
                                    alert.showAndWait();

                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    Stage root = FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
                                    stage.setScene(root.getScene());
                                    root.setResizable(true);

                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("Check the password !");
                                alert.showAndWait();
                                phoneEmployeeField.clear();
                                confirmEmployeeField.clear();
                                passwordEmployeeField.requestFocus();
                            }

                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("All fields must be filled in !");
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                    ex.printStackTrace();
                }

            } catch (NumberFormatException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input !");
                alert.showAndWait();
                phoneEmployeeField.clear();
                phoneEmployeeField.requestFocus();
            }

        } catch (NumberFormatException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input !");
            alert.showAndWait();
            idEmployeeField.clear();
            idEmployeeField.requestFocus();
        }

    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
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
