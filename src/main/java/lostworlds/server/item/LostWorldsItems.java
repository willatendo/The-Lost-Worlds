package lostworlds.server.item;

import static lostworlds.LostWorldsMod.CENTRAL_REGISTRATE;

import com.tterrag.registrate.util.entry.ItemEntry;

import lostworlds.client.craft.AmberDNAExtractorRecipeManager;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.block.utils.Foods;
import lostworlds.server.container.recipes.RecipeManager;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.item.armour.MaskItem;
import lostworlds.server.item.armour.ModArmourMaterial;
import lostworlds.server.item.armour.PinItem;
import lostworlds.server.item.block.SeedItem;
import lostworlds.server.item.tool.BrushItem;
import lostworlds.server.item.tool.CrystalScarabGemBrushItem;
import lostworlds.server.item.tool.ModItemTier;
import lostworlds.server.util.LostWorldsRegistrate;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;

public class LostWorldsItems {
	private static final LostWorldsRegistrate REGISTRATE = CENTRAL_REGISTRATE.get().itemGroup(() -> LostWorldsUtils.ITEMS);

	// Tools
	public static final ItemEntry<CrystalScarabSwordItem> CRYSTAL_SCARAB_SWORD = REGISTRATE.item("crystal_scarab_sword", properties -> new CrystalScarabSwordItem(ModItemTier.CRYSTAL_SCARAB, 3, -2.4F, properties.setNoRepair())).lang("Crystal Scarab Sword").register();
	public static final ItemEntry<CrystalScarabShovelItem> CRYSTAL_SCARAB_SHOVEL = REGISTRATE.item("crystal_scarab_shovel", properties -> new CrystalScarabShovelItem(ModItemTier.CRYSTAL_SCARAB, 1.5F, -3.0F, properties.setNoRepair())).lang("Crystal Scarab Shovel").register();
	public static final ItemEntry<CrystalScarabPickaxeItem> CRYSTAL_SCARAB_PICKAXE = REGISTRATE.item("crystal_scarab_pickaxe", properties -> new CrystalScarabPickaxeItem(ModItemTier.CRYSTAL_SCARAB, 1, -2.8F, properties.setNoRepair())).lang("Crystal Scarab Pickaxe").register();
	public static final ItemEntry<CrystalScarabAxeItem> CRYSTAL_SCARAB_AXE = REGISTRATE.item("crystal_scarab_axe", properties -> new CrystalScarabAxeItem(ModItemTier.CRYSTAL_SCARAB, 6.0F, -3.2F, properties.setNoRepair())).lang("Crystal Scarab Axe").register();
	public static final ItemEntry<CrystalScarabHoeItem> CRYSTAL_SCARAB_HOE = REGISTRATE.item("crystal_scarab_hoe", properties -> new CrystalScarabHoeItem(ModItemTier.CRYSTAL_SCARAB, 0, -3.0F, properties.setNoRepair())).lang("Crystal Scarab Hoe").register();

	public static final ItemEntry<BrushItem> LEATHER_BRUSH = REGISTRATE.item("leather_brush", properties -> new BrushItem(ModItemTier.LEATHER, properties)).lang("Leather Brush").tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			IRON_BRUSH = REGISTRATE.item("iron_brush", properties -> new BrushItem(ModItemTier.IRON, properties)).lang("Iron Brush").tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			GOLD_BRUSH = REGISTRATE.item("gold_brush", properties -> new BrushItem(ModItemTier.GOLD, properties)).lang("Gold Brush").tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			DIAMOND_BRUSH = REGISTRATE.item("diamond_brush", properties -> new BrushItem(ModItemTier.DIAMOND, properties)).lang("Diamond Brush").tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			NETHERITE_BRUSH = REGISTRATE.item("netherite_brush", properties -> new BrushItem(ModItemTier.NETHERITE, properties)).lang("Netherite Brush").tag(LostWorldsTags.ModItemTags.BRUSHES).register();
	public static final ItemEntry<CrystalScarabGemBrushItem> CRYSTAL_SCARAB_BRUSH = REGISTRATE.item("crystal_scarab_brush", properties -> new CrystalScarabGemBrushItem(properties)).lang("Crystal Scarab Brush").tag(LostWorldsTags.ModItemTags.BRUSHES).register();

	public static final ItemEntry<HammerItem> HAMMER = REGISTRATE.item("hammer", properties -> new HammerItem(ItemTier.IRON, 6.0F, -3.1F, properties)).lang("Hammer").register();

