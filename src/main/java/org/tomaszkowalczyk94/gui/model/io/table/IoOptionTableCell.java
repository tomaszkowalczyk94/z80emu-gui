package org.tomaszkowalczyk94.gui.model.io.table;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import org.controlsfx.glyphfont.Glyph;


public class IoOptionTableCell extends TableCell<IoDeviceTableRow, Void> {

    private final Button btn;

    public IoOptionTableCell() {
        super();

        Glyph icon = new Glyph("FontAwesome", "trash");

            btn = new Button("Usuń", icon);

        setActionForButton();
    }

    private void setActionForButton() {
        btn.setOnAction((ActionEvent event) -> {
            IoDeviceTableRow IoDeviceTableRow = getTableView().getItems().get(getIndex());
            System.out.println("selectedData: " + IoDeviceTableRow);
        });
    }

    @Override
    public void updateItem(java.lang.Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(btn);
        }
    }
}
