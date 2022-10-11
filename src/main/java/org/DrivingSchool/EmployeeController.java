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

public class EmployeeController {

    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> employeeSSNColumn;
    public TableColumn<Employee, String> employeeNameColumn;
    public TableColumn<Employee, String> employeePhoneColumn;
    public TableColumn<Employee, String> employeeAddressColumn;
    public TableColumn<Employee, String> employeeEmailColumn;
    public TableColumn<Employee, Integer> employeeAgeColumn;
    public Alert alert;

    String name;
    String ssn;
    String phone;
    String address;
    LocalDate birthDate;
    int age;
    String email;

    public void initialize() {
        employeeSSNColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("ssn"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        employeePhoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        employeeAddressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        employeeEmailColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        employeeAgeColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("age"));
        employeeTableView.setItems(getEmployee());

    }

    public ObservableList<Employee> getEmployee() {

        ObservableList<Employee> employeeItem = FXCollections.observableArrayList();
        try {

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SSN,NAME,ADDRESS," +
                    "PHONE_NUMBER,BIRTH_DATE,EMAIL from EMPLOYEE");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                ssn = resultSet.getString("SSN");
                name = resultSet.getString("NAME");
                Date date = resultSet.getDate("BIRTH_DATE");
                birthDate = date.toLocalDate();
                LocalDate localDate = LocalDate.now();
                age = localDate.getYear() - birthDate.getYear();
                phone = resultSet.getString("PHONE_NUMBER");
                address = resultSet.getString("ADDRESS");
                email = resultSet.getString("EMAIL");

                Employee employee = new Employee();
                employee.setSsn(ssn);
                employee.setName(name);
                employee.setAge(age);
                employee.setPhone(phone);
                employee.setAddress(address);
                employee.setEmail(email);
                employeeItem.add(employee);


            }

        } catch (SQLException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }


        return employeeItem;
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

    public void add(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/addEmployee.fxml"));
        stage.setScene(root.getScene());
    }

    public void delete(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("/FXML/deleteEmployee.fxml"));
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
