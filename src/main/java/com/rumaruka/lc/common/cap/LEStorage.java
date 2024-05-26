package com.rumaruka.lc.common.cap;

import com.rumaruka.lc.common.config.LCConfig;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class LEStorage implements ILEStorage, INBTSerializable<Tag> {
    protected int sizeLE;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public LEStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }


    public LEStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public LEStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public LEStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.sizeLE = Math.max(0, Math.min(capacity, energy));
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
        int energyReceived = Math.min(capacity - sizeLE, Math.min(this.maxReceive, amount));
        sizeLE += energyReceived;
        return energyReceived;
    }

    @Override
    public int usingLE(int amount) {
        int leReceive =Math.min(sizeLE, Math.min(this.maxExtract, amount));
        sizeLE -= leReceive;
        return leReceive;
    }

    public int setLE(int amount) {
      return   this.sizeLE=amount;
    }

    @Override
    public boolean hasLE() {
        return sizeLE > 0;
    }
}
