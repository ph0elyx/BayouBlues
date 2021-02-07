package com.teamaurora.bayou_blues.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.bayou_blues.common.item.DrinkItem;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BayouBlues.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BayouBluesItems {
    public static final ItemSubRegistryHelper HELPER = BayouBlues.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> CYPRESS_BOAT = HELPER.createBoatItem("cypress", BayouBluesBlocks.CYPRESS_PLANKS);

    public static final RegistryObject<Item> GOOSEBERRIES = HELPER.createItem("gooseberries", ()->new Item(new Item.Properties().food(Foods.GOOSEBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> GOOSEBERRY_JUICE = HELPER.createItem("gooseberry_juice", ()->new DrinkItem(new Item.Properties().food(Foods.GOOSEBERRY_JUICE).maxStackSize(16).group(ItemGroup.FOOD)));

    public static class Foods {
        public static final Food GOOSEBERRIES = (new Food.Builder()).hunger(2).saturation(0.2F).build();
        public static final Food GOOSEBERRY_JUICE = (new Food.Builder()).hunger(5).saturation(0.4F).build();
    }
}
