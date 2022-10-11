package org.DrivingSchool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import java.sql.*;
import oracle.jdbc.pool.*;

public class AddVehicleController {

    public TextField nameVehicleField;
    public  TextField idVehicleField;
    public ComboBox<String> categoryVehicleBox;
    public TextField modelVehicleField;
    public TextField enginePowerField;
    public TextField colorField;
    public ComboBox<String> fuelTypeBox;//not define
    public RadioButton manualRadioButton;
    public RadioButton automaticRadioButton;
    public ToggleGroup Gear;
    public TextField yearVehicleField;
    public DatePicker expiryDateOfInsurance;
    public Alert alert;

    ObservableList<String> categoryList= FXCollections.observableArrayList("Car", "Taxi", "Bus", "Motorcycle", "Truck");
    ObservableList<String> fuelList= FXCollections.observableArrayList("Benzene","Diesel");

    @FXML
    public void initialize(){
        categoryVehicleBox.getItems().setAll(categoryList);
        fuelTypeBox.getItems().addAll(fuelList);

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
    public void add(ActionEvent event)throws IOException{
        //TODO add new vehicle to database
        //TODO check if all field is not empty

        String name=nameVehicleField.getText();
        try{

            int id=Integer.parseInt(idVehicleField.getText());

            String category= categoryVehicleBox.getValue();
            String fuel= fuelTypeBox.getValue();
            LocalDate expiryDate=expiryDateOfInsurance.getValue();
            String gear="";
            if(manualRadioButton.isSelected()){
                gear="Manual";
                //change it from database -- done
            }else if(automaticRadioButton.isSelected()){
                gear="Automatic";
            }
            try {
                int engine=Integer.parseInt(enginePowerField.getText());

                try{
                    int year=Integer.parseInt(yearVehicleField.getText());

                    String model=modelVehicleField.getText();
                    String color=colorField.getText();
                    if(!name.isEmpty()&&category!=null&& fuel!=null && !color.isEmpty()
                            && expiryDate!=null &&!gear.isEmpty()&&!model.isEmpty() ){





                        //TODO check if the vehicle exist or not
                        //TODO add the vehicle to database

                        OracleDataSource oracleDataSource = new OracleDataSource();
                        String url = "jdbc:oracle:thin:@localhost:1521/orcl";
                        oracleDataSource.setURL(url);
                        oracleDataSource.setUser("school");
                        oracleDataSource.setPassword("123456");
                        Connection connection = oracleDataSource.getConnection();

                        String idString=String.valueOf(id);

                        String insuranceID;
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID from INSURANCE" +
                                " where ENDDATE=?");
                        preparedStatement.setDate(1, java.sql.Date.valueOf(expiryDate));
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next()){
                            insuranceID=resultSet.getString(1);



                            PreparedStatement statement=
                                    connection.prepareStatement("insert into VEHICLE values (?,?,?,?,?,?,?,?,?,?,?)");
                            statement.setString(1,idString);
                            statement.setString(2,name);
                            statement.setString(3,category);
                            statement.setString(4,model);
                            statement.setString(5,String.valueOf(engine));
                            statement.setString(6,fuel);
                            statement.setString(7,gear);
                            statement.setString(8,color);
                            statement.setInt(9, year);
                            statement.setDate(10,java.sql.Date.valueOf(expiryDate));
                            statement.setString(11,insuranceID);
                            int row=statement.executeUpdate();
                            if(row>0){
                                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("The vehicle has been added !");
                                alert.showAndWait();

                                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                Stage root =  FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
                                stage.setScene(root.getScene());

                            }
                        }else{
                            Alert alert=new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("this expiry date is not allowed \nPlease try again !");
                            alert.showAndWait();
                            expiryDateOfInsurance.setValue(null);
                        }


                    }else{
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("All fields must be filled in !");
                        alert.showAndWait();
                    }
                }catch (NumberFormatException e){
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input !\nYear Field");
                    alert.showAndWait();
                    yearVehicleField.clear();
                } catch (SQLException ex) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                    ex.printStackTrace();
                }

            }catch (NumberFormatException e){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input !\nEngine Power Field");
                alert.showAndWait();
                enginePowerField.clear();
            }

        }catch (NumberFormatException e){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input !\nID Field");
            alert.showAndWait();
            idVehicleField.clear();
        }


    }
    public void cancel(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
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
