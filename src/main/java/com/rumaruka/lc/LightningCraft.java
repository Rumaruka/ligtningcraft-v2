package com.rumaruka.lc;


import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import static com.rumaruka.lc.LightningCraft.MODID;
import static com.rumaruka.lc.init.LCCapability.LE_ITEM;

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


        bus.addListener((RegisterCapabilitiesEvent event) -> {
            event.registerItem(LE_ITEM,(itemStack, context) -> new LEStorage(1000),LCItems.ELECTRO_PICKAXE_ITEM.get());
        });


        // bus.addListener(ClientSetup::registerScreens);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LCConfig.SPEC);


    }


}
