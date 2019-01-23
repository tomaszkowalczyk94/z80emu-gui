package org.tomaszkowalczyk94.gui.view;

import javafx.scene.control.Alert;

public class DialogHelper {

    public void displayError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    public void displayError(String header, Throwable e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(header);
        alert.setContentText(e.getLocalizedMessage());

        alert.showAndWait();
    }
}
