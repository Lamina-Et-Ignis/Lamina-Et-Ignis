package net.lordofthetime.laminaetignis.block.custom;

import net.lordofthetime.laminaetignis.block.entity.DryingRackBlockEntity;
import net.lordofthetime.laminaetignis.block.entity.ModBlockEntities;
import net.lordofthetime.laminaetignis.recipe.DryingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DryingRackBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DryingRackBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING,pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);
        switch (dir) {
            case NORTH:
            default:
                return Block.box(4,0,0,12,10,16);
            case SOUTH:
                return Block.box(4,0,0,12,10,16);
            case WEST:
                return Block.box(0,0,4,16,10,12);
            case EAST:
                return Block.box(0,0,4,16,10,12);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if(blockEntity instanceof DryingRackBlockEntity rack){
            rack.drops();
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack handItem = pPlayer.getMainHandItem();
        if(!pLevel.isClientSide){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof DryingRackBlockEntity rack){
                if(!rack.hasItem()){
                    SimpleContainer fake = new SimpleContainer(1);
                    fake.setItem(0, handItem);
                    Optional<DryingRecipe> recipe = pLevel.getRecipeManager()
                            .getRecipeFor(DryingRecipe.Type.INSTANCE, fake, pLevel);
                    if (recipe.isPresent()) {
                        DryingRecipe dryingRecipe = recipe.get();
                        rack.startDrying(dryingRecipe.getInput(),dryingRecipe.getOutput(),dryingRecipe.getDryingTime());
                        handItem.shrink(1);
                        return InteractionResult.CONSUME;
                    } else {
                        return InteractionResult.PASS;
                    }
                }else{
                    ItemStack itemStack = rack.stopDrying();
                    pPlayer.addItem(itemStack);
                }
            }
        }
        return InteractionResult.CONSUME;
    }
    @Override
    public  @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState){
        return new DryingRackBlockEntity(pPos,pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide){
            return null;
        }

        return createTickerHelper(pBlockEntityType,ModBlockEntities.DRYING_RACK_BLOCK_ENTITY.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tickServer(pLevel1,pPos,pState1));
    }
}
