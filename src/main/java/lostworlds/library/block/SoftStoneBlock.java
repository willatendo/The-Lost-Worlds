package lostworlds.library.block;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.item.HammerItem;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoftStoneBlock extends Block
{
	private static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	private static final EnumProperty<PotentialPart> POTENTIAL_PART = EnumProperty.create("potential_part", PotentialPart.class);
	private static final EnumProperty<DinoTypes> POTENTIAL_CREATURE = EnumProperty.create("potential_creature", DinoTypes.class);
	private static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);
	
	public SoftStoneBlock()
	{
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PART, PotentialPart.NONE).setValue(POTENTIAL_CREATURE, DinoTypes.CHILESAURUS).setValue(DAMAGE, Damage.NONE));
	}
	
	@Override
	public void playerDestroy(World world, PlayerEntity entity, BlockPos pos, BlockState state, TileEntity tileentity, ItemStack stack) 
	{
		ItemStack heldItem = entity.getItemInHand(Hand.MAIN_HAND);
		if(!(heldItem.getItem() instanceof HammerItem))
		{
			BlockState up = world.getBlockState(pos.above());
			BlockState below = world.getBlockState(pos.below());
			BlockState north = world.getBlockState(pos.north());
			BlockState south = world.getBlockState(pos.south());
			BlockState east = world.getBlockState(pos.east());
			BlockState west = world.getBlockState(pos.west());
			if(up.is(BlockInit.SOFT_STONE))
			{
				world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
			}
			
			if(below.is(BlockInit.SOFT_STONE))
			{
				world.setBlockAndUpdate(pos.below(), Blocks.AIR.defaultBlockState());
			}

			if(north.is(BlockInit.SOFT_STONE))
			{
				world.setBlockAndUpdate(pos.north(), Blocks.AIR.defaultBlockState());
			}

			if(south.is(BlockInit.SOFT_STONE))
			{
				world.setBlockAndUpdate(pos.south(), Blocks.AIR.defaultBlockState());
			}

			if(east.is(BlockInit.SOFT_STONE))
			{
				world.setBlockAndUpdate(pos.east(), Blocks.AIR.defaultBlockState());
			}

			if(west.is(BlockInit.SOFT_STONE))
			{
				world.setBlockAndUpdate(pos.west(), Blocks.AIR.defaultBlockState());
			}
		}
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(ERA, POTENTIAL_PART, POTENTIAL_CREATURE, DAMAGE);
	}
}
