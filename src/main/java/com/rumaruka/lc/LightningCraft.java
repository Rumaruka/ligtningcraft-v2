package com.rumaruka.lc;


import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.common.cap.LEStorageItem;
import com.rumaruka.lc.common.config.LCConfig;
import com.rumaruka.lc.init.*;
import com.rumaruka.lc.misc.LCStorageUtils;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import static com.rumaruka.lc.LightningCraft.MODID;


@Mod(MODID)
public class LightningCraft {
    public static final String MODID = "lc";


    public LightningCraft(IEventBus bus) {
        LCBlocks.setup(bus);
        LCTiles.setup(bus);
        LCRecipes.setup(bus);
        LCItems.setup(bus);
        LCMenu.setup(bus);
        LCCreativeTabs.setup(bus);
        LCAttachment.setup(bus);
       // bus.addListener(this::registerCapabilities);
            bus.addListener(RegisterCapabilitiesEvent.class, (event -> {
                event.registerItem(LCCapability.LEEnergy.ITEM,((object, context) ->new LEStorage(LCUtils.getMaxEnergyTools())),
                        LCItems.ELECTRO_PICKAXE_ITEM.get()
                );
            }));
        // bus.addListener(ClientSetup::registerScreens);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LCConfig.SPEC);


    }


}
