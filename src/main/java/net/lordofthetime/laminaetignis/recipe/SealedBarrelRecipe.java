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

import java.util.Arrays;

public class SealedBarrelRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final FluidStack fluid;
    private final FluidStack fluidOutput;
    private final int time;

    public SealedBarrelRecipe(ResourceLocation id, Ingredient input, ItemStack output, FluidStack fluid, FluidStack fluidOutput, int time) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.fluid = fluid;
        this.fluidOutput = fluidOutput;
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
        return fluid.copy();
    }

    public int getTime(){
        return time;
    }

    public ItemStack getOutput(){
        return output.copy();
    }
    public FluidStack getFluidOutput() {
        return fluidOutput.copy();
    }

    public static class Serializer implements RecipeSerializer<SealedBarrelRecipe> {

        public static final SealedBarrelRecipe.Serializer INSTANCE = new SealedBarrelRecipe.Serializer();
        public static final ResourceLocation ID = ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"sealed_barrel");

        @Override

        public SealedBarrelRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.get("input"), true);

            ItemStack output = json.has("output") ?
                    ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output")) : ItemStack.EMPTY;

            JsonObject fluidObj = json.getAsJsonObject("fluid");
            ResourceLocation fluidId = ResourceLocation.tryParse(fluidObj.get("fluid").getAsString());
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidId);
            int amount = fluidObj.get("amount").getAsInt();
            FluidStack fluidStack = new FluidStack(fluid, amount);

            int time = json.get("time").getAsInt();

            FluidStack fluidStack1;
            JsonObject fluidOutputObj = json.has("fluidOutput") ? json.getAsJsonObject("fluidOutput") : null;
            if(fluidOutputObj == null){
                fluidStack1 = FluidStack.EMPTY;
            }
            else{
                fluidStack1 = new FluidStack(
                        ForgeRegistries.FLUIDS.getValue(ResourceLocation.tryParse(fluidOutputObj.get("fluid").getAsString())),
                        fluidOutputObj.get("amount").getAsInt());
            }
            return new SealedBarrelRecipe(id,input,output,fluidStack, fluidStack1, time);
        }

        @Override
        public SealedBarrelRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(buf.readResourceLocation());
            int amount = buf.readInt();
            Fluid fluid1 = ForgeRegistries.FLUIDS.getValue(buf.readResourceLocation());
            int amount1 = buf.readInt();
            FluidStack fluidStack = new FluidStack(fluid, amount);
            FluidStack fluidStack1 = new FluidStack(fluid1, amount1);
            int time = buf.readInt();

            return new SealedBarrelRecipe(id, input, output, fluidStack,fluidStack1,time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SealedBarrelRecipe recipe) {
            recipe.input.toNetwork(buf);
            buf.writeItem(recipe.output);
            buf.writeResourceLocation(ForgeRegistries.FLUIDS.getKey(recipe.fluid.getFluid()));
            buf.writeInt(recipe.fluid.getAmount());
            buf.writeResourceLocation(ForgeRegistries.FLUIDS.getKey(recipe.fluidOutput.getFluid()));
            buf.writeInt(recipe.fluidOutput.getAmount());
            buf.writeInt(recipe.time);
        }
    }

    public static class Type implements RecipeType<SealedBarrelRecipe>{
        public static final SealedBarrelRecipe.Type INSTANCE = new SealedBarrelRecipe.Type();
        public static final String ID = "sealed_barrel";

    }
}
