package org.tomaszkowalczyk94.gui.view;

public class ValueFormatter {

    public String getUnsignedHex(int value, int capacity) {
        return String.format(
                String.format("%%0%dx", capacity),
                value
        ).toUpperCase();
    }

}
