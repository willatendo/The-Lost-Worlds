package lostworlds.library.block;

import lostworlds.content.ModRegistry;
import lostworlds.library.item.block.BrazileaItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

public class BrazileaBlock extends BushBlock {
	private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);

	public BrazileaBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		super.entityInside(state, world, pos, entity);
		if (world instanceof ServerWorld && entity instanceof BoatEntity) {
			world.destroyBlock(new BlockPos(pos), true, entity);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader reader, BlockPos pos) {
		FluidState fluidstate = reader.getFluidState(pos);
		return (fluidstate.getType() == Fluids.WATER);
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.WATER;
	}

	public static Block create(String id) {
		Block block = new BrazileaBlock(AbstractBlock.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).noOcclusion().noCollission());
		Item item = new BrazileaItem(block);
		ModRegistry.register(id, block);
		ModRegistry.register(id, item);
		return block;
	}
}
