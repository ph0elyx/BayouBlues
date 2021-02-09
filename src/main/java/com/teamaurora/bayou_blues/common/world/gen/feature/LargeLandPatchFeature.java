package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;

import java.util.Random;

public class LargeLandPatchFeature extends LargePatchFeature {
    public LargeLandPatchFeature(Codec<BlockStateFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        int i = 0;
        for (BlockPos newPos : BlockPos.getAllInBoxMutable(pos.add(-6, -6, -6), pos.add(6, 6, 6))) {
            if (config.state.getBlock().isValidPosition(config.state, reader, newPos) && reader.getBlockState(newPos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                if (rand.nextFloat() <= 1.0F - (newPos.distanceSq(pos) / 72)) {
                    reader.setBlockState(newPos, config.state, 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}
