package com.teamaurora.bayou_blues.common.block;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import java.util.Random;
import java.util.function.Supplier;

public class LilyFlowerBlock extends LilyPadBlock {
    protected static final VoxelShape LILY_FLOWER_AABB = Block.makeCuboidShape(3.0D, 1.5D, 3.0D, 13.0D, 13.D, 13.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.or(LILY_PAD_AABB, LILY_FLOWER_AABB);
    private final Supplier<Item> item;

    public LilyFlowerBlock(Supplier<Item> item, Properties builder) {
        super(builder);
        this.item = item;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return LILY_PAD_AABB;
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.item.get());
    }

    public static Block getRandomLily(Random rand) {
        int type = rand.nextInt(8);
        switch (type) {
            case 0:
                return BayouBluesBlocks.LIGHT_GRAY_LILY.get();
            case 1:
                return BayouBluesBlocks.WHITE_LILY.get();
            case 2:
                return BayouBluesBlocks.CYAN_LILY.get();
            case 3:
                return BayouBluesBlocks.BLUE_LILY.get();
            case 4:
                return BayouBluesBlocks.LIGHT_BLUE_LILY.get();
            case 5:
                return BayouBluesBlocks.MAGENTA_LILY.get();
            case 6:
                return BayouBluesBlocks.PINK_LILY.get();
            case 7:
                return BayouBluesBlocks.PURPLE_LILY.get();
        }
        return null;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        FluidState fluidstate = worldIn.getFluidState(pos.down());
        FluidState fluidstate1 = worldIn.getFluidState(pos);
        return (fluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getFluid() == Fluids.EMPTY;
    }
}
