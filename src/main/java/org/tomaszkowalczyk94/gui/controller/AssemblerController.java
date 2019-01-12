package org.tomaszkowalczyk94.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import org.tomaszkowalczyk94.gui.model.Context;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerException;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerFacade;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput.AssemblerLine;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput;

import java.net.URL;
import java.util.ResourceBundle;

public class AssemblerController implements Initializable {

    private AssemblerFacade assemblerFacade;
    private AssemblyOutput lastAssemblyOutput;

    private Context context;
    @Setter private MemoryController memoryController;

    @FXML public TextArea asmTextArea;

    @FXML public Button assemblyButton;
    @FXML public Button loadButton;
    @FXML public Button assemblyAndLoadButton;

    @FXML public TableView<AssemblerLine> asmHexTable;
    @FXML public TableColumn asmHexAddressColumn;
    @FXML public TableColumn asmHexBytesColumn;
    @FXML public TableColumn asmHexInstructionColumn;

    private final ObservableList<AssemblerLine> asmHexTableData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        asmHexAddressColumn.setCellValueFactory(new PropertyValueFactory<AssemblerLine, String>("addressInTable"));
        asmHexBytesColumn.setCellValueFactory(new PropertyValueFactory<AssemblerLine, String>("bytesInTable"));
        asmHexInstructionColumn.setCellValueFactory(new PropertyValueFactory<AssemblerLine, String>("instructionInTable"));
    }

    public void setContext(Context context) {
        this.context = context;
        assemblerFacade = new AssemblerFacade(context.getValueFormatter());
    }

    public void onAssemblyButton(ActionEvent actionEvent) {

        try {
            lastAssemblyOutput = assemblerFacade.assembly(asmTextArea.getText());
            displayHexAsm(lastAssemblyOutput);

        } catch (AssemblerException e) {
            context.getDialogHelper().displayError("błąd asemblera",e);
        }
    }

    public void displayHexAsm(AssemblyOutput assemblyOutput) {
        asmHexTable.setItems(asmHexTableData);

        asmHexTableData.clear();
        asmHexTableData.addAll(assemblyOutput.getLines());

        asmHexTable.refresh();
    }

    public void onLoadButton(ActionEvent actionEvent) {
    }

    public void onAssemblyAndLoadButton(ActionEvent actionEvent) {
    }

}
