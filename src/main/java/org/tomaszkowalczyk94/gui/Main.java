package org.tomaszkowalczyk94.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Injector injector = Guice.createInjector(new BasicModule());

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));

        loader.setControllerFactory(injector::getInstance);

        Parent root = loader.load();
        root.getStylesheets().add("css/style.css");
        primaryStage.setTitle("Z80 emu Tomasz Kowalczyk");
        primaryStage.setScene(new Scene(root, 1330, 740));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
