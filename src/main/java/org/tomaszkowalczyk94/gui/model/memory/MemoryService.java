package org.tomaszkowalczyk94.gui.model.memory;

import com.google.inject.Inject;
import lombok.NonNull;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.Memory;

import java.io.File;
import java.io.FileOutputStream;

public class MemoryService {

    @Inject Z80 z80;

    @FunctionalInterface
    public interface MemoryAddressConsumer {
        void accept(Integer address) throws Exception;
    }

    public void resetMemory() throws Exception {
        foreachOnMemory(address -> z80.getMem().write(address, XBit8.valueOfSigned(0)));
    }

    public void saveToFile(@NonNull File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        foreachOnMemory(address ->
                fileOutputStream.write(z80.getMem().read(address).getSignedValue())
        );

        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private void foreachOnMemory(MemoryAddressConsumer method) throws Exception {
        for (int address = 0; address < Memory.MEMORY_SIZE; address++) {
            method.accept(address);
        }
    }
}
