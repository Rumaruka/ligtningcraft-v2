package com.rumaruka.lc.common.items.tools.electro;

import com.rumaruka.lc.common.cap.ILEStorage;
import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.common.items.tools.base.ILETools;
import com.rumaruka.lc.init.LCAttachment;
import com.rumaruka.lc.init.LCCapability;
import com.rumaruka.lc.misc.LCStorageUtils;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class LCItemPickaxe extends PickaxeItem   {

    private static final LEStorage storage =  LCStorageUtils.getTools();

    public LCItemPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);

    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {


        LCUtils.tooltipLE(pTooltipComponents, storage);


    }


    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        //  var storage = getLeEnergy();
        int finalUsed = Math.max(0, amount);

        storage.usingLE(finalUsed);

        return 0;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Color.BLUE.getRGB();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        //  var storage = getLeEnergy();
        var cap = stack.getCapability(LCCapability.LEEnergy.ITEM);
        if (cap == null) {
            return false;
        }
        return cap.getLE() < cap.getMaxLE();

    }

    @Override
    public int getBarWidth(ItemStack stack) {
        //  var storage = getLeEnergy();




        return Math.min(13 * storage.getLE() / storage.getMaxLE(), 13);

    }


    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {


        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                boolean b = pLevel.getGameTime() % 200 == 0;
                boolean isCreative = player.getAbilities().instabuild;

                storage.additionLE(100);
                System.out.println(storage.getLE());
                if (b && !isCreative) {
                    storage.additionLE(100);

                   // storage.usingLE(10);
                }
//                if (isCreative) {
//                    storage.setLE(0);
//                }
            }

        }


    }


    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        //  var storage =getLeEnergy();
        if (storage != null && storage.getLE() == 0) {
            return 0;
        }
        return super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide()) {
            //  var storage = getLeEnergy();

            if (storage.getLE() > 1) {
                storage.usingLE(100);
                return true;
            }
        }


        return true;
    }



}
