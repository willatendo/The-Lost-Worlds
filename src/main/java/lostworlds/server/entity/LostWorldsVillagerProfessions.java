package lostworlds.server.entity;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;

public class LostWorldsVillagerProfessions {
	public static final VillagerProfession ARCHAEOLOGIST = LostWorldsRegistry.register("archaeologist", new VillagerProfession("archaeologist", LostWorldsPOIs.ARCHAEOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	public static final VillagerProfession PALEONTOLOGIST = LostWorldsRegistry.register("paleontologist", new VillagerProfession("paleontologist", LostWorldsPOIs.PALEONTOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_TOOLSMITH));
	// public static final VillagerProfession PALEOBOTANIST =
	// LostWorldsRegistry.register("paleobotanist", new VillagerProfession("paleobotanist",
	// LostWorldsPOIs.PALEOBOTANY_TABLE, ImmutableSet.of(), ImmutableSet.of(),
	// SoundEvents.VILLAGER_WORK_TOOLSMITH));

	// Registry
	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Villager Professions");
	}
}
