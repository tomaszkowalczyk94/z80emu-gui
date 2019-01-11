package org.tomaszkowalczyk94.gui.model;

public class ValueFormatter {

    public String getUnsignedHex(int value, int capacity) {
        return String.format(
                String.format("%%0%dx", capacity),
                value
        ).toUpperCase();
    }

}
