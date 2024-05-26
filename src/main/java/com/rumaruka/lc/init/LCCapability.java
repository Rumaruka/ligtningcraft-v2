package com.rumaruka.lc.init;

import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.common.cap.ILEStorage;
import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

public class LCCapability {

    public static final class LEEnergy {
        public static final BlockCapability<ILEStorage, @Nullable Direction> BLOCK = BlockCapability.createSided(create("le"), ILEStorage.class);
        public static final EntityCapability<ILEStorage, @Nullable Direction> ENTITY = EntityCapability.createSided(create("le"), ILEStorage.class);
        public static final ItemCapability<ILEStorage, Void> ITEM = ItemCapability.createVoid(create("le"), ILEStorage.class);

        private LEEnergy() {}
    }


    private static ResourceLocation create(String path) {
        return new ResourceLocation(LightningCraft.MODID, path);
    }

}
