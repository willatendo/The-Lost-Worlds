package lostworlds.server.item;

import static lostworlds.LostWorldsMod.CENTRAL_REGISTRATE;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import lostworlds.client.craft.AmberDNAExtractorRecipeManager;
import lostworlds.client.sounds.LostWorldsSounds;
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
import lostworlds.server.item.CrystalScarabGemItem.CEChargedCrystalScarabGemItem;
import lostworlds.server.item.CrystalScarabGemItem.CECrystalScarabGemItem;
import lostworlds.server.item.armour.MaskItem;
import lostworlds.server.item.armour.ModArmourMaterial;
import lostworlds.server.item.armour.PinItem;
import lostworlds.server.item.block.SeedItem;
import lostworlds.server.item.tool.BrushItem;
import lostworlds.server.item.tool.CrystalScarabGemBrushItem;
import lostworlds.server.item.tool.ModItemTier;
import lostworlds.server.util.LostWorldsRegistrate;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;

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
	public static final ItemEntry<WetPaperItem> WET_PAPER = REGISTRATE.item("wet_paper", WetPaperItem::new).lang("Wet Paper").register();
	public static final ItemEntry<SyringeItem> SYRINGE = REGISTRATE.item("syringe", SyringeItem::new).properties(properties -> properties.stacksTo(1)).lang("Syringe").register();
	public static final ItemEntry<LostWorldsLexicon> LOST_WORLDS_LEXICON = REGISTRATE.item("lost_worlds_lexicon", LostWorldsLexicon::new).properties(properties -> properties.stacksTo(1).rarity(Rarity.RARE).fireResistant()).lang("Lost Worlds Lexicon").register();
	public static final ItemEntry<FieldGuideItem> FIELD_GUIDE = REGISTRATE.item("field_guide", FieldGuideItem::new).properties(properties -> properties.stacksTo(1)).lang("Field Guide").register();
	public static final ItemEntry<TabletItem> TABLET = REGISTRATE.item("tablet", TabletItem::new).properties(Properties -> Properties.stacksTo(1)).lang("Tablet").register();
	public static final ItemEntry<Item> CONTRACEPTIVES = REGISTRATE.item("contraceptives", Item::new).lang("Contraceptives").register();

	public static final ItemEntry<TimeBookItem> PERMIAN_PERIOD_TIME_BOOK = REGISTRATE.item("permian_period_time_book", properties -> new TimeBookItem(properties, TimeEras.PERMIAN_PERIOD, LostWorldsDimensions.PERMIAN_WORLD)).lang("Permian Period Time Book").register(),
			JURASSIC_PERIOD_TIME_BOOK = REGISTRATE.item("jurassic_period_time_book", properties -> new TimeBookItem(properties, TimeEras.JURASSIC_PERIOD, LostWorldsDimensions.JURASSIC_WORLD)).lang("Jurassic Period Time Book").register(),
			CRETACEOUS_PERIOD_TIME_BOOK = REGISTRATE.item("cretaceous_period_time_book", properties -> new TimeBookItem(properties, TimeEras.CRETACEOUS_PERIOD, LostWorldsDimensions.CRETACEOUS_WORLD)).lang("Cretaceous Period Time Book").register();

	public static final ItemEntry<Item> MUD_BALL = REGISTRATE.item("mud_ball", Item::new).lang("Mud Ball").register();

	// Electronics
	public static final ItemEntry<Item> COPPER_WIRE = REGISTRATE.item("copper_wire", Item::new).lang("Copper Wire").register(),
			COMPUTER_FAN = REGISTRATE.item("computer_fan", Item::new).lang("Computer Fan").register(),
			COMPUTER_SCREEN = REGISTRATE.item("computer_screen", Item::new).lang("Computer Screen").register(),
			COMPUTER_FRAME = REGISTRATE.item("computer_frame", Item::new).model(custom((item, provider) -> provider.withExistingParent("computer_frame", provider.modLoc("item/computer_frame")))).lang("Computer Frame").register(),
			COMPUTER_STORAGE_PORT = REGISTRATE.item("computer_storage_port", Item::new).lang("Computer Storage Port").register(),
			MOTHERBOARD = REGISTRATE.item("motherboard", Item::new).lang("Motherboard").register(),
			CPU = REGISTRATE.item("cpu", Item::new).lang("CPU").register(),
			RAM = REGISTRATE.item("ram", Item::new).lang("RAM").register(),
			COMPUTER_CORE = REGISTRATE.item("computer_core", Item::new).model(custom((item, provider) -> provider.withExistingParent("computer_core", provider.modLoc("item/computer_core")))).lang("Computer Core").register(),
			STORAGE_DISC = REGISTRATE.item("storage_disc", Item::new).lang("Storage Disc").register(),
			TAG = REGISTRATE.item("tag", Item::new).lang("Tag").register();

	// Decoration
	public static final ItemEntry<CollectibleItem> AMBER_KEYCHAIN = REGISTRATE.item("amber_keychain", CollectibleItem::new).lang("Amber Keychain").register(),
			BALLOON = REGISTRATE.item("balloon", CollectibleItem::new).lang("Balloon").register(),
			TYRANNOSAURUS_PLUSH = REGISTRATE.item("tyrannosaurus_plush", CollectibleItem::new).model(custom((item, provider) -> provider.withExistingParent("tyrannosaurus_plush", provider.modLoc("item/tyrannosaurus_plush")))).lang("Tyrannosaurus Plush").register();
	public static final ItemEntry<PinItem> DINO_BUTTON = REGISTRATE.item("dino_button", properties -> new PinItem(ModArmourMaterial.DECO, EquipmentSlotType.CHEST, properties)).lang("Dino Button").register();

	// Miscellaneous
	public static final ItemEntry<Item> EMPTY_VILE = REGISTRATE.item("empty_vile", Item::new).register();

	public static final ItemEntry<Item> FERN_LEAVES = REGISTRATE.item("fern_leaves", Item::new).properties(properties -> properties.food(LostWorldsFoods.FERN_LEAVES)).lang("Fern Leaves").register(),
			COOKED_FERN_LEAVES = REGISTRATE.item("cooked_fern_leaves", Item::new).properties(properties -> properties.food(LostWorldsFoods.COOKED_LEAVES)).lang("Cooked Fern Leaves").register(),
			PALEO_SALAD = REGISTRATE.item("paleo_salad", Item::new).properties(properties -> properties.food(LostWorldsFoods.PALEO_SALAD)).lang("Paleo Salad").register();

	public static final ItemEntry<BannerPatternItem> SCARAB_BANNER_PATTERN = REGISTRATE.item("scarab_banner_pattern", properties -> new BannerPatternItem(LostWorldsBanners.SCARAB, properties)).lang("Banner Pattern").register();

	public static final ItemEntry<ModSpawnEggItem> FOSSIL_POACHER_SPAWN_EGG = REGISTRATE.item("fossil_poacher_spawn_egg", properties -> new ModSpawnEggItem(() -> LostWorldsEntities.FOSSIL_POACHER, 0x959b9b, 0x363031, properties)).properties(properties -> properties.tab(ItemGroup.TAB_MISC)).lang("Fossil Poacher Spawn Egg").model(spawnEgg()).register();

	public static final ItemEntry<AscentedMusicDiscItem> MUSIC_DISC_ASCENTED = REGISTRATE.item("music_disc_ascented", properties -> new AscentedMusicDiscItem(13, () -> LostWorldsSounds.ASCENTED, properties)).lang("Music Disc").register();

	// Fossils
	public static final ItemEntry<CEChargedCrystalScarabGemItem> CHARGED_CRYSTAL_SCARAB_GEM = REGISTRATE.item("charged_crystal_scarab_gem", CEChargedCrystalScarabGemItem::new).properties(properties -> properties.fireResistant().rarity(Rarity.RARE)).model(textured("crystal_scarab_gem")).lang("Charged Crystal Scarab Gem").register();
	public static final ItemEntry<CECrystalScarabGemItem> CRYSTAL_SCARAB_GEM = REGISTRATE.item("crystal_scarab_gem", CECrystalScarabGemItem::new).properties(properties -> properties.fireResistant().rarity(Rarity.RARE)).lang("Crystal Scarab Gem").register();
	public static final ItemEntry<Item> CRYSTAL_SCARAB_ABDOMEN = REGISTRATE.item("crystal_scarab_abdomen", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).lang("Crystal Scarab Abdomen").register(),
			CRYSTAL_SCARAB_BOTTOM_LEFT_LEG = REGISTRATE.item("crystal_scarab_bottom_left_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).lang("Crystal Scarab Bottom Left Leg").register(),
			CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG = REGISTRATE.item("crystal_scarab_bottom_right_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).lang("Crystal Scarab Bottom Right Leg").register(),
			CRYSTAL_SCARAB_THORAX = REGISTRATE.item("crystal_scarab_thorax", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).lang("Crystal Scarab Thorax").register(),
			CRYSTAL_SCARAB_TOP_LEFT_LEG = REGISTRATE.item("crystal_scarab_top_left_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).lang("Crystal Scarab Top Left Leg").register(),
			CRYSTAL_SCARAB_TOP_RIGHT_LEG = REGISTRATE.item("crystal_scarab_top_right_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).lang("Crystal Scarab Top Right Leg").register();

	public static final ItemEntry<AmberItem> AMBER = REGISTRATE.item("amber", AmberItem::new).lang("Amber").register();
	public static final ItemEntry<Item> FOSSILIZED_FEATHER = REGISTRATE.item("fossilized_feather", Item::new).lang("Fossilized Feather").register(),
			FOSSILIZED_SKIN_IMPRESSION = REGISTRATE.item("fossilized_skin_impression", Item::new).lang("Fossilized Skin Impression").register();
	public static final ItemEntry<ModBoneMealItem> GROUND_FOSSIL = REGISTRATE.item("ground_fossil", ModBoneMealItem::new).lang("Ground Fossil").register(),
			PLANT_WASTE = REGISTRATE.item("plant_waste", ModBoneMealItem::new).lang("Plant Waste").register();

	public static final ItemEntry<Item> ARAUCARIA_BARK_SAMPLE = REGISTRATE.item("araucaria_bark_sample", Item::new).tag(LostWorldsTags.ModItemTags.BARK_SAMPLES).register(),
			CALAMITES_BARK_SAMPLE = REGISTRATE.item("calamites_bark_sample", Item::new).tag(LostWorldsTags.ModItemTags.BARK_SAMPLES).register(),
			CONIFER_BARK_SAMPLE = REGISTRATE.item("conifer_bark_sample", Item::new).tag(LostWorldsTags.ModItemTags.BARK_SAMPLES).register(),
			CYPRESS_BARK_SAMPLE = REGISTRATE.item("cypress_bark_sample", Item::new).tag(LostWorldsTags.ModItemTags.BARK_SAMPLES).register(),
			GINKGO_BARK_SAMPLE = REGISTRATE.item("ginkgo_bark_sample", Item::new).tag(LostWorldsTags.ModItemTags.BARK_SAMPLES).register(),
			SEQUOIA_BARK_SAMPLE = REGISTRATE.item("sequoia_bark_sample", Item::new).tag(LostWorldsTags.ModItemTags.BARK_SAMPLES).register();

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Items");

		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				ItemEntry<FossilItem> plasteredRibCage = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_rib_cage", properties -> new FossilItem(properties, () -> dinos.getRibCage(), true)).model(textured("plastered_fossil")).register();
				dinos.setPlasteredRibCageItem(() -> plasteredRibCage.get());
				ItemEntry<FossilItem> plasteredLegBones = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_leg_bones", properties -> new FossilItem(properties, () -> dinos.getLegBones(), true)).model(textured("plastered_fossil")).register();
				dinos.setPlasteredLegBonesItem(() -> plasteredLegBones.get());
				ItemEntry<FossilItem> plasteredArmBones = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_arm_bones", properties -> new FossilItem(properties, () -> dinos.getArmBones(), true)).model(textured("plastered_fossil")).register();
				dinos.setPlasteredArmBonesItem(() -> plasteredArmBones.get());
				ItemEntry<FossilItem> plasteredTail = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_tail", properties -> new FossilItem(properties, () -> dinos.getTail(), true)).model(textured("plastered_fossil")).register();
				dinos.setPlasteredTailItem(() -> plasteredTail.get());
				ItemEntry<FossilItem> plasteredSkull = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_skull", properties -> new FossilItem(properties, () -> dinos.getSkull(), true)).model(textured("plastered_fossil")).register();
				dinos.setPlasteredSkullItem(() -> plasteredSkull.get());
				ItemEntry<FossilItem> ribCage = REGISTRATE.item(dinos.name().toLowerCase() + "_rib_cage", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("rib_cage")), () -> dinos.getRibCage(), false)).model(fossil()).register();
				dinos.setRibCageItem(() -> ribCage.get());
				ItemEntry<FossilItem> legBones = REGISTRATE.item(dinos.name().toLowerCase() + "_leg_bones", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("leg_bones")), () -> dinos.getLegBones(), false)).model(fossil()).register();
				dinos.setLegBonesItem(() -> legBones.get());
				ItemEntry<FossilItem> armBones = REGISTRATE.item(dinos.name().toLowerCase() + "_arm_bones", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("arm_bones")), () -> dinos.getArmBones(), false)).model(fossil()).register();
				dinos.setArmBonesItem(() -> armBones.get());
				ItemEntry<FossilItem> tail = REGISTRATE.item(dinos.name().toLowerCase() + "_tail", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("tail")), () -> dinos.getTail(), false)).model(fossil()).register();
				dinos.setTailItem(() -> tail.get());
				ItemEntry<FossilItem> skull = REGISTRATE.item(dinos.name().toLowerCase() + "_skull", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("skull")), () -> dinos.getSkull(), false)).model(fossil()).register();
				dinos.setSkullItem(() -> skull.get());
				ItemEntry<FossilItem> skeleton = REGISTRATE.item(dinos.name().toLowerCase() + "_skeleton", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER()), () -> dinos.getSkeleton(), false)).model(fossil()).register();
				dinos.setSkeletonPick(() -> skeleton.get());
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_exoskeleton", properties -> new FossilItem(properties, () -> dinos.getExoskeleton(), true)).model(textured("plastered_fossil")).register();
				REGISTRATE.item(dinos.name().toLowerCase() + "_exoskeleton", properties -> new FossilItem(properties, () -> dinos.getExoskeleton(), false)).register();
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_body", properties -> new FossilItem(properties, () -> dinos.getBody(), true)).model(textured("plastered_fossil")).register();
				REGISTRATE.item(dinos.name().toLowerCase() + "_body", properties -> new FossilItem(properties, () -> dinos.getBody(), false)).register();
			}

			if (dinos.feathered().contains(dinos)) {
				ItemEntry<Item> feather = REGISTRATE.item(dinos.name().toLowerCase() + "_feather", Item::new).tag(Tags.Items.FEATHERS).register();
				dinos.setFeather(() -> feather.get());
			}
			if (dinos.createHide().contains(dinos)) {
				REGISTRATE.item(dinos.name().toLowerCase() + "_hide", Item::new).model(textured("plastered_fossil")).tag(Tags.Items.LEATHER).register();
			}
			if (dinos.hasSpawn().contains(dinos)) {
				ItemEntry<SpawnItem> spawn = REGISTRATE.item(dinos.name().toLowerCase() + "_spawn", properties -> new SpawnItem(properties, () -> dinos.getEntityType())).model(model("template_spawn")).register();
				dinos.setSpawn(() -> spawn.get());
			}

			REGISTRATE.item(dinos.name().toLowerCase() + "_spawn_egg", properties -> new ModSpawnEggItem(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour(), properties)).properties(properties -> properties.tab(ItemGroup.TAB_MISC)).model(spawnEgg()).register();
			ItemEntry<Item> softTissue = REGISTRATE.item(dinos.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("soft_tissue")).register();
			dinos.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> bloodSample = REGISTRATE.item(dinos.name().toLowerCase() + "_blood_syringe", Item::new).model(textured("blood_syringe")).tag(LostWorldsTags.ModItemTags.BLOOD_SYRINGES).register();
			dinos.setBloodSample(() -> bloodSample.get());
			REGISTRATE.item(dinos.name().toLowerCase() + "_blood_vile", Item::new).model(textured("blood_vile")).tag(LostWorldsTags.ModItemTags.BLOOD_VILES).register();
			ItemEntry<Item> dna = REGISTRATE.item(dinos.name().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).register();
			dinos.setDNA(() -> dna.get());
			ItemEntry<Item> dnaDisc = REGISTRATE.item(dinos.name().toLowerCase() + "_dna_disc", Item::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			dinos.setDNADisc(() -> dnaDisc.get());
			if (!dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_meat", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).model(mcTextured("barrier")).register();
				REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_meat", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).model(mcTextured("barrier")).register();
				dinos.setMeat(() -> raw.get());
			} else if (dinos == DinoTypes.NAUTILUS) {
				ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_tentacle", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).fast().build())).model(mcTextured("barrier")).register();
				REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_tentacle", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).fast().build())).model(mcTextured("barrier")).register();
				dinos.setMeat(() -> raw.get());
			} else if (dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				ItemEntry<ModFishBucketItem> bucket = REGISTRATE.item(dinos.name().toLowerCase() + "_bucket", properties -> new ModFishBucketItem(() -> dinos.getEntityType(), Fluids.WATER, properties)).register();
				dinos.setFishBucket(() -> bucket.get());
				if (dinos != DinoTypes.ANOMALOCARIS) {
					ItemEntry<Item> meat = REGISTRATE.item(dinos.name().toLowerCase(), Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).model(mcTextured("barrier")).register();
					REGISTRATE.item("cooked_" + dinos.name().toLowerCase(), Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).model(mcTextured("barrier")).register();
					dinos.setMeat(() -> meat.get());
				} else {
					ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_claw", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).model(mcTextured("barrier")).register();
					REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_claw", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).model(mcTextured("barrier")).register();
					dinos.setMeat(() -> raw.get());
				}
			}
		}

		for (Plants plants : Plants.values()) {
			ItemEntry<Item> item = REGISTRATE.item(plants.toString().toLowerCase() + "_fossil", Item::new).model(textured("plant_fossil")).register();
			plants.setDrop(() -> item.get());
			REGISTRATE.item(plants.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("soft_tissue")).register();
			REGISTRATE.item(plants.toString().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).model(textured("plant_dna")).register();
			REGISTRATE.item(plants.toString().toLowerCase() + "_dna_disc", Item::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			ItemEntry<SeedItem> seed = REGISTRATE.item(plants.toString().toLowerCase() + "_seeds", properties -> new SeedItem(plants.getPlant(), properties)).tag(LostWorldsTags.ModItemTags.ANCIENT_SEEDS).register();
			plants.setSeed(() -> seed.get());
		}

		for (Trees trees : Trees.values()) {
			REGISTRATE.item(trees.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("soft_tissue")).register();
			ItemEntry<Item> dna = REGISTRATE.item(trees.toString().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).model(textured("plant_dna")).register();
			trees.setDNA(() -> dna.get());
			REGISTRATE.item(trees.toString().toLowerCase() + "_dna_disc", Item::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
		}

		RecipeManager.initAlternateRecipes();
		AmberDNAExtractorRecipeManager.init();
		Foods.init();
	}

	public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> custom(NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> consumer) {
		return consumer;
	}

	public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> fossil() {
		ModelFile parent = new ModelFile(new ResourceLocation("builtin/entity")) {
			@Override
			protected boolean exists() {
				return true;
			}
		};
		return (item, provider) -> provider.getBuilder(item.get().getRegistryName().getPath()).parent(parent).transforms().transform(Perspective.THIRDPERSON_RIGHT).rotation(75, 45, 0).translation(0, 2.5F, 0).scale(0.375F).end().transform(Perspective.THIRDPERSON_LEFT).rotation(75, 45, 0).translation(0, 2.5F, 0).scale(0.375F).end().transform(Perspective.FIRSTPERSON_RIGHT).rotation(0, 45, 0).translation(1, -1.5F, 1.5F).scale(0.4F).end().transform(Perspective.FIRSTPERSON_LEFT).rotation(0, 45, 0).translation(1, -1.5F, 1.5F).scale(0.4F).end().transform(Perspective.GROUND).translation(0, 3, 0).scale(0.25F).end().transform(Perspective.GUI).rotation(30, 225, 0).translation(1.75F, -4.5F, 0).scale(0.3F).end().transform(Perspective.FIXED).rotation(0, -90, 0).translation(2.25F, -3.75F, 0).scale(0.3F).end().end();
	}

	public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> spawnEgg() {
		return (item, provider) -> provider.withExistingParent(item.get().getRegistryName().getPath(), provider.mcLoc("item/template_spawn_egg"));
	}

	public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> textured(String model) {
		return (item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + model));
	}

	public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> mcTextured(String model) {
		return (item, provider) -> provider.generated(() -> item.get(), provider.mcLoc("item/" + model));
	}

	public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> model(String model) {
		return (item, provider) -> provider.withExistingParent(item.get().getRegistryName().getPath(), provider.modLoc("item/" + model));
	}
}
