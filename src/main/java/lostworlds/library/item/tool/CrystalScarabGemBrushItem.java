package lostworlds.library.item.tool;

import lostworlds.library.util.ModRegistry;
import net.minecraft.item.Item;

public class CrystalScarabGemBrushItem extends BrushItem
{
	protected CrystalScarabGemBrushItem() 
	{
		super(ModItemTeir.CRYSTAL_SCARAB_GEM, new Properties().setNoRepair());
	}
	

	public static Item create()
	{
		Item item = new CrystalScarabGemBrushItem();
		ModRegistry.register("crystal_scarab_gem_brush", item);
		return item;
	}
}
