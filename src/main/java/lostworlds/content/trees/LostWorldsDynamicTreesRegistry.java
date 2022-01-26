package lostworlds.content.trees;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;

import lostworlds.content.ModUtils;

public class LostWorldsDynamicTreesRegistry {
	public static void init() {
		RegistryHandler.setup(ModUtils.DT_ID);
	}
}
