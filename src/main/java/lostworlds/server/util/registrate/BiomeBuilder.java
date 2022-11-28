package lostworlds.server.util.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeBuilder<T extends Biome, P> extends AbstractBuilder<Biome, T, P, BiomeBuilder<T, P>> {
	public static final int BASE_WATER_COLOUR = 4159204;
	public static final int BASE_WATER_FOG_COLOUR = 329011;

	public static final int OCEAN_WATER_COLOUR = 4159204;
	public static final int OCEAN_WATER_FOG_COLOUR = 4159204;

	public static final int LUKE_WARM_OCEAN_WATER_COLOUR = 4566514;
	public static final int LUKE_WARM_OCEAN_WATER_FOG_COLOUR = 267827;

	public static final int WARM_OCEAN_WATER_COLOUR = 4445678;
	public static final int WARM_OCEAN_WATER_FOG_COLOUR = 270131;

	public static final int COLD_OCEAN_WATER_COLOUR = 4020182;
	public static final int COLD_OCEAN_WATER_FOG_COLOUR = 329011;

	public static final int BASE_FOG_COLOUR = 12638463;

	public static final Music NORMAL_MUSIC = null;

	public static <T extends Biome, P> BiomeBuilder<T, P> create(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullFunction<Biome.BiomeBuilder, T> factory) {
		return new BiomeBuilder(owner, parent, name, callback, factory, () -> new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).temperature(0.8F).downfall(0.4F).mobSpawnSettings(new MobSpawnSettings.Builder().build()).generationSettings(new BiomeGenerationSettings.Builder().build()).specialEffects(new BiomeSpecialEffects.Builder().waterColor(0x3F76E4).waterFogColor(0x50533).fogColor(0xC0D8FF).skyColor(calculateSkyColor(0.8F)).backgroundMusic(null).build())).defaultLang();
	}

	private final NonNullFunction<Biome.BiomeBuilder, T> factory;

	private NonNullSupplier<Biome.BiomeBuilder> initialProperties;
	private NonNullFunction<Biome.BiomeBuilder, Biome.BiomeBuilder> propertiesCallback = NonNullUnaryOperator.identity();

	public BiomeBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullFunction<Biome.BiomeBuilder, T> factory, NonNullSupplier<Biome.BiomeBuilder> initialProperties) {
		super(owner, parent, name, callback, Registry.BIOME_REGISTRY);
		this.factory = factory;
		this.initialProperties = initialProperties;
	}

	public static int calculateSkyColor(float temperature) {
		float $$1 = temperature / 3.0F;
		$$1 = Mth.clamp($$1, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
	}

	public static Biome.BiomeBuilder biome(Precipitation precipitation, float downfall, float temperature, BiomeSpecialEffects.Builder effects, BiomeGenerationSettings.Builder settings, MobSpawnSettings.Builder spawningInfo) {
		return biome(precipitation, downfall, temperature, NORMAL_MUSIC, effects, settings, spawningInfo);
	}

	public static Biome.BiomeBuilder biome(Precipitation precipitation, float downfall, float temperature, Music music, BiomeSpecialEffects.Builder effects, BiomeGenerationSettings.Builder settings, MobSpawnSettings.Builder spawningInfo) {
		effects.backgroundMusic(music);
		return new Biome.BiomeBuilder().precipitation(precipitation).temperature(temperature).generationSettings(settings.build()).specialEffects(effects.build()).mobSpawnSettings(spawningInfo.build());
	}

	public BiomeBuilder<T, P> properties(NonNullFunction<BiomeBuilder<T, P>, NonNullUnaryOperator<Biome.BiomeBuilder>> func) {
		this.propertiesCallback = this.propertiesCallback.andThen(func.apply(this));
		return this;
	}

	public BiomeBuilder<T, P> defaultLang() {
		return this.lang(RegistrateLangProvider.toEnglishName(this.getName()));
	}

	public BiomeBuilder<T, P> lang(String name) {
		return super.lang(biome -> Util.makeDescriptionId("biome", ForgeRegistries.BIOMES.getKey(biome)), name);
	}

	@Override
	protected T createEntry() {
		Biome.BiomeBuilder biomeBuilder = this.initialProperties.get();
		biomeBuilder = this.propertiesCallback.apply(biomeBuilder);
		return this.factory.apply(biomeBuilder);
	}

	@Override
	protected RegistryEntry<T> createEntryWrapper(RegistryObject<T> delegate) {
		return new BiomeEntry<>(this.getOwner(), delegate);
	}

	@Override
	public BiomeEntry<T> register() {
		return (BiomeEntry<T>) super.register();
	}
}
