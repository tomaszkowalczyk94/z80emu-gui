package org.tomaszkowalczyk94.gui.controller;

import javafx.fxml.FXML;
import org.tomaszkowalczyk94.gui.model.Context;

public class MainController {

    private Context context;

    @FXML
    DebuggerController debuggerController;

    public void initialize() {
        context = new Context();
        debuggerController.setContext(context);

    }
}
