package com.rumaruka.lc.init;

import com.rumaruka.lc.common.items.materials.ElectroIron;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    public static void setup(IEventBus bus) {
        ITEMS.register(bus);

    }

    public static final DeferredItem<ElectroIron> ELECTRO_IRON_INGOT = ITEMS.register("electro_iron_ingot", () -> new ElectroIron(new Item.Properties()));

    // public static final DeferredItem<DustItem> IRON_DUST = ITEMS.register("dust_iron", DustItem::new);
    //  public static final DeferredItem<Item> GRINDERBLOCK_ITEM = ITEMS.register("coal_grinder", () -> new BlockItem(SGBlocks.COAL_GRINDER.get(), new Item.Properties()));

}
