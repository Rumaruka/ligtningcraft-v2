package com.rumaruka.lc.common.items.tools;

import com.rumaruka.lc.init.LCItems;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LightningWand extends Item {
    public LightningWand(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()){

            LCUtils.damageStack(1, itemInHand,pPlayer, EquipmentSlot.MAINHAND);
            LCUtils.lightning(pLevel,pPlayer);
            return InteractionResultHolder.success(itemInHand);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}
