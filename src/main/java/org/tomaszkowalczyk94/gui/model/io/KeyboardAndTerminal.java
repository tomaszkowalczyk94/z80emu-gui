package org.tomaszkowalczyk94.gui.model.io;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.io.IoDevice;

public class KeyboardAndTerminal implements IoDevice {

    @Override
    public XBit8 read(XBit16 addressBus) {
        XBit8 port = addressBus.getLowByte();

        return null;
    }

    @Override
    public void write(XBit16 addressBus, XBit8 dataBus) {
        XBit8 port = addressBus.getLowByte();
    }


}
