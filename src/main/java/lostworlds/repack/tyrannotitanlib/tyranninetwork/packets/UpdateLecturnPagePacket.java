package lostworlds.repack.tyrannotitanlib.tyranninetwork.packets;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookHelper;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.util.TileEntityHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class UpdateLecturnPagePacket implements ThreadSafePacket {
	private final BlockPos pos;
	private final String page;

	public UpdateLecturnPagePacket(PacketBuffer buffer) {
		this.pos = buffer.readBlockPos();
		this.page = buffer.readUtf(100);
	}

	public UpdateLecturnPagePacket(BlockPos pos, String page) {
		this.pos = pos;
		this.page = page;
	}

	@Override
	public void encode(PacketBuffer buf) {
		buf.writeBlockPos(pos);
		buf.writeUtf(page);
	}

	@Override
	public void handleThreadsafe(Context context) {
		PlayerEntity player = context.getSender();
		if (player != null && this.page != null) {
			World world = player.getCommandSenderWorld();
			TileEntityHelper.getTile(LecternTileEntity.class, world, this.pos).ifPresent(te -> {
				ItemStack stack = te.getBook();
				if (!stack.isEmpty()) {
					TyrannobookHelper.writeSavedPageToBook(stack, this.page);
				}
			});
		}
	}
}
