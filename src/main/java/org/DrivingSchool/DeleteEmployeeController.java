package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DeleteEmployeeController {

    public TextField employeeIDField;
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



        try {
            int employeeID = Integer.parseInt(employeeIDField.getText());

            //TODO check if the employee exist
            //TODO check if the employee has meetings
            //TODO delete meetings from database
            //TODO delete student from database

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();


            String idString = String.valueOf(employeeID);

            PreparedStatement statement =
                    connection.prepareStatement("SELECT *FROM EMPLOYEE " +
                            "WHERE SSN =?");
            statement.setString(1, idString);
            int row = statement.executeUpdate();
            if (row > 0) {

                //check if this employee have meeting
                //yes --> ask user to delete
                //   ok ==>> delete meetings , delete employee
                //   cancel ==>> employeeField.clear();
                //no --> delete employee
                PreparedStatement statement1 = connection.prepareStatement(" select *from MEETING" +
                        " where M_SSN =?");

                statement1.setString(1, idString);
                int row1 = statement1.executeUpdate();
                if (row1 > 0) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("This employee have "+row1+" meeting\ndo you want to delete is too?");
                    ButtonType yesButton=new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType noButton=new ButtonType("No",ButtonBar.ButtonData.NO);
                    alert.getButtonTypes().setAll(yesButton,noButton);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == yesButton) {

                        PreparedStatement statement2 = connection.prepareStatement(" delete from MEETING" +
                                " where M_SSN =?");

                        statement2.setString(1, idString);
                        statement2.executeUpdate();

                        PreparedStatement statement3 =
                                connection.prepareStatement("DELETE FROM EMPLOYEE " +
                                        "WHERE SSN =?");
                        statement3.setString(1, idString);

                        int row3 = statement3.executeUpdate();
                        if (row3 > 0) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("The employee has been deleted from database !");
                            alert.showAndWait();

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Stage root = FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
                            stage.setScene(root.getScene());

                        }

                    } else {
                        employeeIDField.clear();
                        employeeIDField.requestFocus();
                    }
                }else{
                    //this employee has not any meeting -- delete
                    PreparedStatement statement3 =
                            connection.prepareStatement("DELETE FROM EMPLOYEE " +
                                    "WHERE SSN =?");
                    statement3.setString(1, idString);
                    int row3 = statement3.executeUpdate();
                    if (row3 > 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("");
                        alert.setHeaderText(null);
                        alert.setContentText("The employee has been deleted from database !");
                        alert.showAndWait();

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Stage root = FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
                        stage.setScene(root.getScene());

                    }
                }


            } else {
                // employee does not exist

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("This employee is not exist !");
                alert.showAndWait();
                employeeIDField.clear();

            }


        } catch (NumberFormatException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input !");
            alert.showAndWait();
            employeeIDField.clear();
        } catch (SQLException ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }





//        try{
//            int employeeID=Integer.parseInt(employeeIDField.getText());
//
//            //TODO check if the employee exist     (done)
//            //TODO delete employee from database   (done)
//
//            OracleDataSource oracleDataSource = new OracleDataSource();
//            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
//            oracleDataSource.setURL(url);
//            oracleDataSource.setUser("school");
//            oracleDataSource.setPassword("123456");
//            Connection connection = oracleDataSource.getConnection();
//
//            String idString=String.valueOf(employeeID);
////                        String phoneString=String.valueOf(phone);
//            PreparedStatement statement=
//                    connection.prepareStatement("DELETE FROM Employee " +
//                            "WHERE SSN =?");
//            statement.setString(1,idString);
//
//             statement.executeUpdate();
////            if(row>0){
////                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
////                alert.setTitle("");
////                alert.setHeaderText(null);
////                alert.setContentText("The employee has been deleted from database !");
////                alert.showAndWait();
////
////                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
////                Stage root =  FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
////                stage.setScene(root.getScene());
////            }else{
////                Alert alert=new Alert(Alert.AlertType.WARNING);
////                alert.setTitle("");
////                alert.setHeaderText(null);
////                alert.setContentText("This employee is not exist !");
////                alert.showAndWait();
////                employeeIDField.clear();
////                employeeIDField.requestFocus();
////            }
//
//                    /*
//
//                    DELETE FROM Employee s
//                        WHERE SSN = ;
//                     */
//
//
//
//        }catch (NumberFormatException ex){
//            alert=new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid input !");
//            alert.showAndWait();
//            employeeIDField.clear();
//            employeeIDField.requestFocus();
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }

    }
    public void cancel(ActionEvent event)throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/employee.fxml"));
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
