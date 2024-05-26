package com.rumaruka.lc.common.cap;

import net.minecraft.world.item.ItemStack;

public class LEStorageItem extends LEStorage {
    protected final ItemStack stack;

    public LEStorageItem(int capacity, ItemStack itemStack) {
        super(getMaxCapacity(itemStack, capacity), Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.stack = itemStack;
        this.sizeLE = stack.hasTag() && stack.getTag().contains("le") ? stack.getTag().getInt("le") : 0;
    }

    private static int getMaxCapacity(ItemStack stack, int capacity) {
        if (!stack.hasTag() || !stack.getTag().contains("max_le"))
            return capacity;

        return stack.getTag().getInt("max_le");
    }

    @Override
    public int additionLE(int amount) {
        return 0;
    }

    @Override
    public int usingLE(int amount) {
        int stored = this.getLE() + maxReceive;
        if (stored < 0) {
            return 0;
        }

        int a = super.usingLE(maxReceive);

        stack.getOrCreateTag().putInt("le", this.sizeLE);

        return a;
    }
}
