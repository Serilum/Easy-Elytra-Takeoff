package com.natamus.easyelytratakeoff.forge.services;

import com.natamus.collective.data.Constants;
import com.natamus.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class ForgeElytraEventHelper implements ElytraEventHelper {
    @Override
    public boolean isWearingAnElytra(Player player) {
        boolean foundelytra = false;
		for (EquipmentSlot equipmentSlot : Constants.equipmentSlots) {
			if (equipmentSlot.equals(EquipmentSlot.OFFHAND)) {
				continue;
			}

			ItemStack slotStack = player.getItemBySlot(equipmentSlot);
			if (!slotStack.isEmpty()) {
				if (slotStack.has(DataComponents.GLIDER)) {
					foundelytra = true;
					break;
				}
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