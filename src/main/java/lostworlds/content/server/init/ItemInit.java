package lostworlds.content.server.init;

import lostworlds.library.entity.TimeEras;
import lostworlds.library.item.BoneMealItem;
import lostworlds.library.item.CrystalScarabGemItem;
import lostworlds.library.item.FieldGuideItem;
import lostworlds.library.item.FoodItem;
import lostworlds.library.item.FoodItem.FoodType;
import lostworlds.library.item.HammerItem;
import lostworlds.library.item.TimeBookItem;
import lostworlds.library.item.armour.MaskItem;
import lostworlds.library.item.armour.OxygenTankItem;
import lostworlds.library.item.builder.ItemBuilder;
import lostworlds.library.item.builder.SpawnEggItemBuilder;
import lostworlds.library.item.tool.BrushItem;
import lostworlds.library.item.tool.CrystalScarabGemBrushItem;
import lostworlds.library.item.tool.ModItemTier;
import lostworlds.library.item.tool.ToolSetBuilder;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;

public class ItemInit
{	
	private static final Properties PROPERTIES = new Properties().tab(ItemGroup.TAB_MISC);

	public static final Item CRYSTAL_SCARAB = ToolSetBuilder.create(ModItemTier.CRYSTAL_SCARAB);
	
	public static final Item LEATHER_BRUSH = BrushItem.createLeather();
	public static final Item IRON_BRUSH = BrushItem.createIron();
	public static final Item GOLD_BRUSH = BrushItem.createGold();
	public static final Item DIAMOND_BRUSH = BrushItem.createDiamond();
	public static final Item NETHERITE_BRUSH = BrushItem.createNetherite();
	public static final Item CRYSTAL_SCARAB_BRUSH = CrystalScarabGemBrushItem.create();
	
	public static final Item HAMMER = ModRegistry.register("hammer", new HammerItem(ItemTier.IRON, 6.0F, -3.1F, (new Item.Properties()).tab(ModItemGroup.ITEMS)));
	
	public static final Item CLOTH_MASK = MaskItem.createCloth();
	
	public static final Item OXYGEN_MASK = MaskItem.createOxygen();
	public static final Item OXYGEN_TANK = ModRegistry.register("oxygen_tank", new OxygenTankItem());
	
	public static final Item WET_PAPER = ItemBuilder.create("wet_paper");
	public static final Item FIELD_GUIDE = FieldGuideItem.create();
	
	public static final Item PERMIAN_PERIOD_TIME_BOOK = TimeBookItem.create(TimeEras.PERMIAN_PERIOD, DimensionInit.PERMIAN_WORLD);
	//public static final Item JURASSIC_ERA_TIME_BOOK = TimeBookItem.create(TimeEras.JURASSIC_PERIOD);

	public static final Item AMBER = ItemBuilder.create("amber");
	public static final Item GROUND_FOSSIL = BoneMealItem.create("ground_fossil");
	public static final Item PLANT_WASTE = BoneMealItem.create("plant_waste");

	public static final Item ARAUCARIA_BARK_SAMPLE = ItemBuilder.create("araucaria_bark_sample", ModItemGroup.PLANTS);
	public static final Item CALAMITES_BARK_SAMPLE = ItemBuilder.create("calamites_bark_sample", ModItemGroup.PLANTS);
	public static final Item CONIFER_BARK_SAMPLE = ItemBuilder.create("conifer_bark_sample", ModItemGroup.PLANTS);
	public static final Item GINKGO_BARK_SAMPLE = ItemBuilder.create("ginkgo_bark_sample", ModItemGroup.PLANTS);
	
	public static final Item EMPTY_VILE = ItemBuilder.create("empty_vile");
	
	public static final Item COPPER_INGOT = ItemBuilder.create("copper_ingot");
	public static final Item COPPER_NUGGET = ItemBuilder.create("copper_nugget");

	public static final Item COPPER_WIRE = ItemBuilder.create("copper_wire");
	public static final Item COMPUTER_FAN = ItemBuilder.create("computer_fan");
	public static final Item COMPUTER_SCREEN = ItemBuilder.create("computer_screen");
	public static final Item COMPUTER_FRAME = ItemBuilder.create("computer_frame");
	public static final Item COMPUTER_STORAGE_PORT = ItemBuilder.create("computer_storage_port");
	public static final Item MOTHERBOARD = ItemBuilder.create("motherboard");
	public static final Item CPU = ItemBuilder.create("cpu");
	public static final Item RAM = ItemBuilder.create("ram");
	
	public static final Item COMPUTER_CORE = ItemBuilder.create("computer_core");
	
	public static final Item STORAGE_DISC = ItemBuilder.create("storage_disc");
	
	public static final Item MUD_BALL = ItemBuilder.create("mud_ball");

	public static final Item CRYSTAL_SCARAB_GEMS = CrystalScarabGemItem.createAll();
	
	public static final Item AMBER_KEYCHAIN = ItemBuilder.create("amber_keychain");
	public static final Item DINO_BUTTON = ItemBuilder.create("raptor_button");
	public static final Item DINO_SNOWGLOBE = ItemBuilder.create("dino_snowglobe");
	public static final Item BALLON = createPerColour("balloon");
	public static final Item FUN_BAG = ItemBuilder.create("fun_bag");
	public static final Item TYRANNOSAURUS_PLUSH = ItemBuilder.create("tyrannosaurus_plush");	
	
	public static final Item FERN_LEAVES = FoodItem.create(FoodType.FERN_LEAVES);
	public static final Item COOKED_FERN_LEAVES = FoodItem.create(FoodType.COOKED_FERN_LEAVES);

	public static final Item PALEO_SALAD = FoodItem.create(FoodType.PALEO_SALAD);
	
	public static final Item SCARAB_BANNER_PATTERN = ModRegistry.register("scarab_banner_pattern", new BannerPatternItem(BannerInit.SCARAB, PROPERTIES.stacksTo(1)));
	
	public static final Item FOSSIL_POACHER_SPAWN_EGG = SpawnEggItemBuilder.create("fossil_poacher_spawn_egg", () -> EntityInit.FOSSIL_POACHER, 0x959b9b, 0x363031);
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Items"); }
	
	public static Item createPerColour(String id)
	{
		for(DyeColor colour : DyeColor.values())
		{
			ItemBuilder.create(colour.getName() + "_" + id);
		}
		return null;
	}
}
