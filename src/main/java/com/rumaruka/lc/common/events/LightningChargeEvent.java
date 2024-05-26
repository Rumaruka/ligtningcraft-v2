package com.rumaruka.lc.common.events;

import com.rumaruka.lc.common.items.tools.base.ILETools;

import com.rumaruka.lc.common.items.tools.electro.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;

@Mod.EventBusSubscriber
public class LightningChargeEvent {
    @SubscribeEvent
    public static void onChargeRecipe(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (entity instanceof Player player){
                for (ItemStack stack: player.getInventory().items){
                    if (stack.getItem() instanceof ILETools ){

                        //Tools
                        //LCItemPickaxe.getLE().additionLE(100);
                       // LCItemHoe.getLE().additionLE(100);
                       // LCItemAxe.getLE().additionLE(100);
                    }
                }
            }
        }
    }
}
