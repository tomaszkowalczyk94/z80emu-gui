package org.tomaszkowalczyk94.gui.model;

import lombok.Getter;
import org.tomaszkowalczyk94.z80emu.core.Z80;

public class Context {

    @Getter private Z80 z80 = new Z80();

}
