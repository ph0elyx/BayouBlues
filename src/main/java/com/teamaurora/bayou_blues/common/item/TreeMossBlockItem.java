package com.teamaurora.bayou_blues.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public class TreeMossBlockItem extends BlockItem implements IForgeItem {
    public TreeMossBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return 3200;
    }
}
