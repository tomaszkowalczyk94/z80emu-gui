package org.tomaszkowalczyk94.gui.model.assembler;

public class AssemblerException extends Exception {
    public AssemblerException(String message) {
        super(message);
    }

    public AssemblerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssemblerException(Throwable cause) {
        super(cause);
    }
}
