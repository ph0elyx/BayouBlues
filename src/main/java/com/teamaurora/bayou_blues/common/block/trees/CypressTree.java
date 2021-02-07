package com.teamaurora.bayou_blues.common.block.trees;

import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class CypressTree extends BigTree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return BayouBluesFeatures.Configured.CYPRESS_GROWN;
    }

    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random randomIn) {
        return BayouBluesFeatures.Configured.MEGA_CYPRESS_GROWN;
    }
}
