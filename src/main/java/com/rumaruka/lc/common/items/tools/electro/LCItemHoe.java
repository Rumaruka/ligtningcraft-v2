//package com.rumaruka.lc.common.items.tools.electro;
//
//import com.rumaruka.lc.common.cap.LEStorage;
//import com.rumaruka.lc.common.items.tools.base.ILETools;
//import com.rumaruka.lc.misc.LCStorageUtils;
//import com.rumaruka.lc.misc.LCUtils;
//import net.minecraft.core.BlockPos;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.item.*;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.state.BlockState;
//import org.jetbrains.annotations.Nullable;
//
//import java.awt.*;
//import java.util.List;
//import java.util.function.Consumer;
//
//public class LCItemHoe extends HoeItem implements ILETools {
//    private static final LEStorage storage =  LCStorageUtils.getTools();
//
//
//    public LCItemHoe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
//        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
//    }
//
//
//
//
//
//
//    @Override
//    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
//
//
//        LCUtils.tooltipLE(pTooltipComponents, storage);
//
//
//
//    }
//
//
//    @Override
//    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
//
//        int finalUsed = Math.max(0, amount);
//
//        storage.usingLE(finalUsed);
//
//        return 0;
//    }
//
//    @Override
//    public int getBarColor(ItemStack pStack) {
//        return Color.BLUE.getRGB();
//    }
//
//    @Override
//    public boolean isBarVisible(ItemStack pStack) {
//
//        return storage.getLE() < storage.getMaxLE();
//
//    }
//
//    @Override
//    public int getBarWidth(ItemStack stack) {
//
//
//        return Math.min(13 * storage.getLE() / storage.getMaxLE(), 13);
//
//    }
//
//
//    @Override
//    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
//
//
//        if (!pLevel.isClientSide()) {
//
//            boolean b = pLevel.getGameTime() % 100 == 0;
//
//
////            assert energy != null;
////            int le = energy.getLE();
//
//            storage.usingLE(10);
//
//
//
//            if (b) {
//                //storage.usingLE(10);  //
//            }
//        }
//
//
//    }
//    public static LEStorage getLE(){
//        return storage;
//    }
//
//    @Override
//    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
//
//        if (storage != null && storage.getLE() == 0) {
//            return 0;
//        }
//        return super.getDestroySpeed(pStack, pState);
//    }
//
//    @Override
//    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
//        if (!pLevel.isClientSide()) {
//
//
//            if ( storage.getLE() > 1) {
//                storage.usingLE(100);
//                return true;
//            }
//        }
//
//
//        return true;
//    }
//
//
//    @Override
//    public LEStorage getStorage() {
//        return storage;
//    }
//}
