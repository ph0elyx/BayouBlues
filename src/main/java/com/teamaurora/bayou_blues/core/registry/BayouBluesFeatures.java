package com.teamaurora.bayou_blues.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamaurora.bayou_blues.common.world.gen.feature.CypressFeature;
import com.teamaurora.bayou_blues.common.world.gen.feature.MegaCypressFeature;
import com.teamaurora.bayou_blues.common.world.gen.treedecorator.HangingCypressLeavesTreeDecorator;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BayouBlues.MODID)
public class BayouBluesFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BayouBlues.MODID);
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, BayouBlues.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> CYPRESS_TREE = FEATURES.register("cypress_tree", ()->new CypressFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> MEGA_CYPRESS_TREE = FEATURES.register("mega_cypress_tree", ()->new MegaCypressFeature(BaseTreeFeatureConfig.CODEC));

    public static final RegistryObject<TreeDecoratorType<?>> HANGING_CYPRESS_LEAVES = TREE_DECORATORS.register("hanging_cypress_leaves", () -> new TreeDecoratorType<>(HangingCypressLeavesTreeDecorator.CODEC));

    public static final class BlockStates {
        public static final BlockState CYPRESS_LOG = BayouBluesBlocks.CYPRESS_LOG.get().getDefaultState();
        public static final BlockState CYPRESS_LEAVES = BayouBluesBlocks.CYPRESS_LEAVES.get().getDefaultState();
        public static final BlockState HANGING_CYPRESS_LEAVES = BayouBluesBlocks.HANGING_CYPRESS_LEAVES.get().getDefaultState();
    }

    public static final class Configs {
        public static final BaseTreeFeatureConfig CYPRESS_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)
        )).setIgnoreVines().setDecorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR)).build();
    }

    public static final class Configured {
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CYPRESS = BayouBluesFeatures.CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_CYPRESS = BayouBluesFeatures.MEGA_CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_TREE_CONFIG);

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BayouBlues.MODID, name), configuredFeature);
        }

        public static void registerConfiguredFeatures() {
            register("cypress", CYPRESS);
            register("mega_cypress", MEGA_CYPRESS);
        }
    }
}
