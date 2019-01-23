package org.tomaszkowalczyk94.gui.controller;


import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Setter;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

public class DebuggerController {

    @Inject private DialogHelper dialogHelper;
    @Inject private Z80 z80;
    @Inject private MemoryController memoryController;
    @Inject private RegistersController registersController;

    @FXML public Button oneStepButton;
    @FXML public Button resetButton;

    public void onOneStepClick() {
        try {
            z80.runOneInstruction();
            memoryController.refreshMemoryTable();
            registersController.refreshRegs();

        } catch (Z80Exception e) {
            dialogHelper.displayError("Z80Exception", e);
        }
    }

    public void onResetClick() {
        System.out.println("kliknieto w reset");
    }
}
