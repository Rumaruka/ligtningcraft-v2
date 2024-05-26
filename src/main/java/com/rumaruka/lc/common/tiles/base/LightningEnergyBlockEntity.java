package com.rumaruka.lc.common.tiles.base;

import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.misc.LCStorageUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class LightningEnergyBlockEntity extends BaseContainerBlockEntity {
    public int maxEnergy;
    private static LEStorage storage;


    public LightningEnergyBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int maxEnergy) {

        super(pType, pPos, pBlockState);
        this.maxEnergy = maxEnergy;
        storage = LCStorageUtils.getMachine(maxEnergy);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AbstractFurnaceBlockEntity pBlockEntity) {
        if (!pLevel.isClientSide()) {
            int le = storage.getLE();


        }
    }


}
