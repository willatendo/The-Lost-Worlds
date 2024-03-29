package lostworlds.server.item;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.Plants;
import lostworlds.server.block.Trees;
import lostworlds.server.block.utils.Foods;
import lostworlds.server.container.recipes.data.ArchaeologyTableRecipeBuilder;
import lostworlds.server.container.recipes.data.PaleontologyTableRecipeBuilder;
import lostworlds.server.container.recipes.data.TimeMachineRecipeBuilder;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
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
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.block.Blocks;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;

public class LostWorldsItems {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate().itemGroup(() -> LostWorldsUtils.ITEMS, "Lost Worlds Items");

	// Copper
	public static final ItemEntry<Item> COPPER_INGOT = REGISTRATE.item("copper_ingot", Item::new).recipe((item, provider) -> {
		provider.smeltingAndBlasting(DataIngredient.items(LostWorldsBlocks.COPPER_ORE.get()), () -> item.get(), 0.7F);
		provider.square(DataIngredient.items(LostWorldsItems.COPPER_NUGGET.get()), () -> item.get(), false);
	}).register(),
			COPPER_NUGGET = REGISTRATE.item("copper_nugget", Item::new).recipe((item, provider) -> ShapelessRecipeBuilder.shapeless(item.get(), 9).requires(LostWorldsItems.COPPER_INGOT.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_INGOT.get())).save(provider)).register();

	// Tools
	public static final ItemEntry<CrystalScarabSwordItem> CRYSTAL_SCARAB_SWORD = REGISTRATE.item("crystal_scarab_sword", properties -> new CrystalScarabSwordItem(ModItemTier.CRYSTAL_SCARAB, 3, -2.4F, properties)).properties(properties -> properties.setNoRepair().rarity(Rarity.RARE)).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_SWORD), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_sword"))).model((item, provider) -> provider.handheld(() -> item.get())).register();
	public static final ItemEntry<CrystalScarabShovelItem> CRYSTAL_SCARAB_SHOVEL = REGISTRATE.item("crystal_scarab_shovel", properties -> new CrystalScarabShovelItem(ModItemTier.CRYSTAL_SCARAB, 1.5F, -3.0F, properties)).properties(properties -> properties.setNoRepair().rarity(Rarity.RARE)).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_SHOVEL), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_shovel"))).model((item, provider) -> provider.handheld(() -> item.get())).register();
	public static final ItemEntry<CrystalScarabPickaxeItem> CRYSTAL_SCARAB_PICKAXE = REGISTRATE.item("crystal_scarab_pickaxe", properties -> new CrystalScarabPickaxeItem(ModItemTier.CRYSTAL_SCARAB, 1, -2.8F, properties)).properties(properties -> properties.setNoRepair().rarity(Rarity.RARE)).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_PICKAXE), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_pickaxe"))).model((item, provider) -> provider.handheld(() -> item.get())).register();
	public static final ItemEntry<CrystalScarabAxeItem> CRYSTAL_SCARAB_AXE = REGISTRATE.item("crystal_scarab_axe", properties -> new CrystalScarabAxeItem(ModItemTier.CRYSTAL_SCARAB, 6.0F, -3.2F, properties)).properties(properties -> properties.setNoRepair().rarity(Rarity.RARE)).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_AXE), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_hoe"))).model((item, provider) -> provider.handheld(() -> item.get())).register();
	public static final ItemEntry<CrystalScarabHoeItem> CRYSTAL_SCARAB_HOE = REGISTRATE.item("crystal_scarab_hoe", properties -> new CrystalScarabHoeItem(ModItemTier.CRYSTAL_SCARAB, 0, -3.0F, properties)).properties(properties -> properties.setNoRepair().rarity(Rarity.RARE)).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_HOE), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_axe"))).model((item, provider) -> provider.handheld(() -> item.get())).register();

	public static final ItemEntry<BrushItem> LEATHER_BRUSH = REGISTRATE.item("leather_brush", properties -> new BrushItem(ModItemTier.LEATHER, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("$$$").pattern(" $ ").define('#', Items.STRING).define('$', Items.STICK).unlockedBy("has_item", provider.hasItem(Items.STRING)).save(provider)).model((item, provider) -> provider.handheld(() -> item.get())).register(),
			IRON_BRUSH = REGISTRATE.item("iron_brush", properties -> new BrushItem(ModItemTier.IRON, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("$$$").pattern(" $ ").define('#', Items.STRING).define('$', Items.IRON_INGOT).unlockedBy("has_item", provider.hasItem(Items.STRING)).save(provider)).model((item, provider) -> provider.handheld(() -> item.get())).register(),
			GOLD_BRUSH = REGISTRATE.item("gold_brush", properties -> new BrushItem(ModItemTier.GOLD, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("$$$").pattern(" $ ").define('#', Items.STRING).define('$', Items.GOLD_INGOT).unlockedBy("has_item", provider.hasItem(Items.STRING)).save(provider)).model((item, provider) -> provider.handheld(() -> item.get())).register(),
			DIAMOND_BRUSH = REGISTRATE.item("diamond_brush", properties -> new BrushItem(ModItemTier.DIAMOND, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("$$$").pattern(" $ ").define('#', Items.STRING).define('$', Items.DIAMOND).unlockedBy("has_item", provider.hasItem(Items.STRING)).save(provider)).model((item, provider) -> provider.handheld(() -> item.get())).register(),
			NETHERITE_BRUSH = REGISTRATE.item("netherite_brush", properties -> new BrushItem(ModItemTier.NETHERITE, properties)).tag(LostWorldsTags.ModItemTags.BRUSHES).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(LostWorldsItems.DIAMOND_BRUSH.get()), Ingredient.of(Items.NETHERITE_INGOT), item.get()).unlocks("has_item", provider.hasItem(Items.NETHERITE_INGOT)).save(provider, LostWorldsUtils.rL("netherite_brush"))).model((item, provider) -> provider.handheld(() -> item.get())).register();
	public static final ItemEntry<CrystalScarabGemBrushItem> CRYSTAL_SCARAB_BRUSH = REGISTRATE.item("crystal_scarab_brush", properties -> new CrystalScarabGemBrushItem(properties)).properties(properties -> properties.setNoRepair().rarity(Rarity.RARE)).tag(LostWorldsTags.ModItemTags.BRUSHES).recipe((item, provider) -> SmithingRecipeBuilder.smithing(Ingredient.of(LostWorldsItems.NETHERITE_BRUSH.get()), Ingredient.of(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()), item.get()).unlocks("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider, LostWorldsUtils.rL("crystal_scarab_brush"))).model((item, provider) -> provider.handheld(() -> item.get())).register();

	public static final ItemEntry<HammerItem> HAMMER = REGISTRATE.item("hammer", properties -> new HammerItem(ItemTier.IRON, 6.0F, -3.1F, properties)).model((item, provider) -> provider.handheld(() -> item.get())).register();

	public static final ItemEntry<ChiselItem> CHISEL = REGISTRATE.item("chisel", properites -> new ChiselItem(properites.stacksTo(1).defaultDurability(32))).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("%$").pattern("#%").define('#', Items.STICK).define('%', Tags.Items.LEATHER).define('$', Items.IRON_INGOT).unlockedBy("has_item", provider.hasItem(Items.IRON_INGOT)).save(provider)).model((item, provider) -> provider.handheld(() -> item.get())).register();

	// Armour
	public static final ItemEntry<MaskItem> CLOTH_MASK = REGISTRATE.item("cloth_mask", properties -> new MaskItem(ModArmourMaterial.CLOTH_MASK, properties)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("%#%").pattern("$#$").define('%', Items.IRON_NUGGET).define('#', ItemTags.WOOL).define('$', Items.STRING).unlockedBy("has_item", provider.hasItem(ItemTags.WOOL)).save(provider)).register();

	// Utilities
	public static final ItemEntry<WetPaperItem> WET_PAPER = REGISTRATE.item("wet_paper", WetPaperItem::new).recipe((item, provider) -> ShapelessRecipeBuilder.shapeless(item.get(), 8).requires(Ingredient.of(Items.PAPER), 8).requires(Items.WATER_BUCKET).unlockedBy("has_item", provider.hasItem(Items.WATER_BUCKET)).save(provider)).register();
	public static final ItemEntry<SyringeItem> SYRINGE = REGISTRATE.item("syringe", SyringeItem::new).properties(properties -> properties.stacksTo(1)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern(" $#").pattern(" #$").pattern("@  ").define('$', ItemTags.STONE_CRAFTING_MATERIALS).define('#', Blocks.GLASS_PANE).define('@', Items.GOLD_INGOT).unlockedBy("has_item", provider.hasItem(Items.GOLD_INGOT)).save(provider)).register();
	public static final ItemEntry<LexiconItem> LOST_WORLDS_LEXICON = REGISTRATE.item("lost_worlds_lexicon", LexiconItem::new).properties(properties -> properties.stacksTo(1).rarity(Rarity.RARE).fireResistant()).register();
	public static final ItemEntry<FieldGuideItem> FIELD_GUIDE = REGISTRATE.item("field_guide", FieldGuideItem::new).properties(properties -> properties.stacksTo(1)).register();
	public static final ItemEntry<TabletItem> TABLET = REGISTRATE.item("tablet", TabletItem::new).properties(Properties -> Properties.stacksTo(1)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("#@#").pattern("#$#").define('#', Items.IRON_INGOT).define('@', LostWorldsItems.COMPUTER_SCREEN.get()).define('$', LostWorldsItems.COMPUTER_CORE.get()).unlockedBy("has_item", provider.hasItem(Items.IRON_INGOT)).save(provider)).register();
	public static final ItemEntry<Item> CONTRACEPTIVES = REGISTRATE.item("contraceptives", Item::new).register();

	public static final ItemEntry<TimeBookItem> PERMIAN_PERIOD_TIME_BOOK = REGISTRATE.item("permian_period_time_book", properties -> new TimeBookItem(properties, TimeEras.PERMIAN_PERIOD, LostWorldsDimensions.PERMIAN_WORLD)).properties(properties -> properties.stacksTo(1).setNoRepair().rarity(Rarity.RARE)).tag(LostWorldsTags.ModItemTags.TIME_BOOKS).recipe((item, provider) -> TimeMachineRecipeBuilder.simple(item.get(), 1).unlockedBy("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider)).register(),
			JURASSIC_PERIOD_TIME_BOOK = REGISTRATE.item("jurassic_period_time_book", properties -> new TimeBookItem(properties, TimeEras.JURASSIC_PERIOD, LostWorldsDimensions.JURASSIC_WORLD)).properties(properties -> properties.stacksTo(1).setNoRepair().rarity(Rarity.RARE)).tag(LostWorldsTags.ModItemTags.TIME_BOOKS).recipe((item, provider) -> TimeMachineRecipeBuilder.simple(item.get(), 1).unlockedBy("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider)).register(),
			CRETACEOUS_PERIOD_TIME_BOOK = REGISTRATE.item("cretaceous_period_time_book", properties -> new TimeBookItem(properties, TimeEras.CRETACEOUS_PERIOD, LostWorldsDimensions.CRETACEOUS_WORLD)).properties(properties -> properties.stacksTo(1).setNoRepair().rarity(Rarity.RARE)).tag(LostWorldsTags.ModItemTags.TIME_BOOKS).recipe((item, provider) -> TimeMachineRecipeBuilder.simple(item.get(), 1).unlockedBy("has_item", provider.hasItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get())).save(provider)).register();

	public static final ItemEntry<Item> MUD_BALL = REGISTRATE.item("mud_ball", Item::new).register();

	// Electronics
	public static final ItemEntry<Item> COPPER_WIRE = REGISTRATE.item("copper_wire", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 16).pattern("###").pattern("$$$").pattern("###").define('#', Blocks.BLACK_CARPET).define('$', LostWorldsItems.COPPER_NUGGET.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_NUGGET.get())).save(provider)).register(),
			COMPUTER_FAN = REGISTRATE.item("computer_fan", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 4).pattern(" # ").pattern("#$#").pattern(" # ").define('#', Items.IRON_INGOT).define('$', Blocks.STONE).unlockedBy("has_item", provider.hasItem(Items.IRON_INGOT)).save(provider)).register(),
			COMPUTER_SCREEN = REGISTRATE.item("computer_screen", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("rgb").pattern("$$$").define('#', Blocks.GLASS_PANE).define('r', Items.RED_DYE).define('g', Items.GREEN_DYE).define('b', Items.BLUE_DYE).define('$', LostWorldsItems.COPPER_WIRE.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_WIRE.get())).save(provider)).register(),
			COMPUTER_FRAME = REGISTRATE.item("computer_frame", Item::new).model((item, provider) -> provider.withExistingParent(item.getName(), provider.modLoc("item/template_frame"))).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("# #").pattern("###").define('#', Items.IRON_INGOT).unlockedBy("has_item", provider.hasItem(Items.IRON_INGOT)).save(provider)).register(),
			COMPUTER_STORAGE_PORT = REGISTRATE.item("computer_storage_port", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("$#$").pattern("#@#").pattern("$#$").define('#', Items.IRON_INGOT).define('$', Blocks.STONE).define('@', LostWorldsItems.COPPER_WIRE.get()).unlockedBy("has_item", provider.hasItem(Items.IRON_INGOT)).save(provider)).register(),
			MOTHERBOARD = REGISTRATE.item("motherboard", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("#@#").pattern("$$$").define('#', LostWorldsItems.COPPER_INGOT.get()).define('$', LostWorldsItems.COPPER_WIRE.get()).define('@', Items.IRON_INGOT).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_INGOT.get())).save(provider)).register(),
			CPU = REGISTRATE.item("cpu", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("@$@").pattern("###").define('#', LostWorldsItems.COPPER_WIRE.get()).define('$', LostWorldsItems.COPPER_INGOT.get()).define('@', Blocks.GREEN_WOOL).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_INGOT.get())).save(provider)).register(),
			RAM = REGISTRATE.item("ram", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("$$$").define('#', Items.IRON_INGOT).define('$', LostWorldsItems.COPPER_WIRE.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_WIRE.get())).save(provider)).register(),
			COMPUTER_CORE = REGISTRATE.item("computer_core", Item::new).model((item, provider) -> provider.withExistingParent(item.getName(), provider.modLoc("item/template_core"))).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapelessRecipeBuilder.shapeless(item.get()).requires(LostWorldsItems.COMPUTER_STORAGE_PORT.get()).requires(LostWorldsItems.COMPUTER_FRAME.get()).requires(LostWorldsItems.MOTHERBOARD.get()).requires(LostWorldsItems.CPU.get()).requires(LostWorldsItems.RAM.get()).requires(LostWorldsItems.COMPUTER_FAN.get()).requires(LostWorldsItems.COMPUTER_SCREEN.get()).requires(LostWorldsItems.COPPER_WIRE.get()).requires(LostWorldsItems.COPPER_WIRE.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_WIRE.get())).save(provider)).register(),
			STORAGE_DISC = REGISTRATE.item("storage_disc", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("#$#").pattern("###").define('#', LostWorldsItems.COPPER_WIRE.get()).define('$', Items.DIAMOND).unlockedBy("has_item", provider.hasItem(Items.DIAMOND)).save(provider)).register(),
			TAG = REGISTRATE.item("tag", Item::new).tag(LostWorldsTags.ModItemTags.ELECTRONICS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 16).pattern("#$").pattern("%@").define('#', Blocks.REDSTONE_LAMP).define('$', Items.IRON_INGOT).define('%', LostWorldsItems.COMPUTER_CORE.get()).define('@', LostWorldsItems.COPPER_WIRE.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.COPPER_WIRE.get())).save(provider)).register();

	// Decoration
	public static final ItemEntry<CollectibleItem> AMBER_KEYCHAIN = REGISTRATE.item("amber_keychain", CollectibleItem::new).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("#").pattern("$").define('#', Items.IRON_NUGGET).define('$', LostWorldsItems.AMBER.get()).unlockedBy("has_item", provider.hasItem(LostWorldsItems.AMBER.get())).save(provider)).register(),
			BALLOON = REGISTRATE.item("balloon", CollectibleItem::new).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("#").pattern("$").define('#', Blocks.RED_WOOL).define('$', Items.STRING).unlockedBy("has_item", provider.hasItem(Items.STRING)).save(provider)).register(),
			TYRANNOSAURUS_PLUSH = REGISTRATE.item("tyrannosaurus_plush", CollectibleItem::new).model((item, provider) -> provider.withExistingParent(item.getName(), provider.modLoc("item/template_plush"))).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern(" # ").define('#', Blocks.GRAY_WOOL).unlockedBy("has_item", provider.hasItem(Blocks.GRAY_WOOL)).save(provider)).register();

	public static final ItemEntry<PinItem> DINO_BUTTON = REGISTRATE.item("dino_button", properties -> new PinItem(ModArmourMaterial.DECO, EquipmentSlotType.CHEST, properties)).tag(LostWorldsTags.ModItemTags.DECORATIVE_ITEMS).recipe((item, provider) -> provider.square(DataIngredient.items(Items.IRON_NUGGET), () -> item.get(), true)).register();

	// Miscellaneous
	public static final ItemEntry<Item> EMPTY_VILE = REGISTRATE.item("empty_vile", Item::new).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 4).pattern(" $#").pattern(" #$").pattern("#  ").define('$', ItemTags.STONE_CRAFTING_MATERIALS).define('#', Blocks.GLASS_PANE).unlockedBy("has_item", provider.hasItem(Blocks.GLASS_PANE)).save(provider)).register();

	public static final ItemEntry<Item> FERN_LEAVES = REGISTRATE.item("fern_leaves", Item::new).properties(properties -> properties.food(LostWorldsFoods.FERN_LEAVES)).register(),
			COOKED_FERN_LEAVES = REGISTRATE.item("cooked_fern_leaves", Item::new).properties(properties -> properties.food(LostWorldsFoods.COOKED_LEAVES)).recipe((item, provider) -> {
				provider.smelting(DataIngredient.items(LostWorldsItems.FERN_LEAVES.get()), () -> item.get(), 0.7F);
				provider.smoking(DataIngredient.items(LostWorldsItems.FERN_LEAVES.get()), () -> item.get(), 0.7F);
				provider.campfire(DataIngredient.items(LostWorldsItems.FERN_LEAVES.get()), () -> item.get(), 0.7F);
			}).register(),
			PALEO_SALAD = REGISTRATE.item("paleo_salad", Item::new).properties(properties -> properties.food(LostWorldsFoods.PALEO_SALAD)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).pattern("###").pattern("#$#").pattern("###").define('#', LostWorldsTags.ModItemTags.ANCIENT_SEEDS).define('$', Items.BOWL).unlockedBy("has_item", provider.hasItem(Items.BOWL)).save(provider)).register();

	public static final ItemEntry<BannerPatternItem> SCARAB_BANNER_PATTERN = REGISTRATE.item("scarab_banner_pattern", properties -> new BannerPatternItem(LostWorldsBanners.SCARAB, properties)).lang("Banner Pattern").register();

	// Fossils
	public static final ItemEntry<CEChargedCrystalScarabGemItem> CHARGED_CRYSTAL_SCARAB_GEM = REGISTRATE.item("charged_crystal_scarab_gem", CEChargedCrystalScarabGemItem::new).properties(properties -> properties.fireResistant().rarity(Rarity.RARE)).model(textured("crystal_scarab_gem")).register();

	public static final ItemEntry<CECrystalScarabGemItem> CRYSTAL_SCARAB_GEM = REGISTRATE.item("crystal_scarab_gem", CECrystalScarabGemItem::new).properties(properties -> properties.fireResistant().rarity(Rarity.RARE)).recipe((item, provider) -> ArchaeologyTableRecipeBuilder.shaped(item.get()).pattern("@%^").pattern("#$!").define('@', LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get()).define('$', LostWorldsItems.CRYSTAL_SCARAB_ABDOMEN.get()).define('^', LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()).define('#', LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get()).define('%', LostWorldsItems.CRYSTAL_SCARAB_THORAX.get()).define('!', LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get()).unlockedBy("has_item", provider.hasItem(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS)).save(provider)).register();
	public static final ItemEntry<Item> CRYSTAL_SCARAB_ABDOMEN = REGISTRATE.item("crystal_scarab_abdomen", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_BOTTOM_LEFT_LEG = REGISTRATE.item("crystal_scarab_bottom_left_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG = REGISTRATE.item("crystal_scarab_bottom_right_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_THORAX = REGISTRATE.item("crystal_scarab_thorax", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_TOP_LEFT_LEG = REGISTRATE.item("crystal_scarab_top_left_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register(),
			CRYSTAL_SCARAB_TOP_RIGHT_LEG = REGISTRATE.item("crystal_scarab_top_right_leg", Item::new).properties(properties -> properties.fireResistant().rarity(Rarity.UNCOMMON)).tag(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS).register();

	public static final ItemEntry<AmberItem> AMBER = REGISTRATE.item("amber", AmberItem::new).properties(properties -> properties.stacksTo(1)).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.TRACE_FOSSILS).register();
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

	static {
		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				ItemEntry<FossilItem> plasteredRibCage = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_rib_cage", properties -> new FossilItem(properties, () -> dinos.getRibCage().get(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredRibCageItem(() -> plasteredRibCage.get());
				ItemEntry<FossilItem> plasteredLegBones = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_leg_bones", properties -> new FossilItem(properties, () -> dinos.getLegBones().get(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredLegBonesItem(() -> plasteredLegBones.get());
				ItemEntry<FossilItem> plasteredArmBones = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_arm_bones", properties -> new FossilItem(properties, () -> dinos.getArmBones().get(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredArmBonesItem(() -> plasteredArmBones.get());
				ItemEntry<FossilItem> plasteredTail = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_tail", properties -> new FossilItem(properties, () -> dinos.getTail().get(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredTailItem(() -> plasteredTail.get());
				ItemEntry<FossilItem> plasteredSkull = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_skull", properties -> new FossilItem(properties, () -> dinos.getSkull().get(), true)).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.PLASTERED_FOSSILS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredSkullItem(() -> plasteredSkull.get());
				ItemEntry<FossilItem> ribCage = REGISTRATE.item(dinos.name().toLowerCase() + "_rib_cage", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("rib_cage")), () -> dinos.getRibCage().get(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setRibCageItem(() -> ribCage.get());
				ItemEntry<FossilItem> legBones = REGISTRATE.item(dinos.name().toLowerCase() + "_leg_bones", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("leg_bones")), () -> dinos.getLegBones().get(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setLegBonesItem(() -> legBones.get());
				ItemEntry<FossilItem> armBones = REGISTRATE.item(dinos.name().toLowerCase() + "_arm_bones", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("arm_bones")), () -> dinos.getArmBones().get(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setArmBonesItem(() -> armBones.get());
				ItemEntry<FossilItem> tail = REGISTRATE.item(dinos.name().toLowerCase() + "_tail", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("tail")), () -> dinos.getTail().get(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setTailItem(() -> tail.get());
				ItemEntry<FossilItem> skull = REGISTRATE.item(dinos.name().toLowerCase() + "_skull", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER("skull")), () -> dinos.getSkull().get(), false)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setSkullItem(() -> skull.get());
				ItemEntry<FossilItem> skeleton = REGISTRATE.item(dinos.name().toLowerCase() + "_skeleton", properties -> new FossilItem(properties.setISTER(() -> dinos.getISTER()), () -> dinos.getSkeleton().get(), false)).recipe((item, provider) -> PaleontologyTableRecipeBuilder.shaped(item.get()).pattern("#$#").pattern("@%@").pattern(" ! ").define('#', dinos.getArmBonesItem().get()).define('$', dinos.getSkullItem().get()).define('@', dinos.getLegBonesItem().get()).define('%', dinos.getRibCageItem().get()).define('!', dinos.getTailItem().get()).unlockedBy("has_item", provider.hasItem(dinos.getFossilTag())).save(provider)).model(fossil()).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.SKELETONS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setSkeletonPick(() -> skeleton.get());
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				ItemEntry<Item> plasteredExoskeleton = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_exoskeleton", Item::new).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredExoskeletonItem(() -> plasteredExoskeleton.get());
				ItemEntry<Item> exoskeleton = REGISTRATE.item(dinos.name().toLowerCase() + "_exoskeleton", Item::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.SKELETONS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setSkeletonPick(() -> exoskeleton.get());
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				ItemEntry<Item> plasteredBody = REGISTRATE.item("plastered_" + dinos.name().toLowerCase() + "_body", Item::new).model(textured("plastered_fossil")).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES).register();
				dinos.setPlasteredBodyItem(() -> plasteredBody.get());
				ItemEntry<Item> body = REGISTRATE.item(dinos.name().toLowerCase() + "_body", Item::new).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS, LostWorldsTags.ModItemTags.SKELETONS, LostWorldsTags.ModItemTags.FOSSILS, Tags.Items.BONES, dinos.getFossilTag()).register();
				dinos.setSkeletonPick(() -> body.get());
			}

			if (dinos.feathered().contains(dinos)) {
				ItemEntry<Item> feather = REGISTRATE.item(dinos.name().toLowerCase() + "_feather", Item::new).tag(Tags.Items.FEATHERS, LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				dinos.setFeather(() -> feather.get());
			}
			if (dinos.createHide().contains(dinos)) {
				ItemEntry<Item> hide = REGISTRATE.item(dinos.name().toLowerCase() + "_hide", Item::new).model(textured("dino_hide")).tag(Tags.Items.LEATHER, LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				dinos.setHide(() -> hide.get());
			}
			if (dinos.hasSpawn().contains(dinos)) {
				ItemEntry<SpawnItem> spawn = REGISTRATE.item(dinos.name().toLowerCase() + "_spawn", properties -> new SpawnItem(properties, () -> (EntityType<? extends PrehistoricEntity>) dinos.getEntityType().get())).model(model("template_spawn")).color(() -> () -> (stack, colour) -> dinos.getColour(colour, 0x000000, dinos.getSetEggColour())).register();
				dinos.setSpawn(() -> spawn.get());
			}

			ItemEntry<Item> softTissue = REGISTRATE.item(dinos.toString().toLowerCase() + "_soft_tissue", Item::new).model(textured("soft_tissue")).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).register();
			dinos.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> bloodSyringe = REGISTRATE.item(dinos.name().toLowerCase() + "_blood_syringe", Item::new).properties(properties -> properties.craftRemainder(LostWorldsItems.SYRINGE.get())).model(textured("blood_syringe")).tag(LostWorldsTags.ModItemTags.BLOOD_SYRINGES).register();
			dinos.setBloodSyringe(() -> bloodSyringe.get());
			ItemEntry<Item> bloodVile = REGISTRATE.item(dinos.name().toLowerCase() + "_blood_vile", Item::new).model(textured("blood_vile")).tag(LostWorldsTags.ModItemTags.BLOOD_VILES).recipe((item, provider) -> ShapelessRecipeBuilder.shapeless(item.get()).requires(dinos.getBloodSyringe().get()).requires(LostWorldsItems.EMPTY_VILE.get()).unlockedBy("has_item", provider.hasItem(LostWorldsTags.ModItemTags.BLOOD_SYRINGES)).save(provider)).register();
			dinos.setBloodVile(() -> bloodVile.get());
			ItemEntry<DNAItem> dna = REGISTRATE.item(dinos.name().toLowerCase() + "_dna", DNAItem::new).properties(properties -> properties.stacksTo(1)).tag(LostWorldsTags.ModItemTags.DNA, LostWorldsTags.ModItemTags.AMBER_RESULTS).register();
			dinos.setDNA(() -> dna.get());
			ItemEntry<DNADiscItem> dnaDisc = REGISTRATE.item(dinos.name().toLowerCase() + "_dna_disc", DNADiscItem::new).properties(properties -> properties.defaultDurability(5)).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			dinos.setDNADisc(() -> dnaDisc.get());
			if (!dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_meat", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_meat", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).recipe((item, provider) -> provider.food(DataIngredient.items(raw.get()), () -> item.get(), 0.7F)).register();
				dinos.setMeat(() -> raw.get());
			} else if (dinos == DinoTypes.NAUTILUS) {
				ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_tentacle", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).fast().build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
				REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_tentacle", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).fast().build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).recipe((item, provider) -> provider.food(DataIngredient.items(raw.get()), () -> item.get(), 0.7F)).register();
				dinos.setMeat(() -> raw.get());
			} else if (dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				ItemEntry<ModFishBucketItem> bucket = REGISTRATE.item(dinos.name().toLowerCase() + "_bucket", properties -> new ModFishBucketItem(() -> (EntityType<? extends PrehistoricEntity>) dinos.getEntityType().get(), Fluids.WATER, properties)).register();
				dinos.setFishBucket(() -> bucket.get());
				if (dinos != DinoTypes.ANOMALOCARIS) {
					ItemEntry<Item> raw = REGISTRATE.item(dinos.name().toLowerCase(), Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
					REGISTRATE.item("cooked_" + dinos.name().toLowerCase(), Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).recipe((item, provider) -> provider.food(DataIngredient.items(raw.get()), () -> item.get(), 0.7F)).register();
					dinos.setMeat(() -> raw.get());
				} else {
					ItemEntry<Item> raw = REGISTRATE.item("raw_" + dinos.name().toLowerCase() + "_claw", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).register();
					REGISTRATE.item("cooked_" + dinos.name().toLowerCase() + "_claw", Item::new).properties(properties -> properties.food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())).tag(LostWorldsTags.ModItemTags.CREATURE_ITEMS).recipe((item, provider) -> provider.food(DataIngredient.items(raw.get()), () -> item.get(), 0.7F)).register();
					dinos.setMeat(() -> raw.get());
				}
			}
		}

		for (Plants plants : Plants.values()) {
			ItemEntry<Item> fossil = REGISTRATE.item(plants.toString().toLowerCase() + "_fossil", Item::new).model(textured("plant_fossil")).tag(LostWorldsTags.ModItemTags.PLANT_FOSSILS).register();
			plants.setDrop(() -> fossil.get());
			ItemEntry<Item> softTissue = REGISTRATE.item(plants.toString().toLowerCase() + "_soft_tissue", Item::new).model(textured("plant_soft_tissue")).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).register();
			plants.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> dna = REGISTRATE.item(plants.toString().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).model(textured("plant_dna")).register();
			plants.setDNA(() -> dna.get());
			ItemEntry<DNADiscItem> dnaDisc = REGISTRATE.item(plants.toString().toLowerCase() + "_dna_disc", DNADiscItem::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			plants.setDNADisc(() -> dnaDisc.get());
			ItemEntry<SeedItem> seed = REGISTRATE.item(plants.toString().toLowerCase() + "_seeds", properties -> new SeedItem(plants.getPlant(), properties)).tag(LostWorldsTags.ModItemTags.ANCIENT_SEEDS).register();
			plants.setSeed(() -> seed.get());
		}

		for (Trees trees : Trees.values()) {
			ItemEntry<Item> softTissue = REGISTRATE.item(trees.toString().toLowerCase() + "_soft_tissue", Item::new).tag(LostWorldsTags.ModItemTags.SOFT_TISSUE).model(textured("plant_soft_tissue")).register();
			trees.setSoftTissue(() -> softTissue.get());
			ItemEntry<Item> dna = REGISTRATE.item(trees.toString().toLowerCase() + "_dna", Item::new).tag(LostWorldsTags.ModItemTags.DNA).model(textured("plant_dna")).register();
			trees.setDNA(() -> dna.get());
			ItemEntry<DNADiscItem> dnaDisc = REGISTRATE.item(trees.toString().toLowerCase() + "_dna_disc", DNADiscItem::new).tag(LostWorldsTags.ModItemTags.DNA_DISCS).model(textured("storage_disc")).register();
			trees.setDNADisc(() -> dnaDisc.get());
		}
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

	private static void doRandomItemStuff() {
		Foods.init();
	}

	public static void registrate() {
		doRandomItemStuff();
	}
}
