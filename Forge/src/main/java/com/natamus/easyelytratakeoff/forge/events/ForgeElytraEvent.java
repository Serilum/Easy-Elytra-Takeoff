package com.natamus.easyelytratakeoff.forge.events;

import com.natamus.easyelytratakeoff.events.ElytraEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeElytraEvent {
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.level();
		if (level.isClientSide || e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		ElytraEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}
	
	@SubscribeEvent
	public void onFirework(PlayerInteractEvent.RightClickItem e) {
		ElytraEvent.onFirework(e.getEntity(), e.getLevel(), e.getHand());
	}
}
