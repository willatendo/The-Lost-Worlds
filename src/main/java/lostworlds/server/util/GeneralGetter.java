package lostworlds.server.util;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.EntityEntry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneralGetter {
	public static Block getWhenCan(BlockEntry entry) {
		return ForgeRegistries.BLOCKS.getValue(entry.getId());
	}

	public static BlockState getStateWhenCan(BlockEntry entry) {
		return ForgeRegistries.BLOCKS.getValue(entry.getId()).defaultBlockState();
	}

	public static EntityType<?> getWhenCan(EntityEntry<? extends Entity> entry) {
		return ForgeRegistries.ENTITIES.getValue(entry.getId());
	}
}
