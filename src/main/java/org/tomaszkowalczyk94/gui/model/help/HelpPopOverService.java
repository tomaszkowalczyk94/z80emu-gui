package org.tomaszkowalczyk94.gui.model.help;

import com.google.inject.Inject;
import javafx.scene.layout.Pane;
import org.controlsfx.control.PopOver;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;
import org.tomaszkowalczyk94.gui.FxmlLoaderCreator;

import java.io.IOException;

public class HelpPopOverService {

    @Inject private FxmlLoaderCreator fxmlLoaderCreator;

    public PopOver createPopOverForIReg() {
        try {
            Pane pane = fxmlLoaderCreator.createFxmlLoader("help/popover/regI.fxml").load();

            PopOver popOver = new PopOver(pane);
            popOver.setDetached(true);
            return popOver;
        } catch (IOException e) {
            throw new EmulatorCriticalException(e);
        }
    }
}
