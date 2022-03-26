package lostworlds.server.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.EntityBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LostWorldsRegistrate extends AbstractRegistrate<LostWorldsRegistrate> {
	protected LostWorldsRegistrate(String modid) {
		super(modid);

		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("itemGroup.lostworlds.items", "Lost Worlds Items"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("itemGroup.lostworlds.blocks", "Lost Worlds Blocks"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.lost_worlds_lexicon.desc", "An Everything-You-Need-to-Know Book!"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.field_guide.desc", "Willatendo - Volume 3"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.music_disc_ascented.desc", "Willatendo - Ascented"));
		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.collectible.desc", "It's a collectible!"));
		for (DyeColor color : DyeColor.values()) {
			this.addDataGenerator(ProviderType.LANG, provider -> provider.add("block.minecraft.banner.scarab." + color.getName(), Arrays.stream(color.getName().toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")) + " Scarab"));
		}
	}

	public static NonNullSupplier<LostWorldsRegistrate> lazy(String modid) {
		return NonNullSupplier.of(() -> new LostWorldsRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}

	public <T extends Entity, P> EntityBuilder<T, P> entity(P parent, String name, EntityType.IFactory<T> factory, EntityClassification classification) {
		return entry(name, callback -> EntityBuilder.create(this, parent, name, callback, factory, classification));
	}
}
