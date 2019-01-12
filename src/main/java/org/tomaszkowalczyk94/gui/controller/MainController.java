package org.tomaszkowalczyk94.gui.controller;

import javafx.fxml.FXML;
import org.tomaszkowalczyk94.gui.model.Context;

public class MainController {

    private Context context = new Context();

    @FXML private DebuggerController debuggerController;
    @FXML private MemoryController memoryController;
    @FXML private RegistersController registersController;
    @FXML private AssemblerController assemblerController;

    public void initialize() {

        memoryController.setContext(context);

        registersController.setContext(context);

        assemblerController.setContext(context);
        assemblerController.setMemoryController(memoryController);

        debuggerController.setContext(context);
        debuggerController.setMemoryController(memoryController);
        debuggerController.setRegistersController(registersController);

    }
}
