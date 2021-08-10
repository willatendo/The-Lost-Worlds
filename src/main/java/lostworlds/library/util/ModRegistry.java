package lostworlds.library.util;

import lostworlds.content.server.init.BiomeInit;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.BlockPlacerInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.FeatureInit;
import lostworlds.content.server.init.FoliagePlacerInit;
import lostworlds.content.server.init.FossilInit;
import lostworlds.content.server.init.Init;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.ParticleInit;
import lostworlds.content.server.init.PointOfInterestInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.SoundInit;
import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.content.server.init.VillagerProfessionInit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * Author: Willatendo
 * Date: July 10, 2021
 */

public class ModRegistry 
{				
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModUtil.ID);
	public static final DeferredRegister<ContainerType<?>> CONTAINER_REGISTRY = DeferredRegister.create(ForgeRegistries.CONTAINERS, ModUtil.ID);
	
	//Registers
	public static ParticleType<BasicParticleType> register(String id, ParticleType<BasicParticleType> particle)
	{
		particle.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.PARTICLE_TYPES.register(particle);
		return particle;
	}
	
	public static IRecipeSerializer<?> register(String id, IRecipeSerializer<?> recipe)
	{
		recipe.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.RECIPE_SERIALIZERS.register(recipe);
		return recipe;
	}
	
	public static SoundEvent register(String id, SoundEvent sound)
	{
		sound.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.SOUND_EVENTS.register(sound);
		return sound;
	}
	
	public static Effect register(String id, Effect effect)
	{
		effect.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.POTIONS.register(effect);
		return effect;
	}
	
	public static Potion register(String id, Potion potion)
	{
		potion.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.POTION_TYPES.register(potion);
		return potion;
	}
	
	public static Item register(String id, Item item)
	{
		item.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.ITEMS.register(item);
		return item;
	}
	
	public static Block register(String id, Block block)
	{
		block.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.BLOCKS.register(block);
		return block;
	}
	
	public static BlockPlacerType<?> register(String id, BlockPlacerType<?> type)
	{
		type.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.BLOCK_PLACER_TYPES.register(type);
		return type;
	}
	
	public static PointOfInterestType register(String id, PointOfInterestType type)
	{
		type.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.POI_TYPES.register(type);
		return type;
	}
	
	private static EntityType<?> register(String id, EntityType<?> entity) 
	{
		entity.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.ENTITIES.register(entity);
		return entity;
	}
	
	public static <T extends Entity> EntityType<?> register(String id, EntityType.IFactory<T> entity, EntityClassification entitytype, Class<T> entityClass, float width, float height) 
	{
		return register(id, EntityType.Builder.of(entity, entitytype).sized(width, height).build(id));
	}
	
	public static VillagerProfession register(String id, VillagerProfession profession)
	{
		profession.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.PROFESSIONS.register(profession);
		return profession;
	}
	
	public static FoliagePlacerType<?> register(String id, FoliagePlacerType<?> foliagePlacer)
	{
		foliagePlacer.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.FOLIAGE_PLACER_TYPES.register(foliagePlacer);
		return foliagePlacer;
	}
	
	public static Biome register(String id, Biome biome) 
	{
		biome.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.BIOMES.register(biome);
		return biome;
	}
	
	public static SurfaceBuilder<?> register(String id, SurfaceBuilder<?> surfaceBuilder)
	{
		surfaceBuilder.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.SURFACE_BUILDERS.register(surfaceBuilder);
		return surfaceBuilder;
	}
	
	public static Feature<?> register(String id, Feature<?> feature)
	{
		feature.setRegistryName(ModUtil.rL(id));
		ForgeRegistries.FEATURES.register(feature);
		return feature;
	}
	
	//Registers Deferred Registers
	public static void register(IEventBus bus)
	{
		ModUtil.LOGGER.debug("Starting: Initialisation");
		
		TILE_ENTITY_REGISTRY.register(bus);
		CONTAINER_REGISTRY.register(bus);
		
		ParticleInit.init();
		RecipeInit.init();
		PotionInit.init();
		SoundInit.init();
		ItemInit.init();
		FossilInit.init();
		TileEntityInit.init();
		ContainerInit.init();
		BlockInit.init();
		BlockPlacerInit.init();
		PointOfInterestInit.init();
		EntityInit.init();
		VillagerProfessionInit.init();
		BiomeInit.init();
		SurfaceBuilderInit.init();
		FoliagePlacerInit.init();
		FeatureInit.init();
		
		Init.setup();
		
		ModUtil.LOGGER.debug("Finished: Setting Up Registries");
	}
}
