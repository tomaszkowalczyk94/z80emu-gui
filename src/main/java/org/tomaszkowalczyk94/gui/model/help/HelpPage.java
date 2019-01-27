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

    public HelpPage(String name, HelpCatalog parentCatalog) {
        super(name, new Glyph("FontAwesome", "folder"));
        this.parentCatalog = parentCatalog;
        this.name = name;
    }

    public URI getUriToFile(){
        try {
            return getClass().getClassLoader().getResource(parentCatalog.getAddressToCatalog().toString()+name).toURI();
        } catch (URISyntaxException e) {
            throw new EmulatorCriticalException(e);
        }
    }
}
