package com.rumaruka.lc.init;

import com.google.common.collect.Lists;
import com.rumaruka.lc.common.items.tools.LightningWand;
import com.rumaruka.lc.common.items.materials.ElectroMaterial;

import com.rumaruka.lc.common.items.tools.electro.*;

import com.rumaruka.lc.common.items.tools.electro.LCItemPickaxe;
import com.rumaruka.lc.common.items.tools.jei_items.LightningItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    public static void setup(IEventBus bus) {
        ITEMS.register(bus);

    }

    //Materials
    public static final DeferredItem<ElectroMaterial> ELECTRO_IRON_INGOT = ITEMS.register("electro_iron_ingot", () -> new ElectroMaterial(new Item.Properties()));
    public static final DeferredItem<ElectroMaterial> ELECTRO_IRON_NUGGET = ITEMS.register("electro_iron_nugget", () -> new ElectroMaterial(new Item.Properties()));
    public static final DeferredItem<ElectroMaterial> ELECTRO_IRON_DUST = ITEMS.register("electro_iron_dust", () -> new ElectroMaterial(new Item.Properties()));


    public static final DeferredItem<LightningWand> LIGHTNING_WAND = ITEMS.register("lightning_wand", () -> new LightningWand(new Item.Properties().durability(100)));
    public static final DeferredItem<LCItemPickaxe> ELECTRO_PICKAXE_ITEM = ITEMS.register("electro_pickaxe", () -> new LCItemPickaxe(Tiers.NETHERITE, 3, 3, new Item.Properties()));
   // public static final DeferredItem<LCItemAxe> ELECTRO_AXE_ITEM = ITEMS.register("electro_axe", () -> new LCItemAxe(Tiers.NETHERITE, 3, 3, new Item.Properties()));
   // public static final DeferredItem<LCItemHoe> ELECTRO_HOE_ITEM = ITEMS.register("electro_hoe", () -> new LCItemHoe(Tiers.NETHERITE, 3, 3, new Item.Properties()));

   public static final DeferredItem<LightningItem> LIGHTNING = ITEMS.register("lightning", LightningItem::new);

    // public static final DeferredItem<DustItem> IRON_DUST = ITEMS.register("dust_iron", DustItem::new);
    //  public static final DeferredItem<Item> GRINDERBLOCK_ITEM = ITEMS.register("coal_grinder", () -> new BlockItem(SGBlocks.COAL_GRINDER.get(), new Item.Properties()));


    public static ArrayList<Item> getAllItems() {
        return
                Lists.newArrayList(
                        ELECTRO_IRON_NUGGET.get(),
                        ELECTRO_IRON_DUST.get(),
                        LIGHTNING_WAND.get(),
                        ELECTRO_PICKAXE_ITEM.get(),
                        LIGHTNING.get(),

                        //ELECTRO_AXE_ITEM.get(),
                        //ELECTRO_HOE_ITEM.get(),
                        ELECTRO_IRON_INGOT.get()
                );

    }
}
