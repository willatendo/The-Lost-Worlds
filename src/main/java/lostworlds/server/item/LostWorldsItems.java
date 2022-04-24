package lostworlds.server.item;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import lostworlds.client.craft.AmberDNAExtractorRecipeManager;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.block.utils.Foods;
import lostworlds.server.container.recipes.RecipeManager;
import lostworlds.server.dimension.LostWorldsDimensions;
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
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;

public class LostWorldsItems {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate().itemGroup(() -> LostWorldsUtils.ITEMS);

	// Copper
	public static final ItemEntry<Item> COPPER_INGOT = REGISTRATE.item("copper_ingot", Item::new).recipe((item, provider) -> {
		provider.blasting(DataIngredient.items(LostWorldsBlocks.COPPER_ORE.get()), () -> item.get(), 0.7F);
		provider.smelting(DataIngredient.items(LostWorldsBlocks.COPPER_ORE.get()), () -> item.get(), 0.7F);
		provider.square(DataIngredient.items(LostWorldsItems.COPPER_NUGGET.get()), () -> item.get(), false);
	}).register(),
			COPPER_NUGGET = REGISTRATE.item("copper_nugget", Item::new).recipe((item, provider) -> ShapelessRecipeBuilder.shapeless(item.get()).requires(LostWorldsItems.COPPER_INGOT.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_INGOT.get())).save(provider)).register();

	// Tools
	public static final ItemEntry<CrystalScarabSwordItem> CRYSTAL_SCARAB_SWORD = REGISTRATE.item("crystal_scarab_sword", properties -> new CrystalScarabSwordItem(ModItemTier.CRYSTAL_SCARAB, 3, -2.4F, properties.setNoRepair())).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_SWORD), Ingredient.of(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_sword"))).register();
	public static final ItemEntry<CrystalScarabShovelItem> CRYSTAL_SCARAB_SHOVEL = REGISTRATE.item("crystal_scarab_shovel", properties -> new CrystalScarabShovelItem(ModItemTier.CRYSTAL_SCARAB, 1.5F, -3.0F, properties.setNoRepair())).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_SHOVEL), Ingredient.of(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_shovel"))).register();
	public static final ItemEntry<CrystalScarabPickaxeItem> CRYSTAL_SCARAB_PICKAXE = REGISTRATE.item("crystal_scarab_pickaxe", properties -> new CrystalScarabPickaxeItem(ModItemTier.CRYSTAL_SCARAB, 1, -2.8F, properties.setNoRepair())).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_PICKAXE), Ingredient.of(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_pickaxe"))).register();
	public static final ItemEntry<CrystalScarabAxeItem> CRYSTAL_SCARAB_AXE = REGISTRATE.item("crystal_scarab_axe", properties -> new CrystalScarabAxeItem(ModItemTier.CRYSTAL_SCARAB, 6.0F, -3.2F, properties.setNoRepair())).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_AXE), Ingredient.of(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_hoe"))).register();
	public static final ItemEntry<CrystalScarabHoeItem> CRYSTAL_SCARAB_HOE = REGISTRATE.item("crystal_scarab_hoe", properties -> new CrystalScarabHoeItem(ModItemTier.CRYSTAL_SCARAB, 0, -3.0F, properties.setNoRepair())).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_HOE), Ingredient.of(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_axe"))).register();

	public static final ItemEntry<BrushItem> LEATHER_BRUSH = REGISTRATE.item("leather_brush", properties -> new BrushItem(ModItemTier.LEATHER, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			IRON_BRUSH = REGISTRATE.item("iron_brush", properties -> new BrushItem(ModItemTier.IRON, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			GOLD_BRUSH = REGISTRATE.item("gold_brush", properties -> new BrushItem(ModItemTier.GOLD, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			DIAMOND_BRUSH = REGISTRATE.item("diamond_brush", properties -> new BrushItem(ModItemTier.DIAMOND, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).register(),
			NETHERITE_BRUSH = REGISTRATE.item("netherite_brush", properties -> new BrushItem(ModItemTier.NETHERITE, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).register();
	public static final ItemEntry<CrystalScarabGemBrushItem> CRYSTAL_SCARAB_BRUSH = REGISTRATE.item("crystal_scarab_brush", properties -> new CrystalScarabGemBrushItem(properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).register();

	public static final ItemEntry<HammerItem> HAMMER = REGISTRATE.item("hammer", properties -> new HammerItem(ItemTier.IRON, 6.0F, -3.1F, properties)).register();

	public static final ItemEntry<ChiselItem> CHISEL = REGISTRATE.item("chisel", properites -> new ChiselItem(properites.stacksTo(1).defaultDurability(32))).register();

	// Armour
	public static final ItemEntry<MaskItem> CLOTH_MASK = REGISTRATE.item("cloth_mask", properties -> new MaskItem(ModArmourMaterial.CLOTH_MASK, properties)).register();

	// Utilities
	public static final ItemEntry<WetPaperItem> WET_PAPER = REGISTRATE.item("wet_paper", WetPaperItem::new).register();
	public static final ItemEntry<SyringeItem> SYRINGE = REGISTRATE.item("syringe", SyringeItem::new).properties(properties -> properties.stacksTo(1)).register();
	public static final ItemEntry<LostWorldsLexicon> LOST_WORLDS_LEXICON = REGISTRATE.item("lost_worlds_lexicon", LostWorldsLexicon::new).properties(properties -> properties.stacksTo(1).rarity(Rarity.RARE).fireResistant()).register();
	public static final ItemEntry<FieldGuideItem> FIELD_GUIDE = REGISTRATE.item("field_guide", FieldGuideItem::new).properties(properties -> properties.stacksTo(1)).register();
	public static final ItemEntry<TabletItem> TABLET = REGISTRATE.item("tablet", TabletItem::new).properties(Properties -> Properties.stacksTo(1)).register();
	public static final ItemEntry<Item> CONTRACEPTIVES = REGISTRATE.item("contraceptives", Item::new).register();

	public static final ItemEntry<TimeBookItem> PERMIAN_PERIOD_TIME_BOOK = REGISTRATE.item("permian_period_time_book", properties -> new TimeBookItem(properties, TimeEras.PERMIAN_PERIOD, LostWorldsDimensions.PERMIAN_WORLD)).tag(LostWorldsTags.ModItemTags.TIME_BOOKS).register(),
			JURASSIC_PERIOD_TIME_BOOK = REGISTRATE.item("jurassic_period_time_book", properties -> new TimeBookItem(properties, TimeEras.JURASSIC_PERIOD, LostWorldsDimensions.JURASSIC_WORLD)).tag(LostWorldsTags.ModItemTags.TIME_BOOKS).register(),
			CRETACEOUS_PERIOD_TIME_BOOK = REGISTRATE.item("cretaceous_period_time_book", properties -> new TimeBookItem(properties, TimeEras.CRETACEOUS_PERIOD, LostWorldsDimensions.CRETACEOUS_WORLD)).tag(LostWorldsTags.ModItemTags.TIME_BOOKS).register();

	public static final ItemEntry<Item> MUD_BALL = REGISTRATE.item("mud_ball", Item::new).register();

	// Electronics
	public static final ItemEntry<Item> COPPER_WIRE = REGISTRATE.item("copper_wire", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			COMPUTER_FAN = REGISTRATE.item("computer_fan", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			COMPUTER_SCREEN = REGISTRATE.item("computer_screen", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			COMPUTER_FRAME = REGISTRATE.item("computer_frame", Item::new).model(custom((item, provider) -> provider.withExistingParent("computer_frame", provider.modLoc("item/computer_frame")))).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			COMPUTER_STORAGE_PORT = REGISTRATE.item("computer_storage_port", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			MOTHERBOARD = REGISTRATE.item("motherboard", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			CPU = REGISTRATE.item("cpu", Item::new).register(),
			RAM = REGISTRATE.item("ram", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			COMPUTER_CORE = REGISTRATE.item("computer_core", Item::new).model(custom((item, provider) -> provider.withExistingParent("computer_core", provider.modLoc("item/computer_core")))).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			STORAGE_DISC = REGISTRATE.item("storage_disc", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register(),
			TAG = REGISTRATE.item("tag", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).register();

	// Decoration
	public static final ItemEntry<CollectibleItem> AMBER_KEYCHAIN = REGISTRATE.item("amber_keychain", CollectibleItem::new).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).register(),
			BALLOON = REGISTRATE.item("balloon", CollectibleItem::new).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).register(),
			TYRANNOSAURUS_PLUSH = REGISTRATE.item("tyrannosaurus_plush", CollectibleItem::new).model(custom((item, provider) -> provider.withExistingParent("tyrannosaurus_plush", provider.modLoc("item/tyrannosaurus_plush")))).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).register();

	public static final ItemEntry<PinItem> DINO_BUTTON = REGISTRATE.item("dino_button", properties -> new PinItem(ModArmourMaterial.DECO, EquipmentSlotType.CHEST, properties)).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).register();

	// Miscellaneous
	public static final ItemEntry<Item> EMPTY_VILE = REGISTRATE.item("empty_vile", Item::new).register();

	public static final ItemEntry<Item> FERN_LEAVES = REGISTRATE.item("fern_leaves", Item::new).properties(properties -> properties.food(LostWorldsFoods.FERN_LEAVES)).register(),
			COOKED_FERN_LEAVES = REGISTRATE.item("cooked_fern_leaves", Item::new).properties(properties -> properties.food(LostWorldsFoods.COOKED_LEAVES)).register(),
			PALEO_SALAD = REGISTRATE.item("paleo_salad", Item::new).properties(properties -> properties.food(LostWorldsFoods.PALEO_SALAD)).register();

	public static final ItemEntry<BannerPatternItem> SCARAB_BANNER_PATTERN = REGISTRATE.item("scarab_banner_pattern", properties -> new BannerPatternItem(LostWorldsBanners.SCARAB, properties)).register();

	public static final ItemEntry<AscentedMusicDiscItem> MUSIC_DISC_ASCENTED = REGISTRATE.item("music_disc_ascented", properties -> new AscentedMusicDiscItem(13, () -> LostWorldsSounds.ASCENTED, properties)).register();

	// Fossils
	public static final ItemEntry<CEChargedCrystalScarabGemItem> CHARGED_CRYSTAL_SCARAB_GEM = REGISTRATE.item("charged_crystal_scarab_gem", CEChargedCrystalScarabGemItem::new).properties(properties -> properties.fireResistant().rarity(Rarity.RARE)).model(textured("crystal_scarab_gem")).register();

	public static final ItemEntry<CECrystalScarabGemItem> CRYSTAL_SCARAB_GEM = REGISTRATE.item("crystal_scarab_gem", CECrystalScarabGemItem::new).properties(properties -> properties.fireResistant().rarity(Rarity.RARE)).register();
	public static final ItemEntry<Item> CRYSTAL_SCARAB_ABDOMEN = REGISTRATE.item("crystal_scarab_abdomen", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_BOTTOM_LEFT_LEG = REGISTRATE.item("crystal_scarab_bottom_left_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG = REGISTRATE.item("crystal_scarab_bottom_right_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_THORAX = REGISTRATE.item("crystal_scarab_thorax", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_TOP_LEFT_LEG = REGISTRATE.item("crystal_scarab_top_left_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_TOP_RIGHT_LEG = REGISTRATE.item("crystal_scarab_top_right_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register();

	public static final ItemEntry<AmberItem> AMBER = REGISTRATE.item("amber", AmberItem::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.TRACE_FOSSILS).register();
	public static final ItemEntry<Item> FOSSILIZED_FEATHER = REGISTRATE.item("fossilized_feather", Item::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.TRACE_FOSSILS).register(),
			FOSSILIZED_SKIN_IMPRESSION = REGISTRATE.item("fossilized_skin_impression", Item::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.TRACE_FOSSILS).register();
	public static final ItemEntry<ModBoneMealItem> GROUND_FOSSIL = REGISTRATE.item("ground_fossil", ModBoneMealItem::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register(),
			PLANT_WASTE = REGISTRATE.item("plant_waste", ModBoneMealItem::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();

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
				ItemEntry<FossilItem> plasteredRibCage = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_rib_cage", properties -> new FossilItem(properties, () -> dinos.getRibCage(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILIS).register();
				dinos.setPlasteredRibCageItem(() -> plasteredRibCage.get());
				ItemEntry<FossilItem> plasteredLegBones = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_leg_bones", properties -> new FossilItem(properties, () -> dinos.getLegBones(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILIS).register();
				dinos.setPlasteredLegBonesItem(() -> plasteredLegBones.get());
				ItemEntry<FossilItem> plasteredArmBones = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_arm_bones", properties -> new FossilItem(properties, () -> dinos.getArmBones(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILIS).register();
				dinos.setPlasteredArmBonesItem(() -> plasteredArmBones.get());
				ItemEntry<FossilItem> plasteredTail = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_tail", properties -> new FossilItem(properties, () -> dinos.getTail(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILIS).register();
				dinos.setPlasteredTailItem(() -> plasteredTail.get());
				ItemEntry<FossilItem> plasteredSkull = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_skull", properties -> new FossilItem(properties, () -> dinos.getSkull(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILIS).register();
				dinos.setPlasteredSkullItem(() -> plasteredSkull.get());
				ItemEntry<FossilItem> ribCage = REGISTRATE.item(dinos.name().toLowerCase() + "_rib_cage", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("rib_cage")), () -> dinos.getRibCage(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, dinos.getFossilTag()).register();
				dinos.setRibCageItem(() -> ribCage.get());
				ItemEntry<FossilItem> legBones = REGISTRATE.item(dinos.name().toLowerCase() + "_leg_bones", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("leg_bones")), () -> dinos.getLegBones(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, dinos.getFossilTag()).register();
				dinos.setLegBonesItem(() -> legBones.get());
				ItemEntry<FossilItem> armBones = REGISTRATE.item(dinos.name().toLowerCase() + "_arm_bones", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("arm_bones")), () -> dinos.getArmBones(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, dinos.getFossilTag()).register();
				dinos.setArmBonesItem(() -> armBones.get());
				ItemEntry<FossilItem> tail = REGISTRATE.item(dinos.name().toLowerCase() + "_tail", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("tail")), () -> dinos.getTail(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, dinos.getFossilTag()).register();
				dinos.setTailItem(() -> tail.get());
				ItemEntry<FossilItem> skull = REGISTRATE.item(dinos.name().toLowerCase() + "_skull", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("skull")), () -> dinos.getSkull(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, dinos.getFossilTag()).register();
				dinos.setSkullItem(() -> skull.get());
				ItemEntry<FossilItem> skeleton = REGISTRATE.item(dinos.name().toLowerCase() + "_skeleton", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER()), () -> dinos.getSkeleton(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.SKELETONS, dinos.getFossilTag()).register();
				dinos.setSkeletonPick(() -> skeleton.get());
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_exoskeleton", properties -> new FossilItem(properties, () -> dinos.getExoskeleton(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				REGISTRATE.item(dinos.name().toLowerCase() + "_exoskeleton", properties -> new FossilItem(properties, () -> dinos.getExoskeleton(), false)).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_body", properties -> new FossilItem(properties, () -> dinos.getBody(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				REGISTRATE.item(dinos.name().toLowerCase() + "_body", properties -> new FossilItem(properties, () -> dinos.getBody(), false)).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
			}

			if (dinos.feathered().contains(dinos)) {
				ItemEntry<Item> feather = REGISTRATE.item(dinos.name().toLowerCase() + "_feather", Item::new).tag(Tags.Items.FEATHERS, LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				dinos.setFeather(() -> feather.get());
			}
			if (dinos.createHide().contains(dinos)) {
				REGISTRATE.item(dinos.name().toLowerCase() + "_hide", Item::new).model(textured("plastered_fossil")).tag(Tags.Items.LEATHER, LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
			}
			if (dinos.hasSpawn().contains(dinos)) {
				ItemEntry<SpawnItem> spawn = REGISTRATE.item(dinos.name().toLowerCase() + "_spawn", properties -> new SpawnItem(properties, () -> dinos.getEntityType())).model(model("template_spawn")).color(() -> () -> (stack, colour) -> dinos.getColour(colour, 0x000000, dinos.getSetEggColour())).register();
				dinos.setSpawn(() -> spawn.get());
			}

			REGISTRATE.item(dinos.name().toLowerCase() + "_spawn_egg", properties -> new ModSpawnEggItem(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour(), properties)).properties(properties -> properties.tab(ItemGroup.TAB_MISC)).model(spawnEgg()).register();
			ItemEntry<Item> softTissue = REGISTRATE.item(dinos.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("soft_tissue")).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).register();
			dinos.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> bloodSample = REGISTRATE.item(dinos.name().toLowerCase() + "_blood_syringe", Item::new).model(textured("blood_syringe")).tag(LostWorldsTags.ModItemTags.BLOOD_SYRINGES).register();
			dinos.setBloodSample(() -> bloodSample.get());
			REGISTRATE.item(dinos.name().toLowerCase() + "_blood_vile", Item::new).model(textured("blood_vile")).tag(LostWorldsTags.ModItemTags.BLOOD_VILES).register();
			ItemEntry<Item> dna = REGISTRATE.item(dinos.name().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).register();
			dinos.setDNA(() -> dna.get());
			ItemEntry<Item> dnaDisc = REGISTRATE.item(dinos.name().toLowerCase() + "_dna_disc", Item::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			dinos.setDNADisc(() -> dnaDisc.get());
			if (!dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_meat", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_meat", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				dinos.setMeat(() -> raw.get());
			} else if (dinos == DinoTypes.NAUTILUS) {
				ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_tentacle", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).fast().build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_tentacle", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).fast().build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				dinos.setMeat(() -> raw.get());
			} else if (dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				ItemEntry<ModFishBucketItem> bucket = REGISTRATE.item(dinos.name().toLowerCase() + "_bucket", properties -> new ModFishBucketItem(() -> dinos.getEntityType(), Fluids.WATER, properties)).register();
				dinos.setFishBucket(() -> bucket.get());
				if (dinos != DinoTypes.ANOMALOCARIS) {
					ItemEntry<Item> meat = REGISTRATE.item(dinos.name().toLowerCase(), Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
					REGISTRATE.item("cooked_" + dinos.name().toLowerCase(), Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
					dinos.setMeat(() -> meat.get());
				} else {
					ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_claw", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
					REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_claw", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).model(mcTextured("barrier")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
					dinos.setMeat(() -> raw.get());
				}
			}
		}

		for (Plants plants : Plants.values()) {
			ItemEntry<Item> fossil = REGISTRATE.item(plants.toString().toLowerCase() + "_fossil", Item::new).model(textured("plant_fossil")).tag(LostWorldsTags.ModItemTags.PLANT_FOSSILS).register();
			plants.setDrop(() -> fossil.get());
			ItemEntry<Item> softTissue = REGISTRATE.item(plants.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("soft_tissue")).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).register();
			plants.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> dna = REGISTRATE.item(plants.toString().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).model(textured("plant_dna")).register();
			plants.setDNA(() -> dna.get());
			ItemEntry<Item> dnaDisc = REGISTRATE.item(plants.toString().toLowerCase() + "_dna_disc", Item::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			plants.setDNADisc(() -> dnaDisc.get());
			ItemEntry<SeedItem> seed = REGISTRATE.item(plants.toString().toLowerCase() + "_seeds", properties -> new SeedItem(plants.getPlant(), properties)).tag(LostWorldsTags.ModItemTags.ANCIENT_SEEDS).register();
			plants.setSeed(() -> seed.get());
		}

		for (Trees trees : Trees.values()) {
			ItemEntry<Item> softTissue = REGISTRATE.item(trees.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("soft_tissue")).register();
			trees.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> dna = REGISTRATE.item(trees.toString().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).model(textured("plant_dna")).register();
			trees.setDNA(() -> dna.get());
			ItemEntry<Item> dnaDisc = REGISTRATE.item(trees.toString().toLowerCase() + "_dna_disc", Item::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			trees.setDNADisc(() -> dnaDisc.get());
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
