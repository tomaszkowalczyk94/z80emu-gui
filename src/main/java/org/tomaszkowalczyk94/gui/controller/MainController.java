package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.tomaszkowalczyk94.gui.model.memory.MemoryService;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.gui.view.FileChooserFactory;

import java.io.File;
import java.io.IOException;

public class MainController {

    @Inject private DialogHelper dialogHelper;
    @Inject private MemoryService memoryService;

    @Inject private FileChooserFactory fileChooserFactory;

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;
    @FXML private RegistersController registersController;
    @FXML private AssemblerController assemblerController;
    @FXML private TerminalController terminalController;

    @FXML public BorderPane mainBorderPane;
    @FXML public MenuItem closeMenuItem;

    public void onOpenAsmFileClicked() {
        FileChooser asmFileChooser = fileChooserFactory.createAsmFileChooser();
        File file = asmFileChooser.showOpenDialog(getWindow());

        if(file != null) {
            fileChooserFactory.setLastOpenedAsmFile(file);
            try {
                assemblerController.loadAsmFromFile(file);
            } catch (IOException e) {
                dialogHelper.displayError("Błąd pliku",e);
            }
        }
    }

    public void onSaveAsmToFileClicked() {
        FileChooser asmFileChooser = fileChooserFactory.createAsmFileChooser();
        File file = asmFileChooser.showSaveDialog(getWindow());

        if(file != null) {
            fileChooserFactory.setLastOpenedAsmFile(file);
            try {
                assemblerController.saveAsmFromFile(file);
            } catch (IOException e) {
                dialogHelper.displayError("Błąd pliku",e);
            }
        }
    }

    public void onClearMemoryClicked() {
        try {
            memoryService.resetMemory();
            memoryController.refreshMemoryTable();
        } catch (Exception e) {
            dialogHelper.displayError("Błąd",e);
        }
    }

    public void onLoadMemoryFromFileClicked() {
        FileChooser asmFileChooser = fileChooserFactory.createMemoryFileChooser();
        File file = asmFileChooser.showOpenDialog(getWindow());

        if(file != null) {
            fileChooserFactory.setLastOpenedMemoryFile(file);
            try {
                memoryService.loadMemoryFromFile(file);
                memoryController.refreshMemoryTable();
            } catch (Exception e) {
                dialogHelper.displayError("Błąd",e);
            }
        }

    }

    public void onSaveMemoryToFileClicked() {

        FileChooser asmFileChooser = fileChooserFactory.createMemoryFileChooser();
        File file = asmFileChooser.showSaveDialog(getWindow());

        if(file != null) {
            fileChooserFactory.setLastOpenedMemoryFile(file);
            try {
                memoryService.saveToFile(file);
            } catch (Exception e) {
                dialogHelper.displayError("Błąd",e);
            }
        }
    }

    public void onCloseClicked() {
        Stage stage = (Stage)getWindow();
        stage.close();
    }

    private Window getWindow() {
        return mainBorderPane.getScene().getWindow();
    }

}
