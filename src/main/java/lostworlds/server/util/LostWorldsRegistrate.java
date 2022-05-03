package lostworlds.server.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ContainerBuilder;
import com.tterrag.registrate.builders.ContainerBuilder.ForgeContainerFactory;
import com.tterrag.registrate.builders.ContainerBuilder.ScreenFactory;
import com.tterrag.registrate.util.NonNullLazyValue;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlockModels;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.DyeColor;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LostWorldsRegistrate extends AbstractRegistrate<LostWorldsRegistrate> {
	protected LostWorldsRegistrate(String modid) {
		super(modid);
		this.extraLangStuff();
	}

	public static NonNullLazyValue<LostWorldsRegistrate> lazy(String modid) {
		return new NonNullLazyValue<>(() -> new LostWorldsRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}

	public void extraLangStuff() {
		this.addDescription("lost_worlds_lexicon", "An Everything-You-Need-To-Know Book!");
		this.addDescription("field_guide", "Willatendo - Volume 3");
		this.addDescription("collectible", "It's a Collectible!");

		this.tabletLang("age", "Age:");
		this.tabletLang("baby", "Baby");
		this.tabletLang("adult", "Adult");
		this.tabletLang("heath", "Heath:");
		this.tabletLang("hunger", "Hunger:");
		this.tabletLang("full", "Full");
		this.tabletLang("satiated", "Satiated");
		this.tabletLang("hungry", "Hungry");
		this.tabletLang("starving", "Starving");
		this.tabletLang("diet", "Diet:");
		this.tabletLang("herbivore", "Herbivore");
		this.tabletLang("carnivore", "Carnivore");
		this.tabletLang("activity_type", "Activity Type:");
		this.tabletLang("cathemeral", "Cathemeral");
		this.tabletLang("nocturnal", "Nocturnal");
		this.tabletLang("crepuscular", "Crepuscular");
		this.tabletLang("diurnal", "Diurnal");
		this.tabletLang("contraceptives", "Contraceptives:");
		this.tabletLang("on_contraceptives", "On");
		this.tabletLang("off_contraceptives", "Off");
		this.tabletLang("tagged_to", "Tagged To:");

		this.addRawLang("item.lostworlds.scarab_banner_pattern.desc", "Scarab");
		for (DyeColor colour : DyeColor.values()) {
			this.addRawLang("block.minecraft.banner.scarab." + colour.getName(), Arrays.stream(colour.getName().toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")) + " Scarab");
		}

		this.addRawLang("effect.lostworlds.ashy_lung_effect", "Ashy Lung");

		this.addRawLang("enchantment.lostworlds.precision.desc", "Reduces the chance of breaking a fossil.");
		this.addRawLang("enchantment.lostworlds.curse_of_breaking.desc", "Increases the chance of breaking a fossil.");

		this.addRawLang("jei.lostworlds.charged_crystal_scarab_gem", "Created in a lightning strike, they are one of the rarest and most valuable items in the game. They are require for a lot of things, including time travel. Made with a Crystal Scarab Gem.");
		this.addRawLang("jei.lostworlds.fossil_cleaner.title", "Fossil Cleaning");
		this.addRawLang("jei.lostworlds.fossil_cleaner_fuel.title", "Fossil Cleaner Fuel");
		this.addRawLang("jei.lostworlds.cleanCount.single", "Cleans 1 Item");
		this.addRawLang("jei.lostworlds.cleanCount.multi", "Cleans %s Items");
		this.addRawLang("jei.lostworlds.fossil_grinder.title", "Fossil Grinding");
		this.addRawLang("jei.lostworlds.fossil_grinder.chance", "Chance:");
		this.addRawLang("jei.lostworlds.dna_extractor.title", "DNA Extracting");
		this.addRawLang("jei.lostworlds.analyzer.title", "Analyzing");
		this.addRawLang("jei.lostworlds.dna_injector.title", "DNA Injecting");
		this.addRawLang("jei.lostworlds.cultivator.title", "Cultivating");
		this.addRawLang("jei.lostworlds.archaeology_table.title", "Archaeology");
		this.addRawLang("jei.lostworlds.paleontology_table.title", "Paleontology");
		this.addRawLang("jei.lostworlds.time_machine.title", "Time Enchanting");

		this.addRawLang("death.attack.ashyLung", "%1$s's lungs filled up with ash");
		this.addRawLang("death.attack.suffication", "%1$s sufficated");
		this.addRawLang("death.attack.hunger", "%1$s died of Hunger");
		this.addRawLang("death.attack.prick", "%1$s was pricked to death with a needle");

		this.addRawLang("entity.minecraft.villager.lostworlds.archaeologist", "Archaeologist");
		this.addRawLang("entity.minecraft.villager.lostworlds.paleontologist", "Paleontologist");

		this.addRawLang("event.lostworlds.load_dev_build", "You are running %s: Dev Edition. Do not redistribute.");
		this.addRawLang("event.lostworlds.load_snapshot_build", "You are running an %s snapshot. Do not redistribute and report any and all bugs.");
		this.addRawLang("event.lostworlds.load_youtube_build", "Thank you for playing with the mod! I greatly appreciate the distrabution of the mod. You are running Youtube Version %s.");
		this.addRawLang("event.lostworlds.load", "Thank you for downloading the mod! You are running Public %s.");

		this.addRawLang("timeBook.lostworlds.doesnt_work", "Time doesn't exist in this dimension!");
		this.addRawLang("timeBook.lostworlds.transport_to_overworld", "Transporting you back home!");
		this.addRawLang("timeBook.lostworlds.transport_to_permian_period", "Transporting you to the Permian Period!");
		this.addRawLang("timeBook.lostworlds.transport_to_jurassic_period", "Transporting you to the Jurassic Period!");
		this.addRawLang("timeBook.lostworlds.transport_to_cretaceous_period", "Transporting you to the Cretaceous Period!");

		this.addRawLang("filled_map.surface_fossil", "Fossil Map");
		this.addRawLang("filled_map.subterranean_fossil", "Fossil Map");

		this.subtitle("machine_whirling", "Machine Wirling");
		this.subtitle("pot_smash", "Pot Smashes");
		this.subtitle("big_walk", "Big Creature Walks");
		this.subtitle("medium_walk", "Medium Creature Walks");
		this.subtitle("small_walk", "Small Creature Walks");

		this.advancement("root", "Lost Worlds", "Welcome to the Lost Worlds!");
		this.advancement("a_terrible_market", "A Terrible Market", "Find a Black Market. A place full of greedy pillagers, but more importantly, a hammer.");
		this.advancement("basic_explorer", "Basic Explorer", "Just the start of your exploring journey.");
		this.advancement("fossils", "Fossils", "Break your first fossil! Soon, you'll have a museum or a park or a battle arena!");
		this.advancement("decoration", "Decoration", "May not bring back creatures, but still cool!");
		this.advancement("in_the_field", "In The Field", "Aquire the field guide! A paleontologist's book!");
		this.advancement("just_a_remnant", "Just a Remnant", "Find a fossil. The last signs of species long lost.");
		this.advancement("just_a_trace", "Just a Trace", "Aquire the soft tissue of an extinct creature.");
		this.advancement("like_indiana_jones", "Like Indiana Jones", "Just the start of archaeology. Soon, you'll be like the famous Indiana Jones!");
		this.advancement("dangerous_relic", "Dangerous Relic", "The broken heart of ancient civilizations! When brought together, it has the power of 8 kilatons of tnt.");
		this.advancement("lost_heart", "Lost Heart", "The heart of ancient civilizations. One of the most powerful and dangerous items of the world!");
		this.advancement("charged", "Charged", "You've done it! It's more powerful then ever! If only it could come into contact with netherite...");
		this.advancement("executioner_style", "Executioner-Style", "The strongest axe on the planet, perfect for anything. After a lot of work!");
		this.advancement("sharpest_sword", "Sharpest Sword", "Kills a wither in a few swings!");
		this.advancement("shovel_thats_all", "Shovel...That's All", "No puns or cleverness.");
		this.advancement("strongest_pick", "The Strongest Pick", "The strongest pickaxe on the planet, who could possibly break it!");
		this.advancement("ungodly_dedication", "Ungodly Dedication", "To whomever completed this in survival, how?");
		this.advancement("paleobotany", "Paleobotany", "Plants are cool! Ancient plants are cooler!");
		this.advancement("petrified_trees", "Petrified Trees", "You found the remains of an ancient tree!");
		this.advancement("plants_at_least_they_dont_kill", "Plants! At Least They Don't Kill...", "Plants may be an odd choise to bring back, but they don't kill people!");
		this.advancement("ancient_seeds", "Ancient Seeds", "After lots of work, you got some seeds!");
		this.advancement("scratching_trees", "Scratching Trees", "You stripped a petrified tree! Soon, they'll be extant again!");
		this.advancement("long_lost_trees", "Long Lost Trees", "Bring back a dead tree!");
		this.advancement("the_broken_k_t_boundry", "The Broken K-T Boundry", "Aquire the DNA of an ancient creature!");
		this.advancement("digitalization", "Digitalization", "Write DNA to a disc!");
		this.advancement("dr_frankenstein", "Dr Frankenstein", "Aquire an egg, spawn, or a syringe!");
		this.advancement("the_museum", "The Museum", "You aquired all you need to make a museum, a paleontology table!");
		this.advancement("for_the_masses", "For The Masses", "You started articulating a skeleton!");
		this.advancement("the_time_traveller", "The Time Traveller", "Travelled to every time era in the mod.");
		this.advancement("the_cretaceous", "The Cretaceous", "Acquire volume 3 of the Time Lord's work!");
		this.advancement("cretaceous_explorer", "Cretaceous Explorer", "Explore every biome in the Cretaceous!");
		this.advancement("the_jurassic", "The Jurassic", "Acquire volume 2 of the Time Lord's work!");
		this.advancement("jurassic_explorer", "Jurassic Explorer", "Explore every biome in the Jurassic!");
		this.advancement("the_permian", "The Permian", "Acquire volume 1 of the Time Lord's work!");
		this.advancement("permian_explorer", "Jurassic Permian", "Explore every biome in the Permian!");
		this.advancement("time_travelling", "Time Travelling", "Just like Doc, you'll find yourself lost in time! Or, maybe, you'll be a lord of it!");
	}

	public void addDescription(String item, String desc) {
		this.addRawLang("item.lostworlds." + item + ".desc", desc);
	}

	public void tabletLang(String stat, String value) {
		this.addRawLang("tablet.lostworlds." + stat, value);
	}

	public void subtitle(String sound, String value) {
		this.addRawLang("subtitle.lostworlds." + sound, value);
	}

	public void advancement(String advancement, String title, String desc) {
		this.addRawLang("advancement.lostworlds." + advancement + ".title", title);
		this.addRawLang("advancement.lostworlds." + advancement + ".desc", desc);
	}

	@Override
	public <T extends Container, SC extends Screen & IHasContainer<T>> ContainerBuilder<T, SC, LostWorldsRegistrate> container(String name, ForgeContainerFactory<T> factory, NonNullSupplier<ScreenFactory<T, SC>> screenFactory) {
		this.addRawLang("container.lostworlds." + name, Arrays.stream(name.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
		return super.container(name, factory, screenFactory);
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockAndItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).simpleItem();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> leaves(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).color(() -> LostWorldsBlocks::getGrassyColour).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), provider.mcLoc("block/leaves")).texture("all", provider.modLoc("block/" + name))))).item().color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemModel(String name, String parent, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.withExistingParent(item.getName(), provider.modLoc("block/" + parent))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlat(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlat(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + texture))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlatColoured(String name, String texture, DinoTypes types, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + texture))).color(() -> () -> LostWorldsBlocks.getEggItem(types)).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> sapling(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().cross(block.getName(), provider.modLoc("block/" + name))))).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> plantColoured(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName(), provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_stem").texture("leaves", "block/" + block.getName() + "_leaves")).build();
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> doublePlantColoured(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_stem", provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_stem_bottom").texture("leaves", "block/" + block.getName() + "_leaves_bottom")).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_stem", provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_branch_stem").texture("leaves", "block/" + block.getName() + "_branch_leaves")).build();
			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> doublePlant(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().cross(block.getName() + "_bottom", provider.modLoc("block/" + block.getName() + "_bottom"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().cross(block.getName() + "_top", provider.modLoc("block/" + block.getName() + "_top"))).build();

			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name + "_top"))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> cephalotaxus(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_bottom", provider.modLoc("block/dense_cross")).texture("plant", provider.modLoc("block/cephalotaxus_bottom"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_top", provider.modLoc("block/dense_cross")).texture("plant", provider.modLoc("block/cephalotaxus_top"))).build();
			}
		})).loot((provider, block) -> provider.add(block, LootTable.lootTable().withPool(provider.withSurvivesExplosion(block, LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(block).when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))))))).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name + "_top"))).color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> plant(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().cross(block.getName(), new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + block.getName()))))).item().model((item, provider) -> provider.generated(() -> item.get(), LostWorldsUtils.rL("block/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> parentName(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName() + "_gen", LostWorldsUtils.rL(block.getName()))))).item().color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> parentNameNoItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName() + "_gen", LostWorldsUtils.rL(block.getName())))));
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> pottedBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		if (texture != "archaefrutus" && texture != "calamites_suckowii") {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().singleTexture(block.getName(), new ResourceLocation("block/flower_pot_cross"), "plant", new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + texture))))).tag(BlockTags.FLOWER_POTS);
		} else if (texture == "calamites_suckowii") {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), new ResourceLocation("block/potted_bamboo")).texture("bamboo", LostWorldsUtils.rL("block/calamites_suckowii_stalk")).texture("leaf", LostWorldsUtils.rL("block/calamites_suckowii_singleleaf"))))).tag(BlockTags.FLOWER_POTS);
		} else {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().singleTexture(block.getName(), LostWorldsUtils.rL("block/water_flower_pot_cross"), "plant", new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + texture))))).addLayer(() -> RenderType::translucent).color(() -> LostWorldsBlocks::getWaterColour).tag(BlockTags.FLOWER_POTS);
		}
	}

	public <T extends RotatedPillarBlock> BlockBuilder<T, LostWorldsRegistrate> rotatedBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.logBlock(block.get())).tag(BlockTags.LOGS_THAT_BURN, BlockTags.LOGS).simpleItem();
	}

	public <T extends RotatedPillarBlock> BlockBuilder<T, LostWorldsRegistrate> rotatedWoodBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.axisBlock(block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture))).tag(BlockTags.LOGS_THAT_BURN, BlockTags.LOGS).simpleItem();
	}

	public <T extends StairsBlock> BlockBuilder<T, LostWorldsRegistrate> stairBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.stairsBlock((StairsBlock) block.get(), provider.modLoc("block/" + texture))).simpleItem();
	}

	public <T extends SlabBlock> BlockBuilder<T, LostWorldsRegistrate> slabBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.slabBlock((SlabBlock) block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture))).simpleItem();
	}

	public <T extends WallBlock> BlockBuilder<T, LostWorldsRegistrate> wallBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.wallBlock((WallBlock) block.get(), provider.modLoc("block/" + texture))).item().model(LostWorldsBlockModels.wallInv(texture)).build().tag(BlockTags.WALLS);
	}

	public <T extends FenceBlock> BlockBuilder<T, LostWorldsRegistrate> fenceBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceBlock((FenceBlock) block.get(), provider.modLoc("block/" + texture))).item().model(LostWorldsBlockModels.fenceInv(texture)).build();
	}

	public <T extends FenceGateBlock> BlockBuilder<T, LostWorldsRegistrate> fenceGateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceGateBlock((FenceGateBlock) block.get(), provider.modLoc("block/" + texture))).tag(BlockTags.FENCE_GATES).simpleItem();
	}

	public <T extends AbstractButtonBlock> BlockBuilder<T, LostWorldsRegistrate> buttonBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.button(block.get(), texture, provider)).item().model((item, provider) -> LostWorldsBlockModels.buttonInv(item.get(), provider)).build();
	}

	public <T extends PressurePlateBlock> BlockBuilder<T, LostWorldsRegistrate> pressurePlateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.pressurePlate(block.get(), texture, provider)).simpleItem();
	}

	public <T extends TrapDoorBlock> BlockBuilder<T, LostWorldsRegistrate> trapdoorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.trapdoorBlock((TrapDoorBlock) block.get(), provider.modLoc("block/" + name), true)).item().model((item, provider) -> provider.withExistingParent(name, provider.modLoc("block/" + name + "_bottom"))).build();
	}

	public <T extends DoorBlock> BlockBuilder<T, LostWorldsRegistrate> doorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.doorBlock((DoorBlock) block.get(), provider.modLoc("block/" + name + "_bottom"), provider.modLoc("block/" + name + "_top"))).loot((provider, block) -> provider.add(block, provider.createDoorTable(block))).item().model((item, provider) -> provider.generated(() -> item.get())).build();
	}

	public <T extends AbstractSignBlock> BlockBuilder<T, LostWorldsRegistrate> signBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().getBuilder(name).texture("particle", provider.modLoc("block/" + texture))))).lang(provider -> "block.lostworlds." + name + ".disabled", "Sign");
	}
}
