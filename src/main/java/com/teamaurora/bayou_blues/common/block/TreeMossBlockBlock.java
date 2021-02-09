package com.teamaurora.bayou_blues.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IForgeShearable;

public class TreeMossBlockBlock extends Block implements IForgeShearable {

    public TreeMossBlockBlock(Properties properties) {
        super(properties);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState stateUp = worldIn.getBlockState(pos.up());
        return stateUp.isSolid() || stateUp.getBlock() == this;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

}
