package lostworlds.server.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LostWorldsFoods {
	public static final Food FERN_LEAVES = new Food.Builder().effect(() -> new EffectInstance(Effects.POISON, 10000), 0.15F).nutrition(3).saturationMod(0.7F).fast().build();
	public static final Food COOKED_LEAVES = new Food.Builder().nutrition(6).saturationMod(0.9F).fast().build();

	public static final Food SEEDS = new Food.Builder().nutrition(1).saturationMod(0.3F).fast().build();

	public static final Food PALEO_SALAD = new Food.Builder().nutrition(10).saturationMod(1.0F).build();
}
