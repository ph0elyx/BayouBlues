package com.teamaurora.bayou_blues.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;

public class BayouBluesCompat {
    public static void registerCompostables() {
        DataUtil.registerCompostable(BayouBluesBlocks.CYPRESS_LEAVES.get(), 0.3F);
        DataUtil.registerCompostable(BayouBluesBlocks.CYPRESS_SAPLING.get(), 0.3F);
        DataUtil.registerCompostable(BayouBluesBlocks.CYPRESS_LEAF_CARPET.get(), 0.3F);
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_LOG.get(), 5, 5);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(BayouBluesBlocks.STRIPPED_CYPRESS_LOG.get(), 5, 5);
        DataUtil.registerFlammable(BayouBluesBlocks.STRIPPED_CYPRESS_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.VERTICAL_CYPRESS_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_VERTICAL_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_BEEHIVE.get(), 5, 20);
    }
}
