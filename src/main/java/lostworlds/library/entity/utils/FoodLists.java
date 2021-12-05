package lostworlds.library.entity.utils;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.Plants;
import lostworlds.library.entity.utils.enums.DinoTypes;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class FoodLists 
{
	public static final Ingredient CARNIVORE = Ingredient.of(Items.BEEF, Items.CHICKEN, Items.MUTTON, Items.PORKCHOP, Items.RABBIT, DinoTypes.CHILESAURUS.getMeat(), DinoTypes.DIICTODON.getMeat(), DinoTypes.DILOPHOSAURUS.getMeat(), DinoTypes.KENTROSAURUS.getMeat(), DinoTypes.LIAONINGOSAURUS.getMeat(), DinoTypes.OPHTHALMOSAURUS.getMeat(), DinoTypes.PSITTACOSAURUS.getMeat(), DinoTypes.TETRACERATOPS.getMeat(), DinoTypes.ZEPHYROSAURUS.getMeat());

	public static final Ingredient HERBIVORE = Ingredient.of(Items.APPLE, Items.BEETROOT, Items.BEETROOT_SEEDS, Items.BREAD, Items.CARROT, Items.DRIED_KELP, Items.MELON, Items.MELON_SLICE, Items.MELON_SEEDS, Items.POTATO, Items.PUMPKIN, Items.CARVED_PUMPKIN, Items.PUMPKIN_PIE, Items.PUMPKIN_SEEDS, Items.SWEET_BERRIES, Items.WHEAT, Items.WHEAT_SEEDS, BlockInit.ALETHOPTERIS, BlockInit.BRAZILEA, BlockInit.CEPHALOTAXUS, BlockInit.CALAMITES_SUCKOWII, BlockInit.DILLHOFFIA, BlockInit.DUISBERGIA, BlockInit.EUDICOTS, BlockInit.GROUND_FERNS, BlockInit.MAGNOLIA, BlockInit.OSMUNDA, BlockInit.PERMIAN_DESERT_FERNS, BlockInit.PERMIAN_DESERT_SHRUB, BlockInit.WILLIAMSONIA, BlockInit.ZAMITES, Plants.ALETHOPTERIS.getSeed(), Plants.ARCHAEFRUTUS.getSeed(), Plants.BRAZILEA.getSeed(), Plants.CALAMITES_SUCKOWII.getSeed(), Plants.CEPHALOTAXUS.getSeed(), Plants.CYCAD.getSeed(), Plants.DICKSONIA.getSeed(), Plants.DILLHOFFIA.getSeed(), Plants.DUISBERGIA.getSeed(), Plants.EUDICOTS.getSeed(), Plants.MAGNOLIA.getSeed(), Plants.OSMUNDA.getSeed(), Plants.WILLIAMSONIA.getSeed(), Plants.ZAMITES.getSeed());

	public static final Ingredient PISCIVORE = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, DinoTypes.PALAEONISCUM.getMeat());
}
