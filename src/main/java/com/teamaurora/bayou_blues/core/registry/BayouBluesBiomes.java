package com.teamaurora.bayou_blues.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.BiomeUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.BiomeSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BayouBlues.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BayouBluesBiomes {
    private static final BiomeSubRegistryHelper HELPER = BayouBlues.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome BAYOU = HELPER.createBiome("bayou", () -> makeBayouBiome(-0.1F, 0.2F));
    public static final BiomeSubRegistryHelper.KeyedBiome BAYOU_HILLS = HELPER.createBiome("bayou_hills", () -> makeBayouBiome(0.0F, 0.4F));

    public static void addHillBiome() {
        BiomeUtil.addHillBiome(BAYOU.getKey(), Pair.of(BAYOU_HILLS.getKey(), 1));
    }

    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BAYOU.getKey(), 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BAYOU_HILLS.getKey(), 0));
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(BAYOU.getKey(), BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.RARE, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BAYOU_HILLS.getKey(), BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.RARE, BiomeDictionary.Type.OVERWORLD);
    }

    private static Biome makeBayouBiome(float depth, float scale) {
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .category(Biome.Category.SWAMP)
                .depth(depth)
                .scale(scale)
                .temperature(0.75F)
                .downfall(1.0F)
                .setEffects((new BiomeAmbience.Builder())
                        .setWaterColor(0x549AD3)
                        .setWaterFogColor(0x294059)
                        .setFogColor(0xA0E2E5)
                        .withSkyColor(getSkyColorWithTemperatureModifier(0.75F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .withMobSpawnSettings(new MobSpawnInfo.Builder().copy())
                .withGenerationSettings((new BiomeGenerationSettings.Builder())
                        .withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244189_u)
                        .build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}
