package lostworlds.library.item.block;

import java.util.Map;

import javax.annotation.Nullable;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

public class SeedItem extends Item 
{
	@Deprecated
	private final Block block;

	public SeedItem(Block block) 
	{
		super(new Properties().tab(ModItemGroup.PLANTS));
		this.block = block;
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) 
	{
		ActionResultType actionresulttype = this.place(new BlockItemUseContext(context));
		return !actionresulttype.consumesAction() && this.isEdible() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : actionresulttype;
	}

	public ActionResultType place(BlockItemUseContext context) 
	{
		if(!context.canPlace()) 
		{
			return ActionResultType.FAIL;
		} 
		else 
		{
			BlockItemUseContext blockitemusecontext = this.updatePlacementContext(context);
			if(blockitemusecontext == null) 
			{
				return ActionResultType.FAIL;
			} 
			else 
			{
				BlockState blockstate = this.getPlacementState(blockitemusecontext);
				if(blockstate == null) 
				{
					return ActionResultType.FAIL;
				} 
				else if(!this.placeBlock(blockitemusecontext, blockstate)) 
				{
					return ActionResultType.FAIL;
				}
				else 
				{
					BlockPos blockpos = blockitemusecontext.getClickedPos();
					World world = blockitemusecontext.getLevel();
					PlayerEntity playerentity = blockitemusecontext.getPlayer();
					ItemStack itemstack = blockitemusecontext.getItemInHand();
					BlockState blockstate1 = world.getBlockState(blockpos);
					Block block = blockstate1.getBlock();
					if(block == blockstate.getBlock()) 
					{
						blockstate1 = this.updateBlockStateFromTag(blockpos, world, itemstack, blockstate1);
						block.setPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);
						if(playerentity instanceof ServerPlayerEntity) 
						{
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerentity, blockpos, itemstack);
						}
					}

					SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
					world.playSound(playerentity, blockpos, this.getPlaceSound(blockstate1, world, blockpos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					if(playerentity == null || !playerentity.abilities.instabuild) 
					{
						itemstack.shrink(1);
					}

					return ActionResultType.sidedSuccess(world.isClientSide);
				}
			}
		}
	}

	@Deprecated
	protected SoundEvent getPlaceSound(BlockState state) 
	{
		return state.getSoundType().getPlaceSound();
	}

	protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) 
	{
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}

	@Nullable
	public BlockItemUseContext updatePlacementContext(BlockItemUseContext context) 
	{
		return context;
	}

	@Nullable
	protected BlockState getPlacementState(BlockItemUseContext context) 
	{
		BlockState blockstate = this.getBlock().getStateForPlacement(context);
		return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
	}

	private BlockState updateBlockStateFromTag(BlockPos pos, World world, ItemStack stack, BlockState state) 
	{
		BlockState blockstate = state;
		CompoundNBT compoundnbt = stack.getTag();
		if(compoundnbt != null) 
		{
			CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
			StateContainer<Block, BlockState> statecontainer = state.getBlock().getStateDefinition();

			for(String s : compoundnbt1.getAllKeys()) 
			{
				Property<?> property = statecontainer.getProperty(s);
				if(property != null) 
				{
					String s1 = compoundnbt1.get(s).getAsString();
					blockstate = updateState(blockstate, property, s1);
				}
			}
		}

		if(blockstate != state) 
		{
			world.setBlock(pos, blockstate, 2);
		}

		return blockstate;
	}

	private static <T extends Comparable<T>> BlockState updateState(BlockState state, Property<T> properties, String nbt) 
	{
		return properties.getValue(nbt).map((comparable) -> 
		{
			return state.setValue(properties, comparable);
		}).orElse(state);
	}

	protected boolean canPlace(BlockItemUseContext context, BlockState state) 
	{
		PlayerEntity playerentity = context.getPlayer();
		ISelectionContext iselectioncontext = playerentity == null ? ISelectionContext.empty() : ISelectionContext.of(playerentity);
		return (!this.mustSurvive() || state.canSurvive(context.getLevel(), context.getClickedPos())) && context.getLevel().isUnobstructed(state, context.getClickedPos(), iselectioncontext);
	}

	protected boolean mustSurvive() 
	{
		return true;
	}

	protected boolean placeBlock(BlockItemUseContext context, BlockState state) 
	{
		return context.getLevel().setBlock(context.getClickedPos(), state, 11);
	}

	public static boolean updateCustomBlockEntityTag(World world, @Nullable PlayerEntity entity, BlockPos pos, ItemStack stack) 
	{
		MinecraftServer minecraftserver = world.getServer();
		if(minecraftserver == null) 
		{
			return false;
		} 
		else 
		{
			CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");
			if(compoundnbt != null) 
			{
				TileEntity tileentity = world.getBlockEntity(pos);
				if(tileentity != null) 
				{
					if(!world.isClientSide && tileentity.onlyOpCanSetNbt() && (entity == null || !entity.canUseGameMasterBlocks())) 
					{
						return false;
					}

					CompoundNBT compoundnbt1 = tileentity.save(new CompoundNBT());
					CompoundNBT compoundnbt2 = compoundnbt1.copy();
					compoundnbt1.merge(compoundnbt);
					compoundnbt1.putInt("x", pos.getX());
					compoundnbt1.putInt("y", pos.getY());
					compoundnbt1.putInt("z", pos.getZ());
					if(!compoundnbt1.equals(compoundnbt2)) 
					{
						tileentity.load(world.getBlockState(pos), compoundnbt1);
						tileentity.setChanged();
						return true;
					}
				}
			}

			return false;
		}
	}

	public Block getBlock() 
	{
		return this.getBlockRaw() == null ? null : this.getBlockRaw().delegate.get();
	}

	private Block getBlockRaw() 
	{
		return this.block;
	}

	public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) 
	{
		blockToItemMap.put(this.getBlock(), item);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item item) 
	{
		blockToItemMap.remove(this.getBlock());
	}

	public static Item create(String plant, Block block) 
	{
		Item item = new SeedItem(block);
		ModRegistry.register(plant + "_seeds", item);
		return item;
	}
}
