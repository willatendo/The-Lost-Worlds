package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.block.Plants;
import lostworlds.library.block.Trees;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.item.CrystalScarabGemItem;
import lostworlds.library.item.FieldGuideItem;
import lostworlds.library.item.HammerItem;
import lostworlds.library.item.ModBoneMealItem;
import lostworlds.library.item.ModItem;
import lostworlds.library.item.ModSpawnEggItem;
import lostworlds.library.item.PlantDNAItem;
import lostworlds.library.item.PlantDiscItem;
import lostworlds.library.item.PlantFossilItem;
import lostworlds.library.item.PlantSoftTissueItem;
import lostworlds.library.item.TabletItem;
import lostworlds.library.item.TimeBookItem;
import lostworlds.library.item.armour.MaskItem;
import lostworlds.library.item.armour.ModArmourMaterial;
import lostworlds.library.item.armour.OxygenTankItem;
import lostworlds.library.item.block.SeedItem;
import lostworlds.library.item.tool.BrushItem;
import lostworlds.library.item.tool.CrystalScarabGemBrushItem;
import lostworlds.library.item.tool.ModItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;

public class ItemInit
{	
	public static final Item CRYSTAL_SCARAB_SWORD = ModRegistry.register("crystal_scarab_sword", new SwordItem(ModItemTier.CRYSTAL_SCARAB, 3, -2.4F, new Properties().tab(ModUtils.ITEMS).setNoRepair())
	{
		@Override
		public boolean isEnchantable(ItemStack stack) 
		{
			return false;
		};
		
		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
		{
			return false;
		};
		
		@Override
		public boolean isFoil(ItemStack stack) 
		{
			return true;
		};
	});
	
	public static final Item CRYSTAL_SCARAB_SHOVEL = ModRegistry.register("crystal_scarab_shovel", new ShovelItem(ModItemTier.CRYSTAL_SCARAB, 1.5F, -3.0F, new Properties().tab(ModUtils.ITEMS).setNoRepair())
	{
		@Override
		public boolean isEnchantable(ItemStack stack) 
		{
			return false;
		};
		
		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
		{
			return false;
		};
		
		@Override
		public boolean isFoil(ItemStack stack) 
		{
			return true;
		};
	});
	
	public static final Item CRYSTAL_SCARAB_PICKAXE = ModRegistry.register("crystal_scarab_pickaxe", new PickaxeItem(ModItemTier.CRYSTAL_SCARAB, 1, -2.8F, new Properties().tab(ModUtils.ITEMS).setNoRepair())
	{
		@Override
		public boolean isEnchantable(ItemStack stack) 
		{
			return false;
		};
		
		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
		{
			return false;
		};
		
		@Override
		public boolean isFoil(ItemStack stack) 
		{
			return true;
		};
	});
	
	public static final Item CRYSTAL_SCARAB_AXE = ModRegistry.register("crystal_scarab_axe", new AxeItem(ModItemTier.CRYSTAL_SCARAB, 6.0F, -3.2F, new Properties().tab(ModUtils.ITEMS).setNoRepair())
	{
		@Override
		public boolean isEnchantable(ItemStack stack) 
		{
			return false;
		};
		
		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
		{
			return false;
		};
		
		@Override
		public boolean isFoil(ItemStack stack) 
		{
			return true;
		};
	});
	
	public static final Item CRYSTAL_SCARAB_HOE = ModRegistry.register("crystal_scarab_hoe", new HoeItem(ModItemTier.CRYSTAL_SCARAB, 0, -3.0F, new Properties().tab(ModUtils.ITEMS).setNoRepair())
	{
		@Override
		public boolean isEnchantable(ItemStack stack) 
		{
			return false;
		};
		
		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
		{
			return false;
		};
		
		@Override
		public boolean isFoil(ItemStack stack) 
		{
			return true;
		};
	});
	
	public static final Item LEATHER_BRUSH = ModRegistry.register("leather_brush", new BrushItem(ModItemTier.LEATHER));
	public static final Item IRON_BRUSH = ModRegistry.register("iron_brush", new BrushItem(ModItemTier.IRON));
	public static final Item GOLD_BRUSH = ModRegistry.register("gold_brush", new BrushItem(ModItemTier.GOLD));
	public static final Item DIAMOND_BRUSH = ModRegistry.register("diamond_brush", new BrushItem(ModItemTier.DIAMOND));
	public static final Item NETHERITE_BRUSH = ModRegistry.register("netherite_brush", new BrushItem(ModItemTier.NETHERITE));
	public static final Item CRYSTAL_SCARAB_BRUSH = ModRegistry.register("crystal_scarab_brush", new CrystalScarabGemBrushItem());
	
