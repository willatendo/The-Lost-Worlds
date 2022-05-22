package lostworlds.server.entity.utils;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.Plants;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class FoodLists {
	public static final Ingredient CARNIVORE = Ingredient.of(Items.BEEF, Items.CHICKEN, Items.MUTTON, Items.PORKCHOP, Items.RABBIT, DinoTypes.CHILESAURUS.getMeat().get(), DinoTypes.DIICTODON.getMeat().get(), DinoTypes.DILOPHOSAURUS.getMeat().get(), DinoTypes.KENTROSAURUS.getMeat().get(), DinoTypes.LIAONINGOSAURUS.getMeat().get(), DinoTypes.OPHTHALMOSAURUS.getMeat().get(), DinoTypes.PSITTACOSAURUS.getMeat().get(), DinoTypes.TETRACERATOPS.getMeat().get(), DinoTypes.ZEPHYROSAURUS.getMeat().get());

	public static final Ingredient HERBIVORE = Ingredient.of(Items.APPLE, Items.BEETROOT, Items.BEETROOT_SEEDS, Items.BREAD, Items.CARROT, Items.DRIED_KELP, Items.MELON, Items.MELON_SLICE, Items.MELON_SEEDS, Items.POTATO, Items.PUMPKIN, Items.CARVED_PUMPKIN, Items.PUMPKIN_PIE, Items.PUMPKIN_SEEDS, Items.SWEET_BERRIES, Items.WHEAT, Items.WHEAT_SEEDS, LostWorldsBlocks.ALETHOPTERIS.get(), LostWorldsBlocks.BRAZILEA.get(), LostWorldsBlocks.CEPHALOTAXUS.get(), LostWorldsBlocks.CALAMITES_SUCKOWII.get(), LostWorldsBlocks.DILLHOFFIA.get(), LostWorldsBlocks.DUISBERGIA.get(), LostWorldsBlocks.EUDICOTS.get(), LostWorldsBlocks.GROUND_FERNS.get(), LostWorldsBlocks.MAGNOLIA.get(), LostWorldsBlocks.OSMUNDA.get(), LostWorldsBlocks.PERMIAN_DESERT_FERNS.get(), LostWorldsBlocks.PERMIAN_DESERT_SHRUB.get(), LostWorldsBlocks.WILLIAMSONIA.get(), LostWorldsBlocks.ZAMITES.get(), Plants.ALETHOPTERIS.getSeed().get(), Plants.ARCHAEFRUTUS.getSeed().get(), Plants.BRAZILEA.getSeed().get(), Plants.CALAMITES_SUCKOWII.getSeed().get(), Plants.CEPHALOTAXUS.getSeed().get(), Plants.CYCAD.getSeed().get(), Plants.DICKSONIA.getSeed().get(), Plants.DILLHOFFIA.getSeed().get(), Plants.DUISBERGIA.getSeed().get(), Plants.EUDICOTS.getSeed().get(), Plants.MAGNOLIA.getSeed().get(), Plants.OSMUNDA.getSeed().get(), Plants.WILLIAMSONIA.getSeed().get(), Plants.ZAMITES.getSeed().get());

	public static final Ingredient PISCIVORE = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, DinoTypes.PALAEONISCUM.getMeat().get());
}
