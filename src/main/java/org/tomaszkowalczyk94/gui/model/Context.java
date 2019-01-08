package org.tomaszkowalczyk94.gui.model;

import lombok.Getter;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class Context {

    @Getter private Z80 z80 = new Z80();

    public Context() {
        try {

            for(int i= 0; i<1000; i++) {
                z80.getMem().write(i, XBit8.valueOfUnsigned(0xFF));
            }

        } catch (MemoryException e) {
            e.printStackTrace();
        }
    }
}
