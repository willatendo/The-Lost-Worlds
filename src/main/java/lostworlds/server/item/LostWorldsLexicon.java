package lostworlds.server.item;

import java.util.List;

import lostworlds.client.book.LostWorldsBooks;
import lostworlds.client.books.tyrannibook.item.TyrannobookItem;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class LostWorldsLexicon extends TyrannobookItem {
	public LostWorldsLexicon(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag) {
		text.add(LostWorldsUtils.gTC("icon", "lost_worlds_lexicon.desc"));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		if (world.isClientSide) {
			LostWorldsBooks.LOST_WORLDS_LEXICON.openGui(hand, stack);
		}
		return ActionResult.success(stack);
	}

	@Override
	public void openLecternScreenClient(BlockPos pos, ItemStack stack) {
		LostWorldsBooks.LOST_WORLDS_LEXICON.openGui(pos, stack);
		;
	}
}
