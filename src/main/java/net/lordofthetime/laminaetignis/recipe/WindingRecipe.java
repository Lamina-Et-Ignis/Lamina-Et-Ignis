package net.lordofthetime.laminaetignis.recipe;

import com.google.gson.JsonObject;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class WindingRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack input;
    private final ItemStack output;

    public WindingRecipe(ResourceLocation id, ItemStack input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }


    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.of(input));
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return pContainer.getItem(0).is(input.getItem());
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    public ResourceLocation getId(){
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WindingRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return WindingRecipe.Type.INSTANCE;
    }

    public ItemStack getInput() { return input; }
    public ItemStack getOutput() { return output; }

    public boolean matches(ItemStack stack) {
        return stack.is(input.getItem());
    }
    public static class Serializer implements RecipeSerializer<WindingRecipe> {

        public static final WindingRecipe.Serializer INSTANCE = new WindingRecipe.Serializer();
        public static final ResourceLocation ID = ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"winding");

        @Override
        public WindingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack input = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));
            return new WindingRecipe(id, input,output);
        }

        @Override
        public WindingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack input = buf.readItem();
            ItemStack output = buf.readItem();
            int time = buf.readInt();
            return new WindingRecipe(id, input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, WindingRecipe recipe) {
            buf.writeItem(recipe.getInput());
            buf.writeItem(recipe.getOutput());
        }

    }

    public static class Type implements RecipeType<WindingRecipe>{
        public static final WindingRecipe.Type INSTANCE = new WindingRecipe.Type();
        public static final String ID = "winding";

    }

}