	public static final ItemEntry<ChiselItem> CHISEL = REGISTRATE.item("chisel", properites -> new ChiselItem(properites.stacksTo(1).defaultDurability(32))).lang("Chisel").register();

	// Armour
	public static final ItemEntry<MaskItem> CLOTH_MASK = REGISTRATE.item("cloth_mask", properties -> new MaskItem(ModArmourMaterial.CLOTH_MASK, properties)).lang("Cloth Mask").register();

	// Utilities
	public static final ItemEntry<WetPaperItem> WET_PAPER = REGISTRATE.item("wet_paper", properties -> new WetPaperItem(properties)).lang("Wet Paper").register();
	public static final ItemEntry<SyringeItem> SYRINGE = REGISTRATE.item("syringe", properties -> new SyringeItem(properties.stacksTo(1))).lang("Syringe").register();
	public static final ItemEntry<LostWorldsLexicon> LOST_WORLDS_LEXICON = REGISTRATE.item("lost_worlds_lexicon", properties -> new LostWorldsLexicon(properties.stacksTo(1).rarity(Rarity.RARE).fireResistant())).lang("Lost Worlds Lexicon").register();
	public static final Item FIELD_GUIDE = LostWorldsRegistry.register("field_guide", new FieldGuideItem());
	public static final Item TABLET = LostWorldsRegistry.register("tablet", new TabletItem());
	public static final Item CONTRACEPTIVES = LostWorldsRegistry.register("contraceptives", new ModItem());

	public static final Item PERMIAN_PERIOD_TIME_BOOK = LostWorldsRegistry.register("permian_period_time_book", new TimeBookItem(TimeEras.PERMIAN_PERIOD, LostWorldsDimensions.PERMIAN_WORLD));
	public static final Item JURASSIC_PERIOD_TIME_BOOK = LostWorldsRegistry.register("jurassic_period_time_book", new TimeBookItem(TimeEras.JURASSIC_PERIOD, LostWorldsDimensions.JURASSIC_WORLD));
	public static final Item CRETACEOUS_PERIOD_TIME_BOOK = LostWorldsRegistry.register("cretaceous_period_time_book", new TimeBookItem(TimeEras.CRETACEOUS_PERIOD, LostWorldsDimensions.CRETACEOUS_WORLD));

	public static final Item MUD_BALL = LostWorldsRegistry.register("mud_ball", new ModItem());

	// Electronics
	public static final Item COPPER_WIRE = LostWorldsRegistry.register("copper_wire", new ModItem());
	public static final Item COMPUTER_FAN = LostWorldsRegistry.register("computer_fan", new ModItem());
	public static final Item COMPUTER_SCREEN = LostWorldsRegistry.register("computer_screen", new ModItem());
	public static final Item COMPUTER_FRAME = LostWorldsRegistry.register("computer_frame", new ModItem());
	public static final Item COMPUTER_STORAGE_PORT = LostWorldsRegistry.register("computer_storage_port", new ModItem());
	public static final Item MOTHERBOARD = LostWorldsRegistry.register("motherboard", new ModItem());
	public static final Item CPU = LostWorldsRegistry.register("cpu", new ModItem());
	public static final Item RAM = LostWorldsRegistry.register("ram", new ModItem());

	public static final Item COMPUTER_CORE = LostWorldsRegistry.register("computer_core", new ModItem());

	public static final Item STORAGE_DISC = LostWorldsRegistry.register("storage_disc", new ModItem());

	public static final Item TAG = LostWorldsRegistry.register("tag", new ModItem(16));

	// Decoration
	public static final Item AMBER_KEYCHAIN = LostWorldsRegistry.register("amber_keychain", new CollectibleItem());
	public static final Item DINO_BUTTON = LostWorldsRegistry.register("dino_button", new PinItem());
	public static final Item BALLOON = LostWorldsRegistry.register("balloon", new CollectibleItem());
	public static final Item TYRANNOSAURUS_PLUSH = LostWorldsRegistry.register("tyrannosaurus_plush", new CollectibleItem());

	// Miscellaneous
	public static final Item EMPTY_VILE = LostWorldsRegistry.register("empty_vile", new ModItem());

	public static final Item FERN_LEAVES = LostWorldsRegistry.register("fern_leaves", new ModItem(LostWorldsFoods.FERN_LEAVES));
	public static final Item COOKED_FERN_LEAVES = LostWorldsRegistry.register("cooked_fern_leaves", new ModItem(LostWorldsFoods.COOKED_LEAVES));

