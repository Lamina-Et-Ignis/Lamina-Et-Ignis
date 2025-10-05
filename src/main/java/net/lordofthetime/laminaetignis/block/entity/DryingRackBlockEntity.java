package net.lordofthetime.laminaetignis.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DryingRackBlockEntity extends BlockEntity{

    private ItemStack dryingItem = ItemStack.EMPTY;
    private ItemStack output = ItemStack.EMPTY;
    private int dryingTimeLeft;

    public DryingRackBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DRYING_RACK_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void startDrying(ItemStack dryingItem, ItemStack output, int dryingTime){
        this.dryingItem = dryingItem.copy();
        this.dryingItem.setCount(1);
        this.dryingTimeLeft = dryingTime;
        this.output = output;
        setChanged();
    }
    public ItemStack stopDrying(){
        setChanged();
        ItemStack itemStack = getItem();
        dryingItem = ItemStack.EMPTY;
        output = ItemStack.EMPTY;
        return itemStack;
    }
    public void tickServer(Level pLevel,BlockPos pPos, BlockState pState){
        if (!dryingItem.isEmpty() && dryingTimeLeft > 0) {
            dryingTimeLeft--;
            if (dryingTimeLeft <= 0) {
                setChanged();
            }
        }
    }
    public void drops(){
        ItemStack drop = getItem();
        this.level.addFreshEntity(
                new ItemEntity(this.level,
                this.worldPosition.getX(),
                this.worldPosition.getY(),
                this.worldPosition.getZ(),
                        drop));
    }

    private ItemStack getItem(){
        if (dryingTimeLeft <= 0) {
            return  output;
        }
        return dryingItem;
    }

    public Boolean hasItem(){
        return dryingItem != ItemStack.EMPTY;
    }
}
