package com.rumaruka.lc.misc;

import com.rumaruka.lc.common.cap.LEStorage;
import com.rumaruka.lc.common.cap.LEStorageItem;
import kotlin.OptionalExpectation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import static com.rumaruka.lc.init.LCBlocks.INFUSER;

public class LCStorageUtils {



    public static LEStorage getTools(){
        return new LEStorage(LCUtils.getMaxEnergyTools());
    }
    public static LEStorage getMachine(int maxEnergy){

        return new LEStorage(maxEnergy);
       // return new LEStorage(LCUtils.getMaxInfuserLE());
    }
    public static LEStorage getItemStorage(ItemStack stack){
        return new LEStorageItem(LCUtils.getMaxEnergyTools(),stack);
    }

}
