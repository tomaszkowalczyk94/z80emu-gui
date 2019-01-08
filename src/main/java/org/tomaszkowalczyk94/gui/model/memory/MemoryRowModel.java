package org.tomaszkowalczyk94.gui.model.memory;

import org.tomaszkowalczyk94.gui.EmulatorCriticalException;

public class MemoryRowModel {

    private short[] memory;

    private int startAddress;

    public MemoryRowModel(int startAddress, short[] memory) {
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
        return getHexFormattedString(startAddress, 4);
    }

    public String get0() {
        return getHexFormattedString(memory[0], 2);
    }

    public String get1() {
        return getHexFormattedString(memory[1], 2);
    }

    public String get2() {
        return getHexFormattedString(memory[2], 2);
    }

    public String get3() {
        return getHexFormattedString(memory[3], 2);
    }

    public String get4() {
        return getHexFormattedString(memory[4], 2);
    }

    public String get5() {
        return getHexFormattedString(memory[5], 2);
    }

    public String get6() {
        return getHexFormattedString(memory[6], 2);
    }

    public String get7() {
        return getHexFormattedString(memory[7], 2);
    }

    public String get8() {
        return getHexFormattedString(memory[8], 2);
    }

    public String get9() {
        return getHexFormattedString(memory[9], 2);
    }

    public String getA() {
        return getHexFormattedString(memory[10], 2);
    }

    public String getB() {
        return getHexFormattedString(memory[11], 2);
    }

    public String getC() {
        return getHexFormattedString(memory[12], 2);
    }

    public String getD() {
        return getHexFormattedString(memory[13], 2);
    }

    public String getE() {
        return getHexFormattedString(memory[14], 2);
    }

    public String getF() {
        return getHexFormattedString(memory[15], 2);
    }

    private String getHexFormattedString(int value, int capacity) {
        return String.format(
                String.format("%%0%dx", capacity),
                value
        ).toUpperCase();
    }
}
