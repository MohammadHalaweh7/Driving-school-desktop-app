package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class addMeetingController {

    public TextField meetingIdField;
    public TextField employeeIdField;
    public TextField studentIdField;
    public TextField vehicleIdField;
    public DatePicker meetingDate;
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

        try {
            int meetingId = Integer.parseInt(meetingIdField.getText());


            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());


                try {
                    int studentId = Integer.parseInt(studentIdField.getText());


                    try {

                        int vehicleNo = Integer.parseInt(vehicleIdField.getText());
                        LocalDate meetingdate = meetingDate.getValue();

                        //TODO check if the meeting exist or not
                        //TODO add meeting to the database

                        OracleDataSource oracleDataSource = new OracleDataSource();
                        String url = "jdbc:oracle:thin:@localhost:1521/orcl";
                        oracleDataSource.setURL(url);
                        oracleDataSource.setUser("school");
                        oracleDataSource.setPassword("123456");
                        Connection connection = oracleDataSource.getConnection();

                        ///////

                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from MEETING" +
                                " where MEETING_NUMBER=?");
                        preparedStatement.setString(1, String.valueOf(meetingId));

                        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT NAME from EMPLOYEE" +
                                " where SSN=?");
                        preparedStatement1.setString(1, String.valueOf(employeeId));

                        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT STUNAME from STUDENT" +
                                " where SID=?");

                        preparedStatement2.setString(1, String.valueOf(studentId));
                        PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * from VEHICLE" +
                                " where VNO=?");
                        preparedStatement3.setString(1, String.valueOf(vehicleNo));

                        ResultSet resultSet = preparedStatement.executeQuery();
                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                        ResultSet resultSet2 = preparedStatement2.executeQuery();
                        ResultSet resultSet3 = preparedStatement3.executeQuery();

                        boolean checkMeetingId = false;
                        boolean checkEmployeeId = false;
                        boolean checkStudentId = false;
                        boolean checkVehicletId = false;

                        if (!resultSet.next()) {
                            checkMeetingId = true;
                        }
                        if (resultSet1.next()) {
                            checkEmployeeId = true;
                        }
                        if (resultSet2.next()) {
                            checkStudentId = true;
                        }
                        if (resultSet3.next()) {
                            checkVehicletId = true;
                        }

                        if (checkMeetingId && checkEmployeeId && checkStudentId && checkVehicletId&& meetingdate!=null) {
                            PreparedStatement statement =
                                    connection.prepareStatement("insert into MEETING values (?,?,?,?,?)");
                            statement.setString(1, String.valueOf(meetingId));
                            statement.setString(2, String.valueOf(employeeId));
                            statement.setString(3, String.valueOf(studentId));
                            statement.setString(4, String.valueOf(vehicleNo));
                            statement.setDate(5, java.sql.Date.valueOf(meetingdate));

                            int row = statement.executeUpdate();
                            if (row > 0) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("The meeting has been added !");
                                alert.showAndWait();

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Stage root = FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
                                stage.setScene(root.getScene());
                                root.setResizable(true);

                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            if (!checkMeetingId) {
                                alert.setContentText("meeting id is already exist !");
                                meetingIdField.requestFocus();
                            }else if (!checkEmployeeId) {
                                alert.setContentText("this employee does not exist !");
                                employeeIdField.requestFocus();
                            } else if (!checkStudentId) {
                                alert.setContentText("this student does not exist !");
                                studentIdField.requestFocus();
                            } else if (!checkVehicletId) {
                                alert.setContentText("this vehicle does not exist !");
                                vehicleIdField.requestFocus();
                            }else  if(meetingdate==null){
                                alert.setContentText("add a date for the meeting !");
                                meetingDate.requestFocus();
                            }

                            alert.showAndWait();

                        }

                    } catch (SQLException ex) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                        ex.printStackTrace();
                    } catch (NumberFormatException e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid input !");
                        alert.showAndWait();
                        vehicleIdField.clear();
                        vehicleIdField.requestFocus();
                    }

                } catch (NumberFormatException e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input !");
                    alert.showAndWait();
                    studentIdField.clear();
                    studentIdField.requestFocus();
                }

            } catch (NumberFormatException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input !");
                alert.showAndWait();
                employeeIdField.clear();
                employeeIdField.requestFocus();
            }

        } catch ( NumberFormatException e) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input !");
            alert.showAndWait();
            meetingIdField.clear();
            meetingIdField.requestFocus();
        }


    }

    public void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
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
