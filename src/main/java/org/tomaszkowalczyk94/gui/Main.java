package org.tomaszkowalczyk94.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        root.getStylesheets().add("css/style.css");
        primaryStage.setTitle("Z80 emu Tomasz Kowalczyk");
        primaryStage.setScene(new Scene(root, 780, 428));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
