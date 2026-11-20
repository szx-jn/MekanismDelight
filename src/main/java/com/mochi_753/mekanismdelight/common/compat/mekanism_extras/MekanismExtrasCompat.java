package com.mochi_753.mekanismdelight.common.compat.mekanism_extras;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLLoader;

public class MekanismExtrasCompat {
    public MekanismExtrasCompat(IEventBus eventBus) {
        if (FMLLoader.getLoadingModList().getModFileById("mekanism_extras") != null) {
            MekanismExtrasDelightItems.register(eventBus);
            eventBus.addListener(MekanismExtrasDelightItems::onBuildCreativeModeTabContents);
        }
    }
}
