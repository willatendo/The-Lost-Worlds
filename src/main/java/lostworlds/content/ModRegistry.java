package lostworlds.content;

import java.util.Locale;

import lostworlds.content.server.init.BannerInit;
import lostworlds.content.server.init.BiomeInit;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.FeatureInit;
import lostworlds.content.server.init.FoliagePlacerInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.ParticleInit;
import lostworlds.content.server.init.PointOfInterestInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.SoundInit;
import lostworlds.content.server.init.StructureInit;
import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.content.server.init.VillagerProfessionInit;
import lostworlds.content.server.init.WorldCarverInit;
import lostworlds.content.server.init.WorldTypeInit;
import lostworlds.library.biome.ModConfiguredCarvers;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.world.ForgeWorldType;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry 
{
	//Registers
	public static ParticleType<BasicParticleType> register(String id, ParticleType<BasicParticleType> particle)
	{
		particle.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.PARTICLE_TYPES.register(particle);
		return particle;
	}
	
	public static IRecipeSerializer<?> register(String id, IRecipeSerializer<?> recipe)
	{
		recipe.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.RECIPE_SERIALIZERS.register(recipe);
		return recipe;
	}
	
	public static SoundEvent register(String id, SoundEvent sound)
	{
		sound.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.SOUND_EVENTS.register(sound);
		return sound;
	}
	
	public static Effect register(String id, Effect effect)
	{
		effect.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.POTIONS.register(effect);
		return effect;
	}
	
	public static Potion register(String id, Potion potion)
	{
		potion.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.POTION_TYPES.register(potion);
		return potion;
	}
	
	public static Item register(String id, Item item)
	{
		item.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.ITEMS.register(item);
		return item;
	}
	
	public static BannerPattern createPattern(String id) 
	{
		return BannerPattern.create(id.toUpperCase(Locale.ROOT), id, id, false);
	}
	
	public static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType<T> tileEntity)
	{
		tileEntity.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.TILE_ENTITIES.register(tileEntity);
		return tileEntity;
	}
	
	public static <T extends Container> ContainerType<T> register(String id, ContainerType<T> container)
	{
		container.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.CONTAINERS.register(container);
		return container;
	}
	
	public static Block register(String id, Block block)
	{
		block.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.BLOCKS.register(block);
		return block;
	}
	
	public static BlockPlacerType<?> register(String id, BlockPlacerType<?> type)
	{
		type.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.BLOCK_PLACER_TYPES.register(type);
		return type;
	}
	
	public static PointOfInterestType register(String id, PointOfInterestType type)
	{
		type.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.POI_TYPES.register(type);
		return type;
	}
	
	public static VillagerProfession register(String id, VillagerProfession profession)
	{
		profession.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.PROFESSIONS.register(profession);
		return profession;
	}
	
	public static <T extends Entity> EntityType<T> register(String id, IFactory<T> factory, EntityClassification classification, float width, float height)
	{
		EntityType<T> entityType = EntityType.Builder.of(factory, classification).sized(width, height).build(id);
		entityType.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.ENTITIES.register(entityType);
		return entityType;
	}
	
	public static FoliagePlacerType<?> register(String id, FoliagePlacerType<?> foliagePlacer)
	{
		foliagePlacer.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.FOLIAGE_PLACER_TYPES.register(foliagePlacer);
		return foliagePlacer;
	}
	
	public static Biome register(String id, Biome biome) 
	{
		biome.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.BIOMES.register(biome);
		return biome;
	}
	
	public static IStructurePieceType register(String id, IStructurePieceType type) 
	{
		return Registry.register(Registry.STRUCTURE_PIECE, ModUtils.rL(id.toLowerCase(Locale.ROOT)), type);
	}
	
	public static Structure<NoFeatureConfig> register(String id, Structure<NoFeatureConfig> structure)
	{
		structure.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.STRUCTURE_FEATURES.register(structure);
		return structure;
	}
	
	public static StructureFeature<?, ?> register(String id, StructureFeature<?, ?> structureFeature)
	{
		return Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, ModUtils.rL(id), structureFeature);
	}
	
	public static SurfaceBuilder<?> register(String id, SurfaceBuilder<?> surfaceBuilder)
	{
		surfaceBuilder.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.SURFACE_BUILDERS.register(surfaceBuilder);
		return surfaceBuilder;
	}
	
	public static Feature<?> register(String id, Feature<?> feature)
	{
		feature.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.FEATURES.register(feature);
		return feature;
	}
	
	public static WorldCarver<ProbabilityConfig> register(String id, WorldCarver<ProbabilityConfig> worldCarver)
	{
		worldCarver.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.WORLD_CARVERS.register(worldCarver);
		return worldCarver;
	}	
	
	public static <WC extends ICarverConfig> ConfiguredCarver<WC> register(String id, ConfiguredCarver<WC> configuredCarver) 
	{
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, ModUtils.rL(id), configuredCarver);
	}
	
	public static ForgeWorldType register(String id, ForgeWorldType worldType)
	{
		worldType.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.WORLD_TYPES.register(worldType);
		return worldType;
	}
	
	//Registers Deferred Registers
	public static void register()
	{
		ParticleInit.init();
		RecipeInit.init();
		PotionInit.init();
		SoundInit.init();
		ItemInit.init();
		BannerInit.init();
		TileEntityInit.init();
		ContainerInit.init();
		BlockInit.init();
		PointOfInterestInit.init();
		EntityInit.init();
		VillagerProfessionInit.init();
		BiomeInit.init();
		StructurePieceInit.init();
		StructureInit.init();
		SurfaceBuilderInit.init();
		FoliagePlacerInit.init();
		FeatureInit.init();
		WorldCarverInit.init();
		ModConfiguredCarvers.init();
		WorldTypeInit.init();
	}
}