package lostworlds.content.server.init;

import com.google.common.collect.ImmutableSet;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;

public class VillagerProfessionInit 
{
	public static final VillagerProfession ARCHAEOLOGIST = ModRegistry.register("archaeologist", new VillagerProfession("archaeologist", PointOfInterestInit.ARCHAEOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	public static final VillagerProfession PALEONTOLOGIST = ModRegistry.register("paleontologist", new VillagerProfession("paleontologist", PointOfInterestInit.PALEONTOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Villager Professions"); }
}
