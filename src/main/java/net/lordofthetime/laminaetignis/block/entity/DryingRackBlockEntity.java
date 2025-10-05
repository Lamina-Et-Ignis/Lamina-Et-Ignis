package net.lordofthetime.laminaetignis.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

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
        update();
    }
    public ItemStack stopDrying(){
        ItemStack finished = getStack();
        dryingItem = ItemStack.EMPTY;
        output = ItemStack.EMPTY;
        dryingTimeLeft = 0;
        update();
        return finished;
    }
    public void tickServer(Level pLevel,BlockPos pPos, BlockState pState){
        if (!dryingItem.isEmpty() && dryingTimeLeft > 0) {
            dryingTimeLeft--;
            if (dryingTimeLeft == 0) {
                update();
            }
        }
    }
    public void drops(){
        this.level.addFreshEntity(
                new ItemEntity(this.level,
                this.worldPosition.getX(),
                this.worldPosition.getY(),
                this.worldPosition.getZ(),
                        getStack()));
    }

    public ItemStack getStack(){
        return dryingTimeLeft <= 0 ? output.copy() : dryingItem.copy();
    }

    public Boolean hasItem(){
        return dryingItem != ItemStack.EMPTY;
    }

    private void update() {
        if (!this.level.isClientSide) {
            this.setChanged();
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("DryingItem", dryingItem.save(new CompoundTag()));
        tag.put("Output", output.save(new CompoundTag()));
        tag.putInt("DryingTimeLeft", dryingTimeLeft);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("DryingItem")) {
            dryingItem = ItemStack.of(tag.getCompound("DryingItem"));
        } else {
            dryingItem = ItemStack.EMPTY;
        }
        if (tag.contains("Output")) {
            output = ItemStack.of(tag.getCompound("Output"));
        } else {
            output = ItemStack.EMPTY;
        }
        dryingTimeLeft = tag.getInt("DryingTimeLeft");
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
