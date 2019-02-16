package org.tomaszkowalczyk94.gui.controller;


import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput.AssemblerLine;

import org.tomaszkowalczyk94.gui.model.emulation.EmulationManager;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import java.net.URL;
import java.util.ResourceBundle;

public class DebuggerController implements Initializable {


    @Inject Z80 z80;
    @Inject private DialogHelper dialogHelper;
    @Inject private MemoryController memoryController;
    @Inject private RegistersController registersController;
    @Inject private HelpController helpController;
    @Inject private EmulationManager emulationManager;

    @FXML public Button oneStepButton;
    @FXML public Button resetButton;
    @FXML public Button cyclicButton;

    @FXML public TextField lastInstructionName;
    @FXML public TextField nextInstructionName;

    @FXML public TextField machineCycles;
    @FXML public TextField clockCycles;
    @FXML public TextField instructionCounter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshCyclicButtonText();
    }

    public void onOneStepClick() {
        try {
            emulationManager.runOneInstruction();
            refreshAllAfterChanges();

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



    public void refreshAllAfterChanges() {
        memoryController.refreshMemoryTable();
        registersController.refreshRegs();
        registersController.refreshFlags();
        refreshCyclicButtonText();

        if(emulationManager.getNextAsmLine() != null) {
            nextInstructionName.setText(emulationManager.getNextAsmLine().getInstruction());
        } else {
            nextInstructionName.setText("BRAK DANYCH");
        }

        if(emulationManager.getPrevAsmLine() != null) {
            lastInstructionName.setText(emulationManager.getPrevAsmLine().getInstruction());
        } else {
            lastInstructionName.setText("BRAK DANYCH");
        }

        machineCycles.setText(Integer.toString(z80.getMachineCyclesCounter()));
        clockCycles.setText(Integer.toString(z80.getClockCyclesCounter()));
        instructionCounter.setText(Integer.toString(z80.getInstructionCounter()));
    }
}