	public static final Item PALEO_SALAD = LostWorldsRegistry.register("paleo_salad", new ModItem(LostWorldsFoods.PALEO_SALAD));

	public static final Item SCARAB_BANNER_PATTERN = LostWorldsRegistry.register("scarab_banner_pattern", new BannerPatternItem(LostWorldsBanners.SCARAB, new Properties().tab(ItemGroup.TAB_MISC).stacksTo(1)));

	public static final Item FOSSIL_POACHER_SPAWN_EGG = LostWorldsRegistry.register("fossil_poacher_spawn_egg", new ModSpawnEggItem(() -> LostWorldsEntities.FOSSIL_POACHER, 0x959b9b, 0x363031, ItemGroup.TAB_MISC));

	public static final Item MUSIC_DISC_ASCENTED = LostWorldsRegistry.register("music_disc_ascented", new MusicDiscItem(13, () -> LostWorldsSounds.ASCENTED, new Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.RARE)));

	// Fossils
	public static final Item AMBER = LostWorldsRegistry.register("amber", new AmberItem());
	public static final Item FOSSILIZED_FEATHER = LostWorldsRegistry.register("fossilized_feather", new ModItem());
	public static final Item FOSSILIZED_SKIN_IMPRESSION = LostWorldsRegistry.register("fossilized_skin_impression", new ModItem());
	public static final Item GROUND_FOSSIL = LostWorldsRegistry.register("ground_fossil", new ModBoneMealItem());
	public static final Item PLANT_WASTE = LostWorldsRegistry.register("plant_waste", new ModBoneMealItem());

	public static final Item ARAUCARIA_BARK_SAMPLE = LostWorldsRegistry.register("araucaria_bark_sample", new ModItem());
	public static final Item CALAMITES_BARK_SAMPLE = LostWorldsRegistry.register("calamites_bark_sample", new ModItem());
	public static final Item CONIFER_BARK_SAMPLE = LostWorldsRegistry.register("conifer_bark_sample", new ModItem());
	public static final Item CYPRESS_BARK_SAMPLE = LostWorldsRegistry.register("cypress_bark_sample", new ModItem());
	public static final Item GINKGO_BARK_SAMPLE = LostWorldsRegistry.register("ginkgo_bark_sample", new ModItem());
	public static final Item SEQUOIA_BARK_SAMPLE = LostWorldsRegistry.register("sequoia_bark_sample", new ModItem());

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Items");

		CrystalScarabGemItem.createAll();

		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				Item plasteredRibCage = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_rib_cage", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getRibCage(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "rib_cage")));
				dinos.setPlasteredRibCageItem(plasteredRibCage);
				Item plasteredLegBones = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_leg_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getLegBones(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "leg_bones")));
				dinos.setPlasteredLegBonesItem(plasteredLegBones);
				Item plasteredArmBones = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_arm_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getArmBones(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "arm_bones")));
				dinos.setPlasteredArmBonesItem(plasteredArmBones);
				Item plasteredTail = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_tail", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getTail(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "tail")));
				dinos.setPlasteredTailItem(plasteredTail);
				Item plasteredSkull = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_skull", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getSkull(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "skull")));
				dinos.setPlasteredSkullItem(plasteredSkull);
				Item ribCage = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_rib_cage", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("rib_cage")), () -> dinos.getRibCage(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "rib_cage")));
				dinos.setRibCageItem(ribCage);
				Item legBones = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_leg_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("leg_bones")), () -> dinos.getLegBones(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "leg_bones")));
				dinos.setLegBonesItem(legBones);
				Item armBones = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_arm_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("arm_bones")), () -> dinos.getArmBones(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "arm_bones")));
				dinos.setArmBonesItem(armBones);
				Item tail = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_tail", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("tail")), () -> dinos.getTail(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "tail")));
				dinos.setTailItem(tail);
				Item skull = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_skull", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("skull")), () -> dinos.getSkull(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "skull")));
				dinos.setSkullItem(skull);
				Item skeleton = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_skeleton", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER()), () -> dinos.getSkeleton(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "skeleton")));
				dinos.setSkeletonPick(skeleton);
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_exoskeleton", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getExoskeleton(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "exoskeleton")));
				LostWorldsRegistry.register(dinos.name().toLowerCase() + "_exoskeleton", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getExoskeleton(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "exoskeleton")));
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_body", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getBody(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "body")));
				LostWorldsRegistry.register(dinos.name().toLowerCase() + "_body", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getBody(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "body")));
			}

			if (dinos.feathered().contains(dinos)) {
				Item feather = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_feather", new FeatherItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
				dinos.setFeather(feather);
			}
			if (dinos.createHide().contains(dinos)) {
				LostWorldsRegistry.register(dinos.name().toLowerCase() + "_hide", new HideItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			}
			if (dinos.hasSpawn().contains(dinos)) {
				Item spawn = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_spawn", new SpawnItem(() -> dinos.getEntityType(), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
				dinos.setSpawn(spawn);
			}

			LostWorldsRegistry.register(dinos.name().toLowerCase() + "_spawn_egg", new DinoSpawnEggItem(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour(), ItemGroup.TAB_MISC, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			Item softTissue = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_soft_tissue", new SoftTissueItem(LostWorldsUtils.tTC("entity", dinos.toString().toLowerCase())));
			dinos.setSoftTissue(softTissue);
			Item bloodSample = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_blood_sample", new FullSyringeItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			dinos.setBloodSample(bloodSample);
			Item dna = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_dna", new DNAItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			dinos.setDNA(dna);
			Item dnaDisc = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_dna_disc", new DNADiscItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			dinos.setDNADisc(dnaDisc);
			if (!dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				Item meat = LostWorldsRegistry.register("raw_" + dinos.name().toLowerCase() + "_meat", new DinoFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), true));
				LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase() + "_meat", new DinoFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), false));
				dinos.setMeat(meat);
			} else if (dinos == DinoTypes.NAUTILUS) {
				Item meat = LostWorldsRegistry.register("raw_" + dinos.name().toLowerCase() + "_tentacle", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).fast().build())));
				LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase() + "_tentacle", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).fast().build())));
				dinos.setMeat(meat);
			} else if (dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				Item bucket = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_bucket", new ModFishBucketItem(() -> dinos.getEntityType(), Fluids.WATER, new Properties().tab(LostWorldsUtils.ITEMS).stacksTo(1)));
				dinos.setFishBucket(bucket);
				if (dinos != DinoTypes.ANOMALOCARIS) {
					Item meat = LostWorldsRegistry.register(dinos.name().toLowerCase(), new FishFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), true));
					LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase(), new FishFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), false));
					dinos.setMeat(meat);
				} else {
					Item meat = LostWorldsRegistry.register("raw_" + dinos.name().toLowerCase() + "_claw", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())));
					LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase() + "_claw", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())));
					dinos.setMeat(meat);
				}
			}
		}

		for (Plants plants : Plants.values()) {
			Item item = LostWorldsRegistry.register(plants.toString().toLowerCase() + "_fossil", new PlantFossilItem(LostWorldsUtils.tTC("block", plants.toString().toLowerCase())));
			plants.setDrop(item);
			LostWorldsRegistry.register(plants.toString().toLowerCase() + "_soft_tissue", new SoftTissueItem(LostWorldsUtils.tTC("block", plants.toString().toLowerCase())));
			LostWorldsRegistry.register(plants.toString().toLowerCase() + "_dna", new DNAItem(LostWorldsUtils.tTC("block", plants.toString().toLowerCase())));
			LostWorldsRegistry.register(plants.toString().toLowerCase() + "_dna_disc", new DNADiscItem(LostWorldsUtils.tTC("block", plants.toString().toLowerCase())));
			Item seed = SeedItem.create(plants.toString().toLowerCase(), plants.getPlant(), LostWorldsUtils.tTC("block", plants.toString().toLowerCase()));
			plants.setSeed(seed);
		}

		for (Trees trees : Trees.values()) {
			LostWorldsRegistry.register(trees.toString().toLowerCase() + "_soft_tissue", new SoftTissueItem(LostWorldsUtils.tTC("tree", trees.toString().toLowerCase())));
			LostWorldsRegistry.register(trees.toString().toLowerCase() + "_dna", new DNAItem(LostWorldsUtils.tTC("tree", trees.toString().toLowerCase())));
			LostWorldsRegistry.register(trees.toString().toLowerCase() + "_dna_disc", new DNADiscItem(LostWorldsUtils.tTC("tree", trees.toString().toLowerCase())));
		}

		RecipeManager.initAlternateRecipes();
		AmberDNAExtractorRecipeManager.init();
		Foods.init();
	}
}