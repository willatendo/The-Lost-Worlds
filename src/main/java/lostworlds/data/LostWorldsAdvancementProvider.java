package lostworlds.data;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.Plants;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.advancements.critereon.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsAdvancementProvider extends AdvancementProvider {
	public LostWorldsAdvancementProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, fileHelper);
	}

	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
		Advancement root = Advancement.Builder.advancement().display(LostWorldsItems.LOST_WORLDS_LEXICON.asStack(), LostWorldsUtils.tTC("advancement", "root.title"), LostWorldsUtils.tTC("advancement", "root.desc"), LostWorldsUtils.rL("textures/block/dried_soil.png"), FrameType.TASK, false, false, false).addCriterion("tick", new TickTrigger.TriggerInstance(EntityPredicate.Composite.ANY)).save(consumer, LostWorldsUtils.rL("lostworlds/root"), fileHelper);
		Advancement aTerribleMarket = this.addAdvancement(consumer, fileHelper, root, LostWorldsItems.HAMMER.get(), "a_terrible_market", FrameType.TASK, true, true, false, Pair.of(LocationTrigger.TriggerInstance.located(LocationPredicate.inFeature(LostWorldsConfiguredStructures.BLACK_MARKET_KEY)), "in_black_market"));
		Advancement basicExplorer = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get(), "basic_explorer", FrameType.GOAL, true, true, false, this.biomeCriteria(LostWorldsBiomes.ARAUCARIA_FOREST), this.biomeCriteria(LostWorldsBiomes.CONIFER_FOREST), this.biomeCriteria(LostWorldsBiomes.GINKGO_FOREST), this.biomeCriteria(LostWorldsBiomes.REDWOODS_FOREST), this.biomeCriteria(LostWorldsBiomes.VOLCANO));
		Advancement fossils = this.addAdvancement(consumer, fileHelper, aTerribleMarket, LostWorldsItems.HAMMER.get(), "fossils", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.PLASTERED_FOSSILS.tag));
		this.addAdvancement(consumer, fileHelper, fossils, LostWorldsItems.FOSSILIZED_FEATHER.get(), "decoration", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.TRACE_FOSSILS.tag));
		this.addAdvancement(consumer, fileHelper, fossils, LostWorldsItems.FIELD_GUIDE.get(), "in_the_field", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.FIELD_GUIDE.get()));
		Advancement justARemnant = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.SOFT_STONE.get(), "just_a_remnant", FrameType.TASK, true, true, false, true, this.structureCriteria(LostWorldsConfiguredStructures.SURFACE_FOSSIL_KEY), this.structureCriteria(LostWorldsConfiguredStructures.SUBTERRANEAN_FOSSIL_KEY));
		Advancement justATrace = this.addAdvancement(consumer, fileHelper, fossils, DinoTypes.ALLOSAURUS.getSoftTissue().get(), "just_a_trace", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.SOFT_TISSUE.tag));
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
		Advancement theBrokenKTBoundry = this.addAdvancement(consumer, fileHelper, justATrace, DinoTypes.ALLOSAURUS.getDNA().get(), "the_broken_kt_boundry", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.DNA.tag));
		Advancement digitalization = this.addAdvancement(consumer, fileHelper, theBrokenKTBoundry, DinoTypes.ALLOSAURUS.getDNADisc().get(), "digitalization", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.DNA_DISCS.tag));
		this.addAdvancement(consumer, fileHelper, digitalization, DinoTypes.ALLOSAURUS.getEgg().get(), "dr_frankenstein", FrameType.CHALLENGE, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.EGGS.tag));
		Advancement theMuseum = this.addAdvancement(consumer, fileHelper, justARemnant, LostWorldsBlocks.PALEONTOLOGY_TABLE.get(), "the_museum", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsBlocks.PALEONTOLOGY_TABLE.get()));
		this.addAdvancement(consumer, fileHelper, theMuseum, Items.BONE, "for_the_masses", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsTags.ModItemTags.SKELETONS.tag));
		this.addAdvancement(consumer, fileHelper, basicExplorer, LostWorldsBlocks.TIME_MACHINE.get(), "the_time_traveller", FrameType.TASK, true, true, false, this.dimensionCriteria(LostWorldsDimensions.CRETACEOUS_WORLD), this.dimensionCriteria(LostWorldsDimensions.JURASSIC_WORLD), this.dimensionCriteria(LostWorldsDimensions.PERMIAN_WORLD));
		Advancement theTimeTraveller = this.addAdvancement(consumer, fileHelper, root, LostWorldsBlocks.TIME_MACHINE.get(), "time_travelling", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsBlocks.TIME_MACHINE.get()));
		Advancement theCretaceous = this.addAdvancement(consumer, fileHelper, theTimeTraveller, LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), "the_cretaceous", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get()));
		this.addAdvancement(consumer, fileHelper, theCretaceous, LostWorldsItems.CRETACEOUS_PERIOD_TIME_BOOK.get(), "cretaceous_explorer", FrameType.CHALLENGE, true, true, false, this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ARAUCARIA_FOREST), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_CONIFER_FOREST), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_GINKGO_FOREST), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_GAME_TRAIL), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_MEDOW), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_PLAINS), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_FLOOD_BASALTS), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ARCTIC), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ARCTIC_SPIRES), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_FROZEN_FOREST), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_MOUNTAINS), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_ERRODED_MOUNTAINS), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_DESERT), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_RED_DESERT), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_MARSH), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_SWAMP), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_FEN), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_BOG), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_RIVER), this.biomeCriteria(LostWorldsBiomes.COLD_CRETACEOUS_OCEAN), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_OCEAN), this.biomeCriteria(LostWorldsBiomes.WARM_CRETACEOUS_OCEAN), this.biomeCriteria(LostWorldsBiomes.CRETACEOUS_SHORE));
		Advancement theJurassic = this.addAdvancement(consumer, fileHelper, theTimeTraveller, LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), "the_jurassic", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get()));
		this.addAdvancement(consumer, fileHelper, theJurassic, LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get(), "jurassic_explorer", FrameType.CHALLENGE, true, true, false, this.biomeCriteria(LostWorldsBiomes.JURASSIC_ARAUCARIA_FOREST), this.biomeCriteria(LostWorldsBiomes.JURASSIC_CONIFER_FOREST), this.biomeCriteria(LostWorldsBiomes.JURASSIC_GINKGO_FOREST), this.biomeCriteria(LostWorldsBiomes.JURASSIC_REDWOODS_FOREST), this.biomeCriteria(LostWorldsBiomes.JURASSIC_PLAINS), this.biomeCriteria(LostWorldsBiomes.JURASSIC_MOUNTAINS), this.biomeCriteria(LostWorldsBiomes.JURASSIC_ERRODED_MOUNTAINS), this.biomeCriteria(LostWorldsBiomes.JURASSIC_VOLCANIC_RANGE), this.biomeCriteria(LostWorldsBiomes.JURASSIC_DESERT), this.biomeCriteria(LostWorldsBiomes.JURASSIC_MARSH), this.biomeCriteria(LostWorldsBiomes.JURASSIC_SWAMP), this.biomeCriteria(LostWorldsBiomes.JURASSIC_FEN), this.biomeCriteria(LostWorldsBiomes.JURASSIC_BOG), this.biomeCriteria(LostWorldsBiomes.JURASSIC_RIVER), this.biomeCriteria(LostWorldsBiomes.JURASSIC_OCEAN), this.biomeCriteria(LostWorldsBiomes.WARM_JURASSIC_OCEAN), this.biomeCriteria(LostWorldsBiomes.JURASSIC_SHORE));
		Advancement thePermian = this.addAdvancement(consumer, fileHelper, theTimeTraveller, LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), "the_permian", FrameType.TASK, true, true, false, this.itemCriteria(LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get()));
		this.addAdvancement(consumer, fileHelper, thePermian, LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get(), "permian_explorer", FrameType.CHALLENGE, true, true, false, this.biomeCriteria(LostWorldsBiomes.PERMIAN_CONIFER_FOREST), this.biomeCriteria(LostWorldsBiomes.PERMIAN_GINKGO_FOREST), this.biomeCriteria(LostWorldsBiomes.PERMIAN_PLAINS), this.biomeCriteria(LostWorldsBiomes.PERMIAN_DRIED_PLAINS), this.biomeCriteria(LostWorldsBiomes.PERMIAN_DESERT), this.biomeCriteria(LostWorldsBiomes.PERMIAN_FLOOD_BASALTS), this.biomeCriteria(LostWorldsBiomes.PERMIAN_ASHY_MEDOWS), this.biomeCriteria(LostWorldsBiomes.PERMIAN_MOUNTAINS), this.biomeCriteria(LostWorldsBiomes.PERMIAN_MARSH), this.biomeCriteria(LostWorldsBiomes.PERMIAN_RIVER), this.biomeCriteria(LostWorldsBiomes.PERMIAN_OCEAN), this.biomeCriteria(LostWorldsBiomes.WARM_PERMIAN_OCEAN), this.biomeCriteria(LostWorldsBiomes.PERMIAN_SHORE));
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

	public Pair<AbstractCriterionTriggerInstance, String> biomeCriteria(Pair<RegistryObject<Biome>, ResourceKey<Biome>> biome) {
		return Pair.of(LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(biome.getSecond())), "in_" + biome.getSecond().location().getPath());
	}

	public Pair<AbstractCriterionTriggerInstance, String> dimensionCriteria(ResourceKey<Level> dimension) {
		return Pair.of(LocationTrigger.TriggerInstance.located(LocationPredicate.inDimension(dimension)), "in_" + dimension.location().getPath());
	}

	public Pair<AbstractCriterionTriggerInstance, String> structureCriteria(ResourceKey<ConfiguredStructureFeature<?, ?>> structure) {
		return Pair.of(LocationTrigger.TriggerInstance.located(LocationPredicate.inFeature(structure)), "in_" + structure.getRegistryName().getPath());
	}

	public Pair<AbstractCriterionTriggerInstance, String> itemCriteria(ItemLike item) {
		return Pair.of(InventoryChangeTrigger.TriggerInstance.hasItems(item), "has_item");
	}

	public Pair<AbstractCriterionTriggerInstance, String> itemCriteria(TagKey<Item> tag) {
		return Pair.of(InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(tag).build()), "has_item");
	}
}
