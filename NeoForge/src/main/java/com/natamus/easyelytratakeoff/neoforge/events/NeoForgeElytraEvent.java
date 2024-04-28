package com.natamus.easyelytratakeoff.neoforge.events;

import com.natamus.easyelytratakeoff.events.ElytraEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeElytraEvent {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.level();
		if (level.isClientSide || e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		ElytraEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
	
	@SubscribeEvent
	public static void onFirework(PlayerInteractEvent.RightClickItem e) {
		ElytraEvent.onFirework(e.getEntity(), e.getLevel(), e.getHand());
	}
}
