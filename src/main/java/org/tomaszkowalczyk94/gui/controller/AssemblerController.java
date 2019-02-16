package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import javafx.scene.control.TitledPane;
import lombok.NonNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.LineNumberFactory;
import org.tomaszkowalczyk94.gui.view.AsmTextArea;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerException;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerFacade;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput.AssemblerLine;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

public class AssemblerController implements Initializable {

    @Inject private DialogHelper dialogHelper;
    @Inject private AssemblerFacade assemblerFacade;
    @Inject private Z80 z80;
    @Inject private MemoryController memoryController;

    private AssemblyOutput assemblyOutput;

    @FXML public TitledPane asmPane;
    @FXML public Button assemblyButton;
    @FXML public Button loadButton;
    @FXML public Button assemblyAndLoadButton;

    @FXML public TableView<AssemblerLine> asmHexTable;
    @FXML public TableColumn asmHexAddressColumn;
    @FXML public TableColumn asmHexBytesColumn;
    @FXML public TableColumn asmHexInstructionColumn;

    @FXML private AsmTextArea asmTextArea = new AsmTextArea();

    private final ObservableList<AssemblerLine> asmHexTableData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        asmHexAddressColumn.setCellValueFactory(new PropertyValueFactory<>("addressInTable"));
        asmHexBytesColumn.setCellValueFactory(new PropertyValueFactory<>("bytesInTable"));
        asmHexInstructionColumn.setCellValueFactory(new PropertyValueFactory<>("instructionInTable"));

        asmTextArea.setParagraphGraphicFactory(LineNumberFactory.get(asmTextArea));
        asmPane.setContent(new VirtualizedScrollPane<>(asmTextArea));
    }

    public void onAssemblyButton() {
        try {
            displayHexAsm(assemblerFacade.assembly(asmTextArea.getText()));
        } catch (AssemblerException e) {
            dialogHelper.displayError("błąd asemblera",e);
        }
    }

    public void onLoadButton() {
        if(assemblyOutput == null) {
            dialogHelper.displayError("brak danych do wczytania");
        } else {
            loadAssemblerOutputToMemory();
        }
    }

    public void loadAsmFromFile(@NonNull File file) throws IOException {
        String asmCodeString = IOUtils.toString(new FileReader(file));
        asmTextArea.clear();
        asmTextArea.replaceText(0, 0, asmCodeString);
    }

    public void saveAsmFromFile(@NonNull File file) throws IOException {
        FileUtils.writeStringToFile(file, "Hello File", Charset.forName("UTF-8"), false);
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
                    z80.getMem().write(address, assemblerLine.getBytes().get(i));
                }
            } catch (MemoryException e) {
                dialogHelper.displayError("memory error", e);
            } finally {
                memoryController.refreshMemoryTable();
            }
        });
    }

    public AssemblerLine getAssemblerLineForAddress(int address) {
        for (AssemblerLine line: assemblyOutput.getLines()) {
            if(line.getAddress() == address) {
                return line;
            }
        }
        return null;
    }
}
