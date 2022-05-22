package lostworlds.server.entity;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsVillagerProfessions {
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, LostWorldsUtils.ID);

	public static final RegistryObject<VillagerProfession> ARCHAEOLOGIST = VILLAGER_PROFESSIONS.register("archaeologist", () -> new VillagerProfession("archaeologist", LostWorldsPOIs.ARCHAEOLOGY_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	public static final RegistryObject<VillagerProfession> PALEONTOLOGIST = VILLAGER_PROFESSIONS.register("paleontologist", () -> new VillagerProfession("paleontologist", LostWorldsPOIs.PALEONTOLOGY_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_TOOLSMITH));

	// Registry
	public static void deferred(IEventBus bus) {
		VILLAGER_PROFESSIONS.register(bus);
	}
}
