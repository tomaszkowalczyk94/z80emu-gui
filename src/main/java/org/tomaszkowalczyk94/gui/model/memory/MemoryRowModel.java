package org.tomaszkowalczyk94.gui.model.memory;

import org.tomaszkowalczyk94.gui.EmulatorCriticalException;
import org.tomaszkowalczyk94.gui.model.ValueFormatter;

public class MemoryRowModel {

    private ValueFormatter valueFormatter;

    private short[] memory;

    private int startAddress;

    public MemoryRowModel(ValueFormatter valueFormatter, int startAddress, short[] memory) {
        this.valueFormatter = valueFormatter;

        if(memory.length != 16) {
            throw new EmulatorCriticalException("parameter of constructor  short[] memory don't have equals 16 items");
        }

        this.memory = memory;
        this.startAddress = startAddress;
    }

    public int getIntStartAddress() {
        return startAddress;
    }

    public String getStartAddress() {
        return valueFormatter.getUnsignedHex(startAddress, 4);
    }

    public String get0() {
        return valueFormatter.getUnsignedHex(memory[0], 2);
    }

    public String get1() {
        return valueFormatter.getUnsignedHex(memory[1], 2);
    }

    public String get2() {
        return valueFormatter.getUnsignedHex(memory[2], 2);
    }

    public String get3() {
        return valueFormatter.getUnsignedHex(memory[3], 2);
    }

    public String get4() {
        return valueFormatter.getUnsignedHex(memory[4], 2);
    }

    public String get5() {
        return valueFormatter.getUnsignedHex(memory[5], 2);
    }

    public String get6() {
        return valueFormatter.getUnsignedHex(memory[6], 2);
    }

    public String get7() {
        return valueFormatter.getUnsignedHex(memory[7], 2);
    }

    public String get8() {
        return valueFormatter.getUnsignedHex(memory[8], 2);
    }

    public String get9() {
        return valueFormatter.getUnsignedHex(memory[9], 2);
    }

    public String getA() {
        return valueFormatter.getUnsignedHex(memory[10], 2);
    }

    public String getB() {
        return valueFormatter.getUnsignedHex(memory[11], 2);
    }

    public String getC() {
        return valueFormatter.getUnsignedHex(memory[12], 2);
    }

    public String getD() {
        return valueFormatter.getUnsignedHex(memory[13], 2);
    }

    public String getE() {
        return valueFormatter.getUnsignedHex(memory[14], 2);
    }

    public String getF() {
        return valueFormatter.getUnsignedHex(memory[15], 2);
    }
}
