package com.teamaurora.bayou_blues.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LilyItem extends Item {
    private final Block flower;

    public LilyItem(Block flower, Properties properties) {
        super(properties);
        this.flower = flower;
    }

    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == Blocks.LILY_PAD) {
            world.setBlockState(pos, flower.getDefaultState());
            ItemStack stack = context.getItem();
            stack.shrink(1);
            SoundType soundtype = SoundType.LILY_PADS;
            PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, pos, this.getPlaceSound(state, world, pos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            playerentity.swingArm(context.getHand());
            return ActionResultType.CONSUME;
        }
        return ActionResultType.PASS;
    }

    protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
        return state.getSoundType(world, pos, entity).getPlaceSound();
    }
}
