package com.teamaurora.bayou_blues.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.bayou_blues.common.item.*;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.item.BlockItem;
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
    public static final RegistryObject<Item> GOOSEBERRY_PIE = HELPER.createItem("gooseberry_pie", ()->new Item(new Item.Properties().food(Foods.GOOSEBERRY_PIE).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> ALGAE = HELPER.createItem("algae", ()->new AlgaeItem(BayouBluesBlocks.ALGAE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> TREE_MOSS = HELPER.createItem("tree_moss", ()->new TreeMossItem(BayouBluesBlocks.TREE_MOSS.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> TREE_MOSS_BLOCK = HELPER.createItem("tree_moss_block", ()->new TreeMossBlockItem(BayouBluesBlocks.TREE_MOSS_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public static final RegistryObject<Item> BLUE_LILY = HELPER.createItem("blue_lily", ()->new LilyItem(BayouBluesBlocks.BLUE_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> LIGHT_GRAY_LILY = HELPER.createItem("light_gray_lily", ()->new LilyItem(BayouBluesBlocks.LIGHT_GRAY_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> CYAN_LILY = HELPER.createItem("cyan_lily", ()->new LilyItem(BayouBluesBlocks.CYAN_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> LIGHT_BLUE_LILY = HELPER.createItem("light_blue_lily", ()->new LilyItem(BayouBluesBlocks.LIGHT_BLUE_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> MAGENTA_LILY = HELPER.createItem("magenta_lily", ()->new LilyItem(BayouBluesBlocks.MAGENTA_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> PINK_LILY = HELPER.createItem("pink_lily", ()->new LilyItem(BayouBluesBlocks.PINK_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> PURPLE_LILY = HELPER.createItem("purple_lily", ()->new LilyItem(BayouBluesBlocks.PURPLE_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<Item> WHITE_LILY = HELPER.createItem("white_lily", ()->new LilyItem(BayouBluesBlocks.WHITE_LILY.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public static class Foods {
        public static final Food GOOSEBERRIES = (new Food.Builder()).hunger(2).saturation(0.2F).build();
        public static final Food GOOSEBERRY_JUICE = (new Food.Builder()).hunger(1).saturation(0.1F).build();
        public static final Food GOOSEBERRY_PIE = (new Food.Builder()).hunger(6).saturation(0.5F).build();
    }
}
