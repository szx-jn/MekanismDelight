package com.mochi_753.mekanismdelight.common.compat.evolvedmekanism;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLLoader;

public class EvolvedMekanismCompat {
    public EvolvedMekanismCompat(IEventBus eventBus) {
        if (FMLLoader.getLoadingModList().getModFileById("evolvedmekanism") != null) {
            EvolvedMekanismDelightItems.register(eventBus);
            eventBus.addListener(EvolvedMekanismDelightItems::onBuildCreativeModeTabContents);
        }
    }
}
