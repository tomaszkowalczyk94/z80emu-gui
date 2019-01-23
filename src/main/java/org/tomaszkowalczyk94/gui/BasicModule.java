package org.tomaszkowalczyk94.gui;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.tomaszkowalczyk94.gui.controller.*;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerFacade;
import org.tomaszkowalczyk94.gui.model.memory.MemoryService;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.gui.view.ValueFormatter;
import org.tomaszkowalczyk94.z80emu.core.Z80;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AssemblerController.class).in(Singleton.class);
        bind(DebuggerController.class).in(Singleton.class);
        bind(MainController.class).in(Singleton.class);
        bind(MemoryController.class).in(Singleton.class);
        bind(RegistersController.class).in(Singleton.class);

        bind(Z80.class).in(Singleton.class);
        bind(DialogHelper.class).in(Singleton.class);
        bind(ValueFormatter.class).in(Singleton.class);
        bind(AssemblerFacade.class).in(Singleton.class);
        bind(MemoryService.class).in(Singleton.class);
    }
}
