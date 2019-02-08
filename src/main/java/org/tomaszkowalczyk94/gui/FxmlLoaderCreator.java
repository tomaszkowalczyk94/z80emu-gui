package org.tomaszkowalczyk94.gui;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;

public class FxmlLoaderCreator {

    @Inject
    private Injector injector;

    public FXMLLoader createFxmlLoader(String file) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(file));
        loader.setControllerFactory(injector::getInstance);
        return loader;
    }
}
