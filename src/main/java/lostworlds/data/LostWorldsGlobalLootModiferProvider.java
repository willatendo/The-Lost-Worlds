package lostworlds.data;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.loot.AddItemLootModifer;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class LostWorldsGlobalLootModiferProvider extends GlobalLootModifierProvider {
	public static final LootItemCondition WEIGHT_CHECK = LootItemRandomChanceCondition.randomChance(0.15F).build();

	public LostWorldsGlobalLootModiferProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	protected void start() {
		this.add("crystal_scarab_abdomen", new AddItemLootModifer(new LootItemCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_ABDOMEN.get()));
		this.add("crystal_scarab_bottom_left_leg", new AddItemLootModifer(new LootItemCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_LEFT_LEG.get()));
		this.add("crystal_scarab_bottom_right_leg", new AddItemLootModifer(new LootItemCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG.get()));
		this.add("crystal_scarab_thorax", new AddItemLootModifer(new LootItemCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_THORAX.get()));
		this.add("crystal_scarab_top_left_leg", new AddItemLootModifer(new LootItemCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_TOP_LEFT_LEG.get()));
		this.add("crystal_scarab_top_right_leg", new AddItemLootModifer(new LootItemCondition[] { new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(), WEIGHT_CHECK }, LostWorldsItems.CRYSTAL_SCARAB_TOP_RIGHT_LEG.get()));
	}
}
