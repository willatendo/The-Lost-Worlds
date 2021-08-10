package lostworlds.library;

import lostworlds.library.enums.QuintupleBlockHalfs;
import lostworlds.library.enums.RockSides;
import lostworlds.library.enums.TripleBlockHalfs;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;

/*
 * Author: Willatendo
 * Date: July 4, 2021
 */

public class ModBlockStateProperties 
{
	public static final BooleanProperty ON = BooleanProperty.create("on");
	public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

	public static final IntegerProperty SMALL_EGGS = IntegerProperty.create("small_eggs", 1, 5);
	public static final IntegerProperty MEDIUM_EGGS = IntegerProperty.create("medium_eggs", 1, 3);
	public static final IntegerProperty LARGE_EGGS = IntegerProperty.create("large_eggs", 1, 2);

	public static final EnumProperty<TripleBlockHalfs> TRIPLE_HALFS = EnumProperty.create("triple_halfs", TripleBlockHalfs.class);
	public static final EnumProperty<QuintupleBlockHalfs> HALFS = EnumProperty.create("quintuple_halfs", QuintupleBlockHalfs.class);

	public static final EnumProperty<RockSides> SIDES = EnumProperty.create("sides", RockSides.class);
}
