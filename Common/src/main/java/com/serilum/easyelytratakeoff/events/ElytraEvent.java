package com.serilum.easyelytratakeoff.events;

import com.natamus.collective.functions.EntityFunctions;
import com.serilum.easyelytratakeoff.services.Services;
import com.serilum.easyelytratakeoff.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;

public class ElytraEvent {
	private static final HashMap<String, Integer> newrockets = new HashMap<String, Integer>();
	private static final HashMap<String, InteractionHand> playerhands = new HashMap<String, InteractionHand>();

	public static void onPlayerTick(ServerLevel level, ServerPlayer player) {
		String playerName = player.getName().getString();
		if (newrockets.containsKey(playerName)) {
			int left = newrockets.get(playerName);
			if (left > -6) {
				if (left > 0) {
					EntityFunctions.setEntityFlag(player, 7, true);

					InteractionHand hand = playerhands.get(playerName);
					if (hand == null || !Util.isReusableRocket(player.getItemInHand(hand).getItem().getDescriptionId())) {
						FireworkRocketEntity efr = new FireworkRocketEntity(level, player.getItemInHand(InteractionHand.MAIN_HAND), player);
						level.addFreshEntity(efr);
					}

					if (hand != null) {
						ItemStack handstack = player.getItemInHand(hand);
						String handname = handstack.getItem().getDescriptionId().toLowerCase();
						if (Util.isElytraBooster(handname)) {
							handstack.use(level, player, hand);
						}
						else if (Util.isReusableRocket(handname)) {
							handstack.use(level, player, hand);
						}
						playerhands.remove(playerName);
					}
				}

				EntityFunctions.setEntityFlag(player, 7, true);
				newrockets.put(playerName, left-1);
			}
		}
	}

	public static InteractionResult onFirework(Player player, Level world, InteractionHand hand) {
		ItemStack handstack = player.getItemInHand(hand);
		if (world.isClientSide()) {
			return InteractionResult.PASS;
		}

		boolean isbooster = false;
		Item item = handstack.getItem();
		String itemname = item.getDescriptionId().toLowerCase();

		if (!handstack.getItem().equals(Items.FIREWORK_ROCKET) ) {
			if (Util.isReusableRocket(itemname)) {
				if (player.isShiftKeyDown()) {
					return InteractionResult.PASS;
				}
			}
			else if (!Util.isElytraBooster(itemname)) {
				return InteractionResult.PASS;
			}
			isbooster = true;
		}
		
		if (player.isFallFlying()) {
			return InteractionResult.PASS;
		}

		BlockPos belowpos = player.blockPosition().below();

		boolean inAir = true;
		for (BlockPos next : BlockPos.betweenClosed(belowpos.getX() - 1, belowpos.getY() - 1, belowpos.getZ() - 1, belowpos.getX() + 1, belowpos.getY() - 1, belowpos.getZ() + 1)) {
			Block nextblock = world.getBlockState(next).getBlock();
			if (!nextblock.equals(Blocks.AIR)) {
				inAir = false;
				break;
			}
		}

		if (inAir) {
			return InteractionResult.PASS;
		}
		
		boolean foundelytra = Services.ELYTRA.isWearingAnElytra(player) || Util.canGlideOnceAirborne(player);
		if (!foundelytra) {
			return InteractionResult.PASS;
		}

		String playerName = player.getName().getString();
		player.push(0, 0.2, 0);
		player.hurtMarked = true;

		newrockets.put(playerName, 1);
		playerhands.put(playerName, hand);

		if (!player.isCreative() && !isbooster) {
			handstack.shrink(1);
		}
		
		return InteractionResult.FAIL;
	}
}