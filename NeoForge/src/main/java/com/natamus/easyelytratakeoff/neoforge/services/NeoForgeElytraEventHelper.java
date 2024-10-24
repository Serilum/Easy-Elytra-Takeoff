package com.natamus.easyelytratakeoff.neoforge.services;

import com.natamus.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class NeoForgeElytraEventHelper implements ElytraEventHelper {
	@Override
	public boolean isWearingAnElytra(Player player) {
		boolean foundelytra = false;
		for (ItemStack nis : player.getArmorSlots()) {
			if (nis.has(DataComponents.GLIDER)) {
				foundelytra = true;
				break;
			}
		}

		if (!foundelytra) {
			Collection<AttributeInstance> atrb = player.getAttributes().getSyncableAttributes();
			for (AttributeInstance ai : atrb) {
				for (AttributeModifier m : ai.getModifiers()) {
					String name = m.toString().toLowerCase();
					if (name.contains("flight modifier") || name.contains("elytra curio modifier")) {
						if (m.amount() >= 1.0) {
							foundelytra = true;
							break;
						}
					}
				}
				if (foundelytra) {
					break;
				}
			}
		}
		return foundelytra;
	}
}