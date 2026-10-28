package com.serilum.easyelytratakeoff.util;

public class Util {
	public static boolean isElytraBooster(String descriptionId) {
		return descriptionId.contains("booster_") && !descriptionId.contains("_empty");
	}

	public static boolean isReusableRocket(String descriptionId) {
		return descriptionId.contains("reusable_rocket");
	}
}
