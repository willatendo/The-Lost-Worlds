package lostworlds.server.loot;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.LootModifier;

public class AddItemLootModifer extends LootModifier {
	private final Item item;

	public AddItemLootModifer(ILootCondition[] conditions, Item item) {
		super(conditions);
		this.item = item;
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(this.item.getDefaultInstance());
		return generatedLoot;
	}

	public ILootCondition[] getConditions() {
		return this.conditions;
	}

	public Item getItem() {
		return item;
	}
}
