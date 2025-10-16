package net.lordofthetime.laminaetignis.recipe;

import com.google.gson.JsonObject;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.Container;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.registries.ForgeRegistries;

public class SealedBarrelRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final FluidStack fluid;
    private final int time;

    public SealedBarrelRecipe(ResourceLocation id, Ingredient input, ItemStack output, FluidStack fluid, int time) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.fluid = fluid;
        this.time = time;
    }

    public boolean fluidMatches(IFluidTank fluidTank){
        return fluidTank.getFluid().isFluidEqual(fluid) && fluidTank.getFluidAmount() > fluid.getAmount();
    }

    @Override
    public boolean matches(net.minecraft.world.Container container, Level level){
        return input.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(net.minecraft.world.Container pContainer, RegistryAccess pRegistryAccess){
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight){
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess){
        return output.copy();
    }

    @Override
    public ResourceLocation getId(){
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer(){
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType(){
        return Type.INSTANCE;
    }

    public FluidStack getFluid(){
        return fluid;
    }

    public int getTime(){
        return time;
    }

    public ItemStack getOutput(){
        return output.copy();
    }

    public static class Serializer implements RecipeSerializer<SealedBarrelRecipe> {

        public static final SealedBarrelRecipe.Serializer INSTANCE = new SealedBarrelRecipe.Serializer();
        public static final ResourceLocation ID = ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"sealed_barrel");

        @Override

        public SealedBarrelRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.getAsJsonObject("input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));

            JsonObject fluidObj = json.getAsJsonObject("fluid");
            ResourceLocation fluidId = ResourceLocation.tryParse(fluidObj.get("fluid").getAsString());
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidId);
            int amount = fluidObj.get("amount").getAsInt();
            FluidStack fluidStack = new FluidStack(fluid, amount);

            int time = json.get("time").getAsInt();

            return new SealedBarrelRecipe(id,input,output,fluidStack,time);
        }

        @Override
        public SealedBarrelRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(buf.readResourceLocation());
            int amount = buf.readInt();
            FluidStack fluidStack = new FluidStack(fluid, amount);
            int time = buf.readInt();

            return new SealedBarrelRecipe(id, input, output, fluidStack,time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SealedBarrelRecipe recipe) {
            recipe.input.toNetwork(buf);
            buf.writeItem(recipe.output);
            buf.writeResourceLocation(ForgeRegistries.FLUIDS.getKey(recipe.fluid.getFluid()));
            buf.writeInt(recipe.fluid.getAmount());
            buf.writeInt(recipe.time);
        }
    }

    public static class Type implements RecipeType<SealedBarrelRecipe>{
        public static final SealedBarrelRecipe.Type INSTANCE = new SealedBarrelRecipe.Type();
        public static final String ID = "sealed_barrel";

    }
}
