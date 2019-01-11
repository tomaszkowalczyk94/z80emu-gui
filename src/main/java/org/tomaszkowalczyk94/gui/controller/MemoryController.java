package org.tomaszkowalczyk94.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;
import org.tomaszkowalczyk94.gui.model.Context;
import org.tomaszkowalczyk94.gui.model.memory.MemoryRowModel;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.net.URL;
import java.util.ResourceBundle;

public class MemoryController implements Initializable {

    private Context context;

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

    public void setContext(Context context) {
        this.context = context;
        refreshMemoryTable();
    }

    public void initialize(URL location, ResourceBundle resources) {
        memoryColumnAddress.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("startAddress"));
        memoryColumn0.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("0"));
        memoryColumn1.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("1"));
        memoryColumn2.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("2"));
        memoryColumn3.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("3"));
        memoryColumn4.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("4"));
        memoryColumn5.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("5"));
        memoryColumn6.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("6"));
        memoryColumn7.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("7"));
        memoryColumn8.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("8"));
        memoryColumn9.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("9"));
        memoryColumnA.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("A"));
        memoryColumnB.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("B"));
        memoryColumnC.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("C"));
        memoryColumnD.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("D"));
        memoryColumnE.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("E"));
        memoryColumnF.setCellValueFactory(new PropertyValueFactory<MemoryRowModel,String>("F"));

        memoryColumn0.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn1.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn2.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn3.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn4.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn5.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn6.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn7.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn8.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumn9.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumnA.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumnB.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumnC.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumnD.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumnE.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
        memoryColumnF.setCellFactory(TextFieldTableCell.<MemoryRowModel>forTableColumn());
    }

    public void refreshMemoryTable() {
        memoryTable.setItems(memoryTableData);
        memoryTableData.clear();

        for(int rowI = 0, startAddress = 0; rowI<COUNT_OF_ROW; rowI++,  startAddress += 16) {

            short[] memRowData = new short[16];

            for(int memAddress = startAddress, columnI = 0 ; memAddress<startAddress + COUNT_OF_COLUMN ; memAddress++, columnI++) {
                try {
                    memRowData[columnI] = context.getZ80().getMem().read(memAddress).getUnsignedValue();
                } catch (MemoryException e) {
                    throw new EmulatorCriticalException(e);
                }
            }

            memoryTableData.add(new MemoryRowModel(context.getValueFormatter(), startAddress, memRowData));
        }

        memoryTable.refresh();
    }


    public void onEditCell(TableColumn.CellEditEvent<MemoryRowModel,String> event) {
        try {

            // -1 because firs column in table is column with address. We cannot take that into account
            int memoryAddress = event.getRowValue().getIntStartAddress() + event.getTablePosition().getColumn() - 1;

            int intNewValue = Integer.parseInt(event.getNewValue(), 16);
            XBit8 newValue = XBit8.valueOfUnsigned(intNewValue);

            context.getZ80().getMem().write(memoryAddress, newValue);

        } catch (NumberFormatException e) {
            context.getDialogHelper().displayError("Podana wartość musi być w formacie hexalnym, w zakresie od 00 do FF");
        } catch (MemoryException e) {
            context.getDialogHelper().displayError("Błąd pamięci", e);
        }

        this.refreshMemoryTable();
    }
}
