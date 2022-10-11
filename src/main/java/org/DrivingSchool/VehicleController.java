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

public class VehicleController {


    public TableView<Vehicle> vehicleTableView;
    public TableColumn<Vehicle, String> vehicleNoColumn;
    public TableColumn<Vehicle, String> vehicleNameColumn;
    public TableColumn<Vehicle, String> vehicleEngineColumn;
    public TableColumn<Vehicle, String> vehicleModelColumn;
    public TableColumn<Vehicle, String> vehicleColorColumn;
    public TableColumn<Vehicle, String> vehicleGearColumn;
    public TableColumn<Vehicle, String> vehicleFuelColumn;
    public Alert alert;

    String name;
    String no;
    String model;
    String fuel;
    String gear;
    String color;
    String engine;


    public void initialize() {
        vehicleNoColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("no"));
        vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("name"));
        vehicleEngineColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("engine"));
        vehicleModelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        vehicleColorColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("color"));
        vehicleGearColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("gear"));
        vehicleFuelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("fuel"));

        vehicleTableView.setItems(getVehicle());

    }

    public ObservableList<Vehicle> getVehicle() {

        ObservableList<Vehicle> vehicleItem = FXCollections.observableArrayList();
        try {

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT VNO,VNAME,MODEL," +
                    "ENGINEPOWER,COLOR,TYPEOFGEAR,FUELTYPE from VEHICLE");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                no = resultSet.getString("VNO");
                name = resultSet.getString("VNAME");
                model=resultSet.getString("MODEL");
                fuel=resultSet.getString("FUELTYPE");
                gear = resultSet.getString("TYPEOFGEAR");
                color = resultSet.getString("COLOR");
                engine = resultSet.getString("ENGINEPOWER");

                Vehicle vehicle = new Vehicle();
                vehicle.setNo(no);
                vehicle.setName(name);
                vehicle.setModel(model);
                vehicle.setColor(color);
                vehicle.setEngine(engine);
                vehicle.setFuel(fuel);
                vehicle.setGear(gear);
                vehicleItem.add(vehicle);


            }

        } catch (SQLException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }


        return vehicleItem;
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
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/addVehicle.fxml"));
        stage.setScene(root.getScene());

    }
    public void delete(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/deleteVehicle.fxml"));
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
