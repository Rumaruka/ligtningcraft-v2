package com.rumaruka.lc.common.recipes;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public abstract class AbstractCustomRecipe implements Recipe<Container> {
    protected final RecipeType<?> type;

    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;


    public AbstractCustomRecipe(RecipeType<?> p_250197_, String p_249518_, Ingredient p_251354_, ItemStack p_252185_) {
        this.type = p_250197_;

        this.group = p_249518_;
        this.ingredient = p_251354_;
        this.result = p_252185_;

    }

    public boolean matches(Container p_43748_, Level p_43749_) {

        return this.ingredient.test(p_43748_.getItem(0));
    }

    public ItemStack assemble(Container p_43746_, RegistryAccess p_267063_) {


        return this.result.copy();
    }
    public boolean isValid (ItemStack input) {

        return this.ingredient.test(input);
    }
    public boolean canCraftInDimensions(int p_43743_, int p_43744_) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }


    public ItemStack getResultItem(RegistryAccess p_266851_) {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }


    public RecipeType<?> getType() {
        return this.type;
    }

}