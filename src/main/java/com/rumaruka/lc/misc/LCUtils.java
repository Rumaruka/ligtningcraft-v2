package com.rumaruka.lc.misc;


import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.common.config.LCConfig;
import com.rumaruka.lc.init.LCCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.common.NeoForge;

import javax.annotation.Nonnull;
import java.util.List;

public class LCUtils {

    public static void lightning(Level level, Player player) {
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);

        if (bolt != null) {
            bolt.setPos(player.getX(), player.getEyeY() - 0.5, player.getZ());
            level.addFreshEntity(bolt);
        }

    }

    public static void damageStack(int damage, @Nonnull ItemStack stack, LivingEntity user, EquipmentSlot slot) {
        if (!stack.isEmpty()) {
            stack.hurtAndBreak(damage, user, livingEntity -> {
                livingEntity.broadcastBreakEvent(slot);
            });
        }


    }
    public static void tooltipLE(List<Component> pTooltipComponents, LEStorage le) {

        pTooltipComponents.add(Component.literal(le.getLE() + "/" + le.getMaxLE()).append(" " + printLighting()));
    }

    public static void tooltipLE(List<Component> pTooltipComponents, ItemStack stack) {
        var le = stack.getCapability(LCCapability.LEEnergy.ITEM);
        if (le ==null)return;
        pTooltipComponents.add(Component.literal(le.getLE() + "/" + le.getMaxLE()).append(" " + printLighting()));
    }

    public static String printLighting() {
        return "⚡";
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(LightningCraft.MODID, path);
    }

 

    public static int getMaxEnergyTools() {
        //TODO:fix to production
        return 1_000_000;
       // return LCConfig.CAPACITRY_ELECTRO_TOOLS.get();
    }

    public static int getMaxInfuserLE() {
        //TODO:fix to production
        return 1_000_000;
        // return LCConfig.CAPACITRY_INFUSER.get();
    }
    public static Vec2 rotatePointAbout(Vec2 in, Vec2 about, double degrees) {
        double rad = degrees * Math.PI / 180.0;
        double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
        double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
        return new Vec2((float) newX, (float) newY);
    }
}
