package lostworlds.data;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.Plants;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LostWorldsAdvancementProvider extends AdvancementProvider {
	public LostWorldsAdvancementProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, fileHelper);
	}

	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
		Advancement root = Advancement.Builder.advancement().display(LostWorldsItems.LOST_WORLDS_LEXICON.asStack(), LostWorldsUtils.tTC("advancement", "root.title"), LostWorldsUtils.tTC("advancement", "root.desc"), LostWorldsUtils.rL("textures/block/dried_soil.png"), FrameType.TASK, false, false, false).addCriterion("tick", new PlayerTrigger.TriggerInstance(CriteriaTriggers.TICK.getId(), EntityPredicate.Composite.ANY)).save(consumer, LostWorldsUtils.rL("lostworlds/root"), fileHelper);
		Advancement aTerribleMarket = this.addAdvancement(consumer, fileHelper, root, LostWorldsItems.HAMMER.get(), "a_terrible_market", FrameType.TASK, true, true, false, structureCriteria(LostWorldsConfiguredStructures.BLACK_MARKET_KEY, "black_market"));
		Advancement basicExplorer = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get(), "basic_explorer", FrameType.GOAL, true, true, false, this.biomeCriteria(LostWorldsBiomes.ARAUCARIA_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CONIFER_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.GINKGO_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.REDWOODS_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.VOLCANO.getResourceKey()));
		Advancement fossils = this.addAdvancement(consumer, fileHelper, aTerribleMarket, LostWorldsItems.HAMMER.get(), "fossils", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.PLASTERED_FOSSILS.tag));
		this.addAdvancement(consumer, fileHelper, fossils, LostWorldsItems.FOSSILIZED_FEATHER.get(), "decoration", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.TRACE_FOSSILS.tag));
		this.addAdvancement(consumer, fileHelper, fossils, LostWorldsItems.FIELD_GUIDE.get(), "in_the_field", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.FIELD_GUIDE.get()));
		Advancement justARemnant = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.SOFT_STONE.get(), "just_a_remnant", FrameType.TASK, true, true, false, true, this.structureCriteria(LostWorldsConfiguredStructures.SURFACE_FOSSIL_KEY, "surface_fossil"), this.structureCriteria(LostWorldsConfiguredStructures.SUBTERRANEAN_FOSSIL_KEY, "subterranean_fossil"));
		Advancement justATrace = this.addAdvancement(consumer, fileHelper, fossils, AncientCreatures.ALLOSAURUS.getSoftTissue().get(), "just_a_trace", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.SOFT_TISSUE.tag));
		Advancement likeIndianaJones = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.ARCHAEOLOGY_TABLE.get(), "like_indiana_jones", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsBlocks.ARCHAEOLOGY_TABLE.get()));
		Advancement dangerousRelic = this.addAdvancement(consumer, fileHelper, likeIndianaJones, LostWorldsItems.CRYSTAL_SCARAB_GEM.get(), "dangerous_relic", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.BROKEN_CRYSTAL_SCARAB_GEMS.tag));
		Advancement lostHeart = this.addAdvancement(consumer, fileHelper, dangerousRelic, LostWorldsItems.CRYSTAL_SCARAB_GEM.get(), "lost_heart", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.CRYSTAL_SCARAB_GEM.get()));
		Advancement charged = this.addAdvancement(consumer, fileHelper, lostHeart, LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get(), "charged", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get()));
		this.addAdvancement(consumer, fileHelper, charged, LostWorldsItems.CRYSTAL_SCARAB_AXE.get(), "executioner_style", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsItems.CRYSTAL_SCARAB_AXE.get()));
		this.addAdvancement(consumer, fileHelper, charged, LostWorldsItems.CRYSTAL_SCARAB_SWORD.get(), "sharpest_sword", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsItems.CRYSTAL_SCARAB_SWORD.get()));
		this.addAdvancement(consumer, fileHelper, charged, LostWorldsItems.CRYSTAL_SCARAB_SHOVEL.get(), "shovel_thats_all", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsItems.CRYSTAL_SCARAB_SHOVEL.get()));
		this.addAdvancement(consumer, fileHelper, charged, LostWorldsItems.CRYSTAL_SCARAB_PICKAXE.get(), "strongest_pick", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsItems.CRYSTAL_SCARAB_PICKAXE.get()));
		this.addAdvancement(consumer, fileHelper, charged, LostWorldsItems.CRYSTAL_SCARAB_HOE.get(), "ungodly_dedication", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsItems.CRYSTAL_SCARAB_HOE.get()));
		Advancement paleobotany = this.addAdvancement(consumer, fileHelper, fossils, Plants.ALETHOPTERIS.getDrop().get(), "paleobotany", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.PLANT_FOSSILS.tag));
		Advancement petrifiedTrees = this.addAdvancement(consumer, fileHelper, fossils, LostWorldsBlocks.CONIFER.getBlock(WoodTypes.PETRIFIED_LOG).get().get(), "petrified_trees", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.PETRIFIED_LOGS.tag));
		Advancement plantsAtLeastTheyDontKill = this.addAdvancement(consumer, fileHelper, paleobotany, Plants.ALETHOPTERIS.getSoftTissue().get(), "plants_at_least_they_dont_kill", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.PLANT_SOFT_TISSUE.tag));
		this.addAdvancement(consumer, fileHelper, plantsAtLeastTheyDontKill, Plants.CYCAD.getSeed().get(), "ancient_seeds", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.ANCIENT_SEEDS.tag));
		Advancement scratchingTrees = this.addAdvancement(consumer, fileHelper, petrifiedTrees, LostWorldsItems.CONIFER_BARK_SAMPLE.get(), "scratching_trees", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.BARK_SAMPLES.tag));
		this.addAdvancement(consumer, fileHelper, scratchingTrees, LostWorldsBlocks.CONIFER.getBlock(WoodTypes.SAPLING).get().get(), "long_lost_trees", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.ANCIENT_SAPLINGS.tag));
		Advancement theBrokenKTBoundry = this.addAdvancement(consumer, fileHelper, justATrace, AncientCreatures.ALLOSAURUS.getDNA().get(), "the_broken_kt_boundry", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.DNA.tag));
		Advancement digitalization = this.addAdvancement(consumer, fileHelper, theBrokenKTBoundry, AncientCreatures.ALLOSAURUS.getDNADisc().get(), "digitalization", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.DNA_DISCS.tag));
		this.addAdvancement(consumer, fileHelper, digitalization, AncientCreatures.ALLOSAURUS.getEgg().get(), "dr_frankenstein", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.EGGS.tag));
		Advancement theMuseum = this.addAdvancement(consumer, fileHelper, justARemnant, LostWorldsBlocks.PALEONTOLOGY_TABLE.get(), "the_museum", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsBlocks.PALEONTOLOGY_TABLE.get()));
		this.addAdvancement(consumer, fileHelper, theMuseum, Items.BONE, "for_the_masses", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.SKELETONS.tag));
		this.addAdvancement(consumer, fileHelper, basicExplorer, LostWorldsBlocks.TIME_MACHINE.get(), "the_time_traveller", FrameType.TASK, true, true, false, this.dimensionCriteria(LostWorldsDimensions.CRETACEOUS_LEVEL), this.dimensionCriteria(LostWorldsDimensions.JURASSIC_LEVEL), this.dimensionCriteria(LostWorldsDimensions.PERMIAN_LEVEL));
		Advancement theTimeTraveller = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.TIME_MACHINE.get(), "time_travelling", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsBlocks.TIME_MACHINE.get()));
		Advancement theCretaceous = this.addAdvancement(consumer, fileHelper, theTimeTraveller, LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), "the_cretaceous", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get()));
		this.addAdvancement(consumer, fileHelper, theCretaceous, LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), "cretaceous_explorer", FrameType.CHALLENGE, true, true, false, this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_CONIFER_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_GINKGO_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_GAME_TRAIL.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_MEDOW.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_PLAINS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_FLOOD_BASALTS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ARCTIC.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ARCTIC_SPIRES.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_FROZEN_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_WINDSWEPT_HILLS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ERRODED_WINDSWEPT_HILLS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_DESERT.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_RED_DESERT.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_MARSH.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_SWAMP.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_FEN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_BOG.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_RIVER.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.COLD_CRETACEOUS_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.WARM_CRETACEOUS_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_SHORE.getResourceKey()));
		Advancement theJurassic = this.addAdvancement(consumer, fileHelper, theTimeTraveller, LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), "the_jurassic", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get()));
		this.addAdvancement(consumer, fileHelper, theJurassic, LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), "jurassic_explorer", FrameType.CHALLENGE, true, true, false, this.biomeCriteria(LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_CONIFER_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_GINKGO_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_REDWOODS_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_PLAINS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_WINDSWEPT_HILLS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_ERRODED_WINDSWEPT_HILLS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_VOLCANIC_RANGE.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_DESERT.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_MARSH.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_SWAMP.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_FEN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_BOG.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_RIVER.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.WARM_JURASSIC_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.JURASSIC_SHORE.getResourceKey()));
		Advancement thePermian = this.addAdvancement(consumer, fileHelper, theTimeTraveller, LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), "the_permian", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get()));
		this.addAdvancement(consumer, fileHelper, thePermian, LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), "permian_explorer", FrameType.CHALLENGE, true, true, false, this.biomeCriteria(LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_DESERT.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_FLOOD_BASALTS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_ASHY_MEDOWS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_WINDSWEPT_HILLS.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_MARSH.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_RIVER.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.WARM_PERMIAN_OCEAN.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_SHORE.getResourceKey()), this.biomeCriteria(LostWorldsBiomes.PERMIAN_DRIPSTONE_CAVES.getResourceKey()));
	}

	private Advancement addAdvancement(Consumer<Advancement> consumer, ExistingFileHelper fileHelper, Advancement parent, ItemLike icon, String name, FrameType type, boolean showToast, boolean announceToChat, boolean hidden, Pair<AbstractCriterionTriggerInstance, String>... criteria) {
		return this.addAdvancement(consumer, fileHelper, parent, icon, name, type, showToast, announceToChat, hidden, false, criteria);
	}

	private Advancement addAdvancement(Consumer<Advancement> consumer, ExistingFileHelper fileHelper, Advancement parent, ItemLike icon, String name, FrameType type, boolean showToast, boolean announceToChat, boolean hidden, boolean criteriaIsOr, Pair<AbstractCriterionTriggerInstance, String>... criteria) {
		Advancement.Builder advancement = Advancement.Builder.advancement().parent(parent).display(icon, LostWorldsUtils.tTC("advancement", name + ".title"), LostWorldsUtils.tTC("advancement", name + ".desc"), null, type, showToast, announceToChat, hidden);
		for (Pair<AbstractCriterionTriggerInstance, String> advancementCriteria : criteria) {
			advancement.addCriterion(advancementCriteria.getSecond(), advancementCriteria.getFirst());
		}

		if (criteriaIsOr) {
			advancement.requirements(RequirementsStrategy.OR);
		}

		if (type == FrameType.CHALLENGE) {
			advancement.rewards(AdvancementRewards.Builder.experience(500));
		}

		return advancement.save(consumer, LostWorldsUtils.rL("lostworlds/" + name), fileHelper);
	}

	public Pair<AbstractCriterionTriggerInstance, String> biomeCriteria(ResourceKey<Biome> biome) {
		return Pair.of(PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(biome)), "in_" + biome.location().getPath());
	}

	public Pair<AbstractCriterionTriggerInstance, String> dimensionCriteria(ResourceKey<Level> dimension) {
		return Pair.of(PlayerTrigger.TriggerInstance.located(LocationPredicate.inDimension(dimension)), "in_" + dimension.location().getPath());
	}

	public Pair<AbstractCriterionTriggerInstance, String> structureCriteria(ResourceKey<Structure> structure, String structureName) {
		return Pair.of(PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(structure)), "in_" + structureName);
	}

	public Pair<AbstractCriterionTriggerInstance, String> itemCriteria(ItemLike item) {
		return Pair.of(InventoryChangeTrigger.TriggerInstance.hasItems(item), "has_item");
	}

	public Pair<AbstractCriterionTriggerInstance, String> itemCriteria(TagKey<Item> tag) {
		return Pair.of(InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(tag).build()), "has_item");
	}
}
