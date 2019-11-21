package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Fetching;

public class View extends Application {

    Fetching fetching;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templates/sample.fxml"));
        primaryStage.setTitle("Stundenbuch");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        fetching = new Fetching(new Controller());
        new Thread(fetching, "fetching").start();

    }

    @Override
    public void stop() throws Exception {
        fetching.interrupt();
    }
}
