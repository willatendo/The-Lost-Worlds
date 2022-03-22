package lostworlds.server.item;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import lostworlds.client.craft.AmberDNAExtractorRecipeManager;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsRegistry;
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
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;

public class LostWorldsItems {
	public static final ArrayList<Supplier<Item>> PLASTERED = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> GENERATED = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> HANDHELD = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> SPAWN_EGG = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> SPAWN = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> ISTER = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> MEAT = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> HIDE = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> SOFT_TISSUE = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> BLOOD_SYRINGE = Lists.newArrayList();
	public static final ArrayList<Supplier<Item>> DNA_DISC = Lists.newArrayList();

	// Tools
	public static final Item CRYSTAL_SCARAB_SWORD = LostWorldsRegistry.register("crystal_scarab_sword", new SwordItem(ModItemTier.CRYSTAL_SCARAB, 3, -2.4F, new Properties().tab(LostWorldsUtils.ITEMS).setNoRepair()) {
		@Override
		public boolean isEnchantable(ItemStack stack) {
			return false;
		};

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		};

		@Override
		public boolean isFoil(ItemStack stack) {
			return true;
		};
	});

	public static final Item CRYSTAL_SCARAB_SHOVEL = LostWorldsRegistry.register("crystal_scarab_shovel", new ShovelItem(ModItemTier.CRYSTAL_SCARAB, 1.5F, -3.0F, new Properties().tab(LostWorldsUtils.ITEMS).setNoRepair()) {
		@Override
		public boolean isEnchantable(ItemStack stack) {
			return false;
		};

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		};

		@Override
		public boolean isFoil(ItemStack stack) {
			return true;
		};
	});

	public static final Item CRYSTAL_SCARAB_PICKAXE = LostWorldsRegistry.register("crystal_scarab_pickaxe", new PickaxeItem(ModItemTier.CRYSTAL_SCARAB, 1, -2.8F, new Properties().tab(LostWorldsUtils.ITEMS).setNoRepair()) {
		@Override
		public boolean isEnchantable(ItemStack stack) {
			return false;
		};

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		};

		@Override
		public boolean isFoil(ItemStack stack) {
			return true;
		};
	});

	public static final Item CRYSTAL_SCARAB_AXE = LostWorldsRegistry.register("crystal_scarab_axe", new AxeItem(ModItemTier.CRYSTAL_SCARAB, 6.0F, -3.2F, new Properties().tab(LostWorldsUtils.ITEMS).setNoRepair()) {
		@Override
		public boolean isEnchantable(ItemStack stack) {
			return false;
		};

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		};

		@Override
		public boolean isFoil(ItemStack stack) {
			return true;
		};
	});

	public static final Item CRYSTAL_SCARAB_HOE = LostWorldsRegistry.register("crystal_scarab_hoe", new HoeItem(ModItemTier.CRYSTAL_SCARAB, 0, -3.0F, new Properties().tab(LostWorldsUtils.ITEMS).setNoRepair()) {
		@Override
		public boolean isEnchantable(ItemStack stack) {
			return false;
		};

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		};

		@Override
		public boolean isFoil(ItemStack stack) {
			return true;
		};
	});

	public static final Item LEATHER_BRUSH = LostWorldsRegistry.register("leather_brush", new BrushItem(ModItemTier.LEATHER));
	public static final Item IRON_BRUSH = LostWorldsRegistry.register("iron_brush", new BrushItem(ModItemTier.IRON));
	public static final Item GOLD_BRUSH = LostWorldsRegistry.register("gold_brush", new BrushItem(ModItemTier.GOLD));
	public static final Item DIAMOND_BRUSH = LostWorldsRegistry.register("diamond_brush", new BrushItem(ModItemTier.DIAMOND));
	public static final Item NETHERITE_BRUSH = LostWorldsRegistry.register("netherite_brush", new BrushItem(ModItemTier.NETHERITE));
	public static final Item CRYSTAL_SCARAB_BRUSH = LostWorldsRegistry.register("crystal_scarab_brush", new CrystalScarabGemBrushItem());

	public static final Item HAMMER = LostWorldsRegistry.register("hammer", new HammerItem(ItemTier.IRON, 6.0F, -3.1F, (new Item.Properties()).tab(LostWorldsUtils.ITEMS)));

	public static final Item CHISEL = LostWorldsRegistry.register("chisel", new ChiselItem());

	// Armour
	public static final Item CLOTH_MASK = LostWorldsRegistry.register("cloth_mask", new MaskItem(ModArmourMaterial.CLOTH_MASK));

	// Utilities
	public static final Item WET_PAPER = LostWorldsRegistry.register("wet_paper", new WetPaperItem());
	public static final Item SYRINGE = LostWorldsRegistry.register("syringe", new SyringeItem());
	public static final Item LOST_WORLDS_LEXICON = LostWorldsRegistry.register("lost_worlds_lexicon", new LostWorldsLexicon(new Properties().tab(LostWorldsUtils.ITEMS).stacksTo(1).rarity(Rarity.RARE).fireResistant()));
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
				PLASTERED.add(() -> plasteredRibCage);
				Item plasteredLegBones = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_leg_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getLegBones(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "leg_bones")));
				dinos.setPlasteredLegBonesItem(plasteredLegBones);
				PLASTERED.add(() -> plasteredLegBones);
				Item plasteredArmBones = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_arm_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getArmBones(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "arm_bones")));
				dinos.setPlasteredArmBonesItem(plasteredArmBones);
				PLASTERED.add(() -> plasteredArmBones);
				Item plasteredTail = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_tail", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getTail(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "tail")));
				dinos.setPlasteredTailItem(plasteredTail);
				PLASTERED.add(() -> plasteredTail);
				Item plasteredSkull = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_skull", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getSkull(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "skull")));
				dinos.setPlasteredSkullItem(plasteredSkull);
				PLASTERED.add(() -> plasteredSkull);
				Item ribCage = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_rib_cage", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("rib_cage")), () -> dinos.getRibCage(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "rib_cage")));
				dinos.setRibCageItem(ribCage);
				ISTER.add(() -> ribCage);
				Item legBones = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_leg_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("leg_bones")), () -> dinos.getLegBones(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "leg_bones")));
				dinos.setLegBonesItem(legBones);
				ISTER.add(() -> legBones);
				Item armBones = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_arm_bones", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("arm_bones")), () -> dinos.getArmBones(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "arm_bones")));
				dinos.setArmBonesItem(armBones);
				ISTER.add(() -> armBones);
				Item tail = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_tail", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("tail")), () -> dinos.getTail(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "tail")));
				dinos.setTailItem(tail);
				ISTER.add(() -> tail);
				Item skull = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_skull", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER("skull")), () -> dinos.getSkull(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "skull")));
				dinos.setSkullItem(skull);
				ISTER.add(() -> skull);
				Item skeleton = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_skeleton", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS).setISTER(() -> dinos.getISTER()), () -> dinos.getSkeleton(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "skeleton")));
				dinos.setSkeletonPick(skeleton);
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				Item plastered = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_exoskeleton", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getExoskeleton(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "exoskeleton")));
				PLASTERED.add(() -> plastered);
				/*Item fossil = */LostWorldsRegistry.register(dinos.name().toLowerCase() + "_exoskeleton", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getExoskeleton(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "exoskeleton")));
