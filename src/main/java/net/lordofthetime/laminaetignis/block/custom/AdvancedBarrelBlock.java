    package net.lordofthetime.laminaetignis.block.custom;

    import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
    import net.lordofthetime.laminaetignis.block.entity.ModBlockEntities;
    import net.minecraft.core.BlockPos;
    import net.minecraft.server.level.ServerPlayer;
    import net.minecraft.world.InteractionHand;
    import net.minecraft.world.InteractionResult;
    import net.minecraft.world.entity.player.Player;
    import net.minecraft.world.level.BlockGetter;
    import net.minecraft.world.level.Level;
    import net.minecraft.world.level.block.BaseEntityBlock;
    import net.minecraft.world.level.block.Block;
    import net.minecraft.world.level.block.RenderShape;
    import net.minecraft.world.level.block.entity.BlockEntity;
    import net.minecraft.world.level.block.entity.BlockEntityTicker;
    import net.minecraft.world.level.block.entity.BlockEntityType;
    import net.minecraft.world.level.block.state.BlockState;
    import net.minecraft.world.phys.BlockHitResult;
    import net.minecraft.world.phys.shapes.CollisionContext;
    import net.minecraft.world.phys.shapes.VoxelShape;
    import net.minecraftforge.network.NetworkHooks;
    import org.jetbrains.annotations.Nullable;

    public class AdvancedBarrelBlock extends BaseEntityBlock {
        public AdvancedBarrelBlock(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
            return Block.box(2,0,2,14,16,14);
        }

        @Override
        public RenderShape getRenderShape(BlockState pState) {
            return RenderShape.MODEL;
        }

        @Override
        public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
            return new AdvancedBarrelBlockEntity(pPos,pState);
        }

        @Override
        public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof AdvancedBarrelBlockEntity barrel){
                barrel.drops();
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }

        @Override
        public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
            if (!pLevel.isClientSide()) {
                BlockEntity entity = pLevel.getBlockEntity(pPos);
                if(entity instanceof AdvancedBarrelBlockEntity barrel) {
                    NetworkHooks.openScreen(((ServerPlayer)pPlayer), barrel, pPos);
                } else {
                    throw new IllegalStateException("Container provider missing for " + entity.toString());
                }
            }
            return InteractionResult.CONSUME;
        }

        @Override
        public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
            if(pLevel.isClientSide()) {
                return null;
            }
            return createTickerHelper(pBlockEntityType, ModBlockEntities.ADVANCED_BARREL_BLOCK_ENTITY.get(),
                    (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
        }
    }
