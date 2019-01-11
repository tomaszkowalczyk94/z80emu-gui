package org.tomaszkowalczyk94.gui.controller;

import javafx.fxml.FXML;
import org.tomaszkowalczyk94.gui.model.Context;

public class MainController {

    private Context context = new Context();

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;
    @FXML private RegistersController registersController;

    public void initialize() {
        memoryController.setContext(context);
        registersController.setContext(context);

        debuggerController.setContext(context);
        debuggerController.setMemoryController(memoryController);
        debuggerController.setRegistersController(registersController);
    }
}
