package com.rumaruka.lc.init;

import com.rumaruka.lc.common.cap.ILEStorage;
import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class LCCapability {

    public static final BlockCapability<ILEStorage, Direction>LE_BLOCK = BlockCapability.createSided(LCUtils.rl("le"), ILEStorage.class);
    public static final ItemCapability<ILEStorage, Void> LE_ITEM = ItemCapability.createVoid(LCUtils.rl("le"), ILEStorage.class);



}
