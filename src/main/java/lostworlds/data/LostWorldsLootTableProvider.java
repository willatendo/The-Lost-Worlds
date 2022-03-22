package lostworlds.data;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import lostworlds.LostWorldsMod;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsLootTableProvider extends LootTableProvider {
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(Pair.of(LostWorldsEntityLoot::new, LootParameterSets.ENTITY));

	public LostWorldsLootTableProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return tables;
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((id, table) -> LootTableManager.validate(validationtracker, id, table));
	}

	private static class LostWorldsEntityLoot extends EntityLootTables {
		@Override
		protected void addTables() {
			for (DinoTypes dinos : DinoTypes.values()) {
				if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
					this.add(dinos.getSkull(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(dinos.getSkullItem()).apply(SetCount.setCount(ConstantRange.exactly(1)))).when(KilledByPlayer.killedByPlayer())));
					this.add(dinos.getArmBones(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(dinos.getArmBonesItem()).apply(SetCount.setCount(ConstantRange.exactly(1)))).when(KilledByPlayer.killedByPlayer())));
					this.add(dinos.getLegBones(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(dinos.getLegBonesItem()).apply(SetCount.setCount(ConstantRange.exactly(1)))).when(KilledByPlayer.killedByPlayer())));
					this.add(dinos.getRibCage(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(dinos.getRibCageItem()).apply(SetCount.setCount(ConstantRange.exactly(1)))).when(KilledByPlayer.killedByPlayer())));
					this.add(dinos.getTail(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(dinos.getTailItem()).apply(SetCount.setCount(ConstantRange.exactly(1)))).when(KilledByPlayer.killedByPlayer())));
				}
			}
		}
		
		@Override
		protected boolean isNonLiving(EntityType<?> entitytype) {
			return false;
		}

		@Override
		protected Iterable<EntityType<?>> getKnownEntities() {
			return ForgeRegistries.ENTITIES.getValues().stream().filter(entityType -> entityType.getRegistryName() != null && LostWorldsMod.ID.equals(entityType.getRegistryName().getNamespace())).collect(Collectors.toSet());
		}
	}
}
