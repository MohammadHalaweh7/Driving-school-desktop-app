package org.DrivingSchool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Stage root =  FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        root.show();
    }

    public static void main(String[] args) {
        launch();
    }

}