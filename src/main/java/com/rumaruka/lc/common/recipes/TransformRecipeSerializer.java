package com.rumaruka.lc.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.stream.IntStream;

public class TransformRecipeSerializer<T extends AbstractCustomRecipe> implements RecipeSerializer<T> {
    private final CookieBaker<T> factory;
    private final Codec<T> codec;

    public TransformRecipeSerializer(CookieBaker<T> p_44330_) {
        this.factory = p_44330_;
        this.codec = RecordCodecBuilder.create((p_296927_) -> {
            return p_296927_.group(ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter((p_296921_) -> {
                        return p_296921_.group;
                    }),
                    Ingredient.CODEC_NONEMPTY.listOf()


                            .fieldOf("base")

                            .flatXmap(ingredients -> {
                                return DataResult
                                        .success(NonNullList.of(Ingredient.EMPTY, ingredients.toArray(Ingredient[]::new)));
                            }, DataResult::success)
                            .forGetter(t -> t.ingredients),


                    ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter((p_301142_) -> {
                        return p_301142_.result;


                    })


            ).apply(p_296927_, p_44330_::create);
        });
    }


    public Codec<T> codec() {
        return this.codec;
    }


    public T fromNetwork(FriendlyByteBuf p_44351_) {
        String s = p_44351_.readUtf();
        NonNullList<Ingredient> ingredients = NonNullList.withSize(p_44351_.readInt(),Ingredient.EMPTY);
        IntStream.range(0, ingredients.size()).forEach(i -> ingredients.set(i, Ingredient.fromNetwork(p_44351_)));
        ItemStack itemstack = p_44351_.readItem();

        return this.factory.create(s, ingredients, itemstack);
    }

    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.group);
        buffer.writeVarInt(recipe.getIngredients().size());
        recipe.ingredients.forEach(ingredient -> ingredient.toNetwork(buffer));

        buffer.writeItem(recipe.result);
    }

    public interface CookieBaker<T extends AbstractCustomRecipe> {
        T create(String p_44354_, NonNullList<Ingredient> p_44355_, ItemStack p_44356_);
    }
}