package lostworlds.mixin;

import org.spongepowered.asm.mixin.Mixin;

import lostworlds.library.util.AlphabeticalItemCategoryFillerUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

@Mixin(SpawnEggItem.class)
public class SpawnEggItemMixin extends Item 
{
	private static final AlphabeticalItemCategoryFillerUtil FILLER = AlphabeticalItemCategoryFillerUtil.forClass(SpawnEggItem.class);

	private SpawnEggItemMixin(Properties properties) 
	{
		super(properties);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) 
	{
		if(this.allowdedIn(group)) 
		{
			ResourceLocation name = this.getRegistryName();
			if((name == null || !name.getNamespace().equals("minecraft")) && (group == ItemGroup.TAB_MISC || group == ItemGroup.TAB_SEARCH)) 
			{
				FILLER.fillItem(this, group, items);
			} 
			else 
			{
				super.fillItemCategory(group, items);
			}
		}
	}
}
