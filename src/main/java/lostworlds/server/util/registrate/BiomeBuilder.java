package lostworlds.server.util.registrate;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.OneTimeEventReceiver;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;

public class BiomeBuilder<T extends Item, P> extends AbstractBuilder<Item, T, P, BiomeBuilder<T, P>> {

    /**
     * Create a new {@link BiomeBuilder} and configure data. Used in lieu of adding side-effects to constructor, so that alternate initialization strategies can be done in subclasses.
     * <p>
     * The item will be assigned the following data:
     * <ul>
     * <li>A simple generated model with one texture (via {@link #defaultModel()})</li>
     * <li>The default translation (via {@link #defaultLang()})</li>
     * </ul>
     * 
     * @param <T>
     *            The type of the builder
     * @param <P>
     *            Parent object type
     * @param owner
     *            The owning {@link AbstractRegistrate} object
     * @param parent
     *            The parent object
     * @param name
     *            Name of the entry being built
     * @param callback
     *            A callback used to actually register the built entry
     * @param factory
     *            Factory to create the item
     * @return A new {@link BiomeBuilder} with reasonable default data generators.
     */
    public static <T extends Item, P> BiomeBuilder<T, P> create(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullFunction<Item.Properties, T> factory) {
        return create(owner, parent, name, callback, factory, null);
    }
    
    /**
     * Create a new {@link BiomeBuilder} and configure data. Used in lieu of adding side-effects to constructor, so that alternate initialization strategies can be done in subclasses.
     * <p>
     * The item will be assigned the following data:
     * <ul>
     * <li>A simple generated model with one texture (via {@link #defaultModel()})</li>
     * <li>The default translation (via {@link #defaultLang()})</li>
     * <li>An {@link ItemGroup} set in the properties from the group supplier parameter, if non-null</li>
     * </ul>
     * 
     * @param <T>
     *            The type of the builder
     * @param <P>
     *            Parent object type
     * @param owner
     *            The owning {@link AbstractRegistrate} object
     * @param parent
     *            The parent object
     * @param name
     *            Name of the entry being built
     * @param callback
     *            A callback used to actually register the built entry
     * @param factory
     *            Factory to create the item
     * @param group
     *            The {@link ItemGroup} for the object, can be null for none
     * @return A new {@link BiomeBuilder} with reasonable default data generators.
     */
    public static <T extends Item, P> BiomeBuilder<T, P> create(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullFunction<Item.Properties, T> factory, @Nullable NonNullSupplier<? extends ItemGroup> group) {
        return new BiomeBuilder<>(owner, parent, name, callback, factory)
                .defaultModel().defaultLang()
                .transform(ib -> group == null ? ib : ib.group(group));
    }

    private final NonNullFunction<Item.Properties, T> factory;
    
    private NonNullSupplier<Item.Properties> initialProperties = Item.Properties::new;
    private NonNullFunction<Item.Properties, Item.Properties> propertiesCallback = NonNullUnaryOperator.identity();
    
    @Nullable
    private NonNullSupplier<Supplier<IItemColor>> colorHandler;
    
    protected BiomeBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullFunction<Item.Properties, T> factory) {
        super(owner, parent, name, callback, Item.class);
        this.factory = factory;
    }

    /**
     * Modify the properties of the item. Modifications are done lazily, but the passed function is composed with the current one, and as such this method can be called multiple times to perform
     * different operations.
     * <p>
     * If a different properties instance is returned, it will replace the existing one entirely.
     * 
     * @param func
     *            The action to perform on the properties
     * @return this {@link BiomeBuilder}
     */
    public BiomeBuilder<T, P> properties(NonNullUnaryOperator<Item.Properties> func) {
        propertiesCallback = propertiesCallback.andThen(func);
        return this;
    }

    /**
     * Replace the initial state of the item properties, without replacing or removing any modifications done via {@link #properties(NonNullUnaryOperator)}.
     * 
     * @param properties
     *            A supplier to to create the initial properties
     * @return this {@link BiomeBuilder}
     */
    public BiomeBuilder<T, P> initialProperties(NonNullSupplier<Item.Properties> properties) {
        initialProperties = properties;
        return this;
    }

    public BiomeBuilder<T, P> group(NonNullSupplier<? extends ItemGroup> group) {
        return properties(p -> p.group(group.get()));
    }
    
    /**
     * Register a block color handler for this item. The {@link IItemColor} instance can be shared across many items.
     * 
     * @param colorHandler
     *            The color handler to register for this item
     * @return this {@link BiomeBuilder}
     */
    public BiomeBuilder<T, P> color(NonNullSupplier<Supplier<IItemColor>> colorHandler) {
        if (this.colorHandler == null) {
            DistExecutor.runWhenOn(Dist.CLIENT, () -> this::registerItemColor);
        }
        this.colorHandler = colorHandler;
        return this;
    }
    
    protected void registerItemColor() {
        OneTimeEventReceiver.addModListener(ColorHandlerEvent.Item.class, e -> {
            NonNullSupplier<Supplier<IItemColor>> colorHandler = this.colorHandler;
            if (colorHandler != null) {
                e.getItemColors().register(colorHandler.get().get(), getEntry());
            }
        });
    }
    
    /**
     * Assign the default model to this item, which is simply a generated model with a single texture of the same name.
     * 
     * @return this {@link BiomeBuilder}
     */
    public BiomeBuilder<T, P> defaultModel() {
        return model((ctx, prov) -> prov.generated(ctx::getEntry));
    }

    /**
     * Configure the model for this item.
     * 
     * @param cons
     *            The callback which will be invoked during data creation
     * @return this {@link BiomeBuilder}
     * @see #setData(ProviderType, NonNullBiConsumer)
     */
    public BiomeBuilder<T, P> model(NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> cons) {
        return setData(ProviderType.ITEM_MODEL, cons);
    }
    
    /**
     * Assign the default translation, as specified by {@link RegistrateLangProvider#getAutomaticName(NonNullSupplier)}. This is the default, so it is generally not necessary to call, unless for undoing
     * previous changes.
     * 
     * @return this {@link BiomeBuilder}
     */
    public BiomeBuilder<T, P> defaultLang() {
        return lang(Biome::getTranslationKey);
    }
    
    /**
     * Set the translation for this item.
     * 
     * @param name
     *            A localized English name
     * @return this {@link BiomeBuilder}
     */
    public BiomeBuilder<T, P> lang(String name) {
        return lang(Biome::getTranslationKey, name);
    }
    
    @Override
    protected T createEntry() {
        Item.Properties properties = this.initialProperties.get();
        properties = propertiesCallback.apply(properties);
        return factory.apply(properties);
    }
    
    @Override
    protected RegistryEntry<T> createEntryWrapper(RegistryObject<T> delegate) {
        return new ItemEntry<>(getOwner(), delegate);
    }
    
    @Override
    public ItemEntry<T> register() {
        return (ItemEntry<T>) super.register();
    }
}
