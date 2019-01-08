package org.tomaszkowalczyk94.gui.controller;

import javafx.fxml.FXML;
import org.tomaszkowalczyk94.gui.model.Context;

public class MainController {

    private Context context;

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;

    public void initialize() {
        context = new Context();
        debuggerController.setContext(context);
        memoryController.setContext(context);
    }
}
