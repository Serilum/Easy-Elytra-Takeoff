package com.natamus.easyelytratakeoff.fabric.services;

import com.natamus.collective.data.Constants;
import com.natamus.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FabricElytraEventHelper implements ElytraEventHelper {
    @Override
    public boolean isWearingAnElytra(Player player) {
        boolean foundelytra = EntityElytraEvents.CUSTOM.invoker().useCustomElytra(player, false);
		if (!foundelytra) {
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
		}
        return foundelytra;
    }
}