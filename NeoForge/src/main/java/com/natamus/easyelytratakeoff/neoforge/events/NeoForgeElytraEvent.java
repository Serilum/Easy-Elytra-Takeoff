package com.natamus.easyelytratakeoff.neoforge.events;

import com.natamus.easyelytratakeoff.events.ElytraEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class NeoForgeElytraEvent {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post e) {
		Player player = e.getEntity();
		Level level = player.level();
		if (level.isClientSide) {
			return;
		}

		ElytraEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
	
	@SubscribeEvent
	public static void onFirework(PlayerInteractEvent.RightClickItem e) {
		ElytraEvent.onFirework(e.getEntity(), e.getLevel(), e.getHand());
	}
}
