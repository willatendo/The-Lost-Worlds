package lostworlds.library.item;

import java.util.Set;

import com.google.common.collect.Sets;

import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class HammerItem extends ToolItem {
	private static final Set<Material> DIGGABLE_MATERIALS = Sets.newHashSet(ModMaterials.SOFT);
	private static final Set<Block> EFFECTIVE_ON_BLOCKS = Sets.newHashSet();

	public HammerItem(IItemTier teir, float attackDamage, float speed, Properties properties) {
		super(attackDamage, speed, teir, EFFECTIVE_ON_BLOCKS, properties.addToolType(ModToolTypes.HAMMER, teir.getLevel()));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return DIGGABLE_MATERIALS.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
	}
}
