package com.serilum.easyelytratakeoff.forge.services;

import com.serilum.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class ForgeElytraEventHelper implements ElytraEventHelper {
	@Override
	public boolean isWearingAnElytra(Player player) {
		boolean foundelytra = false;
		for (ItemStack nis : player.getArmorSlots()) {
			if (nis.canElytraFly(player)) {
				foundelytra = true;
				break;
			}
		}

		if (!foundelytra) {
			Collection<AttributeInstance> atrb = player.getAttributes().getSyncableAttributes();
			for (AttributeInstance ai : atrb) {
				if (ai.getAttribute().value().getDescriptionId().equals("caelus.fallFlying")) {
					foundelytra = ai.getValue() >= 1.0;
					break;
				}
			}
		}
		return foundelytra;
	}
}