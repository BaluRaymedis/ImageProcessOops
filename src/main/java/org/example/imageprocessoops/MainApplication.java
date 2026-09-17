package org.example.imageprocessoops;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import nu.pattern.OpenCV;
import org.opencv.core.Core;

public class MainApplication extends Application {
    @Override
    public void init(){
        OpenCV.loadLocally();
        System.out.println("OpenCV " + Core.getVersionString() + " loaded successfully");
        

    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("imageProcess.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        MainController controller = fxmlLoader.getController();
        controller.attachStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
