package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.MathUtil;
import com.mojang.serialization.Codec;
import com.teamaurora.bayou_blues.common.block.AlgaeBlock;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class LargePatchFeature extends Feature<BlockStateFeatureConfig> {
    public LargePatchFeature(Codec<BlockStateFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        int i = 0;
        for (BlockPos newPos : BlockPos.getAllInBoxMutable(pos.add(-6, -6, -6), pos.add(6, 6, 6))) {
            if (config.state.getBlock().isValidPosition(config.state, reader, newPos)) {
                if (rand.nextFloat() <= 1.0F - (newPos.distanceSq(pos) / 72)) {
                    reader.setBlockState(newPos, config.state, 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}
