package lostworlds.content;

import java.util.Locale;

import lostworlds.content.server.init.BannerInit;
import lostworlds.content.server.init.BiomeInit;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.EnchantmentInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.FeatureInit;
import lostworlds.content.server.init.FoliagePlacerInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.PlacementInit;
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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
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
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.world.ForgeWorldType;
import net.minecraftforge.registries.ForgeRegistries;
import tyrannotitanlib.library.base.biome.TyrannoBiome;
import tyrannotitanlib.library.tyrannoregister.TyrannoRegister;

public class ModRegistry 
{
	//Registers
	public static IRecipeSerializer<?> register(String id, IRecipeSerializer<?> recipe)
	{
		TyrannoRegister.register(recipe, id);
		return recipe;
	}
	
	public static SoundEvent register(String id, SoundEvent sound)
	{
		TyrannoRegister.registerSound(id, sound);
		return sound;
	}
	
	public static Effect register(String id, Effect effect)
	{
		TyrannoRegister.registerEffect(id, effect);
		return effect;
	}
	
	public static Potion register(String id, Potion potion)
	{
		TyrannoRegister.registerPotion(id, potion);
		return potion;
	}
	
	public static Enchantment register(String id, Enchantment enchantment)
	{
		TyrannoRegister.registerEnchantment(id, enchantment);
		return enchantment;
	}
	
	public static Item register(String id, Item item)
	{
		TyrannoRegister.registerItem(id, item);
		return item;
	}
	
	public static BannerPattern createPattern(String id) 
	{
		return BannerPattern.create(id.toUpperCase(Locale.ROOT), id, id, false);
	}
	
	public static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType<T> tileEntity)
	{
		TyrannoRegister.registerBlockEntity(id, tileEntity);
		return tileEntity;
	}
	
	public static <T extends Container> ContainerType<T> register(String id, ContainerType<T> container)
	{
		TyrannoRegister.registerContainer(id, container);
		return container;
	}
	
	public static Block register(String id, Block block)
	{
		TyrannoRegister.registerBlock(id, block);
		return block;
	}
	
	public static BlockPlacerType<?> register(String id, BlockPlacerType<?> type)
	{
		TyrannoRegister.registerBlockPlacer(id, type);
		return type;
	}
	
	public static PointOfInterestType register(String id, PointOfInterestType type)
	{
		TyrannoRegister.registerPointOfInterest(id, type);
		return type;
	}
	
	public static VillagerProfession register(String id, VillagerProfession profession)
	{
		TyrannoRegister.registerVillagerProfession(id, profession);
		return profession;
	}
	
	public static <T extends Entity> EntityType<T> register(String id, IFactory<T> factory, EntityClassification classification, float width, float height)
	{
		EntityType<T> entityType = EntityType.Builder.of(factory, classification).sized(width, height).build(id);
		entityType.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.ENTITIES.register(entityType);
		return entityType;
	}
	
	public static FoliagePlacerType<?> register(String id, FoliagePlacerType<?> type)
	{
		TyrannoRegister.registerFoliagePlacer(id, type);
		return type;
	}
	
	public static Biome register(String id, TyrannoBiome biome) 
	{
		Biome realBiome = biome.getBiome();
		TyrannoRegister.registerBiome(id, realBiome);
		return realBiome;
	}
	
	public static IStructurePieceType register(String id, IStructurePieceType type) 
	{
		return Registry.register(Registry.STRUCTURE_PIECE, ModUtils.rL(id.toLowerCase(Locale.ROOT)), type);
	}
	
	public static Structure<NoFeatureConfig> register(String id, Structure<NoFeatureConfig> structure)
	{
		TyrannoRegister.registerStructure(id, structure);
		return structure;
	}
	
	public static StructureFeature<?, ?> register(String id, StructureFeature<?, ?> structureFeature)
	{
		return Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, ModUtils.rL(id), structureFeature);
	}
	
	public static SurfaceBuilder<?> register(String id, SurfaceBuilder<?> surfaceBuilder)
	{
		TyrannoRegister.registerSurfaceBuilder(id, surfaceBuilder);
		return surfaceBuilder;
	}
	
	public static <T extends IPlacementConfig> Placement<T> register(String id, Placement<T> placement)
	{
		placement.setRegistryName(ModUtils.rL(id));
		ForgeRegistries.DECORATORS.register(placement);
		return placement;
	}
	
	public static Feature<?> register(String id, Feature<?> feature)
	{
		TyrannoRegister.registerFeature(id, feature);
		return feature;
	}
	
	public static WorldCarver<ProbabilityConfig> register(String id, WorldCarver<ProbabilityConfig> worldCarver)
	{
		TyrannoRegister.registerWorldCarver(id, worldCarver);
		return worldCarver;
	}	
	
	public static <WC extends ICarverConfig> ConfiguredCarver<WC> register(String id, ConfiguredCarver<WC> configuredCarver) 
	{
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, ModUtils.rL(id), configuredCarver);
	}
	
	public static ForgeWorldType register(String id, ForgeWorldType worldType)
	{
		TyrannoRegister.registerWorldType(id, worldType);
		return worldType;
	}
	
	public static void register()
	{	
		//Game Objects
		BlockInit.init();
		ItemInit.init();
		PotionInit.init();
		SoundInit.init();
		EnchantmentInit.init();
		EntityInit.init();
		BannerInit.init();
		TileEntityInit.init();
		ContainerInit.init();
		RecipeInit.init();
		
		//Villages
		VillagerProfessionInit.init();
		PointOfInterestInit.init();

		//World Generation
		WorldCarverInit.init();
		ModConfiguredCarvers.init();
		SurfaceBuilderInit.init();
		FeatureInit.init();
		PlacementInit.init();
		StructureInit.init();
		StructurePieceInit.init();
		FoliagePlacerInit.init();

		//Data Driven
		BiomeInit.init();
	
		//Forge
		WorldTypeInit.init();
	}
}
