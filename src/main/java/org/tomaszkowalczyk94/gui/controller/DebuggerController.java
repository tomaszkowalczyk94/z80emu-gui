package org.tomaszkowalczyk94.gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Setter;
import org.tomaszkowalczyk94.gui.model.Context;

public class DebuggerController {

    @Setter
    private Context context;

    @FXML public Button oneStepButton;
    @FXML public Button resetButton;

    public void onOneStepClick(ActionEvent actionEvent) {
        System.out.println("kliknieto one step");
    }

    public void onResetClick(ActionEvent actionEvent) {
        System.out.println("kliknieto w reset");
    }
}
