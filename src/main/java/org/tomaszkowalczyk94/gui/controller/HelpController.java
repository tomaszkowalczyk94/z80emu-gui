package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import org.tomaszkowalczyk94.gui.view.HelpStage;

public class HelpController {

    @Inject private HelpStage helpStage;


    public void openHelpWindow() {
        helpStage.show();
    }
}
