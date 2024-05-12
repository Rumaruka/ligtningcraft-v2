package com.rumaruka.lc.common.cap;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class LEStorage implements ILEStorage, INBTSerializable<Tag> {
    protected int sizeLE;
    protected int capacity;

    public LEStorage(int sizeLE, int capacity) {
        this.sizeLE = sizeLE;
        this.capacity = capacity;
    }
    public LEStorage( int capacity) {
       this(0,capacity);
    }

    @Override
    public @UnknownNullability Tag serializeNBT() {
        return IntTag.valueOf(getLE());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.sizeLE = intNbt.getAsInt();
    }

    @Override
    public int getMaxLE() {
        return capacity;
    }

    @Override
    public int getLE() {
        return sizeLE;
    }

    @Override
    public int additionLE(int amount) {
        int leReceive = Math.min(capacity-sizeLE, amount);
        sizeLE += leReceive;
        return leReceive;
    }

    @Override
    public int usingLE(int amount) {
        int leReceive = Math.min(sizeLE, amount);
        sizeLE -= leReceive;
        return leReceive;
    }

    @Override
    public boolean hasLE() {
        return sizeLE > 0;
    }
}
