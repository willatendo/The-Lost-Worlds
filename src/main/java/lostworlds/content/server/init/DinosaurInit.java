package lostworlds.content.server.init;

import lostworlds.library.entity.DinoTypes;
import lostworlds.library.item.builder.ItemBuilder;
import lostworlds.library.item.builder.SpawnEggItemBuilder;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class DinosaurInit 
{	
	public static void init() 
	{
		for(DinoTypes dinos : DinoTypes.values())
		{
			ItemBuilder.create(dinos.name().toLowerCase() + "_rib_cage");
			ItemBuilder.create(dinos.name().toLowerCase() + "_leg_bones");
			ItemBuilder.create(dinos.name().toLowerCase() + "_arm_bones");
			ItemBuilder.create(dinos.name().toLowerCase() + "_foot_bone");
			ItemBuilder.create(dinos.name().toLowerCase() + "_skull");
			ItemBuilder.create(dinos.name().toLowerCase() + "_special");
			ItemBuilder.create(dinos.name().toLowerCase() + "_spawn_egg", new SpawnEggItemBuilder(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour()));
			ItemBuilder.create(dinos.name().toLowerCase() + "_egg");
			ItemBuilder.create("raw_" + dinos.name().toLowerCase() + "_meat", new Item(new Item.Properties().food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())));
			ItemBuilder.create("cooked_" + dinos.name().toLowerCase() + "_meat", new Item(new Item.Properties().food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())));
		}
	}
}
