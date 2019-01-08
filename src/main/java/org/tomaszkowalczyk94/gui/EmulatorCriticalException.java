package org.tomaszkowalczyk94.gui;

public class EmulatorCriticalException extends RuntimeException {
    public EmulatorCriticalException(String message) {
        super(message);
    }

    public EmulatorCriticalException(Throwable cause) {
        super(cause);
    }
}
