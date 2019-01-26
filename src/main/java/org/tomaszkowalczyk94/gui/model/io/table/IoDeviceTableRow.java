package org.tomaszkowalczyk94.gui.model.io.table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IoDeviceTableRow {
    String name;
    String port;
    String data;
}
