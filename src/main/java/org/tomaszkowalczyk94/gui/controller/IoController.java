package org.tomaszkowalczyk94.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tomaszkowalczyk94.gui.model.io.table.IoDeviceTableRow;
import org.tomaszkowalczyk94.gui.model.io.table.IoOptionTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class IoController implements Initializable {

    @FXML public TableView<IoDeviceTableRow> ioTable;
    @FXML public TableColumn ioTablePortColumn;
    @FXML public TableColumn ioTableNameColumn;
    @FXML public TableColumn ioTableDataColumn;
    @FXML public TableColumn<IoDeviceTableRow, Void> ioTableOptionColumn;

    private final ObservableList<IoDeviceTableRow> tvObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableObservableListWithSampleData();
        ioTable.setItems(tvObservableList);

        ioTablePortColumn.setCellValueFactory(new PropertyValueFactory<>("port"));
        ioTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ioTableDataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));

        addButtonToTable();
    }

    private void addButtonToTable() {
        ioTableOptionColumn.setCellFactory(param -> new IoOptionTableCell());
    }

    private void fillTableObservableListWithSampleData() {
        tvObservableList.add(new IoDeviceTableRow("test 1", "01h","45"));
        tvObservableList.add(new IoDeviceTableRow("test 2", "02h","FF"));
        tvObservableList.add(new IoDeviceTableRow("test 3", "03h","FF"));
    }
}
