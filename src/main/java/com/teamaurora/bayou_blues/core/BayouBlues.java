package com.teamaurora.bayou_blues.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.teamaurora.bayou_blues.core.other.BayouBluesCompat;
import com.teamaurora.bayou_blues.core.other.BayouBluesRendering;
import com.teamaurora.bayou_blues.core.registry.BayouBluesBiomes;
import com.teamaurora.bayou_blues.core.registry.BayouBluesFeatures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.teamaurora.bayou_blues.core.BayouBlues.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bayou_blues")
public class BayouBlues
{
    public static final String MODID = "bayou_blues";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public BayouBlues() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.register(eventBus);

        BayouBluesFeatures.FEATURES.register(eventBus);
        BayouBluesFeatures.TREE_DECORATORS.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BayouBluesFeatures.Configured.registerConfiguredFeatures();
            BayouBluesCompat.registerFlammables();
            BayouBluesCompat.registerCompostables();

            BayouBluesBiomes.addBiomeTypes();
            BayouBluesBiomes.registerBiomesToDictionary();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BayouBluesRendering.registerBlockColors();
            BayouBluesRendering.setupRenderLayer();
        });
    }
}