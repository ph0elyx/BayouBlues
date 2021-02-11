package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.block.TreeMossBlock;
import com.teamaurora.bayou_blues.common.block.TreeMossBlockBlock;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class TreeMossTreeDecorator extends TreeDecorator {
    public static final Codec<TreeMossTreeDecorator> CODEC;
    public static final TreeMossTreeDecorator DECORATOR = new TreeMossTreeDecorator();

    @Override
    protected TreeDecoratorType<?> func_230380_a_() {
        return BayouBluesFeatures.HANGING_CYPRESS_LEAVES.get();
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void func_225576_a_(ISeedReader world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, MutableBoundingBox bounds) {
        for (BlockPos pos : logs) {
            if (TreeUtil.isAirOrLeaves(world, pos.down())) {
                boolean flag = true;
                int rand1 = rand.nextInt(3) + 1;
                for (int i = 0; i < rand1; i++) {
                    if (!TreeUtil.isAirOrLeaves(world, pos.down(i + 1))) {
                        flag = false;
                        break;
                    }
                }
                if (!TreeUtil.isAir(world, pos.down(rand1 + 1))) flag = false;
                if (flag) {
                    for (int i = 0; i < rand1; i++) {
                        world.setBlockState(pos.down(i + 1), BayouBluesBlocks.TREE_MOSS_BLOCK.get().getDefaultState().with(TreeMossBlockBlock.PERSISTENT, false), 3);
                    }
                    int rand2 = rand.nextInt(6) + 1;
                    for (int i = 0; i < rand2; i++) {
                        if (!world.isAirBlock(pos.down(rand1 + i + 1))) {
                            if (i > 0) {
                                world.setBlockState(pos.down(rand1 + i), BayouBluesBlocks.TREE_MOSS.get().getDefaultState(), 3);
                            }
                            break;
                        }
                        if (i == rand2 - 1) {
                            world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.TREE_MOSS.get().getDefaultState(), 3);
                        } else {
                            world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.TREE_MOSS.get().getDefaultState().with(TreeMossBlock.HALF, DoubleBlockHalf.UPPER), 3);
                        }
                    }
                }
            }
        }
        for (BlockPos pos : leaves) {
            if (rand.nextInt(6) == 0) {
                if (TreeUtil.isAirOrLeaves(world, pos.down())) {
                    boolean flag = true;
                    int rand1 = rand.nextInt(2) + 1;
                    for (int i = 0; i < rand1; i++) {
                        if (!TreeUtil.isAirOrLeaves(world, pos.down())) {
                            flag = false;
                            break;
                        }
                    }
                    if (!TreeUtil.isAir(world, pos.down(rand1 + 1))) flag = false;
                    if (flag) {
                        for (int i = 0; i < rand1; i++) {
                            world.setBlockState(pos.down(i + 1), BayouBluesBlocks.TREE_MOSS_BLOCK.get().getDefaultState().with(TreeMossBlockBlock.PERSISTENT, false), 3);
                        }
                        int rand2 = rand.nextInt(4) + 1;
                        for (int i = 0; i < rand2; i++) {
                            if (!world.isAirBlock(pos.down(rand1 + i + 1))) {
                                if (i > 0) {
                                    world.setBlockState(pos.down(rand1 + i), BayouBluesBlocks.TREE_MOSS.get().getDefaultState(), 3);
                                }
                                break;
                            }
                            if (i == rand2 - 1) {
                                world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.TREE_MOSS.get().getDefaultState(), 3);
                            } else {
                                world.setBlockState(pos.down(rand1 + i + 1), BayouBluesBlocks.TREE_MOSS.get().getDefaultState().with(TreeMossBlock.HALF, DoubleBlockHalf.UPPER), 3);
                            }
                        }
                    }
                }
            }
        }
    }
}