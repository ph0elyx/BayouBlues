package com.teamaurora.bayou_blues.core.other;

import com.teamaurora.bayou_blues.common.block.CypressKneeBlock;
import com.teamaurora.bayou_blues.common.block.DoubleCypressKneeBlock;
import com.teamaurora.bayou_blues.common.block.LilyFlowerBlock;
import com.teamaurora.bayou_blues.core.BayouBlues;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BayouBlues.MODID)
public class BayouBluesEvents {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getSource() == DamageSource.FALL) {
            BlockPos pos = event.getEntity().getPosition().down();
            IWorldReader world = event.getEntity().getEntityWorld();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block instanceof CypressKneeBlock || block instanceof DoubleCypressKneeBlock) {
                event.getEntity().attackEntityFrom(DamageSource.GENERIC, event.getAmount() * 2);
            }
        }
    }

    @SubscribeEvent
    public static void onBonemealUse(BonemealEvent event) {
        BlockState state = event.getBlock();
        BlockPos pos = event.getPos();
        World world = event.getWorld();
        if (state.getBlock() == Blocks.LILY_PAD) {
            Block lily = LilyFlowerBlock.getRandomLily(world.getRandom());
            if (lily != null) {
                world.setBlockState(pos, lily.getDefaultState(), 3);
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}
