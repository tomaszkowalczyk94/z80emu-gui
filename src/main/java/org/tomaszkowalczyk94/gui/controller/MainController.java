package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.io.File;
import java.io.IOException;

public class MainController {

    @Inject private DialogHelper dialogHelper;

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;
    @FXML private RegistersController registersController;
    @FXML private AssemblerController assemblerController;

    @FXML public BorderPane mainBorderPane;
    @FXML public MenuItem closeMenuItem;

    private File lastOpenedAsmFile;

    public void onOpenAsmFileClicked() {
        FileChooser asmFileChooser = createAsmFileChooser();
        File file = asmFileChooser.showOpenDialog(getWindow());

        if(file != null) {
            lastOpenedAsmFile = file;
            try {
                assemblerController.loadAsmFromFile(file);
            } catch (IOException e) {
                dialogHelper.displayError("Błąd pliku",e);
            }
        }
    }

    public void onSaveAsmToFileClicked() {
        FileChooser asmFileChooser = createAsmFileChooser();
        File file = asmFileChooser.showSaveDialog(getWindow());

        if(file != null) {
            lastOpenedAsmFile = file;
            try {
                assemblerController.saveAsmFromFile(file);
            } catch (IOException e) {
                dialogHelper.displayError("Błąd pliku",e);
            }
        }
    }

    public void onClearMemoryClicked() {
        try {
            memoryController.resetMemory();
        } catch (MemoryException e) {
            dialogHelper.displayError("Błąd pamięci",e);
        }
    }

    public void onLoadMemoryFromFileClicked(ActionEvent actionEvent) {
       //not yet implemented
    }

    public void onSaveMemoryToFileClicked(ActionEvent actionEvent) {
        //not yet implemented
    }

    public void onCloseClicked() {
        Stage stage = (Stage)getWindow();
        stage.close();
    }

    private Window getWindow() {
        return mainBorderPane.getScene().getWindow();
    }

    private FileChooser createAsmFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "plik asm", "*.asm"),
                new FileChooser.ExtensionFilter( "wszystkie pliki", "*.*")
        );

        if(lastOpenedAsmFile != null && lastOpenedAsmFile.getParentFile() != null) {
            fileChooser.setInitialDirectory(lastOpenedAsmFile.getParentFile());
        }
        return fileChooser;
    }

}
