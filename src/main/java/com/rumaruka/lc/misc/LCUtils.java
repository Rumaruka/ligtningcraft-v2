package com.rumaruka.lc.misc;


import com.mojang.datafixers.kinds.IdF;
import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.init.LCCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LCUtils {

    public static void lightning(Level level,Player player) {
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);

        if (bolt!=null){
            bolt.setPos(player.getX(), player.getEyeY()-0.5, player.getZ());
            level.addFreshEntity(bolt);
        }

    }
    public static void damageStack(int damage, @Nonnull ItemStack stack, LivingEntity user, EquipmentSlot slot) {
        if (!stack.isEmpty()){
            stack.hurtAndBreak(damage, user,livingEntity -> {
                livingEntity.broadcastBreakEvent(slot);
            });
        }


    }


    public static void tooltipLE( List<Component> pTooltipComponentsm, LEStorage le){

        pTooltipComponentsm.add(Component.translatable("tooltip.lc.le").append(le.getLE() + "/" + le.getMaxLE()));
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(LightningCraft.MODID, path);
    }


}
