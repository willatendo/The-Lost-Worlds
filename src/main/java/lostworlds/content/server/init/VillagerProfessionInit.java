package lostworlds.content.server.init;

import com.google.common.collect.ImmutableSet;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class VillagerProfessionInit 
{
	public static final VillagerProfession ARCHAEOLOGIST = ModRegistry.register("archaeologist", new VillagerProfession("archaeologist", PointOfInterestInit.ARCHAEOLOGY_TABLE_POI, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	
	//Registry
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Villager Professions"); }
}
