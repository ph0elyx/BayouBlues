package com.teamaurora.bayou_blues.common.block;

import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class DoubleCypressKneeBlock extends Block implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    protected static final VoxelShape SHAPE_BOTTOM = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    protected static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape SHAPE_TOP_COLLISION = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 9.0D, 15.0D);
    private final Supplier<Block> block;
    private final boolean strippable; // this code is fucking ABYSMAL

    public DoubleCypressKneeBlock(Supplier<Block> block, boolean strippable, AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER));
        this.block = block;
        this.strippable = strippable;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(HALF) == DoubleBlockHalf.LOWER ? SHAPE_BOTTOM : SHAPE_TOP;
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(HALF) == DoubleBlockHalf.LOWER ? SHAPE_BOTTOM : SHAPE_TOP_COLLISION;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if (state.get(HALF) != DoubleBlockHalf.UPPER) {
            return worldIn.getBlockState(pos.down()).isSolid();
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            if (!(state.getBlock() instanceof DoubleCypressKneeBlock)) return worldIn.getBlockState(pos.down()).isSolid();
            return blockstate.getBlock() instanceof DoubleCypressKneeBlock && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public void placeAt(IWorld worldIn, BlockPos pos, int flags) {
        worldIn.setBlockState(pos, this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), flags);
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), flags);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
            if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.isIn(this) && facingState.get(HALF) != doubleblockhalf) {
                if (stateIn.get(WATERLOGGED)) {
                    worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
                }
                return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
            } else {
                return Blocks.AIR.getDefaultState();
            }
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote) {
            if (player.isCreative()) {
                removeBottomHalf(worldIn, pos, state, player);
            } else {
                spawnDrops(state, worldIn, pos, (TileEntity)null, player, player.getHeldItemMainhand());
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }

    protected static void removeBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.get(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.down();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockState(blockpos, blockstate.get(WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }

    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate1 = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());

        if (blockstate1.isValidPosition(iworldreader, blockpos)) {
            return blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) ? super.getStateForPlacement(context).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER) : null;
        }

        return null;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(WATERLOGGED, worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER), 3);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(WATERLOGGED, HALF));
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItem(hand);
        if (strippable && stack.getItem() instanceof AxeItem) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote) {
                if (state.get(HALF) == DoubleBlockHalf.LOWER) {
                    world.setBlockState(pos, block.get().getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)).with(HALF, DoubleBlockHalf.LOWER), 51);
                    world.setBlockState(pos.up(), block.get().getDefaultState().with(WATERLOGGED, world.getBlockState(pos.up()).get(WATERLOGGED)).with(HALF, DoubleBlockHalf.UPPER), 51);
                } else {
                    world.setBlockState(pos, block.get().getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)).with(HALF, DoubleBlockHalf.UPPER), 51);
                    world.setBlockState(pos.down(), block.get().getDefaultState().with(WATERLOGGED, world.getBlockState(pos.down()).get(WATERLOGGED)).with(HALF, DoubleBlockHalf.LOWER), 51);
                }

                stack.damageItem(1, player, (playerIn) -> {
                    playerIn.sendBreakAnimation(hand);
                });
            }

            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }
}
