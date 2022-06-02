package lostworlds.server.network.book;

import lostworlds.client.book.BookLoader;
import lostworlds.client.book.data.BookData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class OpenNamedBookPacket implements ThreadsafePacket {
	private static final String BOOK_ERROR = "command.tyrannobook.book_test.not_found";
	private final ResourceLocation book;

	public OpenNamedBookPacket(FriendlyByteBuf buffer) {
		this.book = buffer.readResourceLocation();
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(this.book);
	}

	@Override
	public void handleThreadsafe(NetworkEvent.Context networkContext) {
		BookData bookData = BookLoader.getBook(this.book);
		if (bookData != null) {
			bookData.openGui(new TextComponent("Book"), "", null, null);
		} else {
			ClientOnly.errorStatus(this.book);
		}
	}

	private static class ClientOnly {
		private static void errorStatus(ResourceLocation resourceLocation) {
			Player player = Minecraft.getInstance().player;
			if (player != null) {
				player.displayClientMessage(new TranslatableComponent(BOOK_ERROR, resourceLocation).withStyle(ChatFormatting.RED), false);
			}
		}
	}
}
