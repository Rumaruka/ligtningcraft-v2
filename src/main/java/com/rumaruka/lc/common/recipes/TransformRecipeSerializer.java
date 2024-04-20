package com.rumaruka.lc.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class TransformRecipeSerializer<T extends AbstractCustomRecipe> implements RecipeSerializer<T> {
   private final CookieBaker<T> factory;
   private final Codec<T> codec;

   public TransformRecipeSerializer(CookieBaker<T> p_44330_) {
      this.factory = p_44330_;
      this.codec = RecordCodecBuilder.create((p_296927_) -> {
         return p_296927_.group(ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter((p_296921_) -> {
            return p_296921_.group;
         }), Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter((p_296920_) -> {
            return p_296920_.ingredient;
         }),  ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter((p_301142_) -> {
            return p_301142_.result;



         })).apply(p_296927_, p_44330_::create);
      });
   }



    public Codec<T> codec() {
      return this.codec;
   }

   public T fromNetwork(FriendlyByteBuf p_44351_) {
      String s = p_44351_.readUtf();
      Ingredient ingredient = Ingredient.fromNetwork(p_44351_);
      ItemStack itemstack = p_44351_.readItem();

      return this.factory.create(s,  ingredient, itemstack);
   }

   public void toNetwork(FriendlyByteBuf p_44335_, T p_44336_) {
      p_44335_.writeUtf(p_44336_.group);
      p_44336_.ingredient.toNetwork(p_44335_);
      p_44335_.writeItem(p_44336_.result);

   }

   public  interface CookieBaker<T extends AbstractCustomRecipe> {
      T create(String p_44354_, Ingredient p_44355_, ItemStack p_44356_);
   }
}