package lostworlds.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LostWorldsChestLoot implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> provider) {
		provider.accept(LostWorldsUtils.rL("chests/black_market_loot"), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 5.0F)).add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.EMERALD).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))).add(LootItem.lootTableItem(Items.HEART_OF_THE_SEA).setWeight(2)).add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(Items.DIAMOND).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(LostWorldsItems.CRYSTAL_SCARAB_ABDOMEN.get()).setWeight(1)).add(LootItem.lootTableItem(LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get()).setWeight(1)).add(LootItem.lootTableItem(LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get()).setWeight(1)).add(LootItem.lootTableItem(LostWorldsItems.CRYSTAL_SCARAB_THORAX.get()).setWeight(1)).add(LootItem.lootTableItem(LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get()).setWeight(1)).add(LootItem.lootTableItem(LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()).setWeight(1)).add(LootItem.lootTableItem(LostWorldsItems.HAMMER.get()).setWeight(2))));
	}
}
