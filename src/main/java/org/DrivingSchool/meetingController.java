package org.DrivingSchool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class meetingController {


    public TableView<Meeting> meetingTableView;
    public TableColumn<Meeting, String> meetingIdColumn;
    public TableColumn<Meeting, String> employeeIdColumn;
    public TableColumn<Meeting, String> studentIdColumn;
    public TableColumn<Meeting, String> vehicleIdColumn;
    public TableColumn<Meeting, LocalDate> meetingDateColumn;
    public Alert alert;

    String meetingId;
    String employee;
    String student;
    String vehicle;
    LocalDate localDate;

    public void initialize() {
        meetingIdColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("meeting"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("employee"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("student"));
        vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("vehicle"));
        meetingDateColumn.setCellValueFactory(new PropertyValueFactory<Meeting, LocalDate>("date"));

       meetingTableView.setItems(getMeeting());

    }

    public ObservableList<Meeting> getMeeting() {

        ObservableList<Meeting> meetingItem = FXCollections.observableArrayList();
        try {

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MEETING_NUMBER,M_SSN,M_SID," +
                    "M_VNO,MEETING_DATE from MEETING");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                System.out.println(resultSet.getString("NAME"));

                meetingId = resultSet.getString("MEETING_NUMBER");
                employee = resultSet.getString("M_SSN");
                student = resultSet.getString("M_SID");
                vehicle = resultSet.getString("M_VNO");
                Date date = resultSet.getDate("MEETING_DATE");
                localDate = date.toLocalDate();
                Meeting meeting = new Meeting();
                meeting.setMeeting(meetingId);
                meeting.setEmployee(employee);
                meeting.setStudent(student);
                meeting.setVehicle(vehicle);
                meeting.setDate(localDate);
                meetingItem.add(meeting);


            }

        } catch (SQLException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }

        return meetingItem;
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
    public void add(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/addMeeting.fxml"));
        stage.setScene(root.getScene());
    }
    public void delete(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/deleteMeeting.fxml"));
        stage.setScene(root.getScene());
    }
    public void cancel(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
        stage.setScene(root.getScene());
    }
    public void home(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/admin.fxml"));
        stage.setScene(root.getScene());

    }

    public void exit(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        stage.setScene(root.getScene());

    }
}
