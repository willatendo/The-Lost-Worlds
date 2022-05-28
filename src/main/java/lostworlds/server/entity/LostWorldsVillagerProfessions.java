package lostworlds.server.entity;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsVillagerProfessions {
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.Keys.VILLAGER_PROFESSIONS, LostWorldsUtils.ID);

	public static final RegistryObject<VillagerProfession> ARCHAEOLOGIST = VILLAGER_PROFESSIONS.register("archaeologist", () -> new VillagerProfession("archaeologist", LostWorldsPOIs.ARCHAEOLOGY_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
	public static final RegistryObject<VillagerProfession> PALEONTOLOGIST = VILLAGER_PROFESSIONS.register("paleontologist", () -> new VillagerProfession("paleontologist", LostWorldsPOIs.PALEONTOLOGY_TABLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_TOOLSMITH));
}
