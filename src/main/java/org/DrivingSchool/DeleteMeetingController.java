package org.DrivingSchool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMeetingController {

    public TextField meetingIDField;
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
            int meetingID=Integer.parseInt(meetingIDField.getText());

            //TODO check if the employee exist     (done)
            //TODO delete employee from database   (done)

            OracleDataSource oracleDataSource = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521/orcl";
            oracleDataSource.setURL(url);
            oracleDataSource.setUser("school");
            oracleDataSource.setPassword("123456");
            Connection connection = oracleDataSource.getConnection();

            String idString=String.valueOf(meetingID);
//                        String phoneString=String.valueOf(phone);
            PreparedStatement statement=
                    connection.prepareStatement("DELETE FROM MEETING " +
                            "WHERE MEETING_NUMBER =?");
            statement.setString(1,idString);



            int row=statement.executeUpdate();
            if(row>0){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("The meeting has been deleted from database !");
                alert.showAndWait();

                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Stage root =  FXMLLoader.load(getClass().getResource("/FXML/meeting.fxml"));
                stage.setScene(root.getScene());
            }else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("This meeting is not exist !");
                alert.showAndWait();
                meetingIDField.clear();
                meetingIDField.requestFocus();
            }

                    /*

                    DELETE FROM Employee s
                        WHERE SSN = ;
                     */



        }catch (NumberFormatException ex){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input !");
            alert.showAndWait();
            meetingIDField.clear();
            meetingIDField.requestFocus();
        }catch (SQLException ex){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }

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
