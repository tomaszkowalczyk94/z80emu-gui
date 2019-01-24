package org.tomaszkowalczyk94.gui.view;

import javafx.stage.FileChooser;
import lombok.Setter;

import java.io.File;

public class FileChooserFactory {

    @Setter private File lastOpenedAsmFile;
    @Setter private File lastOpenedMemoryFile;

    public FileChooser createAsmFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "plik asm", "*.asm"),
                new FileChooser.ExtensionFilter( "wszystkie pliki", "*.*")
        );

        if(lastOpenedAsmFile != null && lastOpenedAsmFile.getParentFile() != null) {
            fileChooser.setInitialDirectory(lastOpenedAsmFile.getParentFile());
        }
        return fileChooser;
    }

    public FileChooser createMemoryFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "plik binarny", "*.bin"),
                new FileChooser.ExtensionFilter( "wszystkie pliki", "*.*")
        );

        if(lastOpenedMemoryFile != null && lastOpenedMemoryFile.getParentFile() != null) {
            fileChooser.setInitialDirectory(lastOpenedMemoryFile.getParentFile());
        }
        return fileChooser;
    }

}
