package com.rumaruka.lc.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tabSimpleGrinder = CREATIVE_MODE_TABS.register("lightningcraft",() -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(Items.DIAMOND))
            .title(Component.translatable("itemGroup.lc"))
            .displayItems((p_270258_, p_259752_) ->  {
              p_259752_.accept(LCItems.ELECTRO_IRON_INGOT.get());
              p_259752_.accept(LCItems.LIGHTNING_WAND.get());
              p_259752_.accept(LCItems.ELECTRO_PICKAXE_ITEM.get());

            }).build()
    );

    public static void setup(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);

    }
}
