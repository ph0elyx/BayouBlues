package com.teamaurora.bayou_blues.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBlocks;
import com.teamaurora.bayou_blues.core.registry.BayouBluesItems;

public class BayouBluesCompat {
    public static void registerCompostables() {
        DataUtil.registerCompostable(BayouBluesBlocks.CYPRESS_LEAVES.get(), 0.3F);
        DataUtil.registerCompostable(BayouBluesBlocks.CYPRESS_SAPLING.get(), 0.3F);
        DataUtil.registerCompostable(BayouBluesBlocks.CYPRESS_LEAF_CARPET.get(), 0.3F);

        DataUtil.registerCompostable(BayouBluesItems.GOOSEBERRIES.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesBlocks.GOOSEBERRY_SACK.get(), 1.0F);
        DataUtil.registerCompostable(BayouBluesItems.ALGAE.get(), 0.15F);

        DataUtil.registerCompostable(BayouBluesItems.BEARD_MOSS.get(), 0.3F);
        DataUtil.registerCompostable(BayouBluesItems.BEARD_MOSS_BLOCK.get(), 0.65F);

        DataUtil.registerCompostable(BayouBluesItems.BLUE_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.LIGHT_BLUE_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.CYAN_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.LIGHT_GRAY_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.WHITE_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.MAGENTA_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.PINK_LILY.get(), 0.65F);
        DataUtil.registerCompostable(BayouBluesItems.PURPLE_LILY.get(), 0.65F);
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(BayouBluesBlocks.HANGING_CYPRESS_LEAVES.get(), 30, 60);
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

        DataUtil.registerFlammable(BayouBluesBlocks.CYPRESS_BRANCH.get(), 60, 100);
        DataUtil.registerFlammable(BayouBluesBlocks.GOOSEBERRY_SACK.get(), 60, 20);

        DataUtil.registerFlammable(BayouBluesBlocks.BEARD_MOSS.get(), 15, 100);
        DataUtil.registerFlammable(BayouBluesBlocks.BEARD_MOSS_BLOCK.get(), 15, 100);
    }
}
