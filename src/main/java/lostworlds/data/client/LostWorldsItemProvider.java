package lostworlds.data.client;

import java.util.function.Supplier;

import lostworlds.LostWorldsMod;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LostWorldsItemProvider extends ItemModelProvider {
	public LostWorldsItemProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, LostWorldsMod.ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (Supplier<Item> items : LostWorldsItems.GENERATED) {
			this.generated(items.get());
		}
		for (Supplier<Item> items : LostWorldsItems.HANDHELD) {
			this.handheld(items.get());
		}
		for (Supplier<Item> items : LostWorldsItems.SPAWN_EGG) {
			this.spawnEgg(items.get());
		}
		for (Supplier<Item> items : LostWorldsItems.PLASTERED) {
			this.textured(items.get(), "plastered_fossil");
		}
		for (Supplier<Item> items : LostWorldsItems.ISTER) {
			this.ister(items.get());
		}
		for (Supplier<Item> items : LostWorldsItems.SPAWN) {
			this.modeled(items.get(), "item/template_spawn");
		}
		for (Supplier<Item> items : LostWorldsItems.DNA_DISC) {
			this.textured(items.get(), "storage_disc");
		}
		for (Supplier<Item> items : LostWorldsItems.HIDE) {
			this.textured(items.get(), "dino_hide");
		}
		for (Supplier<Item> items : LostWorldsItems.BLOOD_SYRINGE) {
			this.textured(items.get(), "blood_syringe");
		}
		for (Supplier<Item> items : LostWorldsItems.SOFT_TISSUE) {
			this.textured(items.get(), "soft_tissue");
		}
		for (Supplier<Item> items : LostWorldsItems.MEAT) {
			this.missingno(items.get());
		}
	}

	public void missingno(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/generated"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "minecraft:item/barrier");
	}

	public void modeled(Item item, String modelName) {
		ModelFile model = getExistingFile(this.modLoc(modelName));
		this.getBuilder(item.getRegistryName().getPath()).parent(model);
	}

	public void ister(Item item) {
		ModelFile model = getExistingFile(new ResourceLocation("lostworlds:item/builtin_entity"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).transforms().transform(Perspective.THIRDPERSON_RIGHT).rotation(75.0F, 45.0F, 0.0F).translation(0.0F, 2.5F, 0.0F).scale(0.375F, 0.375F, 0.375F).end().transform(Perspective.THIRDPERSON_LEFT).rotation(75.0F, 45.0F, 0.0F).translation(0.0F, 2.5F, 0.0F).scale(0.375F, 0.375F, 0.375F).end().transform(Perspective.FIRSTPERSON_RIGHT).rotation(0.0F, 45.0F, 0.0F).translation(1.0F, -1.5F, 1.5F).scale(0.4F, 0.4F, 0.4F).end().transform(Perspective.FIRSTPERSON_LEFT).rotation(0.0F, 45.0F, 0.0F).translation(1.0F, -1.5F, 1.5F).scale(0.4F, 0.4F, 0.4F).end().transform(Perspective.GROUND).rotation(0.0F, 0.0F, 0.0F).translation(0.0F, 3.0F, 0.0F).scale(0.25F, 0.25F, 0.25F).end().transform(Perspective.GUI).rotation(30.0F, 225.0F, 0.0F).translation(1.75F, -4.5F, 0.0F).scale(0.3F, 0.3F, 0.3F).end().transform(Perspective.FIXED).rotation(0.0F, -90.0F, 0.0F).translation(2.25F, -3.75F, 0.0F).scale(0.3F, 0.3F, 0.3F).end().end();
	}

	public void textured(Item item, String texture) {
		ModelFile model = getExistingFile(this.mcLoc("item/generated"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + texture);
	}

	public void generated(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/generated"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + item.getRegistryName().getPath());
	}

	public void handheld(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/handheld"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + item.getRegistryName().getPath());
	}

	public void spawnEgg(Item item) {
		ModelFile model = getExistingFile(this.mcLoc("item/template_spawn_egg"));
		this.getBuilder(item.getRegistryName().getPath()).parent(model);
	}
}
