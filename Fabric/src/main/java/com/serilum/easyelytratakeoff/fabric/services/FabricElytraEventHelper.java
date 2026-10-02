package com.serilum.easyelytratakeoff.fabric.services;

import com.serilum.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;

public class FabricElytraEventHelper implements ElytraEventHelper {
	@Override
	public boolean isWearingAnElytra(Player player) {
		boolean foundelytra = EntityElytraEvents.CUSTOM.invoker().useCustomElytra(player, false);
		if (!foundelytra) {
			for (ItemStack nis : player.getArmorSlots()) {
				if (nis.getItem() instanceof ElytraItem) {
					foundelytra = true;
					break;
				}
			}
		}
		return foundelytra;
	}
}