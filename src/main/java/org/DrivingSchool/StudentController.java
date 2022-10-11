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

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import oracle.jdbc.pool.OracleDataSource;

public class StudentController {

    //TODO add student to table
    public TableView<Student> studentTableView;
    public TableColumn<Student,String> studentIdColumn;
    public TableColumn<Student,String> studentNameColumn;
    public TableColumn<Student,String> studentPhoneColumn;
    public TableColumn<Student,String> studentLicenseColumn;
    public TableColumn<Student,String> studentEmailColumn;
    public TableColumn<Student,Integer> studentAgeColumn;
    public Alert alert;

    String name;
    String id;
    String phone;
    String license;
    LocalDate birthDate;
    int age;
    String email;

    public void initialize(){
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Name"));
        studentPhoneColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Phone"));
        studentLicenseColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("typeOfLicense"));
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Email"));
        studentAgeColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("Age"));
        studentTableView.setItems(getStudent());

    }

    public ObservableList<Student> getStudent(){

        ObservableList<Student> studentItem= FXCollections.observableArrayList();
        try {

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SID,STUNAME,STUBIRTHDATE," +
                    "STUPHONENUMBER,TYPEOFLICENSE,EMAIL from STUDENT");

                    ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){

                        id=resultSet.getString("SID");
                        name=resultSet.getString("STUNAME");
                        Date date=resultSet.getDate("STUBIRTHDATE");
                        birthDate=date.toLocalDate();
                        LocalDate localDate=LocalDate.now();
                        age=localDate.getYear()-birthDate.getYear();
                        phone=resultSet.getString("STUPHONENUMBER");
                        license=resultSet.getString("TYPEOFLICENSE");
                        email=resultSet.getString("EMAIL");

                        Student student=new Student();
                        student.setId(id);
                        student.setName(name);
                        student.setAge(age);
                        student.setPhone(phone);
                        student.setTypeOfLicense(license);
                        student.setEmail(email);
                        studentItem.add(student);


                    }

        }catch (SQLException ex){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }


        return studentItem;
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
    public void add(MouseEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/addStudent.fxml"));
        stage.setScene(root.getScene());
    }
    public void delete(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/deleteStudent.fxml"));
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
