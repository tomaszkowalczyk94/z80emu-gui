package org.tomaszkowalczyk94.gui;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.tomaszkowalczyk94.gui.controller.*;
import org.tomaszkowalczyk94.gui.model.assembler.AssemblerFacade;
import org.tomaszkowalczyk94.gui.model.emulation.EmulationManager;
import org.tomaszkowalczyk94.gui.model.emulation.EmulatorThread;
import org.tomaszkowalczyk94.gui.model.help.HelpPopOverService;
import org.tomaszkowalczyk94.gui.model.help.HelpService;
import org.tomaszkowalczyk94.gui.model.memory.MemoryService;
import org.tomaszkowalczyk94.gui.view.DialogHelper;
import org.tomaszkowalczyk94.gui.view.HelpStage;
import org.tomaszkowalczyk94.gui.view.ValueFormatter;
import org.tomaszkowalczyk94.z80emu.core.Z80;

public class DependencyInjectionModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FxmlLoaderCreator.class).in(Singleton.class);

        bind(HelpStage.class).in(Singleton.class);

        bind(AssemblerController.class).in(Singleton.class);
        bind(DebuggerController.class).in(Singleton.class);
        bind(MainController.class).in(Singleton.class);
        bind(MemoryController.class).in(Singleton.class);
        bind(RegistersController.class).in(Singleton.class);
        bind(HelpController.class).in(Singleton.class);

        bind(Z80.class).in(Singleton.class);
        bind(DialogHelper.class).in(Singleton.class);
        bind(ValueFormatter.class).in(Singleton.class);
        bind(AssemblerFacade.class).in(Singleton.class);
        bind(MemoryService.class).in(Singleton.class);
        bind(HelpService.class).in(Singleton.class);
        bind(HelpPopOverService.class).in(Singleton.class);
        bind(EmulationManager.class).in(Singleton.class);
        bind(EmulatorThread.class).in(Singleton.class);
    }
}
