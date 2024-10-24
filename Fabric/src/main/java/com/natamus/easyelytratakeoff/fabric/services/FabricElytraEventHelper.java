package com.natamus.easyelytratakeoff.fabric.services;

import com.natamus.easyelytratakeoff.services.helpers.ElytraEventHelper;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FabricElytraEventHelper implements ElytraEventHelper {
    @Override
    public boolean isWearingAnElytra(Player player) {
        boolean foundelytra = EntityElytraEvents.CUSTOM.invoker().useCustomElytra(player, false);
		if (!foundelytra) {
			for (ItemStack nis : player.getArmorSlots()) {
				if (nis.has(DataComponents.GLIDER)) {
					foundelytra = true;
					break;
				}
			}
		}
        return foundelytra;
    }
}