package com.rumaruka.lc.init;


import com.rumaruka.lc.common.recipes.TransformRecipe;
import com.rumaruka.lc.common.recipes.TransformRecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCRecipes {

    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);

    public static void setup(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);

    }

   public static final DeferredHolder<RecipeSerializer<?>, TransformRecipeSerializer<TransformRecipe>> TRANSFORM = RECIPE_SERIALIZER.register("transform", () -> new TransformRecipeSerializer<>(TransformRecipe::new));

}
