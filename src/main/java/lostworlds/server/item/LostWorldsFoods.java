package lostworlds.server.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class LostWorldsFoods {
	public static final FoodProperties FERN_LEAVES = new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.POISON, 10000), 0.15F).nutrition(3).saturationMod(0.7F).fast().build();
	public static final FoodProperties COOKED_LEAVES = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).fast().build();

	public static final FoodProperties SEEDS = new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).fast().build();

	public static final FoodProperties PALEO_SALAD = new FoodProperties.Builder().nutrition(10).saturationMod(1.0F).build();
}
