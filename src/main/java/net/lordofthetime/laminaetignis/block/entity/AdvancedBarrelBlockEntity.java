package net.lordofthetime.laminaetignis.block.entity;

import net.lordofthetime.laminaetignis.gui.menu.AdvancedBarrelMenu;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.network.ModMessages;
import net.lordofthetime.laminaetignis.network.PacketSyncFluidClient;
import net.lordofthetime.laminaetignis.network.PacketToggleSealClient;
import net.lordofthetime.laminaetignis.recipe.SealedBarrelRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.lordofthetime.laminaetignis.block.custom.AdvancedBarrelBlock.SEALED;


public class AdvancedBarrelBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            updateClient();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            // 🔒 Block ALL insertions when sealed
            if (sealed) return false;

            // Slot 0: only fluid containers
            if (slot == LIQUID_INPUT_SLOT) {
                return stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
            }

            // Slot 1 and 3: output only
            if (slot == LIQUID_OUTPUT_SLOT || slot == ITEM_OUTPUT_SLOT) {
                return false;
            }

            // Slot 2: normal slot
            return true;
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            // 🔒 Block insertion logic too
            if (sealed) return stack;
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            // 🔒 Block extraction logic
            if (sealed) return ItemStack.EMPTY;
            return super.extractItem(slot, amount, simulate);
        }
    };

    private static  final int LIQUID_INPUT_SLOT = 0;
    private static  final int LIQUID_OUTPUT_SLOT = 1;

    private static  final int ITEM_INPUT_SLOT = 2;
    private static  final int ITEM_OUTPUT_SLOT = 3;

    private final ContainerData data;

    public Boolean sealed = false;

    private int progress = 0;
    private int maxProgress = 80;

    private final FluidTank fluidTank = new FluidTank(10000);

    private final IFluidHandler fluidHandler = new IFluidHandler() {

        @Override
        public int getTanks() {
            return 1;
        }

        @Override
        public @NotNull FluidStack getFluidInTank(int tank) {
            return fluidTank.getFluid();
        }

        @Override
        public int getTankCapacity(int tank) {
            return fluidTank.getCapacity();
        }

        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
            return true;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return fluidTank.fill(resource,action);
        }

        @Override
        public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
            return fluidTank.drain(resource,action);
        }

        @Override
        public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
            return fluidTank.drain(maxDrain,action);
        }
    };

    private final IFluidHandlerItem fluidHandlerItem = new IFluidHandlerItem() {
        @Override
        public @NotNull ItemStack getContainer() {
            return itemHandler.getStackInSlot(LIQUID_INPUT_SLOT);
        }

        @Override
        public int getTanks() {
            return fluidHandler.getTanks();
        }

        @Override
        public @NotNull FluidStack getFluidInTank(int tank) {
            return fluidHandler.getFluidInTank(tank);
        }

        @Override
        public int getTankCapacity(int tank) {
            return fluidHandler.getTankCapacity(tank);
        }

        @Override
        public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
            return fluidHandler.isFluidValid(tank,stack);
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return fluidHandler.fill(resource,action);
        }

        @Override
        public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
            return fluidHandler.drain(resource,action);
        }

        @Override
        public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
            return fluidHandler.drain(maxDrain,action);
        }
    };

    LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
    LazyOptional<IFluidHandlerItem> lazyFluidHandlerItem = LazyOptional.empty();

    public AdvancedBarrelBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ADVANCED_BARREL_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                  case 0 -> AdvancedBarrelBlockEntity.this.progress;
                  case 1 -> AdvancedBarrelBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AdvancedBarrelBlockEntity.this.progress = pValue;
                    case 1 -> AdvancedBarrelBlockEntity.this.maxProgress = pValue;
                }
            }
            @Override
            public int getCount() {
                return 2;
            }};
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyFluidHandler = LazyOptional.of(() -> fluidHandler);
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyFluidHandlerItem = LazyOptional.of(() -> fluidHandlerItem);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyFluidHandler.invalidate();
        lazyItemHandler.invalidate();
        lazyFluidHandlerItem.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        if(cap == ForgeCapabilities.FLUID_HANDLER){
            return lazyFluidHandler.cast();
        }
        if(cap ==ForgeCapabilities.FLUID_HANDLER_ITEM){
            return lazyFluidHandlerItem.cast();
        }
        return super.getCapability(cap, side);
    }


    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Wooden Barrel");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AdvancedBarrelMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory",itemHandler.serializeNBT());
        tag.put("liquid", fluidTank.writeToNBT(new CompoundTag()));
        tag.putInt("progress",progress);
        tag.putInt("maxProgress",maxProgress);
        tag.putBoolean("sealed",sealed);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
        fluidTank.readFromNBT(tag.getCompound("liquid"));
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("maxProgress");
        setSealed(tag.getBoolean("sealed"));
        super.load(tag);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if(sealed){
            Optional<SealedBarrelRecipe> recipe = getRecipe(itemHandler.getStackInSlot(ITEM_INPUT_SLOT));
            if(hasRecipe(recipe)){
                if(progress == 0){
                    maxProgress = recipe.get().getTime();
                }
                progress++;
                if(hasProgressFinished()) {
                    craft(recipe.get());
                    progress = 0;
                    setSealed(false);
                }
            }
            else{
                progress = 0;
            }
            updateClient();
            return;
        }
        progress = 0;
        fluidOperations();
        updateClient();
    }

    public void setSealed(boolean sealed) {
        if (this.sealed != sealed) { // only react if it changed
            this.sealed = sealed;

            // update blockstate so the model changes
            if (level != null) {
                BlockState currentState = level.getBlockState(worldPosition);
                level.getBlockEntity(worldPosition, ModBlockEntities.ADVANCED_BARREL_BLOCK_ENTITY.get()).ifPresent(barrel -> {
                    level.setBlockAndUpdate(worldPosition, currentState.setValue(SEALED, sealed));
                });
            }

            // optionally update client visuals / fluid sync
            updateClient();
        }
    }

    private  Boolean hasRecipe(Optional<SealedBarrelRecipe> recipe){
        return recipe.isPresent() && recipe.get().fluidMatches(fluidTank) && itemHandler.getStackInSlot(ITEM_OUTPUT_SLOT).isEmpty();
    }

    private Optional<SealedBarrelRecipe> getRecipe(ItemStack input){
        SimpleContainer fake = new SimpleContainer(1);
        fake.setItem(0, input);
        return level.getRecipeManager()
                .getRecipeFor(SealedBarrelRecipe.Type.INSTANCE, fake, level);
    }
    public void updateClient() {
        setChanged();
        if (level != null && !level.isClientSide) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 3);
            ModMessages.sendToTracking(level, worldPosition, new PacketSyncFluidClient(worldPosition, fluidTank.getFluid(), level));
            ModMessages.sendToTracking(level, worldPosition, new PacketToggleSealClient(worldPosition, sealed));

        }
    }

    public FluidTank getFluidTank(){
        return this.fluidTank;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }
    private void craft(SealedBarrelRecipe recipe) {
        ItemStack input = itemHandler.getStackInSlot(ITEM_INPUT_SLOT);

        int fluidPerCraft = recipe.getFluid().getAmount();
        int maxFromFluid = fluidTank.getFluidAmount() / fluidPerCraft;

        int inputPerCraft = 1; // adjust if your recipe needs more than 1 input
        int maxFromInput = input.getCount() / inputPerCraft;

        // How many times we can craft this recipe
        int maxCraftable = Math.min(maxFromFluid, maxFromInput);

        if (maxCraftable <= 0) return; // nothing to craft

        input.shrink(maxCraftable * inputPerCraft);
        itemHandler.setStackInSlot(ITEM_INPUT_SLOT, input);

        fluidTank.drain(fluidPerCraft * maxCraftable, IFluidHandler.FluidAction.EXECUTE);

        ItemStack resultStack = recipe.getOutput().copy();
        resultStack.setCount(resultStack.getCount() * maxCraftable);

        itemHandler.setStackInSlot(ITEM_OUTPUT_SLOT, resultStack);
    }
    private void fluidOperations(){
        ItemStack fluidInput = itemHandler.getStackInSlot(LIQUID_INPUT_SLOT);
        ItemStack fluidOutput = itemHandler.getStackInSlot(LIQUID_OUTPUT_SLOT);

        if (!fluidInput.isEmpty()) {
            ItemStack singleInput = fluidInput.copy();
            singleInput.setCount(1);

            singleInput.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
                FluidStack contained = handler.getFluidInTank(0);
                ItemStack resultContainer = ItemStack.EMPTY;
                // --- Check if output is at max stack size while not being empty
                if(fluidOutput.getMaxStackSize() == fluidOutput.getCount() && !fluidOutput.isEmpty() ||
                        fluidInput.getItem() == fluidOutput.getItem()){
                    return;
                }
                // --- Case 1: Input item has fluid → dump into tank ---
                if (!contained.isEmpty()) {
                    int filled = fluidTank.fill(contained, IFluidHandler.FluidAction.EXECUTE);
                    if (filled > 0) {
                        handler.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                        resultContainer = handler.getContainer();
                    }
                }
                // --- Case 2: Input item empty → fill from tank ---
                else if (!fluidTank.getFluid().isEmpty()) {
                    FluidStack available = fluidTank.getFluid();
                    int filled = handler.fill(available, IFluidHandler.FluidAction.EXECUTE);
                    if (filled > 0) {
                        fluidTank.drain(filled, IFluidHandler.FluidAction.EXECUTE);
                        resultContainer = handler.getContainer();
                    }
                }
                // --- Handle moving the result container to output slot ---
                if (!resultContainer.isEmpty()) {
                    if (fluidOutput.isEmpty()) {
                        itemHandler.setStackInSlot(LIQUID_OUTPUT_SLOT, resultContainer.copy());
                        fluidInput.shrink(1);
                    } else if (ItemHandlerHelper.canItemStacksStack(resultContainer, fluidOutput)
                            && fluidOutput.getCount() < fluidOutput.getMaxStackSize()) {
                        fluidOutput.grow(1);
                        fluidInput.shrink(1);
                    }
                }
            });
        }
    }
}
