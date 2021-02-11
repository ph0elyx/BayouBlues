package com.teamaurora.bayou_blues.common.block;

import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.Random;

public class LilyFlowerBlock extends LilyPadBlock {
    protected static final VoxelShape LILY_FLOWER_AABB = Block.makeCuboidShape(3.0D, 1.5D, 3.0D, 13.0D, 13.D, 13.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.or(LILY_PAD_AABB, LILY_FLOWER_AABB);

    public LilyFlowerBlock(Properties builder) {
        super(builder);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return LILY_PAD_AABB;
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
}
