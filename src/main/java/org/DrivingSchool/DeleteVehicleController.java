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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteVehicleController {

    public TextField vehicleIDField;
    public Alert alert;

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
    public void delete(ActionEvent event)throws IOException{

        try{
            int vehicleID=Integer.parseInt(vehicleIDField.getText());

            //TODO check if the vehicle exist    (done)
            //TODO delete vehicle from database  (done)

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            connection.setAutoCommit(false);
            String idString=String.valueOf(vehicleID);
            PreparedStatement statement5=
                    connection.prepareStatement("SELECT VNO FROM VEHICLE " +
                            "WHERE VNO =?");
            statement5.setString(1,idString);
            int row5=statement5.executeUpdate();
            if(row5>0){
                PreparedStatement statement=
                        connection.prepareStatement("SELECT MEETING_NUMBER FROM MEETING " +
                                "WHERE M_VNO =?");
                statement.setString(1,idString);
                int row=statement.executeUpdate();

                PreparedStatement statement1=
                        connection.prepareStatement("SELECT MEETING_NUMBER FROM MEETING " +
                                "WHERE M_VNO =?");
                statement1.setString(1,idString);
                ResultSet resultSet1=statement1.executeQuery();

                if(row>0){
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("The vehicle will be used in "+row+" meetings\ndo you we want to replace it?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get()==ButtonType.OK){
                        //ok
                        List<String> choices = new ArrayList<>();
                        PreparedStatement statement2=
                                connection.prepareStatement("SELECT VNO FROM VEHICLE " +
                                        "WHERE VNO !=?");
                        statement2.setString(1,idString);
                        ResultSet resultSet2 = statement2.executeQuery();

                        while(resultSet2.next()){
                            choices.add(resultSet2.getString("VNO"));
                        }
                        if(choices.isEmpty()){
                            alert=new Alert(Alert.AlertType.WARNING);                        alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("This vehicle is last vehicle you can not delete it.");
                            alert.showAndWait();

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Stage root = FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
                            stage.setScene(root.getScene());

                        }else{
                            for(int i=0;i<row;i++){
                                resultSet1.next();
                                ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0),choices);
                                dialog.setTitle("Replace vehicle");
                                dialog.setHeaderText("Meeting number "+resultSet1.getString("MEETING_NUMBER"));
                                dialog.setContentText("Choose Vehicle number :");

                                Optional<String> result1 = dialog.showAndWait();
                                if (result1.isPresent()){
                                    String selectVNO=dialog.getSelectedItem();
                                    PreparedStatement statement3=connection.prepareStatement( "update MEETING set M_VNO=?" +
                                            " where MEETING_NUMBER=?");
                                    statement3.setString(1,selectVNO);
                                    statement3.setString(2,resultSet1.getString("MEETING_NUMBER"));
                                    statement3.executeUpdate();
                                    System.out.println(selectVNO);


                                }
//                                else{
//                                    alert=new Alert(Alert.AlertType.INFORMATION);
//                                    alert.setTitle("");
//                                    alert.setHeaderText(null);
//                                    alert.setContentText("The vehicle was not deleted.");
//                                    alert.showAndWait();
//                                    return;
//                                }

                            }
                            PreparedStatement statement4=
                                    connection.prepareStatement("delete FROM VEHICLE " +
                                            "WHERE VNO =?");
                            statement4.setString(1,idString);
                            int row4=statement4.executeUpdate();
                            connection.commit();

                            alert=new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("The vehicle has been deleted ");
                            alert.showAndWait();

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Stage root = FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
                            stage.setScene(root.getScene());

                        }

                    }

                }else{
                    //delete
                    PreparedStatement statement4=
                            connection.prepareStatement("delete FROM VEHICLE " +
                                    "WHERE VNO =?");
                    statement4.setString(1,idString);
                    int row4=statement4.executeUpdate();

                    connection.commit();
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("The vehicle has been deleted ");
                    alert.showAndWait();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Stage root = FXMLLoader.load(getClass().getResource("/FXML/vehicle.fxml"));
                    stage.setScene(root.getScene());
                }
            }else{
                //does not exist

                alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("This vehicle it not exist");
                alert.showAndWait();
                vehicleIDField.clear();
            }


        }catch (NumberFormatException ex){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input !");
            alert.showAndWait();
            vehicleIDField.clear();
        }catch (SQLException ex){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
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
