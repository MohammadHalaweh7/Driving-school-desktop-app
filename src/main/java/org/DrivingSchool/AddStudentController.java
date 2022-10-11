package org.DrivingSchool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import java.sql.*;


import oracle.jdbc.pool.*;

public class AddStudentController {


    public TextField nameStudentField;
    public TextField idStudentField;
    public TextField phoneStudentField;
    public DatePicker birthStudentDate;
    public RadioButton maleRadioStudentButton;
    public RadioButton femaleRadioStudentButton;
    public ToggleGroup Gender;
    public ComboBox<String> licenseStudentField;
    public TextField emailStudentField;
    public PasswordField passwordStudentField;
    public PasswordField confirmStudentField;
    public Alert alert;


    Student student;

    ObservableList<String> licenseList = FXCollections.observableArrayList("Personal", "Taxi", "Bus", "Motorcycle", "Commercial");

    @FXML
    public void initialize() {
        licenseStudentField.getItems().setAll(licenseList);
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
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
        stage.setScene(root.getScene());
    }

    public void add(ActionEvent event) throws IOException {
        //TODO add new student to database (create account)

        String name = nameStudentField.getText();
        int id;
        int phone;
        String license;
        LocalDate birthDate;
        String gender = "";
        String email;
        String password;
        String confirmPassword;
        try {
            id = Integer.parseInt(idStudentField.getText());

            try {
                phone = Integer.parseInt(phoneStudentField.getText());
                birthDate = birthStudentDate.getValue();
                //type of license

                license = licenseStudentField.getValue();

                if (maleRadioStudentButton.isSelected()) {
                    gender = "Male";
                } else if (femaleRadioStudentButton.isSelected()) {
                    gender = "Female";
                }
                email= emailStudentField.getText();
                email=email.toLowerCase();
                password = passwordStudentField.getText();
                confirmPassword = confirmStudentField.getText();


                if (!name.isEmpty() && !gender.isEmpty() && !email.isEmpty() && license != null
                        && !password.isEmpty() && !confirmPassword.isEmpty() && birthDate != null) {


                    //TODO check if the student exist or not
                    //TODO check if the email not unique
                    //TODO add student to the database
                    OracleDataSource oracleDataSource = new OracleDataSource();
                    String url = "jdbc:oracle:thin:@localhost:1521/orcl";
                    oracleDataSource.setURL(url);
                    oracleDataSource.setUser("school");
                    oracleDataSource.setPassword("123456");
                    Connection connection = oracleDataSource.getConnection();

//                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from STUDENT" +
//                            " where SID=?");
//                    preparedStatement.setString(1,"555555555");
//
//                    ResultSet resultSet = preparedStatement.executeQuery();
//                    while(resultSet.next()){
//                        System.out.println(resultSet.getString("STUNAME"));
//                    }
//////////////////////////////
                    String idString = String.valueOf(id);
                    String phoneString = "0"+String.valueOf(phone);

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT STUNAME from STUDENT" +
                            " where SID=?");
                    preparedStatement.setString(1, idString);

                    PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT SID,STUNAME,EMAIL from STUDENT" +
                            " where EMAIL=?");
                    preparedStatement1.setString(1, email);
                    PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT SSN,NAME,EMAIL from EMPLOYEE" +
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
                            //System.out.println(resultSet.getString("STUNAME"));
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("This Student exist !\n SID="
                                    + idString + "\n Name is " + resultSet.getString("STUNAME")
                                    + "\n\nPlease try again");
                            alert.showAndWait();
                            idStudentField.clear();
                            idStudentField.requestFocus();
                        } else if (checkEmail) {
                            //System.out.println(resultSet1.getString("STUNAME"));
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("This email has been existed !"
                                    + "\n SID=" + resultSet1.getString("SID")
                                    + "\n Name is " + resultSet1.getString("STUNAME")
                                    + "\n Email is" + resultSet1.getString("Email")
                                    + "\n\nPlease try again");
                            alert.showAndWait();
                            emailStudentField.clear();
                            emailStudentField.requestFocus();
                        } else if (checkEmail2) {
                            System.out.println(resultSet2.getString("NAME"));
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("This email has been existed in table employee!"
                                    + "\n SSN=" + resultSet2.getString("SSN")
                                    + "\n Name is " + resultSet2.getString("NAME")
                                    + "\n Email is" + resultSet2.getString("Email")
                                    + "\n\nPlease try again");
                            alert.showAndWait();
                            emailStudentField.clear();
                            emailStudentField.requestFocus();
                        }

                    } else {

                        if (password.equals(confirmPassword)) {
                            PreparedStatement statement =
                                    connection.prepareStatement("insert into STUDENT values (?,?,?,?,?,?,?,?)");
                            statement.setString(1, idString);
                            statement.setString(2, name);
                            statement.setString(3, phoneString);
                            statement.setDate(4, java.sql.Date.valueOf(birthDate));
                            statement.setString(5, license);
                            statement.setString(6, gender);
                            statement.setString(7, email);
                            statement.setString(8, password);

                            int row = statement.executeUpdate();
                            if (row > 0) {

                                LocalDate localDate=LocalDate.now();
                                int age=localDate.getYear()-birthDate.getYear();
                                System.out.println("age="+age);
                                student=new Student();
                                student.setId(idString);
                                student.setName(name);
                                student.setAge(age);
                                student.setPhone(phoneString);
                                student.setGender(gender);
                                student.setTypeOfLicense(license);
                                student.setEmail(email);



                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("The student has been added !");
                                alert.showAndWait();

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Stage root = FXMLLoader.load(getClass().getResource("/FXML/student.fxml"));
                                stage.setScene(root.getScene());
                            }

                        }else{
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Check the password !");
                            alert.showAndWait();
                            passwordStudentField.clear();
                            confirmStudentField.clear();
                            passwordStudentField.requestFocus();
                        }
                    }





                    /*

                    DELETE FROM STUDENT s
                        WHERE SID =111111111;
                     */


                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("All fields must be filled in !");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input !\nphone number");
                alert.showAndWait();
                phoneStudentField.clear();
                phoneStudentField.requestFocus();
            } catch (SQLException ex) {
                // from OracleDataSource
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
            alert.setContentText("Invalid input !\nID");
            alert.showAndWait();
            idStudentField.clear();
            idStudentField.requestFocus();
        }

    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/student.fxml"));
        stage.setScene(root.getScene());
    }

    public void home(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/admin.fxml"));
        stage.setScene(root.getScene());
    }

    public void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());
    }

}
