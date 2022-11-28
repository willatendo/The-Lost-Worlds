package lostworlds.server.entity;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsPOIs {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.Keys.POI_TYPES, LostWorldsUtils.ID);

	private static Set<BlockState> getBlockStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}

	public static final RegistryObject<PoiType> ARCHAEOLOGY_TABLE = POI_TYPES.register("archaeology_table", () -> new PoiType(getBlockStates(LostWorldsBlocks.ARCHAEOLOGY_TABLE.get()), 1, 1));
	public static final RegistryObject<PoiType> PALEONTOLOGY_TABLE = POI_TYPES.register("paleontology_table", () -> new PoiType(getBlockStates(LostWorldsBlocks.PALEONTOLOGY_TABLE.get()), 1, 1));
}
