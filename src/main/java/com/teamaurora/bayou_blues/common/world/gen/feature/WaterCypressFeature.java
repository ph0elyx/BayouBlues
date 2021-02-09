package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.*;

public class WaterCypressFeature extends Feature<BaseTreeFeatureConfig> {
    public WaterCypressFeature(Codec<BaseTreeFeatureConfig> config) {
        super(config);
    }

    private class DirectionalBlockPos {
        public BlockPos pos;
        public Direction direction;

        public DirectionalBlockPos(BlockPos p, Direction a) {
            pos = p;
            direction = a;
        }
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos posIn, BaseTreeFeatureConfig config) {
        int height = rand.nextInt(5) + 12;
        boolean bald = rand.nextInt(15) == 0;
        if (posIn.getY() <= 0 || posIn.getY() + height > worldIn.getHeight() - 1) {
            return false;
        }
        int surfaceY = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, posIn).getY();
        int waterY = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, posIn).getY();
        if (waterY <= surfaceY) {
            return false;
        }
        BlockPos position = new BlockPos(posIn.getX(), waterY, posIn.getZ());
        BlockPos bottom = new BlockPos(posIn.getX(), surfaceY, posIn.getZ());
        if (!isAirOrWaterOrLeaves(worldIn, bottom)) {
            return false;
        }

        List<DirectionalBlockPos> logs = new ArrayList<>();
        List<BlockPos> leaves = new ArrayList<>();

        for (int i = 0; i <= waterY - surfaceY; i++) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (i == 0 && worldIn.getFluidState(bottom.add(x, -1, z)).getFluid() == Fluids.WATER) {
                        return false;
                    }
                    logs.add(new DirectionalBlockPos(bottom.add(x, i, z), Direction.UP));
                }
            }
        }
        for (int i = 1; i <= height; i++) {
            if (i <= 2) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 || z == 0) {
                            logs.add(new DirectionalBlockPos(position.add(x, i, z), Direction.UP));
                        }
                    }
                }
            } else {
                logs.add(new DirectionalBlockPos(position.up(i), Direction.UP));
            }
        }
        int numBranches = rand.nextInt(4) + 1;
        if (numBranches == 4) numBranches = 2;
        for (int i = 0; i < numBranches; i++) {
            Direction dir = Direction.byHorizontalIndex(rand.nextInt(4));
            int x = rand.nextInt(height - 5) + 6;
            logs.add(new DirectionalBlockPos(position.up(x).offset(dir), dir));
            logs.add(new DirectionalBlockPos(position.up(x).offset(dir,2), dir));
            disc2H(position.up(x).offset(dir,2), leaves, rand);
            disc1(position.up(x+1).offset(dir,2), leaves);
        }
        if (!bald) {
            disc1(position.up(height - 1), leaves);
            disc3H(position.up(height), leaves, rand);
            disc2(position.up(height + 1), leaves);
        }

        List<BlockPos> leavesClean = cleanLeavesArray(leaves, logs);

        boolean flag = true;
        for (DirectionalBlockPos log : logs) {
            if (!isAirOrWaterOrLeaves(worldIn, log.pos)) {
                flag = false;
            }
        }
        if (!flag) return false;

        TreeUtil.setDirtAt(worldIn, position.down());

        for (DirectionalBlockPos log : logs) {
            TreeUtil.placeDirectionalLogAt(worldIn, log.pos, log.direction, rand, config);
        }
        for (BlockPos leaf : leavesClean) {
            TreeUtil.placeLeafAt(worldIn, leaf, rand, config);
        }


        Set<BlockPos> decSet = Sets.newHashSet();
        MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getNewBoundingBox();

        List<BlockPos> logsPos = new ArrayList<>();
        for (DirectionalBlockPos log : logs) {
            logsPos.add(log.pos);
        }

        if (!config.decorators.isEmpty()) {
            logsPos.sort(Comparator.comparingInt(Vector3i::getY));
            leavesClean.sort(Comparator.comparingInt(Vector3i::getY));
            config.decorators.forEach((decorator) -> decorator.func_225576_a_(worldIn, rand, logsPos, leavesClean, decSet, mutableBoundingBox));
        }

        return true;
    }

    private void disc1(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != 1 || Math.abs(z) != 1) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void disc2(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void disc2H(BlockPos pos, List<BlockPos> leaves, Random rand) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
                    leaves.add(pos.add(x, 0, z));
                    if (rand.nextInt(3) == 0) {
                        leaves.add(pos.add(x, -1, z));
                        if (rand.nextInt(3) == 0) {
                            leaves.add(pos.add(x, -2, z));
                        }
                    }
                }
            }
        }
    }

    private void disc3H(BlockPos pos, List<BlockPos> leaves, Random rand) {
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (Math.abs(x) != 3 || Math.abs(z) != 3) {
                    leaves.add(pos.add(x, 0, z));
                    if (rand.nextInt(3) == 0) {
                        leaves.add(pos.add(x, -1, z));
                        if (rand.nextBoolean()) {
                            leaves.add(pos.add(x, -2, z));
                        }
                    }
                }
            }
        }
    }

    private List<BlockPos> cleanLeavesArray(List<BlockPos> leaves, List<DirectionalBlockPos> logs) {
        List<BlockPos> logsPos = new ArrayList<>();
        for (DirectionalBlockPos log : logs) {
            logsPos.add(log.pos);
        }
        List<BlockPos> newLeaves = new ArrayList<>();
        for (BlockPos leaf : leaves) {
            if (!logsPos.contains(leaf)) {
                newLeaves.add(leaf);
            }
        }
        return newLeaves;
    }

    public static boolean isAirOrWater(IWorldGenerationBaseReader world, BlockPos pos) {
        if (!(world instanceof IBlockReader)) {
            return world.hasBlockState(pos, BlockState::isAir) || world.hasBlockState(pos, state -> state.getFluidState().isTagged(FluidTags.WATER));
        } else {
            return world.hasBlockState(pos, state -> state.isAir((IBlockReader) world, pos)) || world.hasBlockState(pos, state -> state.getFluidState().isTagged(FluidTags.WATER));
        }
    }

    public static boolean isAirOrWaterOrLeaves(IWorldGenerationBaseReader world, BlockPos pos) {
        if (world instanceof IWorldReader) {
            return world.hasBlockState(pos, state -> state.canBeReplacedByLeaves((IWorldReader) world, pos)) || world.hasBlockState(pos, state -> state.getFluidState().isTagged(FluidTags.WATER));
        }
        return world.hasBlockState(pos, (state) -> {
            return isAirOrWater(world, pos) || state.isIn(BlockTags.LEAVES);
        });
    }
}
