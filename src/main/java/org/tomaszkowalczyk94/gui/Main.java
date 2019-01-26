package org.tomaszkowalczyk94.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.tomaszkowalczyk94.gui.view.HelpStage;

import java.io.IOException;

public class Main extends Application {

    private Injector injector = Guice.createInjector(new BasicModule());

    private FXMLLoader createFxmlLoader(String file) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(file));
        loader.setControllerFactory(injector::getInstance);
        return loader;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = createFxmlLoader("main.fxml");

        loader.setControllerFactory(injector::getInstance);

        Parent root = loader.load();
        root.getStylesheets().add("css/style.css");
        primaryStage.setTitle("Z80 emu Tomasz Kowalczyk");
        primaryStage.setScene(new Scene(root, 1330, 740));
        primaryStage.show();

        initHelpWindow(primaryStage);
    }

    /**
     * initializing, don't showing
     * @param parent owner window of help window
     */
    private void initHelpWindow(Window parent) throws IOException {
        FXMLLoader loader = createFxmlLoader("help.fxml");

        HelpStage helpStage = injector.getInstance(HelpStage.class);

        helpStage.setScene(new Scene(loader.load(), 450, 450));
        helpStage.initOwner(parent);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
