package com.rumaruka.lc.common.events;

import com.google.common.collect.Lists;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber
public class LightningTransformEvent {

    @SubscribeEvent
    public static void onTransformRecipe(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();


        if (!level.isClientSide() && entity instanceof ItemEntity item) {

            transform(item);

        }


    }

    private static void transform(ItemEntity entity) {
        var level = entity.level();
        if (!level.isClientSide()) {
            var region = new AABB(entity.getX() - 1, entity.getY() - 1, entity.getZ() - 1, entity.getX() + 1, entity.getY() + 1, entity.getZ() + 1);
            List<ItemEntity> itemEntities = level.getEntities(null, region).stream().filter(e -> e instanceof ItemEntity && !e.isRemoved()).map(e -> (ItemEntity) e).toList();

            for (var holder : level.getRecipeManager().getAllRecipesFor(TransformRecipe.RECIPE_TYPE)) {
                var recipe = holder.value();
                if (recipe.getIngredients().isEmpty()) continue;

                List<Ingredient> missingIngredients = Lists.newArrayList(recipe.getIngredients());
                Set<ItemEntity> selectedEntities = new ReferenceOpenHashSet<>(missingIngredients.size());

                for (var itemEntity : itemEntities) {
                    final ItemStack other = itemEntity.getItem();
                    if (!other.isEmpty()) {
                        for (var it = missingIngredients.iterator(); it.hasNext(); ) {
                            Ingredient ing = it.next();
                            if (ing.test(other)) {
                                selectedEntities.add(itemEntity);
                                it.remove();
                                break;
                            }
                        }
                    }
                }

                if (missingIngredients.isEmpty()) {
                    SimpleContainer recipeContainer = new SimpleContainer(selectedEntities.size());
                    int i = 0;
                    for (var e : selectedEntities) {
                        recipeContainer.setItem(i++, e.getItem().split(1));

                        if (e.getItem().getCount() <= 0) {
                            e.discard();
                        }
                    }

                    if (!level.isClientSide()) {
                        ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), recipe.assemble(recipeContainer, level.registryAccess()));
                        itemEntity.setInvisible(true);
                        level.addFreshEntity(itemEntity);

                    }


                }

            }
        }


    }

}
