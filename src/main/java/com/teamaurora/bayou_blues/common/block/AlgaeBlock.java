package com.teamaurora.bayou_blues.common.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AlgaeBlock extends BushBlock implements IGrowable {
    protected static final VoxelShape ALGAE_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public AlgaeBlock(AbstractBlock.Properties builder) {
        super(builder);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return ALGAE_AABB;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        //if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        //    return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
        FluidState fluidstate = worldIn.getFluidState(pos.down());
        FluidState fluidstate1 = worldIn.getFluidState(pos);
        return (fluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getFluid() == Fluids.EMPTY;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        for (BlockPos pos2 : BlockPos.getAllInBoxMutable(pos.add(-2, -2, -2), pos.add(2, 2, 2))) {
            if (pos.withinDistance(pos2, 2.0F)) {
                if (worldIn.getBlockState(pos2).isAir() && worldIn.getFluidState(pos2.down()).getFluid() == Fluids.WATER) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        for (BlockPos pos2 : BlockPos.getAllInBoxMutable(pos.add(-3, -3, -3), pos.add(3, 3, 3))) {
            if (pos.withinDistance(pos2, 3.0F)) {
                if (rand.nextFloat() <= 1.0F - pos2.distanceSq(pos)/18.0F) {
                    if (worldIn.getBlockState(pos2).isAir() && worldIn.getFluidState(pos2.down()).getFluid() == Fluids.WATER) {
                        worldIn.setBlockState(pos2, this.getDefaultState());
                    }
                }
            }
        }
    }
}