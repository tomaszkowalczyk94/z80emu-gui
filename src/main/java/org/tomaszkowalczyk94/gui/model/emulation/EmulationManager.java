package org.tomaszkowalczyk94.gui.model.emulation;

import com.google.inject.Inject;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;
import org.tomaszkowalczyk94.gui.controller.DebuggerController;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import static java.lang.Thread.State.RUNNABLE;

public class EmulationManager {

    @Inject DialogHelper dialogHelper;
    @Inject Z80 z80;
    @Inject DebuggerController debuggerController;

    @Inject EmulatorThread emulatorThread;

    public void startCyclicMode() {
        emulatorThread.unPause();
    }

    public void stopCyclicEmulation() {
        emulatorThread.pause();
    }

    public boolean cyclicModeIsRunning() {
        if(emulatorThread == null) {
            return false;
        }

        return (emulatorThread.getState() == RUNNABLE);
    }

    public void runOneInstruction() throws Z80Exception {
        if(emulatorThread.isAlive()) {
            throw new EmulatorCriticalException("cannot run one instruction when emulation is working");
        }
        z80.runOneInstruction();
    }



}
