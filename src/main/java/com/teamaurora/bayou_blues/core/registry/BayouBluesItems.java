package com.teamaurora.bayou_blues.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BayouBlues.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BayouBluesItems {
    public static final ItemSubRegistryHelper HELPER = BayouBlues.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> CYPRESS_BOAT = HELPER.createBoatItem("cypress", BayouBluesBlocks.CYPRESS_PLANKS);
}
