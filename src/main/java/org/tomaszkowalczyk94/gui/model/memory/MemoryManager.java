package org.tomaszkowalczyk94.gui.model.memory;

import lombok.NonNull;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.Memory;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class MemoryManager {

    public void resetMemory(Z80 z80) throws MemoryException {
        for (int address = 0; address < Memory.MEMORY_SIZE; address++) {
            z80.getMem().write(address, XBit8.valueOfSigned(0));
        }
    }
}
