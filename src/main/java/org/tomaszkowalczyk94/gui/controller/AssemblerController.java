package org.tomaszkowalczyk94.gui.controller;

import com.sun.istack.internal.NotNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.LineNumberFactory;
import org.tomaszkowalczyk94.gui.model.Context;
import org.tomaszkowalczyk94.gui.model.assembler.AsmTextArea;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerException;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerFacade;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput.AssemblerLine;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AssemblerController implements Initializable {


    private AssemblerFacade assemblerFacade;
    private AssemblyOutput assemblyOutput;

    private Context context;
    @Setter private MemoryController memoryController;

    @FXML public GridPane asmMainGridPane;

    @FXML public Button assemblyButton;
    @FXML public Button loadButton;
    @FXML public Button assemblyAndLoadButton;

    @FXML public TableView<AssemblerLine> asmHexTable;
    @FXML public TableColumn asmHexAddressColumn;
    @FXML public TableColumn asmHexBytesColumn;
    @FXML public TableColumn asmHexInstructionColumn;

    @FXML public AsmTextArea asmTextArea = new AsmTextArea();

    private final ObservableList<AssemblerLine> asmHexTableData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        asmHexAddressColumn.setCellValueFactory(new PropertyValueFactory<AssemblerLine, String>("addressInTable"));
        asmHexBytesColumn.setCellValueFactory(new PropertyValueFactory<AssemblerLine, String>("bytesInTable"));
        asmHexInstructionColumn.setCellValueFactory(new PropertyValueFactory<AssemblerLine, String>("instructionInTable"));

        asmTextArea.setParagraphGraphicFactory(LineNumberFactory.get(asmTextArea));
        StackPane stackPane = new StackPane(new VirtualizedScrollPane<>(asmTextArea));
        asmMainGridPane.add(stackPane, 0, 0);
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

    public void loadAsmFromFile(@NotNull File file) throws IOException {
        String asmCodeString = IOUtils.toString(new FileReader(file));
        asmTextArea.clear();
        asmTextArea.replaceText(0, 0, asmCodeString);
    }

    public void onAssemblyAndLoadButton() {
        onAssemblyButton();
        onLoadButton();
    }

    private void displayHexAsm(AssemblyOutput assemblyOutput) {
        this.assemblyOutput = assemblyOutput;

        asmHexTable.setItems(asmHexTableData);

        asmHexTableData.clear();
        asmHexTableData.addAll(assemblyOutput.getLines());

        asmHexTable.refresh();
    }

    private void loadAssemblerOutputToMemory() {
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
