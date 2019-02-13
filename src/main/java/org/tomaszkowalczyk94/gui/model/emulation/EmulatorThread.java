package org.tomaszkowalczyk94.gui.model.emulation;

import com.google.inject.Inject;
import org.tomaszkowalczyk94.gui.controller.DebuggerController;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80;

public class EmulatorThread extends Thread {

    @Inject Z80 z80;
    @Inject DialogHelper dialogHelper;
    @Inject DebuggerController debuggerController;

    private Object lock = new Object();
    boolean pause = false;

    public void pause() {
        pause = true;
    }

    public void unPause() {
        synchronized(lock) {
            lock.notify();
        }

    }

    private void pauseEmulationIfPausedFlag() throws InterruptedException {
        synchronized(lock) {
            if(pause) {
                lock.wait();
                pause = false;
            }
        }
    }

    @Override
    public void run() {
        try {
            pause = true;
            while (true) {
                synchronized (lock) {
                    pauseEmulationIfPausedFlag();
                    makeInterruptsRequests();
                    z80.runOneInstruction();
                    refreshGui();
                }
            }
        } catch (Exception e) {
            dialogHelper.displayError("emulation error", e);
            debuggerController.refreshCyclicButtonText();
        }
    }

    private void refreshGui() {
    }

    private void makeInterruptsRequests() {

    }


}
