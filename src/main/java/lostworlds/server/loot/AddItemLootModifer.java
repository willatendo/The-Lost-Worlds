package lostworlds.server.loot;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;

public class AddItemLootModifer extends LootModifier {
	private final Item item;

	public AddItemLootModifer(LootItemCondition[] conditions, Item item) {
		super(conditions);
		this.item = item;
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(this.item.getDefaultInstance());
		return generatedLoot;
	}

	public LootItemCondition[] getConditions() {
		return this.conditions;
	}

	public Item getItem() {
		return item;
	}
}
