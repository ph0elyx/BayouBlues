package com.teamaurora.bayou_blues.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.block.CypressBranchBlock;
import com.teamaurora.bayou_blues.common.block.DoubleCypressKneeBlock;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class CypressBranchTreeDecorator extends TreeDecorator {
    public static final Codec<CypressBranchTreeDecorator> CODEC;
    public static final CypressBranchTreeDecorator DECORATOR = new CypressBranchTreeDecorator();

    @Override
    protected TreeDecoratorType<?> func_230380_a_() {
        return BayouBluesFeatures.CYPRESS_BRANCH.get();
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void func_225576_a_(ISeedReader world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, MutableBoundingBox bounds) {

        for (BlockPos pos : logs) {
            if (rand.nextInt(150) == 0) {
                Direction dir = Direction.byHorizontalIndex(rand.nextInt(4));
                if (world.getBlockState(pos.offset(dir)).isAir()) {
                    int i = 0;
                    if (rand.nextInt(3) == 0) {
                        i = 1;
                        if (rand.nextInt(3) == 0) {
                            i = 2;
                        }
                    }
                    world.setBlockState(pos.offset(dir), BayouBluesBlocks.CYPRESS_BRANCH.get().getDefaultState().with(CypressBranchBlock.FACING, dir).with(CypressBranchBlock.AGE, i), 3);
                }
            }
        }
    }
}