//				GENERATED.add(() -> fossil);S
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				Item plastered = LostWorldsRegistry.register("plastered_" + dinos.name().toLowerCase() + "_body", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getBody(), true, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "body")));
				PLASTERED.add(() -> plastered);
				/*Item fossil = */LostWorldsRegistry.register(dinos.name().toLowerCase() + "_body", new FossilItem(new Properties().tab(LostWorldsUtils.ITEMS), () -> dinos.getBody(), false, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), LostWorldsUtils.tTC("fossilPart", "body")));
//				GENERATED.add(() -> fossil);
			}

			if (dinos.feathered().contains(dinos)) {
				Item feather = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_feather", new FeatherItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
				dinos.setFeather(feather);
				GENERATED.add(() -> feather);
			}
			if (dinos.createHide().contains(dinos)) {
				Item hide = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_hide", new HideItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
				HIDE.add(() -> hide);
			}
			if (dinos.hasSpawn().contains(dinos)) {
				Item spawn = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_spawn", new SpawnItem(() -> dinos.getEntityType(), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
				dinos.setSpawn(spawn);
				SPAWN.add(() -> spawn);
			}

			Item spawnEgg = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_spawn_egg", new DinoSpawnEggItem(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour(), ItemGroup.TAB_MISC, LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			SPAWN_EGG.add(() -> spawnEgg);
			Item softTissue = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_soft_tissue", new SoftTissueItem(LostWorldsUtils.tTC("entity", dinos.toString().toLowerCase())));
			dinos.setSoftTissue(softTissue);
			SOFT_TISSUE.add(() -> softTissue);
			Item bloodSample = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_blood_sample", new FullSyringeItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			dinos.setBloodSample(bloodSample);
			BLOOD_SYRINGE.add(() -> bloodSample);
			Item dna = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_dna", new DNAItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			dinos.setDNA(dna);
			GENERATED.add(() -> dna);
			Item dnaDisc = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_dna_disc", new DNADiscItem(LostWorldsUtils.tTC("entity", dinos.name().toLowerCase())));
			dinos.setDNADisc(dnaDisc);
			DNA_DISC.add(() -> dnaDisc);
			if (!dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				Item meat = LostWorldsRegistry.register("raw_" + dinos.name().toLowerCase() + "_meat", new DinoFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), true));
				Item cooked = LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase() + "_meat", new DinoFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), false));
				dinos.setMeat(meat);
				MEAT.add(() -> meat);
				MEAT.add(() -> cooked);
			} else if (dinos == DinoTypes.NAUTILUS) {
				Item meat = LostWorldsRegistry.register("raw_" + dinos.name().toLowerCase() + "_tentacle", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).fast().build())));
				Item cooked = LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase() + "_tentacle", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).fast().build())));
				dinos.setMeat(meat);
				MEAT.add(() -> meat);
				MEAT.add(() -> cooked);
			} else if (dinos.fish().contains(dinos) && dinos != DinoTypes.NAUTILUS) {
				Item bucket = LostWorldsRegistry.register(dinos.name().toLowerCase() + "_bucket", new ModFishBucketItem(() -> dinos.getEntityType(), Fluids.WATER, new Properties().tab(LostWorldsUtils.ITEMS).stacksTo(1)));
				dinos.setFishBucket(bucket);
				GENERATED.add(() -> bucket);
				if (dinos != DinoTypes.ANOMALOCARIS) {
					Item meat = LostWorldsRegistry.register(dinos.name().toLowerCase(), new FishFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), true));
					Item cooked = LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase(), new FishFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build()), LostWorldsUtils.tTC("entity", dinos.name().toLowerCase()), false));
					dinos.setMeat(meat); 
					MEAT.add(() -> meat);
					MEAT.add(() -> cooked);
				} else {
					Item meat = LostWorldsRegistry.register("raw_" + dinos.name().toLowerCase() + "_claw", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())));
					Item cooked = LostWorldsRegistry.register("cooked_" + dinos.name().toLowerCase() + "_claw", new EntityFoodItem(new Item.Properties().tab(LostWorldsUtils.ITEMS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())));
					dinos.setMeat(meat);
					MEAT.add(() -> meat);
					MEAT.add(() -> cooked);
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

	static {
		HANDHELD.add(() -> LostWorldsItems.CRYSTAL_SCARAB_SWORD);
		HANDHELD.add(() -> LostWorldsItems.CRYSTAL_SCARAB_SHOVEL);
		HANDHELD.add(() -> LostWorldsItems.CRYSTAL_SCARAB_PICKAXE);
		HANDHELD.add(() -> LostWorldsItems.CRYSTAL_SCARAB_AXE);
		HANDHELD.add(() -> LostWorldsItems.CRYSTAL_SCARAB_HOE);
		HANDHELD.add(() -> LostWorldsItems.LEATHER_BRUSH);
		HANDHELD.add(() -> LostWorldsItems.IRON_BRUSH);
		HANDHELD.add(() -> LostWorldsItems.GOLD_BRUSH);
		HANDHELD.add(() -> LostWorldsItems.DIAMOND_BRUSH);
		HANDHELD.add(() -> LostWorldsItems.NETHERITE_BRUSH);
		HANDHELD.add(() -> LostWorldsItems.CRYSTAL_SCARAB_BRUSH);
		HANDHELD.add(() -> LostWorldsItems.HAMMER);
		HANDHELD.add(() -> LostWorldsItems.CHISEL);

		GENERATED.add(() -> LostWorldsItems.CLOTH_MASK);
		GENERATED.add(() -> LostWorldsItems.WET_PAPER);
		GENERATED.add(() -> LostWorldsItems.SYRINGE);
		GENERATED.add(() -> LostWorldsItems.LOST_WORLDS_LEXICON);
		GENERATED.add(() -> LostWorldsItems.FIELD_GUIDE);
		GENERATED.add(() -> LostWorldsItems.TABLET);
		GENERATED.add(() -> LostWorldsItems.CONTRACEPTIVES);
		GENERATED.add(() -> LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK);
		GENERATED.add(() -> LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK);
		GENERATED.add(() -> LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK);
		GENERATED.add(() -> LostWorldsItems.MUD_BALL);
		GENERATED.add(() -> LostWorldsItems.COPPER_WIRE);
		GENERATED.add(() -> LostWorldsItems.COMPUTER_FAN);
		GENERATED.add(() -> LostWorldsItems.COMPUTER_SCREEN);
		GENERATED.add(() -> LostWorldsItems.COMPUTER_STORAGE_PORT);
		GENERATED.add(() -> LostWorldsItems.MOTHERBOARD);
		GENERATED.add(() -> LostWorldsItems.CPU);
		GENERATED.add(() -> LostWorldsItems.RAM);
		GENERATED.add(() -> LostWorldsItems.STORAGE_DISC);
		GENERATED.add(() -> LostWorldsItems.TAG);
		GENERATED.add(() -> LostWorldsItems.AMBER_KEYCHAIN);
		GENERATED.add(() -> LostWorldsItems.DINO_BUTTON);
		GENERATED.add(() -> LostWorldsItems.BALLOON);
		GENERATED.add(() -> LostWorldsItems.EMPTY_VILE);
		GENERATED.add(() -> LostWorldsItems.FERN_LEAVES);
		GENERATED.add(() -> LostWorldsItems.COOKED_FERN_LEAVES);
		GENERATED.add(() -> LostWorldsItems.PALEO_SALAD);
		GENERATED.add(() -> LostWorldsItems.SCARAB_BANNER_PATTERN);
		GENERATED.add(() -> LostWorldsItems.MUSIC_DISC_ASCENTED);
		GENERATED.add(() -> LostWorldsItems.AMBER);
		GENERATED.add(() -> LostWorldsItems.FOSSILIZED_FEATHER);
		GENERATED.add(() -> LostWorldsItems.FOSSILIZED_SKIN_IMPRESSION);
		GENERATED.add(() -> LostWorldsItems.GROUND_FOSSIL);
		GENERATED.add(() -> LostWorldsItems.PLANT_WASTE);
		GENERATED.add(() -> LostWorldsItems.ARAUCARIA_BARK_SAMPLE);
		GENERATED.add(() -> LostWorldsItems.CALAMITES_BARK_SAMPLE);
		GENERATED.add(() -> LostWorldsItems.CONIFER_BARK_SAMPLE);
		GENERATED.add(() -> LostWorldsItems.CYPRESS_BARK_SAMPLE);
		GENERATED.add(() -> LostWorldsItems.GINKGO_BARK_SAMPLE);
		GENERATED.add(() -> LostWorldsItems.SEQUOIA_BARK_SAMPLE);

		SPAWN_EGG.add(() -> LostWorldsItems.FOSSIL_POACHER_SPAWN_EGG);
	}
}
