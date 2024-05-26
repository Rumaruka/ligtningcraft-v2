package com.rumaruka.lc.common.recipes.crusher;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.lc.api.recipe.AbstractCustomRecipe;
import com.rumaruka.lc.api.recipe.AbstractSingleIngredientRecipe;
import com.rumaruka.lc.common.recipes.transform.TransformRecipeSerializer;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class CrusherRecipeSerializer<T extends AbstractSingleIngredientRecipe> implements RecipeSerializer<T> {
    private final CookieBaker<T> factory;
    private final Codec<T> codec;

    public CrusherRecipeSerializer(CookieBaker<T> factory, int pCookingTime) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.create(
                p_300831_ -> p_300831_.group(
                                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(p_300832_ -> p_300832_.group),
                                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(p_300833_ -> p_300833_.ingredient),
                                ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(o -> o.result),
                                Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(p_300826_ -> p_300826_.experience),
                                Codec.INT.fieldOf("cookingtime").orElse(pCookingTime).forGetter(p_300834_ -> p_300834_.cookingTime)
                        )
                        .apply(p_300831_, factory::create)
        );
    }

    @Override
    public Codec<T> codec() {
        return codec;
    }

    @Override
    public T fromNetwork(FriendlyByteBuf pBuffer) {
        String group = pBuffer.readUtf();
        Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
        ItemStack result = pBuffer.readItem();
        float f = pBuffer.readFloat();
        int i = pBuffer.readVarInt();
        return this.factory.create(group,  ingredient, result, f, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, T pRecipe) {
        pBuffer.writeUtf(pRecipe.group);
        pRecipe.ingredient.toNetwork(pBuffer);
        pBuffer.writeItem(pRecipe.result);
        pBuffer.writeFloat(pRecipe.experience);
        pBuffer.writeVarInt(pRecipe.cookingTime);
    }


    public interface CookieBaker<T extends AbstractSingleIngredientRecipe> {
        T create(String p_44354_, Ingredient p_44355_, ItemStack p_44356_,float pExperience, int pCookingTime);
    }
}
