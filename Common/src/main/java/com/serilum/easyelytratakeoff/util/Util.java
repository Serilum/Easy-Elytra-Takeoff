package com.serilum.easyelytratakeoff.util;

import com.serilum.easyelytratakeoff.mixin.LivingEntityInvoker;
import net.minecraft.world.entity.player.Player;

public class Util {
	public static boolean isElytraBooster(String descriptionId) {
		return descriptionId.contains("booster_") && !descriptionId.contains("_empty");
	}

	public static boolean isReusableRocket(String descriptionId) {
		return descriptionId.contains("reusable_rocket");
	}

	public static boolean canGlideOnceAirborne(Player player) {
		boolean wasOnGround = player.onGround();
		player.setOnGround(false);

		boolean canGlide = ((LivingEntityInvoker)player).invokeCanGlide();
		player.setOnGround(wasOnGround);

		return canGlide;
	}
}
