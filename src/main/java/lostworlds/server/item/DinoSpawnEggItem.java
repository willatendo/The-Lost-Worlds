package lostworlds.server.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.NonNullSupplier;

public class DinoSpawnEggItem extends ModSpawnEggItem {
	private final ITextComponent dinoName;

	public DinoSpawnEggItem(NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, int primaryColour, int secondaryColour, ItemGroup group, ITextComponent dinoName) {
		super(entityTypeSupplier, primaryColour, secondaryColour, group);
		this.dinoName = dinoName;
	}

	@Override
	public ITextComponent getName(ItemStack stack) {
		return new TranslationTextComponent("item.lostworlds.spawn_egg", this.dinoName);
	}
}
