package org.tomaszkowalczyk94.gui.controller;


import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.tomaszkowalczyk94.gui.model.emulation.EmulationManager;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import java.net.URL;
import java.util.ResourceBundle;

public class DebuggerController implements Initializable {


    @Inject private DialogHelper dialogHelper;
    @Inject private MemoryController memoryController;
    @Inject private RegistersController registersController;
    @Inject private HelpController helpController;
    @Inject private EmulationManager emulationManager;

    @FXML public Button oneStepButton;
    @FXML public Button resetButton;
    @FXML public Button cyclicButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshCyclicButtonText();
    }

    public void onOneStepClick() {
        try {
            emulationManager.runOneInstruction();
            memoryController.refreshMemoryTable();
            registersController.refreshRegs();

        } catch (Z80Exception e) {
            dialogHelper.displayError("Z80Exception", e);
        }
    }

    public void onResetClick() {
        System.out.println("kliknieto w reset");

        helpController.openHelpWindow();
    }

    public void onCyclicButton() {

        if(emulationManager.cyclicModeIsRunning()) {
            emulationManager.stopCyclicEmulation();
        } else {
            emulationManager.startCyclicMode();
        }

        refreshCyclicButtonText();
    }

    public void refreshCyclicButtonText() {
        if(emulationManager.cyclicModeIsRunning()) {
            cyclicButton.setText("STOP");
        } else {
            cyclicButton.setText("Tryb ciągły");
        }
    }
}
