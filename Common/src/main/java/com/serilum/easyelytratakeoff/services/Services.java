package com.serilum.easyelytratakeoff.services;

import com.serilum.easyelytratakeoff.services.helpers.ElytraEventHelper;
import com.serilum.easyelytratakeoff.util.Reference;

import java.util.ServiceLoader;

public class Services {
	public static final ElytraEventHelper ELYTRA = load(ElytraEventHelper.class);

	public static <T> T load(Class<T> clazz) {
		return ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("[" + Reference.NAME + "] Failed to load service for " + clazz.getName() + "."));
	}
}