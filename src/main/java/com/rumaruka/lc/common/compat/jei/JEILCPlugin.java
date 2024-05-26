package com.rumaruka.lc.common.compat.jei;

import com.rumaruka.lc.common.compat.jei.api.TransformCategory;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import com.rumaruka.lc.init.LCItems;
import com.rumaruka.lc.misc.LCUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@JeiPlugin
public class JEILCPlugin implements IModPlugin {
    private static final ResourceLocation ID = LCUtils.rl( "lc");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        RecipeManager recipeManager = level.getRecipeManager();
        var transform = recipeManager.getAllRecipesFor(TransformRecipe.RECIPE_TYPE);
        registration.addRecipes(ModConts.TRANSFORM,transform);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TransformCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(LCItems.LIGHTNING.get()),ModConts.TRANSFORM);
    }
}
