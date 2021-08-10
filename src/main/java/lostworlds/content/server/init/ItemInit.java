package lostworlds.content.server.init;

import lostworlds.library.enums.TimeEras;
import lostworlds.library.item.BoneMealItem;
import lostworlds.library.item.BrushItem;
import lostworlds.library.item.BrushItem.Teirs;
import lostworlds.library.item.CrystalScarabGemItem.Variant;
import lostworlds.library.item.CrystalScarabAxeItem;
import lostworlds.library.item.CrystalScarabGemItem;
import lostworlds.library.item.CrystalScarabHoeItem;
import lostworlds.library.item.CrystalScarabPickaxeItem;
import lostworlds.library.item.CrystalScarabShovelItem;
import lostworlds.library.item.CrystalScarabSwordItem;
import lostworlds.library.item.FieldGuideItem;
import lostworlds.library.item.FoodItem;
import lostworlds.library.item.FoodItem.FoodType;
import lostworlds.library.item.basic.BasicItem;
import lostworlds.library.item.FoodSeedsItem;
import lostworlds.library.item.TimeBookItem;
import lostworlds.library.util.ModUtil;
import net.minecraft.item.Item;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class ItemInit
{	
	public static final Item LEATHER_BRUSH = BrushItem.create(Teirs.LEATHER);
	public static final Item IRON_BRUSH = BrushItem.create(Teirs.IRON);
	public static final Item GOLD_BRUSH = BrushItem.create(Teirs.GOLD);
	public static final Item DIAMOND_BRUSH = BrushItem.create(Teirs.DIAMOND);
	public static final Item NETHERITE_BRUSH = BrushItem.create(Teirs.NETHERITE);
	public static final Item CRYSTAL_SCARAB_BRUSH = BrushItem.create(Teirs.CRYSTAL_SCARAB);
	
	public static final Item WET_PAPER = BasicItem.create("wet_paper");
	
	public static final Item FIELD_GUIDE = FieldGuideItem.create();
	
	public static final Item EMPTY_VILE = BasicItem.create("empty_vile");
	
	public static final Item PERMIAN_PERIOD_TIME_BOOK = TimeBookItem.create(TimeEras.PERMIAN_PERIOD);
	//public static final Item JURASSIC_ERA_TIME_BOOK = JurassicTimeBook.create();
	
	public static final Item FERN_LEAVES = FoodItem.create(FoodType.FERN_LEAVES);
	public static final Item COOKED_FERN_LEAVES = FoodItem.create(FoodType.COOKED_FERN_LEAVES);
	
	public static final Item CYCAD_SEEDS = FoodSeedsItem.create("cycad");
	public static final Item OSMUNDA_SEEDS = FoodSeedsItem.create("osmunda");
	public static final Item DUISBERGIA_SEEDS = FoodSeedsItem.create("duisbergia");
	public static final Item CEPHALOTAXUS_SEEDS = FoodSeedsItem.create("cephalotaxus");
	public static final Item LYCOPHYTA_SEEDS = FoodSeedsItem.create("lycophyta");
	public static final Item DILLHOFFIA_SEEDS = FoodSeedsItem.create("dillhoffia");
	
	public static final Item PALEO_SALAD = FoodItem.create(FoodType.PALEO_SALAD);
	
	public static final Item GROUND_FOSSIL = BoneMealItem.create("ground_fossil");
	
	public static final Item AMBER = BasicItem.create("amber");
	
	public static final Item MUD_BALL = BasicItem.create("mud_ball");
	
	public static final Item COPPER_INGOT = BasicItem.create("copper_ingot");
	public static final Item COPPER_NUGGET = BasicItem.create("copper_nugget");
	
	public static final Item COPPER_WIRE = BasicItem.create("copper_wire");
	public static final Item COMPUTER_FAN = BasicItem.create("computer_fan");
	public static final Item COMPUTER_SCREEN = BasicItem.create("computer_screen");
	public static final Item COMPUTER_FRAME = BasicItem.create("computer_frame");
	public static final Item COMPUTER_STORAGE_PORT = BasicItem.create("computer_storage_port");
	public static final Item MOTHERBOARD = BasicItem.create("motherboard");
	public static final Item CPU = BasicItem.create("cpu");
	public static final Item RAM = BasicItem.create("ram");
	
	public static final Item COMPUTER_CORE = BasicItem.create("computer_core");
	
	public static final Item STORAGE_DISC = BasicItem.create("storage_disc");
	
	public static final Item BROKEN_CRYSTAL_SCARAB_GEM = CrystalScarabGemItem.create(Variant.BROKEN);
	public static final Item UNCHARGED_CRYSTAL_SCARAB_GEM = CrystalScarabGemItem.create(Variant.UNCHARGED);
	public static final Item CHARGED_CRYSTAL_SCARAB_GEM = CrystalScarabGemItem.create(Variant.CHARGED);
	
	public static final Item CRYSTAL_SCARAB_SWORD = CrystalScarabSwordItem.create();
	public static final Item CRYSTAL_SCARAB_SHOVEL = CrystalScarabShovelItem.create();
	public static final Item CRYSTAL_SCARAB_PICKAXE = CrystalScarabPickaxeItem.create();
	public static final Item CRYSTAL_SCARAB_AXE = CrystalScarabAxeItem.create();
	public static final Item CRYSTAL_SCARAB_HOE = CrystalScarabHoeItem.create();
		
	//Registry
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Items");}
}
