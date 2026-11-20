package com.mochi_753.mekanismdelight.common.compat.mekanism;

import com.mochi_753.mekanismdelight.MekanismDelight;
import com.mochi_753.mekanismdelight.common.block.AlloyJellyMedleyBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MekanismDelightBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MekanismDelight.MOD_ID);

    public static final DeferredHolder<Block, Block> ALLOY_JELLY_MEDLEY = BLOCKS.register("alloy_jelly_medley",
            () -> new AlloyJellyMedleyBlock(BlockBehaviour.Properties.of().strength(2.0F).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
