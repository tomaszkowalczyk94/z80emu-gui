package org.tomaszkowalczyk94.gui.model;

import org.junit.Test;
import org.tomaszkowalczyk94.gui.model.memory.MemoryRowModel;

import static org.junit.Assert.*;

public class MemoryRowModelTest {

    @Test
    public void getStartAddress() {
        MemoryRowModel memoryRowModel = new MemoryRowModel(new ValueFormatter(), 0x00A0, new short[16]);
        assertEquals("00A0", memoryRowModel.getStartAddress());

        memoryRowModel = new MemoryRowModel(new ValueFormatter(),0xFFF0, new short[16]);
        assertEquals("FFF0", memoryRowModel.getStartAddress());


    }
}