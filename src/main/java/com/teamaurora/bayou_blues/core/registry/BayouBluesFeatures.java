package com.teamaurora.bayou_blues.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamaurora.bayou_blues.common.world.gen.feature.CypressFeature;
import com.teamaurora.bayou_blues.common.world.gen.feature.MegaCypressFeature;
import com.teamaurora.bayou_blues.common.world.gen.feature.WaterCypressFeature;
import com.teamaurora.bayou_blues.common.world.gen.feature.WaterMegaCypressFeature;
import com.teamaurora.bayou_blues.common.world.gen.treedecorator.CypressBranchTreeDecorator;
import com.teamaurora.bayou_blues.common.world.gen.treedecorator.CypressKneesTreeDecorator;
import com.teamaurora.bayou_blues.common.world.gen.treedecorator.HangingCypressLeavesTreeDecorator;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
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
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> WATER_CYPRESS_TREE = FEATURES.register("water_cypress_tree", ()->new WaterCypressFeature(BaseTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> WATER_MEGA_CYPRESS_TREE = FEATURES.register("water_mega_cypress_tree", ()->new WaterMegaCypressFeature(BaseTreeFeatureConfig.CODEC));

    public static final RegistryObject<TreeDecoratorType<?>> HANGING_CYPRESS_LEAVES = TREE_DECORATORS.register("hanging_cypress_leaves", () -> new TreeDecoratorType<>(HangingCypressLeavesTreeDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<?>> CYPRESS_KNEES = TREE_DECORATORS.register("cypress_knees", () -> new TreeDecoratorType<>(CypressKneesTreeDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<?>> CYPRESS_BRANCH = TREE_DECORATORS.register("cypress_branch", () -> new TreeDecoratorType<>(CypressBranchTreeDecorator.CODEC));

    public static final class BlockStates {
        public static final BlockState CYPRESS_LOG = BayouBluesBlocks.CYPRESS_LOG.get().getDefaultState();
        public static final BlockState CYPRESS_LEAVES = BayouBluesBlocks.CYPRESS_LEAVES.get().getDefaultState();
        public static final BlockState HANGING_CYPRESS_LEAVES = BayouBluesBlocks.HANGING_CYPRESS_LEAVES.get().getDefaultState();
    }

    public static final class Configs {
        public static final BaseTreeFeatureConfig CYPRESS_TREE_CONFIG_GROWN = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)
        )).setIgnoreVines().setDecorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR)).build();

        public static final BaseTreeFeatureConfig CYPRESS_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)
        )).setIgnoreVines().setDecorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR)).build();

        public static final BaseTreeFeatureConfig CYPRESS_KNEE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)
        )).setIgnoreVines().setDecorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, CypressKneesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR)).build();

        public static final BaseTreeFeatureConfig WATER_CYPRESS_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)
        )).setIgnoreVines().setMaxWaterDepth(3).setDecorators(ImmutableList.of(HangingCypressLeavesTreeDecorator.DECORATOR, CypressBranchTreeDecorator.DECORATOR)).build();

        public static final BaseTreeFeatureConfig CYPRESS_BUSH_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LOG),
                new SimpleBlockStateProvider(BlockStates.CYPRESS_LEAVES),
                new BushFoliagePlacer(FeatureSpread.func_242252_a(2),FeatureSpread.func_242252_a(1), 2),
                new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0)
        )).func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build();
    }

    public static final class Configured {
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CYPRESS_GROWN = BayouBluesFeatures.CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_TREE_CONFIG_GROWN);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_CYPRESS_GROWN = BayouBluesFeatures.MEGA_CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_TREE_CONFIG_GROWN);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CYPRESS = BayouBluesFeatures.CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_CYPRESS = BayouBluesFeatures.MEGA_CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MEGA_CYPRESS_KNEES = BayouBluesFeatures.MEGA_CYPRESS_TREE.get().withConfiguration(Configs.CYPRESS_KNEE_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WATER_CYPRESS = BayouBluesFeatures.WATER_CYPRESS_TREE.get().withConfiguration(Configs.WATER_CYPRESS_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WATER_MEGA_CYPRESS = BayouBluesFeatures.WATER_MEGA_CYPRESS_TREE.get().withConfiguration(Configs.WATER_CYPRESS_TREE_CONFIG);

        public static final ConfiguredFeature<?, ?> CYPRESS_BUSH = Feature.TREE.withConfiguration(Configs.CYPRESS_BUSH_CONFIG);

        public static final ConfiguredFeature<?, ?> TREES_BAYOU = Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(CYPRESS_BUSH.withChance(0.35F), MEGA_CYPRESS_KNEES.withChance(0.333333334F)), CYPRESS)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(20, 0.1F, 1)));
        public static final ConfiguredFeature<?, ?> TREES_BAYOU_WATER = Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(WATER_MEGA_CYPRESS.withChance(0.333333334F)), WATER_CYPRESS)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(9, 0.1F, 1)));

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BayouBlues.MODID, name), configuredFeature);
        }

        public static void registerConfiguredFeatures() {
            register("cypress_grown", CYPRESS_GROWN);
            register("mega_cypress_grown", MEGA_CYPRESS_GROWN);
            register("cypress", CYPRESS);
            register("mega_cypress", MEGA_CYPRESS);
            register("mega_cypress_knees", MEGA_CYPRESS_KNEES);
            register("water_cypress", WATER_CYPRESS);
            register("water_mega_cypress", WATER_MEGA_CYPRESS);

            register("cypress_bush", CYPRESS_BUSH);

            register("trees_bayou", TREES_BAYOU);
            register("trees_bayou_water", TREES_BAYOU_WATER);
        }
    }
}
