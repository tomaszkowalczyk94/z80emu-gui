package org.tomaszkowalczyk94.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.net.URL;
import java.util.ResourceBundle;

public class AssemblerController implements Initializable {

    private AssemblerFacade assemblerFacade;
    private AssemblyOutput assemblyOutput;

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

    public void onAssemblyButton() {
        try {
            displayHexAsm(assemblerFacade.assembly(asmTextArea.getText()));
        } catch (AssemblerException e) {
            context.getDialogHelper().displayError("błąd asemblera",e);
        }
    }

    public void onLoadButton() {
        if(assemblyOutput == null) {
            context.getDialogHelper().displayError("brak danych do wczytania");
        } else {
            loadAssemblerOutputToMemory();
        }
    }

    public void onAssemblyAndLoadButton() {
        onAssemblyButton();
        onLoadButton();
    }

    public void displayHexAsm(AssemblyOutput assemblyOutput) {
        this.assemblyOutput = assemblyOutput;

        asmHexTable.setItems(asmHexTableData);

        asmHexTableData.clear();
        asmHexTableData.addAll(assemblyOutput.getLines());

        asmHexTable.refresh();
    }

    public void loadAssemblerOutputToMemory() {
        assemblyOutput.getLines().forEach(assemblerLine -> {
            try {
                for(int address = assemblerLine.getAddress(), i = 0; i<assemblerLine.getBytes().size(); address++, i++) {
                    context.getZ80().getMem().write(address, assemblerLine.getBytes().get(i));
                }
            } catch (MemoryException e) {
                context.getDialogHelper().displayError("memory error", e);
            } finally {
                memoryController.refreshMemoryTable();
            }
        });
    }



}