	public static final Item HAMMER = ModRegistry.register("hammer", new HammerItem(ItemTier.IRON, 6.0F, -3.1F, (new Item.Properties()).tab(ModUtils.ITEMS)));
	
	public static final Item CLOTH_MASK = ModRegistry.register("cloth_mask", new MaskItem(ModArmourMaterial.CLOTH_MASK));
	
	public static final Item OXYGEN_MASK = ModRegistry.register("oxygen_mask", new MaskItem(ModArmourMaterial.OXYGEN_MASK));
	public static final Item OXYGEN_TANK = ModRegistry.register("oxygen_tank", new OxygenTankItem());
	
	public static final Item WET_PAPER = ModRegistry.register("wet_paper", new ModItem());
	public static final Item FIELD_GUIDE = ModRegistry.register("field_guide", new FieldGuideItem());
	public static final Item TABLET = ModRegistry.register("tablet", new TabletItem());
	public static final Item CONTRACEPTIVES = ModRegistry.register("contraceptives", new ModItem());
	
	public static final Item PERMIAN_PERIOD_TIME_BOOK = ModRegistry.register("permian_period_time_book", new TimeBookItem(TimeEras.PERMIAN_PERIOD, DimensionInit.PERMIAN_WORLD));
	//public static final Item JURASSIC_ERA_TIME_BOOK = ModRegistry.register("leather_brush", TimeBookItem.create(TimeEras.JURASSIC_PERIOD));
	
	public static final Item MUD_BALL = ModRegistry.register("mud_ball", new ModItem());

	public static final Item COPPER_INGOT = ModRegistry.register("copper_ingot", new ModItem());
	public static final Item COPPER_NUGGET = ModRegistry.register("copper_nugget", new ModItem());

	public static final Item COPPER_WIRE = ModRegistry.register("copper_wire", new ModItem());
	public static final Item COMPUTER_FAN = ModRegistry.register("computer_fan", new ModItem());
	public static final Item COMPUTER_SCREEN = ModRegistry.register("computer_screen", new ModItem());
	public static final Item COMPUTER_FRAME = ModRegistry.register("computer_frame", new ModItem());
	public static final Item COMPUTER_STORAGE_PORT = ModRegistry.register("computer_storage_port", new ModItem());
	public static final Item MOTHERBOARD = ModRegistry.register("motherboard", new ModItem());
	public static final Item CPU = ModRegistry.register("cpu", new ModItem());
	public static final Item RAM = ModRegistry.register("ram", new ModItem());
	
	public static final Item COMPUTER_CORE = ModRegistry.register("computer_core", new ModItem());
	
	public static final Item STORAGE_DISC = ModRegistry.register("storage_disc", new ModItem());

	public static final Item TAG = ModRegistry.register("tag", new ModItem());	

	public static final Item AMBER = ModRegistry.register("amber", new ModItem());
	public static final Item GROUND_FOSSIL = ModRegistry.register("ground_fossil", new ModBoneMealItem());
	public static final Item PLANT_WASTE = ModRegistry.register("plant_waste", new ModBoneMealItem());

	public static final Item ARAUCARIA_BARK_SAMPLE = ModRegistry.register("araucaria_bark_sample", new ModItem());
	public static final Item CALAMITES_BARK_SAMPLE = ModRegistry.register("calamites_bark_sample", new ModItem());
	public static final Item CONIFER_BARK_SAMPLE = ModRegistry.register("conifer_bark_sample", new ModItem());
	public static final Item GINKGO_BARK_SAMPLE = ModRegistry.register("ginkgo_bark_sample", new ModItem());
	
	public static Item ALETHOPTERIS_FOSSIL;
	public static Item BRAZILEA_FOSSIL;
	public static Item CALAMITES_SUCKOWII_FOSSIL;
	public static Item CEPHALOTAXUS_FOSSIL;
	public static Item DILLHOFFIA_FOSSIL;
	public static Item DUISBERGIA_FOSSIL;
	public static Item OSMUNDA_FOSSIL;
	public static Item WILLIAMSONIA_FOSSIL;
	public static Item ZAMITES_FOSSIL;
	
	public static final Item EMPTY_VILE = ModRegistry.register("empty_vile", new ModItem());
	
	public static final Item AMBER_KEYCHAIN = ModRegistry.register("amber_keychain", new ModItem());
	public static final Item DINO_BUTTON = ModRegistry.register("dino_button", new ModItem());
	public static final Item DINO_SNOWGLOBE = ModRegistry.register("dino_snowglobe", new ModItem());
	public static final Item BALLOON = ModRegistry.register("balloon", new ModItem());
	public static final Item FUN_BAG = ModRegistry.register("fun_bag", new ModItem());
	public static final Item TYRANNOSAURUS_PLUSH = ModRegistry.register("tyrannosaurus_plush", new ModItem());	
	
