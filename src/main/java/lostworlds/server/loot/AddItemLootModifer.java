package lostworlds.server.loot;

import com.mojang.serialization.Codec;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class AddItemLootModifer extends LootModifier {
	private final Item item;

	public AddItemLootModifer(LootItemCondition[] conditions, Item item) {
		super(conditions);
		this.item = item;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return null;
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(this.item.getDefaultInstance());
		return generatedLoot;
	}

	public LootItemCondition[] getConditions() {
		return this.conditions;
	}

	public Item getItem() {
		return this.item;
	}
}
