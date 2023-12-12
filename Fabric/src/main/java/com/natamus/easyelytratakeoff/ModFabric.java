package com.natamus.easyelytratakeoff;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectivePlayerEvents;
import com.natamus.easyelytratakeoff.events.ElytraEvent;
import com.natamus.easyelytratakeoff.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectivePlayerEvents.PLAYER_TICK.register((ServerLevel world, ServerPlayer player) -> {
			ElytraEvent.onPlayerTick(world, player);
		});

		UseItemCallback.EVENT.register((player, world, hand) -> {
			return ElytraEvent.onFirework(player, world, hand);
		});
	}

	private static void setGlobalConstants() {

	}
}
