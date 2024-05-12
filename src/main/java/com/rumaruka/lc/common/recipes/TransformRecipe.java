package com.rumaruka.lc.common.recipes;

import com.rumaruka.lc.init.LCRecipes;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collections;
import java.util.Map;

public class TransformRecipe extends AbstractCustomRecipe{

    public static final RecipeType<TransformRecipe> RECIPE_TYPE = new RecipeType<TransformRecipe>() {
        @Override
        public String toString() {
            return "lc:transform";
        }
    };





    // Initialized by reload listener
    public static Map<ResourceLocation, TransformRecipe> recipeList = Collections.emptyMap();

    public TransformRecipe(String p_249518_, NonNullList<Ingredient> p_251354_, ItemStack p_252185_) {
        super(RECIPE_TYPE, p_249518_, p_251354_, p_252185_);
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(Items.DIAMOND);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return LCRecipes.TRANSFORM.get();
    }



}
