package lostworlds.data;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.loot.AddItemLootModifer;
import lostworlds.server.loot.LostWorldsGlobalLootModifers;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class LostWorldsGlobalLootModiferProvider extends GlobalLootModifierProvider {
	public static final ILootCondition WEIGHT_CHECK = RandomChance.randomChance(0.15F).build();

	public LostWorldsGlobalLootModiferProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	protected void start() {
		this.add("crystal_scarab_abdomen", LostWorldsGlobalLootModifers.ADD_ITEM_LOOT_MODIFIER_SERIALIZER.get(), new AddItemLootModifer(new ILootCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_ABDOMEN.get()));
		this.add("crystal_scarab_bottom_left_leg", LostWorldsGlobalLootModifers.ADD_ITEM_LOOT_MODIFIER_SERIALIZER.get(), new AddItemLootModifer(new ILootCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get()));
		this.add("crystal_scarab_bottom_right_leg", LostWorldsGlobalLootModifers.ADD_ITEM_LOOT_MODIFIER_SERIALIZER.get(), new AddItemLootModifer(new ILootCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get()));
		this.add("crystal_scarab_thorax", LostWorldsGlobalLootModifers.ADD_ITEM_LOOT_MODIFIER_SERIALIZER.get(), new AddItemLootModifer(new ILootCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_THORAX.get()));
		this.add("crystal_scarab_top_left_leg", LostWorldsGlobalLootModifers.ADD_ITEM_LOOT_MODIFIER_SERIALIZER.get(), new AddItemLootModifer(new ILootCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get()));
		this.add("crystal_scarab_top_right_leg", LostWorldsGlobalLootModifers.ADD_ITEM_LOOT_MODIFIER_SERIALIZER.get(), new AddItemLootModifer(new ILootCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()));
	}
}
