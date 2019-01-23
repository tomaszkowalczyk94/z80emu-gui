package org.tomaszkowalczyk94.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.tomaszkowalczyk94.gui.model.Context;

public class MainController {



    private Context context = new Context();

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;
    @FXML private RegistersController registersController;
    @FXML private AssemblerController assemblerController;

    @FXML public BorderPane mainBorderPane;
    @FXML public MenuItem closeMenuItem;

    public void initialize() {

        memoryController.setContext(context);

        registersController.setContext(context);

        assemblerController.setContext(context);
        assemblerController.setMemoryController(memoryController);

        debuggerController.setContext(context);
        debuggerController.setMemoryController(memoryController);
        debuggerController.setRegistersController(registersController);

    }


    public void onOpenAsmFileClicked(ActionEvent actionEvent) {
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
        Stage stage = (Stage)mainBorderPane.getScene().getWindow();
        stage.close();
    }


}
