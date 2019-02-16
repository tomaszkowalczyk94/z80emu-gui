package org.tomaszkowalczyk94.gui.model.emulation;

import com.google.inject.Inject;
import lombok.Getter;
import org.tomaszkowalczyk94.gui.EmulatorCriticalException;
import org.tomaszkowalczyk94.gui.controller.AssemblerController;
import org.tomaszkowalczyk94.gui.controller.DebuggerController;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblyOutput;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import static java.lang.Thread.State.RUNNABLE;

public class EmulationManager {

    @Inject DialogHelper dialogHelper;
    @Inject Z80 z80;
    @Inject DebuggerController debuggerController;
    @Inject private AssemblerController assemblerController;

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
        if(emulatorThread.getState() == RUNNABLE) {
            throw new EmulatorCriticalException("cannot run one instruction when emulation is working");
        }
        z80.runOneInstruction();
        refreshNextAndPrevAsmLine();
    }

    @Getter AssemblyOutput.AssemblerLine prevAsmLine;
    @Getter AssemblyOutput.AssemblerLine nextAsmLine;

    public void refreshNextAndPrevAsmLine() {

        AssemblyOutput.AssemblerLine nextAsmLineLocal = assemblerController.getAssemblerLineForAddress(
                z80.getRegs().getPc().getUnsignedValue()
        );
        prevAsmLine = nextAsmLine;

        if (nextAsmLineLocal != null) {
            nextAsmLine = nextAsmLineLocal;
        } else {
            nextAsmLine = null;
        }
    }
}
