package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import org.tomaszkowalczyk94.gui.model.help.HelpCatalog;
import org.tomaszkowalczyk94.gui.model.help.HelpPage;
import org.tomaszkowalczyk94.gui.model.help.HelpService;
import org.tomaszkowalczyk94.gui.view.HelpStage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @Inject private HelpStage helpStage;
    @Inject private HelpService helpService;


    @FXML public TreeView<String> menuTree;
    @FXML public WebView webView;

    HelpCatalog helpRoot = new HelpCatalog();

    public void openHelpWindow() {
        helpStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuTree.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue instanceof HelpPage) {
                        HelpPage helpPage = (HelpPage) newValue;
                        webView.getEngine().load(helpPage.getUriToFile().toString());
                    }
                });

        helpService.createTree(helpRoot);
        menuTree.setRoot(helpRoot);
    }
}
