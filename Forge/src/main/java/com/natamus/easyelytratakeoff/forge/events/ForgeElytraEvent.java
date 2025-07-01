package com.natamus.easyelytratakeoff.forge.events;

import com.natamus.easyelytratakeoff.events.ElytraEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeElytraEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeElytraEvent.class);
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent.Pre e) {
		Player player = e.player;
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
