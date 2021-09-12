package lostworlds.content.server.init;

import lostworlds.library.entity.DinoTypes;
import lostworlds.library.item.builder.ItemBuilder;
import lostworlds.library.item.builder.SpawnEggItemBuilder;
import lostworlds.library.tab.ModItemGroup;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class DinosaurInit 
{	
	public static void init() 
	{
		for(DinoTypes dinos : DinoTypes.values())
		{
			ItemBuilder.create(dinos.name().toLowerCase() + "_rib_cage", ModItemGroup.DINOSAURS);
			ItemBuilder.create(dinos.name().toLowerCase() + "_leg_bones", ModItemGroup.DINOSAURS);
			ItemBuilder.create(dinos.name().toLowerCase() + "_arm_bones", ModItemGroup.DINOSAURS);
			ItemBuilder.create(dinos.name().toLowerCase() + "_foot_bone", ModItemGroup.DINOSAURS);
			ItemBuilder.create(dinos.name().toLowerCase() + "_skull", ModItemGroup.DINOSAURS);
			ItemBuilder.create(dinos.name().toLowerCase() + "_special", ModItemGroup.DINOSAURS);
			ItemBuilder.create(dinos.name().toLowerCase() + "_spawn_egg", new SpawnEggItemBuilder(() -> dinos.getEntityType(), dinos.getPrimaryColour(), dinos.getSecondaryColour()));
			ItemBuilder.create(dinos.name().toLowerCase() + "_egg", ModItemGroup.DINOSAURS);
			ItemBuilder.create("raw_" + dinos.name().toLowerCase() + "_meat", new Item(new Item.Properties().tab(ModItemGroup.DINOSAURS).food(new Food.Builder().nutrition(dinos.getRawNutrition()).saturationMod(dinos.getRawSaturation()).build())));
			ItemBuilder.create("cooked_" + dinos.name().toLowerCase() + "_meat", new Item(new Item.Properties().tab(ModItemGroup.DINOSAURS).food(new Food.Builder().nutrition(dinos.getCookedNutrition()).saturationMod(dinos.getCookedSaturation()).build())));
		}
	}
}
