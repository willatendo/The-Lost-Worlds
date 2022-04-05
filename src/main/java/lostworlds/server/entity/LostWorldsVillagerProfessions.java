package lostworlds.server.entity;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsVillagerProfessions {
	public static final VillagerProfession ARCHAEOLOGIST = register("archaeologist", new VillagerProfession("archaeologist", LostWorldsPOIs.ARCHAEOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	public static final VillagerProfession PALEONTOLOGIST = register("paleontologist", new VillagerProfession("paleontologist", LostWorldsPOIs.PALEONTOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_TOOLSMITH));

	public static VillagerProfession register(String id, VillagerProfession villageProfession) {
		villageProfession.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.PROFESSIONS.register(villageProfession);
		return villageProfession;
	}

	// Registry
	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Villager Professions");
	}
}
