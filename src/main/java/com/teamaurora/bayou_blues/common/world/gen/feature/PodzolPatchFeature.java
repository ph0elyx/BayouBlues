package com.teamaurora.bayou_blues.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class PodzolPatchFeature extends Feature<NoFeatureConfig> {
    public PodzolPatchFeature(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        for (BlockPos newPos : BlockPos.getAllInBoxMutable(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
            if (reader.getBlockState(newPos).getBlock() == Blocks.GRASS_BLOCK) {
                if (rand.nextFloat() <= 1.0F - (newPos.distanceSq(pos) / 32)) {
                    reader.setBlockState(newPos, Blocks.PODZOL.getDefaultState(), 3);
                    i++;
                }
            }
        }
        return i > 0;
    }
}
