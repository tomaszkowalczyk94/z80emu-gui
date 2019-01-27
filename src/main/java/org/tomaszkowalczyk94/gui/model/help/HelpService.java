package org.tomaszkowalczyk94.gui.model.help;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

public class HelpService {

    private static final String BOOTSTRAP_FILE_DIR = "help/bootstrap.json";
    public static final String MAIN_HELP_DIR = "help/";

    /**
     * @return root of tree
     */
    public void createTree(HelpCatalog root) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(BOOTSTRAP_FILE_DIR);
            JSONObject bootstrapJson = new JSONObject(IOUtils.toString(in, "UTF-8"));
            createTreeNode(root, bootstrapJson.toMap());
        } catch (IOException | URISyntaxException e) {
            throw new EmulatorCriticalException(e);
        }
    }

    private void createTreeNode(HelpCatalog parentCatalog, Map<String, Object> map) throws URISyntaxException {

        for(Map.Entry<String, Object> entry : map.entrySet() ) {

            if(entry.getValue() instanceof String) {
                parentCatalog.getChildren().add(
                        new HelpPage((String)entry.getValue(), parentCatalog)
                );
            } else {
                HelpCatalog newCatalog = new HelpCatalog(entry.getKey(), parentCatalog);
                parentCatalog.getChildren().add(newCatalog);
                createTreeNode(newCatalog, (Map)entry.getValue());
            }
        }

    }
}
