package org.tomaszkowalczyk94.gui.model.help;

import javafx.scene.control.TreeItem;
import lombok.Getter;
import lombok.NonNull;

public class HelpCatalog extends TreeItem<String> {



    @Getter private HelpCatalog parentCatalog;

    @Getter private String catalogName;

    public HelpCatalog() {
        super("root");
        catalogName = "root";
    }

    public HelpCatalog(String catalogName, @NonNull  HelpCatalog parentCatalog) {
        super(catalogName);
        this.parentCatalog = parentCatalog;
        this.catalogName = catalogName;
    }

    public String getAddressToCatalog() {
        if(parentCatalog == null) {
            return HelpService.MAIN_HELP_DIR;
        }

        return parentCatalog.getAddressToCatalog().toString()+catalogName+"/";
    }
}
