package lostworlds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

@Mixin(Items.class)
public class ItemsMixin 
{
	@Inject(method = "registerItem", at = @At("HEAD"), cancellable = true)
	private static void swapNautilusShell(String string, Item item, CallbackInfoReturnable<Item> cir) 
	{
		if(string.equals("nautilus_shell")) 
		{
			cir.setReturnValue(Registry.register(Registry.ITEM, string, new BlockItem(BlockInit.NAUTILUS_SHELL, new Item.Properties().tab(ItemGroup.TAB_MATERIALS))));
		}
	}
}
