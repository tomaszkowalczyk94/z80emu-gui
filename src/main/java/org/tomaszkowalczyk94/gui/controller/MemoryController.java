package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;
import org.tomaszkowalczyk94.gui.model.memory.MemoryService;
import org.tomaszkowalczyk94.gui.model.memory.MemoryRowModel;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.gui.view.ValueFormatter;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.net.URL;
import java.util.ResourceBundle;

public class MemoryController implements Initializable {

    @Inject private ValueFormatter valueFormatter;
    @Inject private DialogHelper dialogHelper;
    @Inject private Z80 z80;
    @Inject private MemoryService memoryService = new MemoryService();

    private static final int COUNT_OF_ROW = 0xFFF+1;
    private static final int COUNT_OF_COLUMN = 0xF+1;

    @FXML public TableView<MemoryRowModel> memoryTable;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnAddress;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn0;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn1;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn2;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn3;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn4;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn5;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn6;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn7;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn8;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumn9;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnA;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnB;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnC;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnD;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnE;
    @FXML public TableColumn<MemoryRowModel, String> memoryColumnF;

    private final ObservableList<MemoryRowModel> memoryTableData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        memoryColumnAddress.setCellValueFactory(new PropertyValueFactory<>("startAddress"));
        memoryColumn0.setCellValueFactory(new PropertyValueFactory<>("0"));
        memoryColumn1.setCellValueFactory(new PropertyValueFactory<>("1"));
        memoryColumn2.setCellValueFactory(new PropertyValueFactory<>("2"));
        memoryColumn3.setCellValueFactory(new PropertyValueFactory<>("3"));
        memoryColumn4.setCellValueFactory(new PropertyValueFactory<>("4"));
        memoryColumn5.setCellValueFactory(new PropertyValueFactory<>("5"));
        memoryColumn6.setCellValueFactory(new PropertyValueFactory<>("6"));
        memoryColumn7.setCellValueFactory(new PropertyValueFactory<>("7"));
        memoryColumn8.setCellValueFactory(new PropertyValueFactory<>("8"));
        memoryColumn9.setCellValueFactory(new PropertyValueFactory<>("9"));
        memoryColumnA.setCellValueFactory(new PropertyValueFactory<>("A"));
        memoryColumnB.setCellValueFactory(new PropertyValueFactory<>("B"));
        memoryColumnC.setCellValueFactory(new PropertyValueFactory<>("C"));
        memoryColumnD.setCellValueFactory(new PropertyValueFactory<>("D"));
        memoryColumnE.setCellValueFactory(new PropertyValueFactory<>("E"));
        memoryColumnF.setCellValueFactory(new PropertyValueFactory<>("F"));

        memoryColumn0.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn4.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn5.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn6.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn7.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn8.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumn9.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumnA.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumnB.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumnC.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumnD.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumnE.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryColumnF.setCellFactory(TextFieldTableCell.forTableColumn());

        refreshMemoryTable();
    }

    public void refreshMemoryTable() {
        memoryTable.setItems(memoryTableData);
        memoryTableData.clear();

        for(int rowI = 0, startAddress = 0; rowI<COUNT_OF_ROW; rowI++,  startAddress += 16) {

            short[] memRowData = new short[16];

            for(int memAddress = startAddress, columnI = 0 ; memAddress<startAddress + COUNT_OF_COLUMN ; memAddress++, columnI++) {
                try {
                    memRowData[columnI] = z80.getMem().read(memAddress).getUnsignedValue();
                } catch (MemoryException e) {
                    throw new EmulatorCriticalException(e);
                }
            }

            memoryTableData.add(new MemoryRowModel(valueFormatter, startAddress, memRowData));
        }

        memoryTable.refresh();
    }


    public void onEditCell(TableColumn.CellEditEvent<MemoryRowModel,String> event) {
        try {

            // -1 because firs column in table is column with address. We cannot take that into account
            int memoryAddress = event.getRowValue().getIntStartAddress() + event.getTablePosition().getColumn() - 1;

            int intNewValue = Integer.parseInt(event.getNewValue(), 16);
            XBit8 newValue = XBit8.valueOfUnsigned(intNewValue);

            z80.getMem().write(memoryAddress, newValue);

        } catch (NumberFormatException e) {
            dialogHelper.displayError("Podana wartość musi być w formacie hexalnym, w zakresie od 00 do FF");
        } catch (MemoryException e) {
            dialogHelper.displayError("Błąd pamięci", e);
        }

        this.refreshMemoryTable();
    }
}