	public static final Item FERN_LEAVES = ModRegistry.register("fern_leaves", new ModItem(FoodInit.FERN_LEAVES));
	public static final Item COOKED_FERN_LEAVES = ModRegistry.register("cooked_fern_leaves", new ModItem(FoodInit.COOKED_LEAVES));

	public static final Item PALEO_SALAD = ModRegistry.register("paleo_salad", new ModItem(FoodInit.PALEO_SALAD));
	
	public static final Item SCARAB_BANNER_PATTERN = ModRegistry.register("scarab_banner_pattern", new BannerPatternItem(BannerInit.SCARAB, new Properties().tab(ItemGroup.TAB_MISC).stacksTo(1)));
	
	public static final Item FOSSIL_POACHER_SPAWN_EGG = ModRegistry.register("fossil_poacher_spawn_egg", new ModSpawnEggItem(() -> EntityInit.FOSSIL_POACHER, 0x959b9b, 0x363031, ModUtils.ITEMS));
	
	public static void init() 
	{
		ModUtils.LOGGER.debug("Registering Mod Items"); 
		
		CrystalScarabGemItem.createAll();
		
		for(DinoTypes dinos : DinoTypes.values())
		{
			ModRegistry.register(dinos.name().toLowerCase() + "_rib_cage", new ModItem());
			ModRegistry.register(dinos.name().toLowerCase() + "_leg_bones", new ModItem());
			ModRegistry.register(dinos.name().toLowerCase() + "_arm_bones", new ModItem());
			ModRegistry.register(dinos.name().toLowerCase() + "_foot_bone", new ModItem());
			ModRegistry.register(dinos.name().toLowerCase() + "_skull", new ModItem());
			ModRegistry.register(dinos.name().toLowerCase() + "_special", new ModItem());
			ModRegistry.register(dinos.name().toLowerCase() + "_spawn_egg", new ModSpawnEggItem(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour(), ModUtils.ITEMS));
			ModRegistry.register(dinos.name().toLowerCase() + "_egg", new ModItem());
			ModRegistry.register("raw_" + dinos.name().toLowerCase() + "_meat", new Item(new Item.Properties().tab(ModUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())));
			ModRegistry.register("cooked_" + dinos.name().toLowerCase() + "_meat", new Item(new Item.Properties().tab(ModUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())));
		}
		
		for(Plants plants : Plants.values())
		{	
			Item fossil = ModRegistry.register(plants.toString().toLowerCase() + "_fossil", new PlantFossilItem(plants.toString().toLowerCase()));
			ModRegistry.register(plants.toString().toLowerCase() + "_soft_tissue", new PlantSoftTissueItem(plants.toString().toLowerCase()));
			ModRegistry.register(plants.toString().toLowerCase() + "_dna", new PlantDNAItem(plants.toString().toLowerCase()));		
			ModRegistry.register(plants.toString().toLowerCase() + "_dna_disc", new PlantDiscItem(plants.toString().toLowerCase()));
			if(plants == Plants.ALETHOPTERIS)
			{
				ALETHOPTERIS_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.ALETHOPTERIS);
			}
			if(plants == Plants.BRAZILEA)
			{
				BRAZILEA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.BRAZILEA);
			}
			if(plants == Plants.CALAMITES_SUCKOWII)
			{
				CALAMITES_SUCKOWII_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.CALAMITES_SUCKOWII);
			}
			if(plants == Plants.CEPHALOTAXUS)
			{
				CEPHALOTAXUS_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.CEPHALOTAXUS);
			}
			if(plants == Plants.DILLHOFFIA)
			{
				DILLHOFFIA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.DILLHOFFIA);
			}
			if(plants == Plants.DUISBERGIA)
			{
				DUISBERGIA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.DUISBERGIA);
			}
			if(plants == Plants.OSMUNDA)
			{
				OSMUNDA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.OSMUNDA);
			}
			if(plants == Plants.WILLIAMSONIA)
			{
				WILLIAMSONIA_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.WILLIAMSONIA);
			}
			if(plants == Plants.ZAMITES)
			{
				ZAMITES_FOSSIL = fossil;
				SeedItem.create(plants.toString().toLowerCase(), BlockInit.ZAMITES);
			}
		}
		
		for(Trees trees : Trees.values())
		{
			ModRegistry.register(trees.toString().toLowerCase() + "_soft_tissue", new PlantSoftTissueItem(trees.toString().toLowerCase()));
			ModRegistry.register(trees.toString().toLowerCase() + "_dna", new PlantDNAItem(trees.toString().toLowerCase()));		
			ModRegistry.register(trees.toString().toLowerCase() + "_dna_disc", new PlantDiscItem(trees.toString().toLowerCase()));
		}
	}
}
