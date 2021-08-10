package lostworlds.content.server.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

public class FoodInit 
{
	public static final Food FERN_LEAVES = new Food.Builder().effect(() -> new EffectInstance(Effects.POISON, 10000), 0.15F).nutrition(3).saturationMod(0.7F).fast().build();
	public static final Food COOKED_LEAVES = new Food.Builder().nutrition(6).saturationMod(0.9F).fast().build();
	
	public static final Food SEEDS = new Food.Builder().nutrition(1).saturationMod(0.3F).fast().build();
	
	public static final Food PALEO_SALAD = new Food.Builder().nutrition(10).saturationMod(1.0F).build();
}
