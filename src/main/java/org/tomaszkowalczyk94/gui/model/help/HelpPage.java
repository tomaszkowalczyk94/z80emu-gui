package org.tomaszkowalczyk94.gui.model.help;

import javafx.scene.control.TreeItem;
import lombok.Getter;
import org.controlsfx.glyphfont.Glyph;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;

import java.net.URI;
import java.net.URISyntaxException;

public class HelpPage extends TreeItem<String> {

    @Getter private HelpCatalog parentCatalog;
    @Getter private String name;
    @Getter private String fileName;

    public HelpPage(String name, String fileName, HelpCatalog parentCatalog) {
        super(name, new Glyph("FontAwesome", "file"));
        this.parentCatalog = parentCatalog;
        this.name = name;
        this.fileName = fileName;
    }

    public URI getUriToFile(){
        try {
            return getClass().getClassLoader().getResource(parentCatalog.getAddressToCatalog().toString()+fileName).toURI();
        } catch (URISyntaxException e) {
            throw new EmulatorCriticalException(e);
        }
    }
}
