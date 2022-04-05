package lostworlds.repack.tyrannotitanlib.tyranninetwork.packets;

import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class DropLecternBookPacket implements ThreadSafePacket 
{
	private final BlockPos pos;

	public DropLecternBookPacket(PacketBuffer buffer) 
	{
		this.pos = buffer.readBlockPos();
	}
	
	public DropLecternBookPacket(BlockPos pos) 
	{
		this.pos = pos;
	}

	@Override
	public void encode(PacketBuffer buffer) 
	{
		buffer.writeBlockPos(pos);
	}

	@Override
	public void handleThreadsafe(Context context) 
	{
		ServerPlayerEntity player = context.getSender();
		if(player == null) 
		{
			return;
		}

		ServerWorld world = player.getLevel();

		if(!world.hasChunkAt(pos)) 
		{
			return;
		}

		BlockState state = world.getBlockState(pos);

		if(state.getBlock() instanceof LecternBlock && state.getValue(LecternBlock.HAS_BOOK)) 
		{
			TileEntity te = world.getBlockEntity(pos);
			if(te instanceof LecternTileEntity) 
			{
				LecternTileEntity lecternTe = (LecternTileEntity) te;

				ItemStack book = lecternTe.getBook().copy();
				if(!book.isEmpty()) 
				{
					if(!player.addItem(book)) 
					{
						player.drop(book, false, false);
					}

					lecternTe.clearContent();

					world.setBlock(pos, state.setValue(LecternBlock.POWERED, false).setValue(LecternBlock.HAS_BOOK, false), 3);
					world.updateNeighborsAt(pos.below(), state.getBlock());
				}
			}
		}
	}
}
