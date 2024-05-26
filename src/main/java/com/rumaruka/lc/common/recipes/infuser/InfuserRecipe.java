package com.rumaruka.lc.common.recipes.infuser;

import com.rumaruka.lc.api.recipe.AbstractCustomRecipe;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class InfuserRecipe extends AbstractCustomRecipe {

    public static final RecipeType<TransformRecipe> RECIPE_TYPE = new RecipeType<TransformRecipe>() {
        @Override
        public String toString() {
            return "lc:infuser";
        }
    };

    public InfuserRecipe( String p_249518_, NonNullList<Ingredient> p_251354_, ItemStack p_252185_) {
        super(RECIPE_TYPE, p_249518_, p_251354_, p_252185_);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
