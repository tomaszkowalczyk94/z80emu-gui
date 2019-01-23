package org.tomaszkowalczyk94.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.tomaszkowalczyk94.gui.model.Context;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Context context = new Context();

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;
    @FXML private RegistersController registersController;
    @FXML private AssemblerController assemblerController;

    @FXML public BorderPane mainBorderPane;
    @FXML public MenuItem closeMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        memoryController.setContext(context);

        registersController.setContext(context);

        assemblerController.setContext(context);
        assemblerController.setMemoryController(memoryController);

        debuggerController.setContext(context);
        debuggerController.setMemoryController(memoryController);
        debuggerController.setRegistersController(registersController);

    }


    public void onOpenAsmFileClicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(getWindow());

        if(file != null) {
            try {
                assemblerController.loadAsmFromFile(file);
            } catch (IOException e) {
                context.getDialogHelper().displayError("brak pliku",e);
            }
        }
    }

    public void onSaveAsmToFileClicked(ActionEvent actionEvent) {
    }

    public void onClearMemoryClicked(ActionEvent actionEvent) {
    }

    public void onLoadMemoryFromFileClicked(ActionEvent actionEvent) {
    }

    public void onSaveMemoryToFileClicked(ActionEvent actionEvent) {
    }

    public void onCloseClicked() {
        Stage stage = (Stage)getWindow();
        stage.close();
    }

    private Window getWindow() {
        return mainBorderPane.getScene().getWindow();
    }

}
