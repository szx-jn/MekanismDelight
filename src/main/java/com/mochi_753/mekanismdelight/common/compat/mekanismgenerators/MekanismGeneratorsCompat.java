package com.mochi_753.mekanismdelight.common.compat.mekanismgenerators;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLLoader;

public class MekanismGeneratorsCompat {
    public MekanismGeneratorsCompat(IEventBus eventBus) {
        if (FMLLoader.getLoadingModList().getModFileById("mekanismgenerators") != null) {
            MekanismGeneratorsDelightItems.register(eventBus);
            eventBus.addListener(MekanismGeneratorsDelightItems::onBuildCreativeModeTabContents);
        }
    }
}
