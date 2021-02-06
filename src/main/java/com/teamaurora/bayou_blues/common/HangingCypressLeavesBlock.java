package com.teamaurora.bayou_blues.common;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;

public class HangingCypressLeavesBlock extends Block implements IForgeShearable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0, 4.0, 1.0, 15.0, 16.0, 15.0);

    public HangingCypressLeavesBlock(Block.Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean moving) {
        if (world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
            world.removeBlock(pos, false);
        }
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
        return true;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Block block = world.getBlockState(pos.up()).getBlock();
        if (block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.LEAVES)) {
            return getDefaultState();
        } else {
            return null;
        }
    }
}
