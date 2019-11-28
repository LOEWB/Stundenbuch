package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Fetching;
import model.XmlHandling;

import java.io.File;

public class View extends Application {

    Fetching fetching;
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templates/interface.fxml"));
        primaryStage.setTitle("Stundenbuch");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

        controller = new Controller();

    }

    @Override
    public void stop() throws Exception {
    }


}
