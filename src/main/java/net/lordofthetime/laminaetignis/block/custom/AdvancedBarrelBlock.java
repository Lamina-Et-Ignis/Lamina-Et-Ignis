    package net.lordofthetime.laminaetignis.block.custom;

    import net.lordofthetime.laminaetignis.block.entity.AdvancedBarrelBlockEntity;
    import net.lordofthetime.laminaetignis.block.entity.ModBlockEntities;
    import net.minecraft.core.BlockPos;
    import net.minecraft.server.level.ServerPlayer;
    import net.minecraft.world.InteractionHand;
    import net.minecraft.world.InteractionResult;
    import net.minecraft.world.entity.player.Player;
    import net.minecraft.world.item.ItemStack;
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
    import net.minecraft.world.level.block.state.properties.BooleanProperty;
    import net.minecraft.world.phys.BlockHitResult;
    import net.minecraft.world.phys.shapes.CollisionContext;
    import net.minecraft.world.phys.shapes.VoxelShape;
    import net.minecraftforge.common.capabilities.ForgeCapabilities;
    import net.minecraftforge.fluids.FluidStack;
    import net.minecraftforge.fluids.capability.IFluidHandler;
    import net.minecraftforge.network.NetworkHooks;
    import org.jetbrains.annotations.Nullable;

    import javax.swing.text.StyledEditorKit;

    public class AdvancedBarrelBlock extends BaseEntityBlock {

        public static final BooleanProperty SEALED = BooleanProperty.create("sealed");

        public AdvancedBarrelBlock(Properties properties) {
            super(properties);
            this.registerDefaultState(this.stateDefinition.any().setValue(SEALED, false));
        }
        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(SEALED);
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
        public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean moved) {
            if (oldState.getBlock() != newState.getBlock()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if(blockEntity instanceof AdvancedBarrelBlockEntity barrel){
                    barrel.drops(); // only drop if actually replacing with a different block
                }
            }
            super.onRemove(oldState, level, pos, newState, moved);
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
