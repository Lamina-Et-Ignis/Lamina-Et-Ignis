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

import java.util.List;

public class DryingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack input;
    private final ItemStack output;
    private final int dryingTime;

    public DryingRecipe(ResourceLocation id, ItemStack input, ItemStack output, int dryingTime) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.dryingTime = dryingTime;
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
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public ItemStack getInput() { return input; }
    public ItemStack getOutput() { return output; }
    public int getDryingTime() { return dryingTime; }

    public boolean matches(ItemStack stack) {
        return stack.is(input.getItem());
    }
    public static class Serializer implements RecipeSerializer<DryingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"drying");

        @Override
        public DryingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack input = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));
            int time = json.get("time").getAsInt();
            return new DryingRecipe(id, input,output,time);
        }

        @Override
        public DryingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack input = buf.readItem();
            ItemStack output = buf.readItem();
            int time = buf.readInt();
            return new DryingRecipe(id, input, output, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, DryingRecipe recipe) {
            buf.writeItem(recipe.getInput());
            buf.writeItem(recipe.getOutput());
            buf.writeInt(recipe.getDryingTime());
        }
    }

    public static class Type implements RecipeType<DryingRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "drying";

    }

}
