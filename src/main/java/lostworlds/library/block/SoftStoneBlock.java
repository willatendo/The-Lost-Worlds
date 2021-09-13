package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.item.HammerItem;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SoftStoneBlock extends Block
{
	private static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	private static final EnumProperty<PotentialPart> POTENTIAL_PART = EnumProperty.create("potential_part", PotentialPart.class);
	private static final EnumProperty<DinoTypes> POTENTIAL_CREATURE = EnumProperty.create("potential_creature", DinoTypes.class);
	private static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);
	
	public SoftStoneBlock()
	{
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PART, PotentialPart.NONE).setValue(POTENTIAL_CREATURE, DinoTypes.TEST).setValue(DAMAGE, Damage.NONE));
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
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) 
	{
		ModUtils.LOGGER.debug("Detected that");
		PotentialPart part = state.getValue(POTENTIAL_PART);
		DinoTypes dino = state.getValue(POTENTIAL_CREATURE);
		Damage damage = state.getValue(DAMAGE);
		Random rand = new Random();
	
		if(part == PotentialPart.LEFT_ARM)
		{
			ModUtils.LOGGER.debug("Got to Here");
			ArmorStandEntity leftArm = (ArmorStandEntity) dino.getLeftArm().create(world);
			if(damage == Damage.NONE)
			{
				ModUtils.LOGGER.debug("Created Entity");
				world.addFreshEntity(leftArm);
			}
			else if(damage == Damage.CHIPPED)
			{
				int chance = rand.nextInt(10);
				if(chance < 9)
				{
					world.addFreshEntity(leftArm);
				}
			}
			else if(damage == Damage.SLIGHTLY)
			{
				int chance = rand.nextInt(7);
				if(chance < 4)
				{
					world.addFreshEntity(leftArm);
				}
			}
			else if(damage == Damage.CRACKED)
			{
				int chance = rand.nextInt(2);
				if(chance == 1)
				{
					world.addFreshEntity(leftArm);
				}
			}
			else if(damage == Damage.DAMAGED)
			{
				int chance = rand.nextInt(7);
				if(chance > 4)
				{
					world.addFreshEntity(leftArm);
				}
			}
		}
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(ERA, POTENTIAL_PART, POTENTIAL_CREATURE, DAMAGE);
	}
}
