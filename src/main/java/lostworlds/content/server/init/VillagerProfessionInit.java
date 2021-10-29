package lostworlds.content.server.init;

import com.google.common.collect.ImmutableSet;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;

public class VillagerProfessionInit 
{
	public static final VillagerProfession ARCHAEOLOGIST = ModRegistry.register("archaeologist", new VillagerProfession("archaeologist", PointOfInterestInit.ARCHAEOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	public static final VillagerProfession PALEONTOLOGIST = ModRegistry.register("paleontologist", new VillagerProfession("paleontologist", PointOfInterestInit.PALEONTOLOGY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_TOOLSMITH));
	public static final VillagerProfession PALEOBOTANIST = ModRegistry.register("paleobotanist", new VillagerProfession("paleobotanist", PointOfInterestInit.PALEOBOTANY_TABLE, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_TOOLSMITH));
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Villager Professions"); }
}
