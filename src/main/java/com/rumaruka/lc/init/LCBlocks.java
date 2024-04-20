package com.rumaruka.lc.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCBlocks {

    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks( MODID);

    public static void setup(IEventBus bus) {
        BLOCKS.register(bus);

    }


   // public static final DeferredBlock<CoalGrinderBlock> COAL_GRINDER = BLOCKS.register("coal_grinder", ()->new CoalGrinderBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(3.5f, 2.5f).lightLevel(SGBlocks.litBlockEmission(13)).noCollission()));
}
