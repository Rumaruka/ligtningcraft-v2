package com.rumaruka.lc;

import com.rumaruka.lc.common.recipes.TransformRecipe;
import com.rumaruka.lc.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;

import java.util.Map;

import static com.rumaruka.lc.LightningCraft.MODID;

@Mod(MODID)
public class LightningCraft {
    public static final String MODID = "lc";


    public LightningCraft(IEventBus bus) {
        LCBlocks.setup(bus);
        LCTiles.setup(bus);
        LCRecipes.setup(bus);
        LCItems.setup(bus);
        LCMenu.setup(bus);
        LCCreativeTabs.setup(bus);


        // bus.addListener(ClientSetup::registerScreens);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LCConfig.SPEC);
        NeoForge.EVENT_BUS.addListener(this::onTransformRecipe);
    }






    //Recipes
    public void onTransformRecipe(EntityStruckByLightningEvent event) {
        LightningBolt lightning = event.getLightning();
        Level level = event.getEntity().level();
        if (!level.isClientSide() && !event.getEntity().isRemoved() && event.getEntity() instanceof ItemEntity item) {

            for (final RecipeHolder<?> holder : this.getRecipes(TransformRecipe.RECIPE_TYPE, level.getRecipeManager()).values()) {

                if (holder.value() instanceof TransformRecipe transformRecipe){

                        for (Entity entity : level.getEntities(item,new AABB(item.getBlockX(), item.getBlockY(), item.getBlockZ(),item.getBlockX(), item.getBlockY(), item.getBlockZ()))) {
                            if (entity.isRemoved() && entity instanceof  ItemEntity && entity!= item&&entity.distanceTo(item)<=2){

                                if (transformRecipe.isValid(item.getItem())) {
                                    item.kill();

                                    BlockPos blockPos = new BlockPos(item.getBlockX(), item.getBlockY(), item.getBlockZ());
                                    ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(),blockPos.getY(),blockPos.getZ(),transformRecipe.getResultItem(level.registryAccess()).copy());
                                    itemEntity.fireImmune();
                                    level.addFreshEntity(itemEntity);

                                    event.setCanceled(true);
                                    break;

                                }
                            }
                        }



                }


                }
            }
        }




    private Map<ResourceLocation, RecipeHolder<?>> getRecipes(RecipeType<?> recipeType, RecipeManager manager) {

        final Map<RecipeType<?>, Map<ResourceLocation, RecipeHolder<?>>> recipesMap = ObfuscationReflectionHelper.getPrivateValue(RecipeManager.class, manager, "recipes");

        return recipesMap.get(recipeType);
    }
}
