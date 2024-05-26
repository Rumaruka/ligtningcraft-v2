package com.rumaruka.lc.init;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCAttachment {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    // Serialization via INBTSerializable
    public static final Supplier<AttachmentType<ItemStackHandler>> HANDLER = ATTACHMENT_TYPES.register(
            "handler", () -> AttachmentType.serializable(() -> new ItemStackHandler(1)).build());
    // Serialization via codec
    public static final Supplier<AttachmentType<Integer>> LE = ATTACHMENT_TYPES.register(
            "le", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());

public static void setup(IEventBus modBus){ATTACHMENT_TYPES.register(modBus);}

}
