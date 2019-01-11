package org.tomaszkowalczyk94.gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Setter;
import org.tomaszkowalczyk94.gui.model.Context;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

public class DebuggerController {

    @Setter private Context context;
    @Setter private MemoryController memoryController;
    @Setter private RegistersController registersController;

    @FXML public Button oneStepButton;
    @FXML public Button resetButton;

    public void onOneStepClick(ActionEvent actionEvent) {
        try {
            context.getZ80().runOneInstruction();
            memoryController.refreshMemoryTable();
            registersController.refreshRegs();

        } catch (Z80Exception e) {
            context.getDialogHelper().displayError("Z80Exception", e);
        }
    }

    public void onResetClick(ActionEvent actionEvent) {
        System.out.println("kliknieto w reset");
    }
}
