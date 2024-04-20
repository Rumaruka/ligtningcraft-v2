package com.rumaruka.lc.common.recipes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
public record TransformIngredient (Ingredient ingredient, int count) implements Predicate<ItemStack> {

    public static final Codec<TransformIngredient> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(TransformIngredient::ingredient),
            Codec.INT.fieldOf("count").forGetter(TransformIngredient::count)
    ).apply(instance, TransformIngredient::new));

    /**
     * An empty ingredient.
     */
    public static final TransformIngredient EMPTY = new TransformIngredient(Ingredient.EMPTY, 0);

    public static TransformIngredient of() {
        return EMPTY;
    }

    public static TransformIngredient of(ItemLike... items) {
        return of(Arrays.stream(items).map(ItemStack::new));
    }

    public static TransformIngredient of(int count, ItemLike... items) {
        return of(count, Arrays.stream(items).map(ItemStack::new));
    }

    public static TransformIngredient of(ItemStack... stacks) {
        return of(Arrays.stream(stacks));
    }

    public static TransformIngredient of(int count, ItemStack... stacks) {
        return of(count, Arrays.stream(stacks));
    }

    public static TransformIngredient of(Stream<ItemStack> stacks) {
        return of(1, stacks);
    }

    public static TransformIngredient of(int count, Stream<ItemStack> stacks) {
        return new TransformIngredient(Ingredient.of(stacks), count);
    }

    public static TransformIngredient of(TagKey<Item> tag) {
        return of(1, tag);
    }

    public static TransformIngredient of(int count, TagKey<Item> tag) {
        return new TransformIngredient(Ingredient.fromValues(Stream.of(new Ingredient.TagValue(tag))), count);
    }

    public static TransformIngredient of(Ingredient ingredient) {
        return new TransformIngredient(ingredient, 1);
    }

    public static TransformIngredient of(int count, Ingredient ingredient) {
        return new TransformIngredient(ingredient, count);
    }

    public List<ItemStack> getItems() {
        ItemStack[] matchingStacks = ingredient.getItems();
        for (ItemStack matchingStack : matchingStacks) {
            matchingStack.setCount(count);
        }
        return List.of(matchingStacks);
    }

    @Override
    public boolean test(@Nullable ItemStack itemStack) {
        return ingredient.test(itemStack) && itemStack.getCount() >= count;
    }

    public JsonElement toJson() {
        return Util.getOrThrow(CODEC.encodeStart(JsonOps.INSTANCE, this), IllegalStateException::new);
    }

    public static TransformIngredient fromNetwork(FriendlyByteBuf buffer) {
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        return new TransformIngredient(ingredient, buffer.readShort());
    }

    public void toNetwork(FriendlyByteBuf buffer) {
        ingredient.toNetwork(buffer);
        buffer.writeShort(count());
    }
}
