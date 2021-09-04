package lostworlds.library.block;

import lostworlds.library.util.ModUtils;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ConnectedTextureBlock extends AbstractGlassBlock 
{
	public final ResourceLocation texture;
	public final boolean connected;

	public ConnectedTextureBlock(Properties properties, String texture, boolean connected) 
	{
		super(properties);
		this.texture = ModUtils.rL(texture);
		this.connected = connected;
	}

	@Override
	public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, EntityType<?> entityType)
	{
		return false;
	}
}
